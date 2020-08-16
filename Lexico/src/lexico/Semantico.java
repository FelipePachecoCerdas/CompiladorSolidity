/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lexico;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Stack;
import java_cup.runtime.Symbol;

/**
 *
 * @author usuario
 */
public class Semantico {

  public LinkedHashMap<String, SimboloTS> ts = new LinkedHashMap<>();
  public Stack<RegistroSemantico> pilaSem = new Stack<>();

  public ArrayList<String> ts_aux = new ArrayList<>();
  public final String TAB = "    ";
  public int factor = 1;

  public String asm_variables = ".DATA\n.UDATA";
  public String asm_inicio = ".CODE\n" + TAB + ".STARUP\n\ninicio:\n";
  public final String asm_end = "\nfin:\n" + TAB + ".EXIT";

  public String asm_funcion = "";
  public String asm_cuerpo = "";
  public String nombre_funcion = "";
  
  
  public String tipo_funcion = null; //Dice si hay returns al inicio de la funcion
  public boolean hayReturn = false; //Dice si hay por lo menos un return en la funcion
  public boolean expresion_variable = false;

  private static Semantico instancia = null;

  public boolean variables_terminadas = false;

  public ArrayList<Symbol> errores = new ArrayList<>();
  public ArrayList<String> erroresStr = new ArrayList<>();

  private Semantico() {
  }

  public static Semantico self() {
    if (instancia == null) {
      instancia = new Semantico();
    }

    return instancia;
  }

  int ultimoIndiceTS = 0;

  public void variables_tipo(Object tipo) {
    System.out.println((String) tipo);
    for (int i = ultimoIndiceTS; i < this.ts.size(); i++) {
      this.ts.get(this.ts_aux.get(i)).tipoDato = (String) tipo;
      if (tipo.equals("int")) {
        if (this.ts.get(this.ts_aux.get(i)).valor == null) {
          this.ts.get(this.ts_aux.get(i)).valor = 0;
        }

        if (this.ts.get(this.ts_aux.get(i)).alcance.equals("global")) {
          this.asm_variables += "\n" + this.ts_aux.get(i) + " resb 4";
        }
      }
    }
    this.ultimoIndiceTS = this.ts.size();
  }

  public void variables_nombre(Object nombre , Object symb) {
    Symbol simp = (Symbol) symb;
    if(this.ts.get(nombre.toString()) != null){
        System.out.println(nombre.toString() + " ya existe ");
        System.out.println("Doble existencia en L:" + Integer.toString(simp.left + this.factor) + " R:" + Integer.toString(simp.right + this.factor));
        Symbol sp2 = new Symbol(simp.sym, simp.left + this.factor, simp.right + this.factor, nombre.toString());
        errores.add(sp2);
        erroresStr.add("Variable ya definida anteriormente");
    }
    else{
        System.out.println(nombre.toString() + " no existe ");
        SimboloTS info = new SimboloTS(null, null, "global", "variable");
        this.ts.put((String) nombre, info);
        this.ts_aux.add((String) nombre);
    }  
      
  public void variables_nombre(Object nombre) {
    if (this.variables_terminadas) {
      System.out.println("Variable local: " + nombre.toString() + " ");
      SimboloTS info = new SimboloTS(null, null, "local", "variable");
      this.ts.put((String) nombre, info);
      this.ts_aux.add((String) nombre);
      return;
    }
    System.out.println("Variable contrato: " + nombre.toString() + " ");
    SimboloTS info = new SimboloTS(null, null, "global", "variable");
    this.ts.put((String) nombre, info);
    this.ts_aux.add((String) nombre);

    /*for (String i : tablaSimbolos.keySet()) {
      System.out.println(i + " " + tablaSimbolos.get(i)[0] + " " + tablaSimbolos.get(i)[1]);
    }*/
  }

  public void funcion_nombre(Object nombre ,Object symb) {
    this.nombre_funcion = (String) nombre;

    if (this.tipo_funcion == null) { // No hay returns por lo tanto no debe de haber return
      this.tipo_funcion = "void";
      if(hayReturn){ //Si es true es que hay return
          System.out.println("Hay return y no deberia");
      }  
    }
    else{ //Hay returns y por lo tanto ocupa un return
      if(!hayReturn){ //Si es false es que no hay return, por eso se pone el not para que sea true y se meta al if
          System.out.println("No hay return");
          Symbol simp = (Symbol) symb;
          System.out.println("No return en L:" + Integer.toString(simp.left) + " R:" + Integer.toString(simp.right));
          Symbol sp2 = new Symbol(simp.sym, simp.left -1, simp.right -1 , nombre.toString());
          errores.add(sp2);
          erroresStr.add("Fin de función y no se encontró return");
      }  
    }

    SimboloTS info = new SimboloTS(this.tipo_funcion, null, "global", "función");
    this.ts.put(this.nombre_funcion, info);
    this.ts_aux.add(this.nombre_funcion);

    this.asm_funcion += this.nombre_funcion + ":\n" + TAB + "enter 0,0\n" + TAB + "sub EDX, EDX\n";
    // meter el cuerpo 

    this.asm_funcion += this.asm_cuerpo + "\n";

    this.asm_funcion += "\n" + TAB + "leave\n" + TAB + "ret\n";

    this.tipo_funcion = null;
    hayReturn = false;
  }

  public String getAsm() {
    this.asm_inicio = "\n\n" + this.asm_inicio;
    this.asm_inicio += TAB + "call " + this.nombre_funcion + "\n";

    return this.asm_variables + this.asm_inicio + this.asm_end + "\n\n" + this.asm_funcion;
  }

  public void print(Object x) {
    System.out.println(x.toString());
  }

  public void variables_expresion() {
    this.expresion_variable = true;
  }

  public void funcion_tipo(Object tipo) {
    this.factor = 0;
    this.tipo_funcion = (String) tipo;
  }

  public void expresion_guardarNum(Object numero) {
    RegistroSemantico_DataObject rs_do = new RegistroSemantico_DataObject("constante", "int", Integer.parseInt((String) numero));
    pilaSem.push(rs_do);
  }

  public void expresion_guardarId(Object identificador_) {

    String identificador = (String) identificador_;
    // Validar que this.ts.get(identificador) exista !!!!!!!!!!!  1 + a
    if (!this.ts.containsKey(identificador)) {
      RegistroSemantico_DataObject rs_do = new RegistroSemantico_DataObject("direccion", "MAL", identificador);
      pilaSem.push(rs_do);
      return;
    }
    RegistroSemantico_DataObject rs_do = new RegistroSemantico_DataObject("direccion", this.ts.get(identificador).tipoDato, identificador);
    pilaSem.push(rs_do);
  }

  public void expresion_guardarOpArit(Object operador) {
    RegistroSemantico_Operador rs_op = new RegistroSemantico_Operador("aritmetico", (String) operador);
    pilaSem.push(rs_op);
  }

  public void expresion_guardarOpLog(Object operador) {
    RegistroSemantico_Operador rs_op = new RegistroSemantico_Operador("logico", (String) operador);
    pilaSem.push(rs_op);
  }

  public void expresion_evalBinary() {

    RegistroSemantico_DataObject rs_do2 = (RegistroSemantico_DataObject) this.pilaSem.pop();
    RegistroSemantico_Operador rs_op = (RegistroSemantico_Operador) this.pilaSem.pop();
    RegistroSemantico_DataObject rs_do1 = (RegistroSemantico_DataObject) this.pilaSem.pop();

    RegistroSemantico_DataObject rs_do_res = new RegistroSemantico_DataObject(null, null, null);

    String exp_str = rs_do1.valor.toString() + rs_op.operador + rs_do2.valor.toString();
    System.out.println("EVALUANDO: " + exp_str);

    if (rs_do1.tipoDO.equals("ERROR") || rs_do2.tipoDO.equals("ERROR")) {
      rs_do_res.tipoDO = "ERROR";
      rs_do_res.valor = "ERROR";
      this.pilaSem.push(rs_do_res);
      return;
    }

    if (rs_do1.tipoDO.equals("direccion")) {

      if (!this.ts.containsKey((String) rs_do1.valor)) {
        String error = "No se puede ejecutar la expresión " + exp_str + " porque el identificador " + (String) rs_do1.valor + " no fue definido";
        System.out.println(error);
        rs_do_res.tipoDO = "ERROR";
        rs_do_res.valor = "ERROR";
        this.pilaSem.push(rs_do_res);
        return;
      }

      if (this.ts.get((String) rs_do1.valor).tipoSimbolo.equals("parámetro")) {
        String error = "No se puede ejecutar la expresión " + exp_str + " porque el parámetro " + (String) rs_do1.valor + " es de contexto local";
        System.out.println(error);
        rs_do_res.tipoDO = "ERROR";
        rs_do_res.valor = "ERROR";
        this.pilaSem.push(rs_do_res);
        return;
      }

      if (this.ts.get((String) rs_do1.valor).alcance.equals("local")) {
        String error = "No se puede ejecutar la expresión " + exp_str + " porque la variable " + (String) rs_do1.valor + " es de contexto local";
        System.out.println(error);
        rs_do_res.tipoDO = "ERROR";
        rs_do_res.valor = "ERROR";
        this.pilaSem.push(rs_do_res);
        return;
      }
    }

    if (rs_do2.tipoDO.equals("direccion")) {

      if (!this.ts.containsKey((String) rs_do2.valor)) {
        String error = "No se puede ejecutar la expresión " + exp_str + " porque el identificador " + (String) rs_do2.valor + " no fue definido";
        System.out.println(error);
        rs_do_res.tipoDO = "ERROR";
        rs_do_res.valor = "ERROR";
        this.pilaSem.push(rs_do_res);
        return;
      }

      if (this.ts.get((String) rs_do2.valor).tipoSimbolo.equals("parámetro")) {
        String error = "No se puede ejecutar la expresión " + exp_str + " porque el parámetro " + (String) rs_do2.valor + " es de contexto local";
        System.out.println(error);
        rs_do_res.tipoDO = "ERROR";
        rs_do_res.valor = "ERROR";
        this.pilaSem.push(rs_do_res);
        return;
      }

      if (this.ts.get((String) rs_do2.valor).alcance.equals("local")) {
        String error = "No se puede ejecutar la expresión " + exp_str + " porque la variable " + (String) rs_do2.valor + " es de contexto local";
        System.out.println(error);
        rs_do_res.tipoDO = "ERROR";
        rs_do_res.valor = "ERROR";
        this.pilaSem.push(rs_do_res);
        return;
      }
    }

    if (rs_op.tipoOperador.equals("aritmetico") && (!rs_do1.tipoDato.equals("int") || !rs_do2.tipoDato.equals("int"))) {
      String error = "No se puede ejecutar la expresión " + exp_str + " porque el operador " + rs_op.operador + " solo puede operar enteros y "
        + ((rs_do1.tipoDato.equals("int")) ? (String) rs_do2.valor : (String) rs_do1.valor) + " no lo es";
      System.out.println(error);
      rs_do_res.tipoDO = "ERROR";
      rs_do_res.valor = "ERROR";
      this.pilaSem.push(rs_do_res);
      return;
    }

    // CONSTANT FOLDING
    if (rs_do1.tipoDO.equals(
      "constante") && rs_do2.tipoDO.equals("constante")) {
      if (rs_do1.tipoDato.equals("int") && rs_do2.tipoDato.equals("int")) {

        rs_do_res.tipoDato = "int";
        rs_do_res.tipoDO = "constante";

        if (rs_op.tipoOperador.equals("aritmetico")) {

          if (rs_op.operador.equals("+")) {
            rs_do_res.valor = (int) rs_do1.valor + (int) rs_do2.valor;
          }
          if (rs_op.operador.equals("-")) {
            rs_do_res.valor = (int) rs_do1.valor - (int) rs_do2.valor;
          }

        }

      }
    } else if (rs_do1.tipoDO.equals(
      "registro")) {

      rs_do_res.tipoDato = "int";
      rs_do_res.tipoDO = "registro";
      rs_do_res.valor = "EDX";

      String rs_do2_asm = (rs_do2.tipoDO.equals("constante"))
        ? "dword " + rs_do2.valor.toString() : "[" + rs_do2.valor + "]";

      String op_asm = "";
      if (rs_op.operador.equals("+")) {
        op_asm = "add";
      }
      if (rs_op.operador.equals("-")) {
        op_asm = "sub";
      }

      String exp_asm = "\n; " + exp_str + "\n" + op_asm + " EDX, " + rs_do2_asm + "\n";

      guardarEnCuerpoActual(exp_asm);

      //System.out.println(exp_asm);
    } else {
      // a + 5  // mov EDX, [a] // add EDX, dword 5  // 

      rs_do_res.tipoDato = "int";
      rs_do_res.tipoDO = "registro";
      rs_do_res.valor = "EDX";

      String rs_do1_asm = (rs_do1.tipoDO.equals("constante"))
        ? "dword " + rs_do1.valor.toString() : "[" + rs_do1.valor + "]";

      String op_asm = "";
      if (rs_op.operador.equals("+")) {
        op_asm = "add";
      }
      if (rs_op.operador.equals("-")) {
        op_asm = "sub";
      }

      String rs_do2_asm = (rs_do2.tipoDO.equals("constante"))
        ? "dword " + rs_do2.valor.toString() : "[" + rs_do2.valor + "]";

      String exp_asm = "\n; " + exp_str + "\n" + "mov EDX, " + rs_do1_asm + "\n";
      exp_asm += op_asm + " EDX, " + rs_do2_asm + "\n";

      guardarEnCuerpoActual(exp_asm);
      //System.out.println(exp_asm);
    }
    //System.out.println(rs_do_res.toString());

    this.pilaSem.push(rs_do_res);

  }

  public void printPila() {
    System.out.println(" -----  PILA: -------");
    for (RegistroSemantico rs : this.pilaSem) {
      System.out.println(rs.toString());
    }
  }

  public void expresion_eval(Object symb) {

    //System.out.println("TENGO QUE EVALUAR WEAS L:" + Integer.toString(((Symbol) symb).left + this.factor) + " R:" + Integer.toString(((Symbol) symb).right + this.factor));
  }
  
  public void evalBreakFuncion(Object b ,Object symb){
      Symbol simp = (Symbol) symb;
        System.out.println("Break en funcion");
        System.out.println("Break en L:" + Integer.toString(simp.left) + " R:" + Integer.toString(simp.right));
        Symbol sp2 = new Symbol(simp.sym, simp.left , simp.right -1 , b.toString());
        errores.add(sp2);
        erroresStr.add("Break en bloque no permitido");
      
  }
  
  public void evalBreakSentencia(Object b ,Object symb){
      Symbol simp = (Symbol) symb;
      System.out.println("Break en sentencia");/*
      if (pilaSem.search("while") == -1){ //EN PROCESO :V no encuentra un while para el break
        System.out.println("Break en bloque no permitido");
        System.out.println("Doble existencia en L:" + Integer.toString(simp.left + this.factor) + " R:" + Integer.toString(simp.right + this.factor));
        Symbol sp2 = new Symbol(simp.sym, simp.left , simp.right , b.toString());
        errores.add(sp2);
        erroresStr.add("Break en bloque no permitido");
      }*/
      
  }
  
  public void returnEncontrado(Object symb){
      hayReturn = true;
      if (this.tipo_funcion == null) { // No hay returns por lo tanto no debe de haber return
        Symbol simp = (Symbol) symb;
        System.out.println("Return donde no debe");
        System.out.println("Return en L:" + Integer.toString(simp.left) + " R:" + Integer.toString(simp.right));
        Symbol sp2 = new Symbol(simp.sym, simp.left +1, simp.right +1 , "return");
        errores.add(sp2);
        erroresStr.add("Return en funcion void");
    }
  }

  public void return_guardar() {
    RegistroSemantico_Return rs_ret = new RegistroSemantico_Return(this.tipo_funcion, "return");
    this.pilaSem.push(rs_ret);
  }

  public void variables_asignarExpresion() { // a 
    RegistroSemantico_DataObject rs_do_exp_res = (RegistroSemantico_DataObject) this.pilaSem.pop();
    String nombreVar = this.ts_aux.get(this.ts_aux.size() - 1);
    String exp_str = nombreVar + "=" + rs_do_exp_res.valor.toString();
    //System.out.println("EVALUANDO: " + exp_str);
    String rs_do_asm;

    if (rs_do_exp_res.tipoDO.equals("ERROR")) {
      return;
    }

    if (rs_do_exp_res.tipoDO.equals("direccion")) {
      System.out.println(this.ts.get((String) rs_do_exp_res.valor).tipoDato);

      if (!this.ts.containsKey((String) rs_do_exp_res.valor)) {
        String error = "No se puede ejecutar la expresión " + exp_str + " porque el identificador " + (String) rs_do_exp_res.valor + " no fue definido";
        System.out.println(error);
        return;
      }

      if (this.ts.get((String) rs_do_exp_res.valor).tipoSimbolo.equals("parámetro")) {
        String error = "No se puede ejecutar la expresión " + exp_str + " porque el parámetro " + (String) rs_do_exp_res.valor + " es de contexto local";
        System.out.println(error);
        return;
      }

      if (this.ts.get((String) rs_do_exp_res.valor).alcance.equals("local")) {
        String error = "No se puede ejecutar la expresión " + exp_str + " porque la variable " + (String) rs_do_exp_res.valor + " es de contexto local";
        System.out.println(error);
        return;
      }
    }

    if (rs_do_exp_res.tipoDato != null && !rs_do_exp_res.tipoDato.equals("int")) {
      String error = "No se puede ejecutar la expresión " + exp_str + " porque el operador " + "=" + " solo puede operar enteros y "
        + ((String) rs_do_exp_res.valor) + " no lo es";
      System.out.println(error);
      return;
    }

    switch (rs_do_exp_res.tipoDO) {
      case "constante":
        rs_do_asm = "dword " + rs_do_exp_res.valor.toString();
        this.ts.get(nombreVar).valor = rs_do_exp_res.valor;
        break;
      case "direccion":
        rs_do_asm = "[" + rs_do_exp_res.valor + "]";
        break;
      default:
        rs_do_asm = "" + rs_do_exp_res.valor;
        break;
    }

    String exp_asm = "\n; " + exp_str + ((rs_do_exp_res.tipoDO.equals("registro")) ? "" : ("\n" + "mov EDX, " + rs_do_asm)) + "\n";
    exp_asm += "mov [" + nombreVar + "], EDX" + "\n";

    guardarEnCuerpoActual(exp_asm);
    //System.out.println(exp_asm);
  }

  public void expresion_consumirExpresion() {
    RegistroSemantico_DataObject rs_do_exp_res = (RegistroSemantico_DataObject) this.pilaSem.pop();

    if (rs_do_exp_res.tipoDO.equals("ERROR")) {
      return;
    }

    if (this.pilaSem.empty()) {
      return;
    }

    if (this.pilaSem.peek() instanceof RegistroSemantico_Operador) {  // a = EDX
      RegistroSemantico_Operador rs_op = (RegistroSemantico_Operador) this.pilaSem.pop();
      RegistroSemantico_DataObject rs_do_idAsig = (RegistroSemantico_DataObject) this.pilaSem.pop();

      // VALIDAR QUE EL RESULTADO Y LA ID SEAN DEL MISMO TIPO (?
      String exp_str = rs_do_idAsig.valor + "=" + rs_do_exp_res.valor.toString();
      System.out.println("EVALUANDO: " + exp_str);
      String rs_do_asm;

      switch (rs_do_exp_res.tipoDO) {
        case "constante":
          rs_do_asm = "dword " + rs_do_exp_res.valor.toString();
          break;
        case "direccion":
          rs_do_asm = "[" + rs_do_exp_res.valor + "]";
          break;
        default:
          rs_do_asm = "" + rs_do_exp_res.valor;
          break;
      }

      String exp_asm = "";
      if (rs_op.operador.equals("=")) {

        exp_asm += "\n; " + exp_str + ((rs_do_exp_res.tipoDO.equals("registro")) ? "" : ("\n" + "mov EDX, " + rs_do_asm)) + "\n";
        exp_asm += "mov [" + rs_do_idAsig.valor + "], EDX" + "\n";

        guardarEnCuerpoActual(exp_asm);
        //System.out.println(exp_asm);
      } // se puedem hacer otros como el +=, ++ y esos
    } else if (this.pilaSem.peek() instanceof RegistroSemantico_Return) {

      String exp_str = "return " + rs_do_exp_res.valor.toString();
      System.out.println("EVALUANDO: " + exp_str);
      String rs_do_asm;

      switch (rs_do_exp_res.tipoDO) {
        case "constante":
          rs_do_asm = "dword " + rs_do_exp_res.valor.toString();
          break;
        case "direccion":
          rs_do_asm = "[" + rs_do_exp_res.valor + "]";
          break;
        default:
          rs_do_asm = "" + rs_do_exp_res.valor;
          break;
      }

      String exp_asm = "\n; " + exp_str + ("\n" + "mov EAX, " + rs_do_asm) + "\n" + "jmp FINAL \n";

      //System.out.println(exp_asm);
      guardarEnCuerpoActual(exp_asm);
    }
  }

  public void guardarEnCuerpoActual(String asm_code) {
    if (this.variables_terminadas) {
      this.asm_cuerpo += asm_code;
    } else {
      this.asm_inicio += asm_code;
    }
  }

  public void variablesEnded() {
    this.factor = 0;
    this.variables_terminadas = true;
  }

  public void parametros_guardarParam(Object tipo, Object identificador) {
    // VALIDAR QUE NO EXISTA
    System.out.println("Parametro de tipo " + tipo + " :  " + identificador);
    SimboloTS info = new SimboloTS((String) tipo, null, "local", "parámetro");
    this.ts.put((String) identificador, info);
    this.ts_aux.add((String) identificador);

    this.ultimoIndiceTS = this.ts.size();
  }

  public void funcion_ended() {
    for (String id : this.ts.keySet()) {
      if (this.ts.get(id).alcance.equals("local")) {
        this.ts.get(id).alcance = "local (" + this.nombre_funcion + ")";
      }
    }

  }

  public void theEndOfItAll() {
    // DIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIE
  }
}
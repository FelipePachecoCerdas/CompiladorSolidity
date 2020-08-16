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
  public String nombre_funcion = "";
  
  
  public String tipo_funcion = null; //Dice si hay returns al inicio de la funcion
  public boolean hayReturn = false; //Dice si hay por lo menos un return en la funcion
  public boolean expresion_variable = false;

  private static Semantico instancia = null;

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
        this.ts.get(this.ts_aux.get(i)).valor = 0;

        this.asm_variables += "\n" + this.ts_aux.get(i) + " resb 4";
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

    this.asm_funcion += this.nombre_funcion + ":\n" + TAB + "enter 0,0\n" + TAB + "sub EBX, EBX\n";
    // meter el cuerpo 
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

    RegistroSemantico_DataObject rs_do = new RegistroSemantico_DataObject("direccion", this.ts.get(identificador).tipoDato, identificador);
    pilaSem.push(rs_do);
  }

  public void expresion_guardarOpArit(Object operador) {
    RegistroSemantico_Operador rs_op = new RegistroSemantico_Operador("aritmetico", (String) operador);
    pilaSem.push(rs_op);
  }

  public void expresion_evalBinary() {
    RegistroSemantico_DataObject rs_do2 = (RegistroSemantico_DataObject) this.pilaSem.pop();
    RegistroSemantico_Operador rs_op = (RegistroSemantico_Operador) this.pilaSem.pop();
    RegistroSemantico_DataObject rs_do1 = (RegistroSemantico_DataObject) this.pilaSem.pop();

    System.out.println("EVALUANDO " + rs_do1.valor.toString() + rs_op.operador + rs_do2.valor.toString());

    rs_do1.valor = "dummy";
    this.pilaSem.push(rs_do1);
  }

  public void printPila() {
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

}

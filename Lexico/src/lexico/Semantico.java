/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lexico;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Stack;

/**
 *
 * @author usuario
 */
public class Semantico {

  public LinkedHashMap<String, SimboloTS> ts = new LinkedHashMap<>();
  public Stack<RegistroSemantico> pilaSem = new Stack<>();

  public ArrayList<String> ts_aux = new ArrayList<>();
  public final String TAB = "    ";

  public String asm_variables = ".DATA\n.UDATA";
  public String asm_inicio = ".CODE\n" + TAB + ".STARUP\n\ninicio:\n";
  public final String asm_end = "\nfin:\n" + TAB + ".EXIT";

  public String asm_funcion = "";
  public String nombre_funcion = "";
  public String tipo_funcion = null;
  public boolean expresion_variable = false;

  private static Semantico instancia = null;

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

  public void variables_nombre(Object nombre) {
    System.out.println(nombre.toString() + " ");
    SimboloTS info = new SimboloTS(null, null, "global", "variable");
    this.ts.put((String) nombre, info);
    this.ts_aux.add((String) nombre);

    /*for (String i : tablaSimbolos.keySet()) {
      System.out.println(i + " " + tablaSimbolos.get(i)[0] + " " + tablaSimbolos.get(i)[1]);
    }*/
  }

  public void funcion_nombre(Object nombre) {
    this.nombre_funcion = (String) nombre;

    if (this.tipo_funcion == null) {
      this.tipo_funcion = "void";
    }

    SimboloTS info = new SimboloTS(this.tipo_funcion, null, "global", "función");
    this.ts.put(this.nombre_funcion, info);
    this.ts_aux.add(this.nombre_funcion);

    this.asm_funcion += this.nombre_funcion + ":\n" + TAB + "enter 0,0\n" + TAB + "sub EBX, EBX\n";
    // meter el cuerpo 
    this.asm_funcion += "\n" + TAB + "leave\n" + TAB + "ret\n";

    this.tipo_funcion = null;
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
    this.tipo_funcion = (String) tipo;
  }

  public void expresion_guardarNum(Object numero) {
    RegistroSemantico_DataObject rs_do = new RegistroSemantico_DataObject("constante", "int", Integer.parseInt((String) numero));
    pilaSem.push(rs_do);
  }

  public void expresion_guardarId(Object identificador_) {
    String identificador = (String) identificador_;
    // Validar que this.ts.get(identificador) exista !!!!!!!!!!!
    RegistroSemantico_DataObject rs_do = new RegistroSemantico_DataObject("direccion", this.ts.get(identificador).tipoDato, identificador);
    pilaSem.push(rs_do);
  }

  public void expresion_guardarOpArit(Object operador) {
    RegistroSemantico_Operador rs_op = new RegistroSemantico_Operador("aritmetico", (String) operador);
    pilaSem.push(rs_op);
  }

  public void expresion_evalBinary() {
    RegistroSemantico_DataObject rs_do1 = (RegistroSemantico_DataObject) this.pilaSem.pop();
    RegistroSemantico_Operador rs_op = (RegistroSemantico_Operador) this.pilaSem.pop();
    RegistroSemantico_DataObject rs_do2 = (RegistroSemantico_DataObject) this.pilaSem.pop();

    System.out.println("EVALUANDO " + rs_do1.valor.toString() + rs_op.operador + rs_do2.valor.toString());

    rs_do1.valor = "dummy";
    this.pilaSem.push(rs_do1);
  }

  public void printPila() {
    for (RegistroSemantico rs : this.pilaSem) {
      System.out.println(rs.toString());
    }
  }

}

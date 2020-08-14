/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lexico;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 *
 * @author usuario
 */
public class Semantico {

  public LinkedHashMap<String, SimboloTS> ts = new LinkedHashMap<>();
  public ArrayList<String> ts_aux = new ArrayList<>();

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
      }
    }
    this.ultimoIndiceTS = this.ts.size();
  }

  public void variables_nombre(Object nombre) {
    System.out.print(nombre.toString() + " ");
    SimboloTS info = new SimboloTS(null, null, "global");
    this.ts.put((String) nombre, info);
    this.ts_aux.add((String) nombre);

    /*for (String i : tablaSimbolos.keySet()) {
      System.out.println(i + " " + tablaSimbolos.get(i)[0] + " " + tablaSimbolos.get(i)[1]);
    }*/
  }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lexico;

import java.util.HashMap;

/**
 *
 * @author usuario
 */
public class Ayudador {
    
    public HashMap<String, String[]> tablaSimbolos = new HashMap<String, String[]>();
    
    private static Ayudador single_instance = null;

  private Ayudador() {
  }

  public static Ayudador getInstance() {
    if (single_instance == null) {
      single_instance = new Ayudador();
    }

    return single_instance;
  }
  
    private String auxTipo = "algo";
    
    public void ponerTipo(Object tipo){
        auxTipo = tipo.toString();
    }
    public void intentar(Object nombre,  String ambito ){
        String[] info = {auxTipo, ambito};
        tablaSimbolos.put(nombre.toString(), info);
        
        for (String i : tablaSimbolos.keySet()) {
            System.out.println(i + " " + tablaSimbolos.get(i)[0] + " " + tablaSimbolos.get(i)[1]);
        }
       
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lexico;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author usuario
 */
class SingletoneEscaner {
    private static SingletoneEscaner single_instance = null;
    
    
    private SingletoneEscaner() 
    { 
    } 
    
    public static SingletoneEscaner getInstance() 
    { 
        if (single_instance == null) 
            single_instance = new SingletoneEscaner(); 
  
        return single_instance; 
    } 
    
    
    public String usarJflex(Lexer lexer) throws IOException{
        String Resultados = "";
        Map<String, Map<String,Integer>> palabras = new HashMap<>();
        
        while (true) {
            Token token = lexer.yylex();
            if (token == null) {
                break;
                //Resultados = Resultados + "FIN";
                //return Resultados;
            }//termina evaluacion
            switch (token) {
                case IDENTIFICADOR: case OPERADOR: case PALABRA_RESERVADA: case TRANSAC: case UNIDAD: case LITERAL:
                    System.out.println(lexer.lexeme);
                    String stringToken=token+" "+lexer.lexeme.split(" ")[0];
                    String numeroLinea =lexer.lexeme.split(" ")[1];
                    if (palabras.get(stringToken) == null){
                        Map<String,Integer> lineas = new HashMap<>();                        
                        lineas.put(numeroLinea,1);
                        palabras.put(stringToken, lineas);
                    }else{
                        palabras.get(stringToken).put(numeroLinea,palabras.get(stringToken).getOrDefault(numeroLinea,0)+1);
                    }  
                    System.out.println(palabras); 
                    System.out.println(token+" "+lexer.lexeme);
                    //Resultados = Resultados + "Token:" + token + " " + lexer.lexeme + "\n";
                break;
                case ERROR:
                    stringToken = token+" "+lexer.lexeme.split(" ")[0];
                    numeroLinea = lexer.lexeme.split(" ")[1];
                    if (palabras.get(stringToken) == null){
                        Map<String,Integer> lineas = new HashMap<>();                        
                        lineas.put(numeroLinea,1);
                        palabras.put(stringToken, lineas);
                    }else{
                        palabras.get(stringToken).put(numeroLinea,palabras.get(stringToken).getOrDefault(numeroLinea,0)+1);
                    }  
                    System.out.println(palabras); 
                    System.out.println(token+" "+lexer.lexeme);
                    //Resultados = Resultados + "Token:" + token + " " + lexer.lexeme + "\n";                    
            }
        }
        for (String palabra : palabras.keySet()){
           Resultados = Resultados + palabra; 
           for (String linea : palabras.get(palabra).keySet()){
              Resultados = Resultados + " " +linea + "("+palabras.get(palabra).get(linea) +"),"; 
           }
           Resultados=Resultados.substring(0, Resultados.length() - 1)+ "\n";
        }
        Resultados = Resultados +"FIN";
        return Resultados;
    }
}

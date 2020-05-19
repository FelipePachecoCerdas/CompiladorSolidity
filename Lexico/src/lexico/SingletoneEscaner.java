/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lexico;

import java.io.IOException;

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
        while (true) {
            Token token = lexer.yylex();
            if (token == null) {
                Resultados = Resultados + "FIN";
                return Resultados;
            }//termina evaluacion
            switch (token) {
                case IDENTIFICADOR: case OPERADOR: case PALABRA_RESERVADA: case TRANSAC: case UNIDAD: case LITERAL:
                    System.out.println("TOKEN: "+token+" "+lexer.lexeme);
                    Resultados = Resultados + "Token:" + token + " " + lexer.lexeme + "\n";
                break;
                case ERROR:
                    System.out.println("TOKEN: "+token+" "+lexer.lexeme);
                    Resultados = Resultados + "Token:" + token + " " + lexer.lexeme + "\n";                    
            }
        }
    }
}

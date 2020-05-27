/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lexico;

import java.io.File;

/**
 *
 * @author usuario
 */
public class Lexico {
    private static boolean Parte1Ready = false;

    
    public static void main(String[] args) {
        
        if (Parte1Ready == true) {
        // TODO code application logic here
        String path = "C:/Users/user/Desktop/TEC/Repositorios/CompiladorSolidity/Lexico/src/lexico/lexer.flex";
        generarLexer (path);
        Parte1Ready = true;
        }
        else{
            Interfaz interfaz = new Interfaz();
            interfaz.setVisible(true);
        }
    }
    
    public static void generarLexer (String path){
        File file = new File(path);
        jflex.Main.generate(file);
        
        
    }
    
}

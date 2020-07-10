/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lexico;

import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author usuario
 */
public class Lexico {

  private static boolean Parte1Ready = false;

  public static void main(String[] args) {

    if (Parte1Ready == false) {
      // TODO code application logic here
      String path = "src/lexico/lexer.flex";
      String pathCup = "src/lexico/lexerCup.flex";
      
      String[] rutaS = {"-parser", "Sintax", "src/lexico/Sintax.cup"  };
      try{
      generar(path, pathCup, rutaS);    
      }
      catch (Exception ex){
          
      }
      
      Parte1Ready = true;
      try {
        for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
          if ("Nimbus".equals(info.getName())) {
            javax.swing.UIManager.setLookAndFeel(info.getClassName());
            break;
          }
        }
      } catch (ClassNotFoundException ex) {
        java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
      } catch (InstantiationException ex) {
        java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
      } catch (IllegalAccessException ex) {
        java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
      } catch (javax.swing.UnsupportedLookAndFeelException ex) {
        java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
      }
      javax.swing.UIManager.getDefaults().put("ScrollBar.minimumThumbSize", new Dimension(29, 29));

      Interfaz interfaz = new Interfaz();
      interfaz.setVisible(true);
    }
  }

  public static void generar(String path, String pathCup, String[] rutaS) throws IOException, Exception {
    File file;
    file = new File(path);
    jflex.Main.generate(file);
    
    
    
    file = new File(pathCup);
    jflex.Main.generate(file);
    java_cup.Main.main(rutaS);
    
    Path rutaSym = Paths.get("src/lexico/sym.java");
    if (Files.exists(rutaSym)) {
            Files.delete(rutaSym);
    }
    Files.move(Paths.get("sym.java"), 
               Paths.get("src/lexico/sym.java")    
    );
    
    
    Path rutaSin = Paths.get("src/lexico/Sintax.java");
    if (Files.exists(rutaSin)) {
        Files.delete(rutaSin);
    } 
    Files.move(Paths.get("Sintax.java"), 
               Paths.get("src/lexico/Sintax.java")    
    );

  }

}

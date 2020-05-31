/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lexico;

import java.awt.Dimension;
import java.io.File;

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
      generarLexer(path);
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

  public static void generarLexer(String path) {
    File file = new File(path);
    jflex.Main.generate(file);

  }

}

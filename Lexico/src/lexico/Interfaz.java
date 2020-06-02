/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lexico;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author usuario
 */
public class Interfaz extends javax.swing.JFrame {

  JTable table;
  DefaultTableModel modelo, modelo2;
  JTable tabla, tabla2;

  /**
   * Creates new form Interfaz
   */
  public Interfaz() {
    javax.swing.UIManager.getDefaults().put("ScrollBar.minimumThumbSize", new Dimension(29, 29));

    Object[][] rows = {};
    Object[] cols = {"Token", "Tipo", "Apariciones"};
    modelo = new DefaultTableModel(rows, cols);

    tabla = new JTable(modelo);
    tabla.setBounds(0, 0, 500, 300);
    tabla.setRowHeight(25);
    tabla.setLocation(0, 0);
    tabla.setFont(new Font("Dialog", Font.PLAIN, 13));

    JScrollPane scroll = new JScrollPane(tabla, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    scroll.setBounds(0, 0, 500, 300);
    scroll.setLocation(50, 100);
    this.add(scroll);

    Object[][] rows2 = {};
    Object[] cols2 = {"Token", "Error", "Aparición"};
    modelo2 = new DefaultTableModel(rows2, cols2);

    tabla2 = new JTable(modelo2);
    tabla2.setBounds(0, 0, 500, 300);
    tabla2.setRowHeight(25);
    tabla2.setLocation(0, 0);
    tabla2.setFont(new Font("Dialog", Font.PLAIN, 13));

    JScrollPane scroll2 = new JScrollPane(tabla2, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    scroll2.setBounds(0, 0, 500, 300);
    scroll2.setLocation(600, 100);
    scroll2.updateUI();
    this.add(scroll2);

    initComponents();
    String s = "393e46";

    tabla.getTableHeader().setForeground(Color.decode("#9a0f98"));
    tabla2.getTableHeader().setForeground(Color.decode("#a0c334"));

    this.setLocationRelativeTo(null);
    this.getContentPane().setBackground(Color.decode("#121212"));

  }

  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    ButtonAnalize = new javax.swing.JButton();
    jLabel2 = new javax.swing.JLabel();
    jLabel3 = new javax.swing.JLabel();
    jLabel4 = new javax.swing.JLabel();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

    ButtonAnalize.setBackground(Color.decode("#4ecca3"));
    ButtonAnalize.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
    ButtonAnalize.setText("Analizar");
    ButtonAnalize.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        ButtonAnalizeActionPerformed(evt);
      }
    });

    jLabel2.setBackground(new java.awt.Color(255, 255, 255));
    jLabel2.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
    jLabel2.setForeground(Color.decode("#29c7ac"));
    jLabel2.setText("Analizador Léxico");

    jLabel3.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
    jLabel3.setForeground(Color.decode("#a0c334"));
    jLabel3.setText("Errores");

    jLabel4.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
    jLabel4.setForeground(Color.decode("#9a0f98"));
    jLabel4.setText("Tokens");

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
        .addGap(262, 262, 262)
        .addComponent(jLabel4)
        .addGap(221, 221, 221)
        .addComponent(ButtonAnalize)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 214, Short.MAX_VALUE)
        .addComponent(jLabel3)
        .addGap(288, 288, 288))
      .addGroup(layout.createSequentialGroup()
        .addGap(487, 487, 487)
        .addComponent(jLabel2)
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(layout.createSequentialGroup()
            .addGap(67, 67, 67)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
              .addComponent(jLabel3)
              .addComponent(jLabel4)))
          .addGroup(layout.createSequentialGroup()
            .addGap(17, 17, 17)
            .addComponent(jLabel2)
            .addGap(5, 5, 5)
            .addComponent(ButtonAnalize, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
        .addContainerGap(377, Short.MAX_VALUE))
    );

    pack();
  }// </editor-fold>//GEN-END:initComponents

  private void ButtonAnalizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonAnalizeActionPerformed
    // TODO add your handling code here:
    try {
      ProbarLexerFile();//llamando al metodo ProbarLexerFile();
    } catch (IOException ex) {
      System.out.println(ex.getMessage());
    }
  }//GEN-LAST:event_ButtonAnalizeActionPerformed


  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton ButtonAnalize;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JLabel jLabel3;
  private javax.swing.JLabel jLabel4;
  // End of variables declaration//GEN-END:variables
  public void ProbarLexerFile() throws IOException {

    JFileChooser elegidor = new JFileChooser();
    int returnVal = elegidor.showOpenDialog(this);

    if (returnVal == JFileChooser.APPROVE_OPTION) {
      File file = elegidor.getSelectedFile();

      System.out.println("Opening: " + file.getName() + ".");
      Reader reader;
      reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "ISO-8859-15"));//TextInput.getText()

      Lexer lexer = new Lexer(reader);

      int k = modelo.getRowCount();
      for (int j = 0; j < k; j++) {
        modelo.removeRow(0);
      }

      k = modelo2.getRowCount();
      for (int j = 0; j < k; j++) {
        modelo2.removeRow(0);
      }

//se comienza a evaluar cada caracter
      LinkedHashMap<String, LinkedHashMap<String, Integer>> palabras = SingletoneEscaner.getInstance().usarJflex(lexer);

      for (String palabra : palabras.keySet()) {
        int i = palabra.indexOf(' ');
        String tipo = palabra.substring(0, i);
        String token = palabra.substring(i, palabra.length());
        String apariciones = "";
        for (String linea : palabras.get(palabra).keySet()) {
          apariciones = apariciones + " " + linea + "(" + palabras.get(palabra).get(linea) + "),";
        }
        apariciones = apariciones.substring(0, apariciones.length() - 1);

        String[] row = {token, tipo, apariciones};
        System.out.println(tipo);
        switch (tipo) {

          case "IDENTIFICADOR":
          case "OPERADOR":
          case "PALABRA_RESERVADA":
          case "TRANSAC":
          case "UNIDAD":
          case "LITERAL":
            modelo.addRow(row);
            break;
          case "ERROR_IDENTIFICADOR":
          case "ERROR_COMENTARIO":
          case "ERROR_STRING":
          case "ERROR_CARACTERES_NO_VALIDOS":
          case "ERROR_HEXADECIMAL":
          case "ERROR_CEROS_A_LA_IZQUIERDA":
          case "ERROR_NOTACION_CIENTIFICA":
            modelo2.addRow(row);
            break;
        }

      }

//jTextPane1.setText(Resultados);//mostrando los resultados

      /*
    for (String palabra : palabras.keySet()) {
      Resultados = Resultados + palabra;
      for (String linea : palabras.get(palabra).keySet()) {
        Resultados = Resultados + " " + linea + "(" + palabras.get(palabra).get(linea) + "),";
      }
      Resultados = Resultados.substring(0, Resultados.length() - 1) + "\n";
    }*/
    } else {
      System.out.println("Open command cancelled by user.");
    }

  }

}

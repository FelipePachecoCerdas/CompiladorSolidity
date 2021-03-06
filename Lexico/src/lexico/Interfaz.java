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
import java.io.StringReader;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java_cup.runtime.Symbol;
import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

/**
 *
 * @author usuario
 */
public class Interfaz extends javax.swing.JFrame {

  JTable table;
  DefaultTableModel modelo, modelo2, modelo3;
  JTable tabla, tabla2, tabla3;
  String infoArchivo, msBox = "", fileDir, fileName;

  /**
   * Creates new form Interfaz
   */
  public Interfaz() {
    javax.swing.UIManager.getDefaults().put("ScrollBar.minimumThumbSize", new Dimension(29, 29));
    javax.swing.UIManager.getDefaults().put("TableHeader.cellBorder", BorderFactory.createEmptyBorder(20, 20, 20, 21));

    Object[][] rows = {};
    Object[] cols = {"Token", "Tipo", "Apariciones"};
    modelo = new DefaultTableModel(rows, cols);
    //Ayudador.getInstance().ponerTipo("hola");
    //Ayudador.getInstance().intentar("", "");

    tabla = new JTable(modelo);
    tabla.setBounds(0, 0, 500, 300);
    tabla.setRowHeight(25);
    tabla.setLocation(0, 0);
    tabla.setFont(new Font("Dialog", Font.PLAIN, 13));

    JScrollPane scroll = new JScrollPane(tabla, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    scroll.setBounds(0, 0, 500, 300);
    scroll.setLocation(50, 150);
    scroll.getViewport().setBackground(Color.decode("#2d132c"));
    scroll.setBackground(Color.BLACK);
    scroll.getVerticalScrollBar().setBackground(Color.red);
    this.add(scroll);

    Object[][] rows2 = {};
    Object[] cols2 = {"Token", "Error", "Apariciones"};
    modelo2 = new DefaultTableModel(rows2, cols2);

    tabla2 = new JTable(modelo2);
    tabla2.setBounds(0, 0, 500, 300);
    tabla2.setRowHeight(25);
    tabla2.setLocation(0, 0);
    tabla2.setFont(new Font("Dialog", Font.PLAIN, 13));

    JScrollPane scroll2 = new JScrollPane(tabla2, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    scroll2.setBounds(0, 0, 500, 300);
    scroll2.setLocation(600, 150);
    scroll2.updateUI();
    scroll2.getViewport().setBackground(Color.decode("#445c3c"));
    this.add(scroll2);

    Object[][] rows3 = {};
    Object[] cols3 = {"Hilera Errónea", "Tipo de Error", "Línea: Carácter"};
    modelo3 = new DefaultTableModel(rows3, cols3);

    tabla3 = new JTable(modelo3);
    tabla3.setBounds(0, 0, 800, 150);
    tabla3.setRowHeight(25);
    tabla3.setLocation(0, 0);
    tabla3.setFont(new Font("Dialog", Font.PLAIN, 13));

    JScrollPane scroll3 = new JScrollPane(tabla3, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    scroll3.setBounds(0, 0, 800, 150);
    scroll3.setLocation(175, 525);
    scroll3.updateUI();
    scroll3.getViewport().setBackground(Color.decode("#cf7500"));
    this.add(scroll3);

    initComponents();
    this.ButtonSemantico.setEnabled(false);
    String s = "393e46";
    tabla.getColumnModel().getColumn(0).setCellRenderer(new ColorRenderer(Color.decode("#581845"), Color.decode("#900c3f"), 2));
    tabla.getColumnModel().getColumn(1).setCellRenderer(new ColorRenderer(Color.decode("#581845"), Color.decode("#900c3f"), 2));
    tabla.getColumnModel().getColumn(2).setCellRenderer(new ColorRenderer(Color.decode("#581845"), Color.decode("#900c3f"), 2));

    tabla2.getColumnModel().getColumn(0).setCellRenderer(new ColorRenderer(Color.decode("#729d39"), Color.decode("#a7d129"), 2));
    tabla2.getColumnModel().getColumn(1).setCellRenderer(new ColorRenderer(Color.decode("#729d39"), Color.decode("#a7d129"), 2));
    tabla2.getColumnModel().getColumn(2).setCellRenderer(new ColorRenderer(Color.decode("#729d39"), Color.decode("#a7d129"), 2));

    tabla3.getColumnModel().getColumn(0).setCellRenderer(new ColorRenderer(Color.decode("#fa744f"), Color.decode("#ffa931"), 2));
    tabla3.getColumnModel().getColumn(1).setCellRenderer(new ColorRenderer(Color.decode("#fa744f"), Color.decode("#ffa931"), 2));
    tabla3.getColumnModel().getColumn(2).setCellRenderer(new ColorRenderer(Color.decode("#fa744f"), Color.decode("#ffa931"), 2));

    tabla.getTableHeader().setDefaultRenderer(new ColorRenderer(Color.decode("#900c3f"), Color.decode("#900c3f"), 1));
    tabla2.getTableHeader().setDefaultRenderer(new ColorRenderer(Color.decode("#445c3c"), Color.decode("#a7d129"), 1));
    tabla3.getTableHeader().setDefaultRenderer(new ColorRenderer(Color.decode("#ffa931"), Color.decode("#ffa931"), 1));
    tabla.setShowGrid(true);
    tabla2.setShowGrid(true);
    tabla3.setShowGrid(true);

    tabla2.getColumnModel().getColumn(1).setPreferredWidth(200);    //Surname
    tabla3.getColumnModel().getColumn(1).setPreferredWidth(50);    //Surname
    tabla3.getColumnModel().getColumn(2).setPreferredWidth(50);    //Surname
    tabla3.getColumnModel().getColumn(0).setPreferredWidth(300);    //Surname
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

    jPanel1 = new javax.swing.JPanel();
    ButtonSemantico = new javax.swing.JButton();
    jLabel2 = new javax.swing.JLabel();
    jLabel3 = new javax.swing.JLabel();
    jLabel5 = new javax.swing.JLabel();
    jLabel6 = new javax.swing.JLabel();
    ButtonAnalize1 = new javax.swing.JButton();

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
      jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGap(0, 100, Short.MAX_VALUE)
    );
    jPanel1Layout.setVerticalGroup(
      jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGap(0, 100, Short.MAX_VALUE)
    );

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

    ButtonSemantico.setBackground(Color.decode("#d9c6a5"));
    ButtonSemantico.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
    ButtonSemantico.setText("Análisis Semántico");
    ButtonSemantico.setAutoscrolls(true);
    ButtonSemantico.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        ButtonSemanticoActionPerformed(evt);
      }
    });

    jLabel2.setBackground(new java.awt.Color(255, 255, 255));
    jLabel2.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
    jLabel2.setForeground(Color.decode("#29c7ac"));
    jLabel2.setText("Compilador Solidity");

    jLabel3.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
    jLabel3.setForeground(Color.decode("#ffa931"));
    jLabel3.setText("Errores Sintácticos");

    jLabel5.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
    jLabel5.setForeground(Color.decode("#a0c334"));
    jLabel5.setText("Errores Léxicos");

    jLabel6.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
    jLabel6.setForeground(Color.decode("#9a0f98"));
    jLabel6.setText("Tokens");

    ButtonAnalize1.setBackground(Color.decode("#4ecca3"));
    ButtonAnalize1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
    ButtonAnalize1.setText("Analizar");
    ButtonAnalize1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        ButtonAnalize1ActionPerformed(evt);
      }
    });

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
        .addGap(270, 270, 270)
        .addComponent(jLabel6)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 175, Short.MAX_VALUE)
        .addComponent(ButtonAnalize1)
        .addGap(134, 134, 134)
        .addComponent(jLabel5)
        .addGap(217, 217, 217))
      .addGroup(layout.createSequentialGroup()
        .addGap(466, 466, 466)
        .addComponent(jLabel3)
        .addContainerGap(479, Short.MAX_VALUE))
      .addGroup(layout.createSequentialGroup()
        .addGap(414, 414, 414)
        .addComponent(jLabel2)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addComponent(ButtonSemantico)
        .addGap(30, 30, 30))
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addGap(24, 24, 24)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jLabel2)
          .addComponent(ButtonSemantico))
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(layout.createSequentialGroup()
            .addGap(41, 41, 41)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
              .addComponent(jLabel5)
              .addComponent(jLabel6)))
          .addGroup(layout.createSequentialGroup()
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(ButtonAnalize1)))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 342, Short.MAX_VALUE)
        .addComponent(jLabel3)
        .addGap(180, 180, 180))
    );

    pack();
  }// </editor-fold>//GEN-END:initComponents

  private void ButtonSemanticoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonSemanticoActionPerformed
    // TODO add your handling code here:
    InterfazSemantica interfazSem = new InterfazSemantica(this.fileDir, this.fileName);
    interfazSem.setVisible(true);
  }//GEN-LAST:event_ButtonSemanticoActionPerformed

  private void ButtonAnalize1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonAnalize1ActionPerformed
    try {

      ProbarLexerFile();//llamando al metodo ProbarLexerFile();
      ProbarSintactico();
    } catch (IOException ex) {
      System.out.println(ex.getMessage());
    }
  }//GEN-LAST:event_ButtonAnalize1ActionPerformed

  private void ProbarSintactico() {

    int k = modelo3.getRowCount();
    for (int j = 0; j < k; j++) {
      modelo3.removeRow(0);
    }

    String ST = infoArchivo;
    lexico.LexerCup lc = new lexico.LexerCup(new StringReader(ST));
    lexico.LexerCup lc2 = new lexico.LexerCup(new StringReader(ST));

    try {
      Symbol s;
      while ((s = (lc2.next_token())).value != null) {
        if (s.value == "struct") {
          break;
        }
        //System.out.println(s.value + ": " + Integer.toString(s.sym));

      }
    } catch (IOException e) {
    }

    Sintax st = new Sintax(lc);

    try {
      st.parse();

      if (!st.errores.isEmpty()) {
        Semantico.self().hayErrores = true;
      }

      for (int j = 0; j < st.errores.size(); j++) {

        Symbol e = st.errores.get(j);
        String[] row = {e.value.toString(), st.erroresStr.get(j), Integer.toString(e.right + 1) + ": " + Integer.toString(e.left + 1)};
        modelo3.addRow(row);
      }
      System.out.println("Parsing done " + Integer.toString(st.errores.size()));
      this.msBox += "\nAnálisis sintácico realizado exitosamente." + ((modelo3.getRowCount() > 0) ? (" Se han encontrado " + Integer.toString(modelo3.getRowCount()) + " errores sintácicos.") : " No se han encontrado errores sintácicos.");
      JOptionPane.showMessageDialog(null, this.msBox, "Estado de Compilación", JOptionPane.INFORMATION_MESSAGE);
      //String[] row = {"", "Analisis realizado correctamente", ""};
      //modelo3.addRow(row);
      //txtAnalizarSin.setText("Analisis realizado correctamente");
      //txtAnalizarSin.setForeground(new Color(25, 111, 61));
    } catch (Exception ex) {
      if (!st.errores.isEmpty()) {
        Semantico.self().hayErrores = true;
      }

      for (int j = 0; j < st.errores.size(); j++) {

        Symbol e = st.errores.get(j);
        String[] row = {e.value.toString(), st.erroresStr.get(j), Integer.toString(e.right + 1) + ": " + Integer.toString(e.left + 1)};
        modelo3.addRow(row);
      }
      this.msBox += "\nAnálisis sintácico realizado exitosamente." + ((modelo3.getRowCount() > 0) ? (" Se han encontrado " + Integer.toString(modelo3.getRowCount()) + " errores sintácicos.") : " No se han encontrado errores sintácicos.");
      JOptionPane.showMessageDialog(null, this.msBox, "Estado de Compilación", JOptionPane.INFORMATION_MESSAGE);

      System.out.println("Errores");
    }
    this.ButtonSemantico.setEnabled(true);

  }
  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton ButtonAnalize1;
  private javax.swing.JButton ButtonSemantico;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JLabel jLabel3;
  private javax.swing.JLabel jLabel5;
  private javax.swing.JLabel jLabel6;
  private javax.swing.JPanel jPanel1;
  // End of variables declaration//GEN-END:variables
  public void ProbarLexerFile() throws IOException {

    JFileChooser elegidor = new JFileChooser();
    int returnVal = elegidor.showOpenDialog(this);

    if (returnVal == JFileChooser.APPROVE_OPTION) {
      Semantico.self().theEndOfItAll();
      File file = elegidor.getSelectedFile();

      File archivo = new File(elegidor.getSelectedFile().getAbsolutePath());
      this.fileDir = elegidor.getSelectedFile().getParent();
      this.fileName = elegidor.getSelectedFile().getName();

      infoArchivo = new String(Files.readAllBytes(archivo.toPath()));

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
        String token = palabra.substring(i + 1, palabra.length());
        String apariciones = "";
        for (String linea : palabras.get(palabra).keySet()) {
          apariciones = apariciones + " " + linea + "(" + palabras.get(palabra).get(linea) + "),";
        }
        apariciones = apariciones.substring(0, apariciones.length() - 1);

        String[] row = {token, tipo, apariciones};
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
            Semantico.self().hayErrores = true;
            modelo2.addRow(row);
            break;
        }

      }
      this.updateRowHeights();
      this.msBox = "Análisis léxico realizado exitosamente." + ((modelo2.getRowCount() > 0) ? (" Se han encontrado " + Integer.toString(modelo2.getRowCount()) + " errores léxicos.") : " No se han encontrado errores léxicos.");
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

  private void updateRowHeights() {
    for (int row = 0; row < tabla.getRowCount(); row++) {
      int rowHeight = tabla.getRowHeight();

      for (int column = 0; column < tabla.getColumnCount(); column++) {
        Component comp = tabla.prepareRenderer(tabla.getCellRenderer(row, column), row, column);
        rowHeight = Math.max(rowHeight, comp.getPreferredSize().height);
      }

      tabla.setRowHeight(row, rowHeight);
    }
  }

}

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
  DefaultTableModel modelo, modelo2;
  JTable tabla, tabla2;
  String infoArchivo;

  /**
   * Creates new form Interfaz
   */
  public Interfaz() {
    javax.swing.UIManager.getDefaults().put("ScrollBar.minimumThumbSize", new Dimension(29, 29));
    javax.swing.UIManager.getDefaults().put("TableHeader.cellBorder", BorderFactory.createEmptyBorder(20, 20, 20, 21));

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

    initComponents();
    String s = "393e46";
    tabla.getColumnModel().getColumn(0).setCellRenderer(new ColorRenderer(Color.decode("#581845"), Color.decode("#900c3f"), 2));
    tabla.getColumnModel().getColumn(1).setCellRenderer(new ColorRenderer(Color.decode("#581845"), Color.decode("#900c3f"), 2));
    tabla.getColumnModel().getColumn(2).setCellRenderer(new ColorRenderer(Color.decode("#581845"), Color.decode("#900c3f"), 2));

    tabla2.getColumnModel().getColumn(0).setCellRenderer(new ColorRenderer(Color.decode("#729d39"), Color.decode("#a7d129"), 2));
    tabla2.getColumnModel().getColumn(1).setCellRenderer(new ColorRenderer(Color.decode("#729d39"), Color.decode("#a7d129"), 2));
    tabla2.getColumnModel().getColumn(2).setCellRenderer(new ColorRenderer(Color.decode("#729d39"), Color.decode("#a7d129"), 2));

    tabla.getTableHeader().setDefaultRenderer(new ColorRenderer(Color.decode("#900c3f"), Color.decode("#900c3f"), 1));
    tabla2.getTableHeader().setDefaultRenderer(new ColorRenderer(Color.decode("#445c3c"), Color.decode("#a7d129"), 1));
    tabla.setShowGrid(true);
    tabla2.setShowGrid(true);

    tabla2.getColumnModel().getColumn(1).setPreferredWidth(200);    //Surname

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
        jScrollPane2 = new javax.swing.JScrollPane();
        txtAnalizarSin = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        ButtonAnalize.setBackground(Color.decode("#4ecca3"));
        ButtonAnalize.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        ButtonAnalize.setText("Analizar");
        ButtonAnalize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonAnalizeActionPerformed(evt);
            }
        });

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jLabel2.setForeground(Color.decode("#29c7ac"));
        jLabel2.setText("Analizador Léxico");

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel3.setForeground(Color.decode("#a0c334"));
        jLabel3.setText("Errores");

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel4.setForeground(Color.decode("#9a0f98"));
        jLabel4.setText("Tokens");

        txtAnalizarSin.setColumns(20);
        txtAnalizarSin.setRows(5);
        jScrollPane2.setViewportView(txtAnalizarSin);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(428, 428, 428)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(252, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(190, 190, 190)
                .addComponent(ButtonAnalize)
                .addGap(191, 191, 191)
                .addComponent(jLabel3)
                .addGap(283, 283, 283))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 690, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ButtonAnalize))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(99, 99, 99)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 328, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

  private void ButtonAnalizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonAnalizeActionPerformed
    // TODO add your handling code here:
    try {
      ProbarLexerFile();//llamando al metodo ProbarLexerFile();
      ProbarSintactico();
    } catch (IOException ex) {
      System.out.println(ex.getMessage());
    }
    
  }//GEN-LAST:event_ButtonAnalizeActionPerformed

  private void ProbarSintactico(){
      String ST = infoArchivo;
        Sintax s = new Sintax(new lexico.LexerCup(new StringReader(ST)));
        
        try {
            s.parse();
            txtAnalizarSin.setText("Analisis realizado correctamente");
            txtAnalizarSin.setForeground(new Color(25, 111, 61));
        } catch (Exception ex) {
            Symbol sym = s.getS();
            txtAnalizarSin.setText("Error de sintaxis. Linea: " + (sym.right + 1) + " Columna: " + (sym.left + 1) + ", Texto: \"" + sym.value + "\"");
            txtAnalizarSin.setForeground(Color.red);
        }
  }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonAnalize;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea txtAnalizarSin;
    // End of variables declaration//GEN-END:variables
  public void ProbarLexerFile() throws IOException {

    JFileChooser elegidor = new JFileChooser();
    int returnVal = elegidor.showOpenDialog(this);

    if (returnVal == JFileChooser.APPROVE_OPTION) {
      File file = elegidor.getSelectedFile();
      
      File archivo = new File(elegidor.getSelectedFile().getAbsolutePath());
      
      infoArchivo = new String(Files.readAllBytes(archivo.toPath()));

      Reader reader;
      reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));//TextInput.getText()

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
            modelo2.addRow(row);
            break;
        }

      }
      this.updateRowHeights();

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

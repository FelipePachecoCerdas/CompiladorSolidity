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
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringReader;
import java.io.Writer;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java_cup.runtime.Symbol;
import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
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
public class InterfazSemantica extends javax.swing.JFrame {

  JTable table;
  DefaultTableModel modelo, modelo2, modelo3;
  JTable tabla, tabla2, tabla3;
  String infoArchivo, msBox = "";

  /**
   * Creates new form Interfaz
   *
   * @param fileDir
   * @param fileName
   */
  public InterfazSemantica(String fileDir, String fileName) {
    javax.swing.UIManager.getDefaults().put("ScrollBar.minimumThumbSize", new Dimension(29, 29));
    javax.swing.UIManager.getDefaults().put("TableHeader.cellBorder", BorderFactory.createEmptyBorder(20, 20, 20, 21));

    Object[][] rows = {};
    Object[] cols = {"Identificador", "Tipo Dato", "Valor", "Alcance", "Tipo"};
    modelo = new DefaultTableModel(rows, cols);

    Semantico sem = Semantico.self();

    for (String s : sem.ts.keySet()) {
      SimboloTS info = sem.ts.get(s);
      System.out.println("Variable: " + s + ", Tipo: " + info.tipoDato + ", Valor: " + info.valor + ", Alcance: " + info.alcance);

      String[] row = {s, info.tipoDato, (info.tipoDato.equals("int") && info.tipoSimbolo.equals("variable")) ? info.valor.toString() : "null", info.alcance, info.tipoSimbolo};
      modelo.addRow(row);
    }

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
    Object[] cols2 = {"Error", "Mensaje de error", "Linea: Carácter"}; // 2; 4+p
    modelo2 = new DefaultTableModel(rows2, cols2);

    for (int i = 0; i < sem.erroresStr.size(); i++) {
      String s = sem.erroresStr.get(i);
      Symbol info = sem.errores.get(i);

      String[] row = {info.value.toString(), s, String.valueOf(info.right) + ": " + String.valueOf(info.left)};
      modelo2.addRow(row);
    }

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
    Object[] cols3 = {"Código"};
    modelo3 = new DefaultTableModel(rows3, cols3);

    if (!Semantico.self().errores.isEmpty()) {
      Semantico.self().hayErrores = true;
    }
    String codigo = sem.getAsm();
    sem.printPila();
    String codigoHtml = "<html><body><pre>";
    for (String linea : codigo.split("\n")) {
      codigoHtml += " " + linea + "<br />";
      //String[] row = {linea};
      //modelo3.addRow(row);
    }

    this.msBox = "Análisis semántico realizado exitosamente." + ((sem.hayErrores) ? (" Se han encontrado " + Integer.toString(sem.errores.size()) + " errores semánticos.") : " No se han encontrado errores semáncticos.");

    codigoHtml += "</pre></body></html>";
    if (!codigo.equals("")) {

      try {
        String baseName = fileName.substring(0, fileName.lastIndexOf('.'));
        fileName = baseName + ".asm";
        // AQUIIII
        String asm_compile = "; nasm -f elf32 " + baseName + ".asm\n";
        asm_compile += "; ld -m elf_i386 -s -o " + baseName + " " + baseName + ".o io.o\n";
        asm_compile += "; ./" + baseName + "\n";
        codigo = asm_compile + codigo;
        File file = new File(fileDir + "\\" + fileName);
        Writer output = new BufferedWriter(new FileWriter(file));

        for (String linea : codigo.split("\n")) {
          output.write(linea + "\n");
        }

        output.close();
        this.msBox += "\nAl no haber errores, se ha guardado el código ensamblador en el archivo \"" + fileDir + "\\" + fileName + "\".";

      } catch (IOException e) {
        System.out.println("Could not create file");
      }
    } else {
      this.msBox += "\nAl haber errores, no se ha generado archivo de ensamblador.";
    }

    tabla3 = new JTable(modelo3);
    tabla3.setBounds(0, 0, 800, 35);
    tabla3.setRowHeight(25);
    tabla3.setLocation(0, 0);
    tabla3.setFont(new Font("Dialog", Font.PLAIN, 13));

    JScrollPane scroll3 = new JScrollPane(tabla3, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    scroll3.setBounds(0, 0, 800, 35);
    scroll3.setLocation(175, 500);
    scroll3.updateUI();
    scroll3.getViewport().setBackground(Color.decode("#cf7500"));
    this.add(scroll3);

    JLabel codigoLabel = new JLabel(codigoHtml);

    codigoLabel.setBounds(0, 0, 800, 150);
    codigoLabel.setForeground(Color.WHITE);
    codigoLabel.setFont(new Font(Font.DIALOG, Font.PLAIN, codigoLabel.getFont().getSize()));
    JScrollPane js = new JScrollPane(codigoLabel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    js.setBackground(Color.red);
    js.setBounds(0, 0, 800, 150);
    js.getViewport().setBackground(Color.decode("#fa744f"));
    js.getViewport().setForeground(Color.WHITE);
    js.setLocation(175, 530);
    this.add(js);

    initComponents();
    String s = "393e46";
    tabla.getColumnModel().getColumn(0).setCellRenderer(new ColorRenderer(Color.decode("#581845"), Color.decode("#900c3f"), 2));
    tabla.getColumnModel().getColumn(1).setCellRenderer(new ColorRenderer(Color.decode("#581845"), Color.decode("#900c3f"), 2));
    tabla.getColumnModel().getColumn(2).setCellRenderer(new ColorRenderer(Color.decode("#581845"), Color.decode("#900c3f"), 2));
    tabla.getColumnModel().getColumn(3).setCellRenderer(new ColorRenderer(Color.decode("#581845"), Color.decode("#900c3f"), 2));
    tabla.getColumnModel().getColumn(4).setCellRenderer(new ColorRenderer(Color.decode("#581845"), Color.decode("#900c3f"), 2));

    tabla2.getColumnModel().getColumn(0).setCellRenderer(new ColorRenderer(Color.decode("#729d39"), Color.decode("#a7d129"), 2));
    tabla2.getColumnModel().getColumn(1).setCellRenderer(new ColorRenderer(Color.decode("#729d39"), Color.decode("#a7d129"), 2));
    tabla2.getColumnModel().getColumn(2).setCellRenderer(new ColorRenderer(Color.decode("#729d39"), Color.decode("#a7d129"), 2));

    tabla3.getColumnModel().getColumn(0).setCellRenderer(new ColorRenderer(Color.decode("#fa744f"), Color.decode("#ffa931"), 2));

    tabla.getTableHeader().setDefaultRenderer(new ColorRenderer(Color.decode("#900c3f"), Color.decode("#900c3f"), 1));
    tabla2.getTableHeader().setDefaultRenderer(new ColorRenderer(Color.decode("#445c3c"), Color.decode("#a7d129"), 1));
    tabla3.getTableHeader().setDefaultRenderer(new ColorRenderer(Color.decode("#ffa931"), Color.decode("#ffa931"), 1));
    tabla.setShowGrid(true);
    tabla2.setShowGrid(true);
    tabla3.setShowGrid(true);

    this.getRootPane().setWindowDecorationStyle(JRootPane.NONE);

    tabla2.getColumnModel().getColumn(1).setPreferredWidth(200);    //Surname
    this.setLocationRelativeTo(null);
    this.getContentPane().setBackground(Color.decode("#121212"));
    this.setVisible(true);
    JOptionPane.showMessageDialog(null, this.msBox, "Estado de Compilación", JOptionPane.INFORMATION_MESSAGE);

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
    jLabel2 = new javax.swing.JLabel();
    jLabel3 = new javax.swing.JLabel();
    jLabel5 = new javax.swing.JLabel();
    jLabel6 = new javax.swing.JLabel();
    jButton1 = new javax.swing.JButton();

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

    jLabel2.setBackground(new java.awt.Color(255, 255, 255));
    jLabel2.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
    jLabel2.setForeground(Color.decode("#29c7ac"));
    jLabel2.setText("Análisis Semántico");

    jLabel3.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
    jLabel3.setForeground(Color.decode("#ffa931"));
    jLabel3.setText("Código Ensamblador");

    jLabel5.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
    jLabel5.setForeground(Color.decode("#a0c334"));
    jLabel5.setText("Errores Semánticos");

    jLabel6.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
    jLabel6.setForeground(Color.decode("#9a0f98"));
    jLabel6.setText("Tabla de Símbolos");

    jButton1.setBackground(Color.decode("#4ecca3"));
    jButton1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
    jButton1.setForeground(new java.awt.Color(0, 0, 0));
    jButton1.setText("←");
    jButton1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jButton1ActionPerformed(evt);
      }
    });

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
        .addGap(211, 211, 211)
        .addComponent(jLabel6)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 335, Short.MAX_VALUE)
        .addComponent(jLabel5)
        .addGap(179, 179, 179))
      .addGroup(layout.createSequentialGroup()
        .addGap(30, 30, 30)
        .addComponent(jButton1)
        .addGap(346, 346, 346)
        .addComponent(jLabel2)
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addComponent(jLabel3)
        .addGap(455, 455, 455))
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addGap(24, 24, 24)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jLabel2)
          .addComponent(jButton1))
        .addGap(41, 41, 41)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel5)
          .addComponent(jLabel6))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 311, Short.MAX_VALUE)
        .addComponent(jLabel3)
        .addGap(202, 202, 202))
    );

    pack();
  }// </editor-fold>//GEN-END:initComponents

  private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    this.dispose();
  }//GEN-LAST:event_jButton1ActionPerformed

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton jButton1;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JLabel jLabel3;
  private javax.swing.JLabel jLabel5;
  private javax.swing.JLabel jLabel6;
  private javax.swing.JPanel jPanel1;
  // End of variables declaration//GEN-END:variables

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

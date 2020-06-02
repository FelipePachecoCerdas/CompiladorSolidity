package lexico;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.TableCellRenderer;
import java.awt.Color;
import java.awt.Component;

public class ColorRenderer extends JLabel implements TableCellRenderer {

  public ColorRenderer() {
  }

  public Component getTableCellRendererComponent(
    JTable table, Object value,
    boolean isSelected, boolean hasFocus,
    int row, int column) {

    //if(column == 4){
    if (true) {
      setForeground(Color.RED);

    }
    //}
    return this;
  }
}

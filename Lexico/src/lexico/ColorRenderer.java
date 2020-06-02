package lexico;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.TableCellRenderer;
import java.awt.Color;
import java.awt.Component;
import javax.swing.table.DefaultTableCellRenderer;

class ColorRenderer extends DefaultTableCellRenderer {

  Color backgroundColor, foregroundColor;

  public ColorRenderer(Color backgroundColor, Color foregroundColor) {
    super();
    this.backgroundColor = backgroundColor;
    this.foregroundColor = foregroundColor;
  }

  public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
    Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

    if (row % 2 == 0) {
      cell.setBackground(backgroundColor);
    } else {
      cell.setBackground(foregroundColor);
    }
    cell.setForeground(Color.WHITE);
    return cell;
  }
}

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
  int val;

  public ColorRenderer(Color backgroundColor, Color foregroundColor, int val) {
    super();
    this.backgroundColor = backgroundColor;
    this.foregroundColor = foregroundColor;
    this.val = val;
  }

  public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
    if (((String) value).contains("...")) {
      String s = ((String) value);
      value = "<html><font color=white> " + s.substring(0, s.indexOf('~')) + "</font> <font color=red>" + s.substring(s.indexOf('~') + 1, s.lastIndexOf('~')) + "</font> <font color=white>" + " ..." + "</font> </html>";
    }
    Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

//"<html><font size='5' color=blue> Welcome to</font> <font            size='6'color=green> Tutorials Point</font></html>"
    if (row % 2 == 0) {
      cell.setBackground(backgroundColor);
    } else {
      cell.setBackground(foregroundColor);
    }
    cell.setForeground(Color.WHITE);
    if (val == 1) {
      cell.setFont(new java.awt.Font("Dialog", 1, 18));
      this.setHorizontalAlignment(JLabel.CENTER);
    }
    return cell;
  }
}

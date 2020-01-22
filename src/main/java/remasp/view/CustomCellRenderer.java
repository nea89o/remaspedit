package remasp.view;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class CustomCellRenderer extends DefaultTableCellRenderer {
	public int getZuFaerbendeZeile() {
		return this.zuFaerbendeZeile;
	}

	public void setZuFaerbendeZeile(int zuFaerbendeZeile) {
		this.zuFaerbendeZeile = zuFaerbendeZeile;
	}

	private int zuFaerbendeZeile = -1;

	public Component getTableCellRendererComponent(JTable table, Object obj, boolean isSelected, boolean hasFocus,
			int row, int column) {
		Component cell = super.getTableCellRendererComponent(table, obj, isSelected, hasFocus, row, column);
		cell.setForeground(Color.black);

		if (row == this.zuFaerbendeZeile) {
			cell.setBackground(Color.yellow);
		} else if (row % 2 == 0 && row != this.zuFaerbendeZeile) {
			cell.setBackground(Color.white);
		} else if (row % 2 == 1 && row != this.zuFaerbendeZeile) {
			cell.setBackground(Color.lightGray);
		}
		return cell;
	}
}

package remasp.view;

import javax.swing.table.AbstractTableModel;

public class RemaspTableModel extends AbstractTableModel {

	public void generateRows(int count) {
		data = new Object[count][];
		for (int i = 0; i < count; i++) {
			data[i] = new Object[] { i == 0 ? "Akkumulator" : i + "", "0" };
		}
	}

	private String[] columnNames;
	private Object[][] data;
	Class<?>[] types;
	boolean[] canEdit;

	RemaspTableModel() {
		this.columnNames = new String[] { "Nr", "Inhalt" };
		generateRows(101);
		this.types = new Class[] { String.class, String.class };
		this.canEdit = new boolean[] { false, true };
	}

	@Override
	public Class<?> getColumnClass(final int columnIndex) {
		return this.types[columnIndex];
	}

	@Override
	public boolean isCellEditable(final int rowIndex, final int columnIndex) {
		return this.canEdit[columnIndex];
	}

	@Override
	public int getColumnCount() {
		return this.columnNames.length;
	}

	@Override
	public String getColumnName(final int col) {
		return this.columnNames[col];
	}

	@Override
	public int getRowCount() {
		return this.data.length;
	}

	@Override
	public Object getValueAt(final int row, final int col) {
		return this.data[row][col];
	}

	@Override
	public void setValueAt(Object value, final int row, final int col) {
		if (col == 1 && value instanceof String) {
			long l = Long.parseLong((((String) value).split(" "))[0]);
			if (l > 32 && l < 127) {
				value = l + " (" + ((char) l) + ")";
			} else if (l == 32) {
				value = "32 (SPACE)";
			} else {
				value = "" + l;
			}
		}
		this.data[row][col] = value;
		this.fireTableCellUpdated(row, col);
	}
}

package remasp.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableCellEditor;

public class RemaspCellEditor extends JTextField implements TableCellEditor, DocumentListener {
	private List<CellEditorListener> listeners = new ArrayList<>();

	public RemaspCellEditor() {
		getDocument().addDocumentListener(this);
		setFont(new Font("Consolas", 0, 18));
	}

	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		setText("");
		return this;
	}

	public void addCellEditorListener(CellEditorListener l) {
		this.listeners.add(l);
	}

	public void cancelCellEditing() {
		ChangeEvent event = new ChangeEvent(this);
		for (CellEditorListener listener : (CellEditorListener[]) this.listeners
				.toArray(new CellEditorListener[this.listeners.size()])) {
			listener.editingCanceled(event);
		}
	}

	public Object getCellEditorValue() {
		return getText();
	}

	public boolean isCellEditable(EventObject anEvent) {
		if (anEvent instanceof MouseEvent) {
			return (((MouseEvent) anEvent).getClickCount() > 1);
		}
		return true;
	}

	public void removeCellEditorListener(CellEditorListener l) {
		this.listeners.remove(l);
	}

	public boolean shouldSelectCell(EventObject anEvent) {
		return true;
	}

	public boolean stopCellEditing() {
		if (!isValidText()) {
			return false;
		}

		ChangeEvent event = new ChangeEvent(this);
		for (CellEditorListener listener : (CellEditorListener[]) this.listeners
				.toArray(new CellEditorListener[this.listeners.size()])) {
			listener.editingStopped(event);
		}
		return true;
	}

	public void changedUpdate(DocumentEvent e) {
		update();
	}

	public void insertUpdate(DocumentEvent e) {
		update();
	}

	public void removeUpdate(DocumentEvent e) {
		update();
	}

	private boolean isValidText() {
		return getText().matches("[0-9]+");
	}

	public void update() {
		Color color;
		if (isValidText()) {
			color = Color.GREEN;
		} else {
			color = Color.RED;
		}
		setBorder(BorderFactory.createLineBorder(color));
	}
}

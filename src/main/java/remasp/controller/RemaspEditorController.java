package remasp.controller;

import java.awt.Color;
import java.awt.Component;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Action;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.AbstractDocument;
import javax.swing.text.DefaultEditorKit;
import javax.swing.text.StyledEditorKit;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;
import remasp.view.RemaspView;

public class RemaspEditorController {
	private final RemaspView remaspView;
	private String currentFileName;
	AbstractDocument doc;
	protected UndoManager undo = new UndoManager();
	boolean textHasChanged = false;

	RemaspEditorController(RemaspView remaspView) {
		this.remaspView = remaspView;
		this.doc = (AbstractDocument) this.remaspView.getjTextPane().getStyledDocument();
		this.doc.addUndoableEditListener(new MyUndoableEditListener());
		addActionListener();
		this.currentFileName = "Unbenannt";
		remaspView.setTitle(this.currentFileName + " - ReMaSp Modded");
	}

	private void addActionListener() {
		NeuActionListener neuListener = new NeuActionListener();
		this.remaspView.getButtonNeu().addActionListener(neuListener);
		this.remaspView.getjMenuItemNeu().addActionListener(neuListener);
		OeffnenActionListener oeffnenListener = new OeffnenActionListener();
		this.remaspView.getButtonOeffnen().addActionListener(oeffnenListener);
		this.remaspView.getjMenuItemOeffnen().addActionListener(oeffnenListener);
		SpeichernAcitonListener speichernListener = new SpeichernAcitonListener();
		this.remaspView.getButtonSpeichern().addActionListener(speichernListener);
		this.remaspView.getjMenuItemSpeichern().addActionListener(speichernListener);
		SpeichernUnterActionListener speichernUnterListener = new SpeichernUnterActionListener();
		this.remaspView.getjMenuItemSpeichernUnter().addActionListener(speichernUnterListener);
		DruckenActionListener druckenListener = new DruckenActionListener();
		this.remaspView.getjMenuItemDrucken().addActionListener(druckenListener);
		this.remaspView.getButtonDrucken().addActionListener(druckenListener);
		BeendenActionListener beendenListener = new BeendenActionListener();
		this.remaspView.getjMenuItemBeenden().addActionListener(beendenListener);

		this.remaspView.getjMenuItemAusschneiden().addActionListener(new DefaultEditorKit.CutAction());
		this.remaspView.getjMenuItemKopieren().addActionListener(new DefaultEditorKit.CopyAction());
		this.remaspView.getjMenuItemEinfuegen().addActionListener(new DefaultEditorKit.PasteAction());
		this.remaspView.getjMenuItemRueckgaengig().addActionListener(new RueckgaengigListener());
		this.remaspView.getjMenuItemRueckgaengig().setEnabled(false);

		JMenu menu = this.remaspView.getjMenuFormat();
		Action action = new StyledEditorKit.BoldAction();
		action.putValue("Name", "Fett");
		menu.add(action);

		action = new StyledEditorKit.ItalicAction();
		action.putValue("Name", "Kursiv");
		menu.add(action);

		action = new StyledEditorKit.UnderlineAction();
		action.putValue("Name", "Unterstrichen");
		menu.add(action);

		menu.addSeparator();

		menu.add(new StyledEditorKit.FontFamilyAction("Serif", "Serif"));

		menu.add(new StyledEditorKit.FontFamilyAction("SansSerif", "SansSerif"));

		menu.addSeparator();

		menu.add(new StyledEditorKit.ForegroundAction("Rot", Color.red));

		menu.add(new StyledEditorKit.ForegroundAction("Grün", Color.green));

		menu.add(new StyledEditorKit.ForegroundAction("Blau", Color.blue));

		menu.add(new StyledEditorKit.ForegroundAction("Schwarz", Color.black));
	}

	protected class MyUndoableEditListener implements UndoableEditListener {
		public void undoableEditHappened(UndoableEditEvent e) {
			RemaspEditorController.this.undo.addEdit(e.getEdit());
			RemaspEditorController.this.textHasChanged = true;
			RemaspEditorController.this.remaspView.getjMenuItemRueckgaengig().setEnabled(true);
		}
	}

	class RueckgaengigListener implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			try {
				if (RemaspEditorController.this.undo.canUndo()) {
					RemaspEditorController.this.undo.undo();
					RemaspEditorController.this.textHasChanged = true;
				} else {
					RemaspEditorController.this.remaspView.getjMenuItemRueckgaengig().setEnabled(false);
					RemaspEditorController.this.textHasChanged = false;
				}

			} catch (CannotUndoException ex) {
				System.out.println("Unable to undo: " + ex);
				ex.printStackTrace();
			}
		}
	}

	class BeendenActionListener implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			if (RemaspEditorController.this.textHasChanged) {
				int auswahl = JOptionPane.showConfirmDialog((Component) RemaspEditorController.this.remaspView,
						"Dokument wurde noch nicht gespeichert.\nWollen Sie speichern?");
				switch (auswahl) {
				case 0:
					if (!RemaspEditorController.this.currentFileName.equals("Unbenannt")) {
						RemaspEditorController.this.saveFile(RemaspEditorController.this.currentFileName);
						break;
					}
					RemaspEditorController.this.saveFileAs();
					break;

				case 2:
					return;
				}

			}
			System.exit(0);
		}
	}

	class DruckenActionListener implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			JTextPane jTextPane = RemaspEditorController.this.remaspView.getjTextPane();
			try {
				jTextPane.print();
			} catch (PrinterException ex) {
				Logger.getLogger(RemaspEditorController.class.getName()).log(Level.SEVERE, null, ex);
				JOptionPane.showMessageDialog(jTextPane, "Das aktuelle Dokument konnte leider nicht gedruckt werden.");
			}
		}
	}

	class SpeichernUnterActionListener implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			RemaspEditorController.this.saveFileAs();
			RemaspEditorController.this.textHasChanged = false;
		}
	}

	class SpeichernAcitonListener implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			if (!RemaspEditorController.this.currentFileName.equals("Unbenannt")) {
				RemaspEditorController.this.saveFile(RemaspEditorController.this.currentFileName);
			} else {
				RemaspEditorController.this.saveFileAs();
			}
			RemaspEditorController.this.textHasChanged = false;
		}
	}

	class NeuActionListener implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			if (RemaspEditorController.this.textHasChanged) {
				int auswahl = JOptionPane.showConfirmDialog((Component) RemaspEditorController.this.remaspView,
						"Dokument wurde noch nicht gespeichert.\nWollen Sie speichern?");
				switch (auswahl) {
				case 0:
					if (!RemaspEditorController.this.currentFileName.equals("Unbenannt")) {
						RemaspEditorController.this.saveFile(RemaspEditorController.this.currentFileName);
						break;
					}
					RemaspEditorController.this.saveFileAs();
					break;

				case 2:
					return;
				}

			}
			RemaspEditorController.this.remaspView.getjTextPane().setText("");
			RemaspEditorController.this.currentFileName = "Unbenannt";
			RemaspEditorController.this.remaspView.setTitle(RemaspEditorController.this.currentFileName + " - ReMaSp Modded");
			RemaspEditorController.this.undo.discardAllEdits();
			RemaspEditorController.this.remaspView.getjMenuItemRueckgaengig().setEnabled(false);
			RemaspEditorController.this.textHasChanged = false;
		}
	}

	class OeffnenActionListener implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			if (RemaspEditorController.this.textHasChanged) {
				int auswahl = JOptionPane.showConfirmDialog((Component) RemaspEditorController.this.remaspView,
						"Dokument wurde noch nicht gespeichert.\nWollen Sie speichern?");
				switch (auswahl) {
				case 0:
					if (!RemaspEditorController.this.currentFileName.equals("Unbenannt")) {
						RemaspEditorController.this.saveFile(RemaspEditorController.this.currentFileName);
						break;
					}
					RemaspEditorController.this.saveFileAs();
					break;

				case 2:
					return;
				}

			}
			JFileChooser dialog = RemaspEditorController.this.remaspView.getjFileChooser();
			if (dialog.showOpenDialog(null) == 0) {
				RemaspEditorController.this.readInFile(dialog.getSelectedFile().getAbsolutePath());

				RemaspEditorController.this.doc = (AbstractDocument) RemaspEditorController.this.remaspView
						.getjTextPane().getStyledDocument();
				RemaspEditorController.this.doc
						.addUndoableEditListener(new RemaspEditorController.MyUndoableEditListener());
				RemaspEditorController.this.undo.discardAllEdits();
				RemaspEditorController.this.remaspView.getjMenuItemRueckgaengig().setEnabled(false);
				RemaspEditorController.this.textHasChanged = false;
			}
		}
	}

	private void saveFileAs() {
		JFileChooser dialog = this.remaspView.getjFileChooser();
		if (dialog.showSaveDialog(null) == 0) {
			saveFile(dialog.getSelectedFile().getAbsolutePath());
			this.textHasChanged = false;
		}
	}

	private void saveFile(String fileName) {
		try {
			FileWriter w = new FileWriter(fileName);
			this.remaspView.getjTextPane().write(w);
			w.close();
			this.currentFileName = fileName;
			this.remaspView.setTitle(this.currentFileName + " - ReMaSp Modded");
			this.textHasChanged = false;
		} catch (IOException e) {
		}
	}

	private void readInFile(String fileName) {
		try {
			FileReader r = new FileReader(fileName);
			this.remaspView.getjTextPane().read(r, null);
			r.close();
			this.currentFileName = fileName;
			this.remaspView.setTitle(this.currentFileName + " - ReMaSp Modded");
		} catch (IOException e) {
			Toolkit.getDefaultToolkit().beep();
			JOptionPane.showMessageDialog((Component) this.remaspView,
					"Remasp kann die folgende Datei nicht finden:  " + fileName);
		}
	}
}

package remasp.controller;

import static remasp.controller.ParsingUtil.erstelleProgrammBefehl;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Element;
import javax.swing.text.Highlighter;
import remasp.model.Addition;
import remasp.model.Befehl;
import remasp.model.Division;
import remasp.model.End;
import remasp.model.ExecutionException;
import remasp.model.Goto;
import remasp.model.JBelowZero;
import remasp.model.JNZero;
import remasp.model.Jzero;
import remasp.model.KeinGueltigerBefehlException;
import remasp.model.KeinGueltigerOperandException;
import remasp.model.Konfiguration;
import remasp.model.Load;
import remasp.model.Multiplikation;
import remasp.model.NegativeZahlenOderBefehleInNichtNegativenKontextException;
import remasp.model.Operand;
import remasp.model.Store;
import remasp.model.Subtraktion;
import remasp.view.RemaspView;
import remasp.view.RemaspTableModel;

public class RemaspMachineController {

	private final RemaspView remaspView;
	private final Konfiguration konfiguration;
	private Timer timerProgramm = null;
	ZeilenFaerber zf;
	TabellenFaerber tf;
	ProgrammAusfuehrerAL programmAusfuehrer;
	ArrayList<Long> registerZwischenSpeicher;

	class ProgrammAusfuehrerAL implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			Befehl aktBefehl = RemaspMachineController.this.konfiguration.getAktuelllerBefehl();
			try {
				aktBefehl.eval(RemaspMachineController.this.konfiguration);
				RemaspMachineController.this.aktualisiereTableView();
				if (RemaspMachineController.this.konfiguration.getBz() != -1) {
					if (RemaspMachineController.this.konfiguration.getBz() >= RemaspMachineController.this.konfiguration
							.getBefehlsSpeicher().size()) {
						throw new Exception("Der letzte Befehl war kein End-Befehl.");
					}
					RemaspMachineController.this.zf.faerbeZeile(Color.yellow,
							RemaspMachineController.this.konfiguration.getAktuelllerBefehl().getStartOffset(),
							RemaspMachineController.this.konfiguration.getAktuelllerBefehl().getEndOffset());
					RemaspMachineController.this.tf.frbeZeile();
				} else {
					String nachricht = "";
					if (aktBefehl instanceof End) {
						nachricht = "Grund: End-Befehl gefunden.";
					} else if (aktBefehl instanceof Division) {
						nachricht = "Grund: Es wurde durch 0 geteilt.";
					} else if (aktBefehl instanceof Store) {
						nachricht = "Grund: Eine indirekte Adresse darf sich beim Store-Befehl nicht auf die Registerzelle 0 beziehen.";
					} else if (aktBefehl instanceof Goto || aktBefehl instanceof JNZero || aktBefehl instanceof Jzero) {
						nachricht = "Die Sprungmarke " + aktBefehl.getOperand().getSprungMarke()
								+ " konnte nicht gefunden werden.";
					}
					RemaspMachineController.this.programmAbbrechen(nachricht, false);
				}
			} catch (BadLocationException ex) {
				RemaspMachineController.this.programmAbbrechen(ex.getLocalizedMessage(), false);
			} catch (ArithmeticException ex) {
				RemaspMachineController.this.programmAbbrechen(
						"Grund: Der Befehl f√ºhrt zu einem arithmetischen √úberlauf in einer Registerzelle.", false);
			} catch (IndexOutOfBoundsException ex) {
				RemaspMachineController.this
						.programmAbbrechen("Es wurde auf eine nicht existierende Registerzelle zugegriffen.", false);
			} catch (Exception ex) {
				RemaspMachineController.this.programmAbbrechen(ex.getLocalizedMessage(), false);
			}
		}
	}

	class StarteProgrammACtionListener implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			try {
				RemaspMachineController.this.transferTableViewToTableModel();
				RemaspMachineController.this.iteriereUeberEditorZeilen();
				if (RemaspMachineController.this.konfiguration.getBefehlsSpeicher().isEmpty()) {
					RemaspMachineController.this.meldungAusgeben("Es konnten keine Befehle gefunden werden.");

					return;
				}
				int zeitProBefehl = ((Integer) RemaspMachineController.this.remaspView.getjSpinnerMilliSekProBefehl()
						.getValue()).intValue();
				RemaspMachineController.this.timerProgramm.setDelay(zeitProBefehl);
				RemaspMachineController.this.timerProgramm.setInitialDelay(zeitProBefehl);
				RemaspMachineController.this.enableGui(false);
				RemaspMachineController.this.remaspView.getjButtonStarteProgramm().setEnabled(false);
				RemaspMachineController.this.remaspView.getjButtonProgrammAbbrechen().setEnabled(true);
				RemaspMachineController.this.remaspView.getjButtonStarteEinzelschrittModus().setEnabled(false);
				RemaspMachineController.this.remaspView.getjButtonEinzelSchritt().setEnabled(false);
				RemaspMachineController.this.timerProgramm.start();
				RemaspMachineController.this.zf.faerbeZeile(Color.yellow,
						RemaspMachineController.this.konfiguration.getAktuelllerBefehl().getStartOffset(),
						RemaspMachineController.this.konfiguration.getAktuelllerBefehl().getEndOffset());
				RemaspMachineController.this.tf.frbeZeile();
			} catch (NumberFormatException nfex) {

				RemaspMachineController.this.programmAbbrechen(nfex.getLocalizedMessage(), true);
			} catch (ExecutionException e) {
				try {
					RemaspMachineController.this.zf.faerbeZeile(Color.RED, e.getStartOffset(), e.getEndOffset());
					RemaspMachineController.this.programmAbbrechen(e.getLocalizedMessage(), true);
				} catch (BadLocationException ex) {
					Logger.getLogger(RemaspMachineController.class.getName()).log(Level.SEVERE, null, ex);
				}
			} catch (Exception ex) {
				RemaspMachineController.this.programmAbbrechen(ex.getLocalizedMessage(), true);
			}
		}
	}

	class StarteEinzelSchirttModusAL implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			try {
				RemaspMachineController.this.transferTableViewToTableModel();
				RemaspMachineController.this.iteriereUeberEditorZeilen();
				if (RemaspMachineController.this.konfiguration.getBefehlsSpeicher().isEmpty()) {
					RemaspMachineController.this.meldungAusgeben("Es konnten keine Befehle gefunden werden.");
					return;
				}
				RemaspMachineController.this.enableGui(false);
				RemaspMachineController.this.remaspView.getjButtonStarteProgramm().setEnabled(false);
				RemaspMachineController.this.remaspView.getjButtonProgrammAbbrechen().setEnabled(true);
				RemaspMachineController.this.remaspView.getjButtonStarteEinzelschrittModus().setEnabled(false);
				RemaspMachineController.this.remaspView.getjButtonEinzelSchritt().setEnabled(true);
				RemaspMachineController.this.zf.faerbeZeile(Color.yellow,
						RemaspMachineController.this.konfiguration.getAktuelllerBefehl().getStartOffset(),
						RemaspMachineController.this.konfiguration.getAktuelllerBefehl().getEndOffset());
				RemaspMachineController.this.tf.frbeZeile();
			} catch (KeinGueltigerBefehlException e) {
				try {
					RemaspMachineController.this.zf.faerbeZeile(Color.RED, e.getStartOffset(), e.getEndOffset());
					RemaspMachineController.this.programmAbbrechen(e.getLocalizedMessage(), true);
					Logger.getLogger(RemaspMachineController.class.getName()).log(Level.INFO, null, e);
				} catch (BadLocationException ex) {
					Logger.getLogger(RemaspMachineController.class.getName()).log(Level.SEVERE, null, ex);
				}
			} catch (KeinGueltigerOperandException e) {
				try {
					RemaspMachineController.this.zf.faerbeZeile(Color.RED, e.getStartOffset(), e.getEndOffset());
					RemaspMachineController.this.programmAbbrechen(e.getLocalizedMessage(), true);
					Logger.getLogger(RemaspMachineController.class.getName()).log(Level.INFO, null, e);
				} catch (BadLocationException ex) {
					Logger.getLogger(RemaspMachineController.class.getName()).log(Level.SEVERE, null, ex);
				}
			} catch (Exception ex) {
				RemaspMachineController.this.programmAbbrechen(ex.getLocalizedMessage(), true);
				Logger.getLogger(RemaspMachineController.class.getName()).log(Level.INFO, null, ex);
			}
		}
	}

	class EinzelSchrittAL implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			RemaspMachineController.this.programmAusfuehrer.actionPerformed(ae);
		}
	}

	private void programmAbbrechen(String nachricht, boolean nur√úbergebeneNachrichtAnzeigen) {
		this.timerProgramm.stop();
		if (nur√úbergebeneNachrichtAnzeigen) {
			meldungAusgeben(nachricht);
		} else {
			meldungAusgeben("Programm ist beendet.\n" + nachricht);
		}

		this.tf.frbeZeile(-1);
		this.zf.allesWei√ü();

		enableGui(true);
		this.remaspView.getjButtonStarteProgramm().setEnabled(true);
		this.remaspView.getjButtonProgrammAbbrechen().setEnabled(false);
		this.remaspView.getjButtonStarteEinzelschrittModus().setEnabled(true);
		this.remaspView.getjButtonEinzelSchritt().setEnabled(false);
		this.konfiguration.setBz(0);
		this.konfiguration.getBefehlsSpeicher().clear();
	}

	class TabellenFaerber {
		void frbeZeile() {
			Operand aktuellerOperand = RemaspMachineController.this.konfiguration.getAktuelllerBefehl().getOperand();
			if (aktuellerOperand != null) {
				if (!aktuellerOperand.getIstKonstante() && !aktuellerOperand.getIstSprungMarke()) {
					int zeilenNr = aktuellerOperand.getRegisterNr();
					RemaspMachineController.this.remaspView.getaCustomCellRenderer().setZuFaerbendeZeile(zeilenNr);
				} else {
					RemaspMachineController.this.remaspView.getaCustomCellRenderer().setZuFaerbendeZeile(-1);
				}
			} else {
				RemaspMachineController.this.remaspView.getaCustomCellRenderer().setZuFaerbendeZeile(-1);
			}
			RemaspMachineController.this.remaspView.getjTable1().repaint();
		}

		void frbeZeile(int zeilenNr) {
			RemaspMachineController.this.remaspView.getaCustomCellRenderer().setZuFaerbendeZeile(zeilenNr);
			RemaspMachineController.this.remaspView.getjTable1().repaint();
		}
	}

	class ProgrammAbbrechenAL implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			RemaspMachineController.this.programmAbbrechen("Benutzerabbruch", false);
		}
	}

	private void transferTableViewToTableModel() throws Exception {
		for (int i = 0; i < this.konfiguration.getRegisters().size(); i++) {
			String aktuellerZellenWert = (this.remaspView.getjTable1().getModel().getValueAt(i, 1) + "").split(" ")[0];
			long value = Long.parseLong(aktuellerZellenWert);
			this.konfiguration.setRegister(i, value);
		}

	}

	private void aktualisiereTableView() {
		for (int i = 0; i < this.konfiguration.getRegisters().size(); i++) {
			long value = Long.valueOf(this.konfiguration.getRegister(i));
			
			this.remaspView.getjTable1().getModel().setValueAt(""+value, i, 1);
		}
	}

	private void setzeAlleRegisterAufNull() {
		for (int i = 0; i < this.konfiguration.getRegisters().size(); i++) {
			this.konfiguration.setRegister(i, 0L);
		}
	}

	private void enableGui(boolean bln) {
		this.remaspView.getButtonDrucken().setEnabled(bln);
		this.remaspView.getButtonNeu().setEnabled(bln);
		this.remaspView.getButtonOeffnen().setEnabled(bln);
		this.remaspView.getButtonSpeichern().setEnabled(bln);
		this.remaspView.getjButtonRegisterZuruecksetzen().setEnabled(bln);
		this.remaspView.getjButtonSetzeNeueAnzahlRegister().setEnabled(bln);
		this.remaspView.getjMenuBearbeiten().setEnabled(bln);
		this.remaspView.getjMenuFormat().setEnabled(bln);
		this.remaspView.getjMenuDatei().setEnabled(bln);
		this.remaspView.getjSpinnerMilliSekProBefehl().setEnabled(bln);
		this.remaspView.getjTextPane().setEditable(bln);
	}

	private void meldungAusgeben(String nachricht) {
		JTextArea anzeigeText = new JTextArea();
		anzeigeText.setText(nachricht);
		Font currentFont = anzeigeText.getFont();
		anzeigeText.setFont(new Font(currentFont.getFontName(), currentFont.getStyle(), currentFont.getSize() + 4));
		JOptionPane.showMessageDialog((Component) this.remaspView, anzeigeText);
	}

	private void iteriereUeberEditorZeilen() throws BadLocationException, Exception {
		Element root = this.remaspView.getjTextPane().getDocument().getDefaultRootElement();

		int anzahlZeilen = root.getElementCount();

		for (int i = 0; i < anzahlZeilen; i++) {
			Element zeilenElement = root.getElement(i);
			int startOffset = zeilenElement.getStartOffset();
			int endOffset = zeilenElement.getEndOffset();

			String zeilenText = root.getDocument().getText(startOffset, endOffset - startOffset);
			erstelleProgrammBefehl(this.konfiguration, zeilenText, startOffset, endOffset);
		}
	}

	private void addActionListeners() {
		this.remaspView.getjButtonStarteProgramm().addActionListener(new StarteProgrammACtionListener());
		this.remaspView.getjButtonRegisterZuruecksetzen().addActionListener(new RegisterAufNullActionListener());
		this.remaspView.getjButtonSetzeNeueAnzahlRegister().addActionListener(new SetzeNeueAnzahlRegisterAL());
		this.remaspView.getjButtonProgrammAbbrechen().addActionListener(new ProgrammAbbrechenAL());
		this.remaspView.getjButtonStarteEinzelschrittModus().addActionListener(new StarteEinzelSchirttModusAL());
		this.remaspView.getjButtonEinzelSchritt().addActionListener(new EinzelSchrittAL());
		this.remaspView.getjButtonRegisterLaden().addActionListener(new RegisterLadenActionListener());
		this.remaspView.getjButtonRegisterSpeichern().addActionListener(new RegisterZwischenspeichernActionListener());
		this.remaspView.getjMenuItemUeber().addActionListener(new UeberActionListener());
	}

	class SetzeNeueAnzahlRegisterAL implements ActionListener {
		String eingabeText;
		int neueAnzahlRegister;

		public void actionPerformed(ActionEvent ae) {
			this.eingabeText = RemaspMachineController.this.remaspView.getjTextFieldNeueAnzahlRegister().getText();

			try {
				this.neueAnzahlRegister = Integer.parseInt(this.eingabeText) + 1;
				if (this.neueAnzahlRegister < 3) {
					throw new Exception("Es m¸ssen mind. 2 Register vorhanden sein.");
				}
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog((Component) RemaspMachineController.this.remaspView,
						"Ung¸ltiges Zahlenformat.\n Bitte nur nat¸rliche Zahlen grˆﬂer 1 eingeben");

				return;
			} catch (Exception ex) {
				JOptionPane.showMessageDialog((Component) RemaspMachineController.this.remaspView,
						"Die eingegebene Zahl muss mindestens 2 groﬂ sein.");

				return;
			}

			RemaspMachineController.this.konfiguration.getRegisters().clear();
			RemaspTableModel tableModel = (RemaspTableModel) RemaspMachineController.this.remaspView.getjTable1()
					.getModel();
			tableModel.generateRows(neueAnzahlRegister);
			RemaspMachineController.this.konfiguration.getRegisters().add(Long.valueOf(0L));
			for (int i = 1; i < this.neueAnzahlRegister; i++) {
				RemaspMachineController.this.konfiguration.getRegisters().add(Long.valueOf(0L));
			}
		}
	}

	class RegisterAufNullActionListener implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			RemaspMachineController.this.setzeAlleRegisterAufNull();
			RemaspMachineController.this.aktualisiereTableView();
		}
	}

	class ZeilenFaerber {
		Highlighter meinHighlighter = RemaspMachineController.this.remaspView.getjTextPane().getHighlighter();

		private void allesWei√ü() {
			this.meinHighlighter.removeAllHighlights();
		}

		private void faerbeZeile(Color eineFarbe, int startOffset, int endOffset) throws BadLocationException {
			this.meinHighlighter.removeAllHighlights();
			this.meinHighlighter.addHighlight(startOffset, endOffset,
					new DefaultHighlighter.DefaultHighlightPainter(eineFarbe));
		}
	}

	public RemaspMachineController(RemaspView remaspView) {
		this.remaspView = remaspView;
		this.konfiguration = new Konfiguration();
		addActionListeners();
		this.zf = new ZeilenFaerber();
		this.tf = new TabellenFaerber();
		this.programmAusfuehrer = new ProgrammAusfuehrerAL();
		this.timerProgramm = new Timer(0, this.programmAusfuehrer);
	}

	private class UeberActionListener implements ActionListener {
		private UeberActionListener() {
		}

		public void actionPerformed(ActionEvent ae) {
			RemaspMachineController.this.meldungAusgeben(
					"ReMaSp: Ein Registermaschinen-Simulationsprogramm.\nCopyright (C) 2017 Norman Sutatyo\nLizenz: GNU GENERAL PUBLIC LICENSE, Version 3.2, 21.08.2018");
		}
	}

	class RegisterZwischenspeichernActionListener implements ActionListener {
		public RegisterZwischenspeichernActionListener() {
			RemaspMachineController.this.registerZwischenSpeicher = new ArrayList<>(100);
		}

		public void actionPerformed(ActionEvent ae) {
			try {
				RemaspMachineController.this.transferTableViewToTableModel();
				RemaspMachineController.this.registerZwischenSpeicher.clear();
				RemaspMachineController.this.registerZwischenSpeicher
						.addAll(RemaspMachineController.this.konfiguration.getRegisters());
				RemaspMachineController.this.remaspView.getjButtonRegisterLaden().setEnabled(true);
			} catch (Exception ex) {
				RemaspMachineController.this.meldungAusgeben(ex.getMessage());
			}
		}
	}

	class RegisterLadenActionListener implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			int minSize = Math.min(RemaspMachineController.this.konfiguration.getRegisters().size(),
					RemaspMachineController.this.registerZwischenSpeicher.size());
			for (int i = 0; i < minSize; i++) {
				RemaspMachineController.this.konfiguration.setRegister(i,
						((Long) RemaspMachineController.this.registerZwischenSpeicher.get(i)).longValue());
			}
			RemaspMachineController.this.aktualisiereTableView();
		}
	}
}

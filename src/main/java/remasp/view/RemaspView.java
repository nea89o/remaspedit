package remasp.view;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.HierarchyEvent;
import java.awt.event.HierarchyListener;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.LayoutStyle;
import javax.swing.SpinnerListModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class RemaspView extends JFrame {
	private JButton buttonDrucken;
	private JButton buttonNeu;
	private JButton buttonOeffnen;
	private JButton buttonSpeichern;
	private JButton jButtonEinzelSchritt;
	private JButton jButtonProgrammAbbrechen;
	private JButton jButtonRegisterLaden;
	private JButton jButtonRegisterSpeichern;
	private JButton jButtonRegisterZuruecksetzen;
	private JButton jButtonSetzeNeueAnzahlRegister;
	private JButton jButtonStarteEinzelschrittModus;
	private JButton jButtonStarteProgramm;
	private JFileChooser jFileChooser;
	private JLabel jLabel3;
	private JLabel jLabel5;

	public JMenuItem getjMenuItemUeber() {
		return this.jMenuItemUeber;
	}

	private JLabel jLabelMilliSekProBefehl;
	private JLabel jLabelRegister;
	private JLabel jLabelTabellenGroesse;
	private JMenu jMenu1;
	private JMenu jMenu2;
	private JMenuBar jMenuBar1;
	private JMenu jMenuBearbeiten;
	private JMenu jMenuDatei;
	private JMenu jMenuFormat;
	private JMenu jMenuHilfe;
	private JMenuItem jMenuItem1;
	private JMenuItem jMenuItemAddRekBsp;
	private JMenuItem jMenuItemAusschneiden;
	private JMenuItem jMenuItemBeenden;
	private JMenuItem jMenuItemDrucken;
	private JMenuItem jMenuItemEditorHilfe;
	private JMenuItem jMenuItemEinfuegen;
	private JMenuItem jMenuItemHTML;
	private JMenuItem jMenuItemIfThenElseBsp;
	private JMenuItem jMenuItemKopieren;
	private JMenuItem jMenuItemListenEndeBsp;
	private JMenuItem jMenuItemMaximumBsp;
	private JMenuItem jMenuItemNeu;
	private JMenuItem jMenuItemOeffnen;
	private JMenuItem jMenuItemRMSteuerung;
	private JMenuItem jMenuItemRueckgaengig;
	private JMenuItem jMenuItemSpeichern;
	private JMenuItem jMenuItemSpeichernUnter;
	private JMenuItem jMenuItemSummeBsp;
	private JMenuItem jMenuItemUeber;
	private JMenuItem jMenuModdedInfo;

	public CustomCellRenderer getaCustomCellRenderer() {
		return this.aCustomCellRenderer;
	}

	private JMenuItem jMenuItemWhileBsp;
	private JScrollPane jScrollPane1;
	private JScrollPane jScrollPane2;
	private JPopupMenu.Separator jSeparator1;
	private JPopupMenu.Separator jSeparator2;
	private JPopupMenu.Separator jSeparator3;
	private JSeparator jSeparator4;
	private JSpinner jSpinnerMilliSekProBefehl;
	private JSpinner jSpinnerTabellenGr;
	private JTable jTable1;
	private JTextField jTextFieldNeueAnzahlRegister;
	private JTextPane jTextPane;
	private JToolBar jToolBar1;
	private JOptionPane meldungsFenster;
	private CustomCellRenderer aCustomCellRenderer;

	public RemaspView() {
		this.aCustomCellRenderer = new CustomCellRenderer();
		initComponents();
	}

	private void initComponents() {
		this.jFileChooser = new JFileChooser();
		this.meldungsFenster = new JOptionPane();
		this.jToolBar1 = new JToolBar();
		this.buttonNeu = new JButton();
		this.buttonOeffnen = new JButton();
		this.buttonSpeichern = new JButton();
		this.buttonDrucken = new JButton();
		this.jScrollPane1 = new JScrollPane();
		this.jTextPane = new JTextPane();
		this.jScrollPane2 = new JScrollPane();
		this.jTable1 = new JTable();
		this.jButtonRegisterZuruecksetzen = new JButton();
		this.jLabel3 = new JLabel();
		this.jButtonStarteProgramm = new JButton();
		this.jButtonSetzeNeueAnzahlRegister = new JButton();
		this.jLabel5 = new JLabel();
		this.jTextFieldNeueAnzahlRegister = new JTextField();
		this.jLabelMilliSekProBefehl = new JLabel();
		this.jSpinnerMilliSekProBefehl = new JSpinner();
		this.jButtonProgrammAbbrechen = new JButton();
		this.jButtonEinzelSchritt = new JButton();
		this.jButtonStarteEinzelschrittModus = new JButton();
		this.jButtonRegisterSpeichern = new JButton();
		this.jButtonRegisterLaden = new JButton();
		this.jLabelTabellenGroesse = new JLabel();
		this.jSpinnerTabellenGr = new JSpinner();
		this.jSeparator4 = new JSeparator();
		this.jLabelRegister = new JLabel();
		this.jMenuBar1 = new JMenuBar();
		this.jMenuDatei = new JMenu();
		this.jMenuItemNeu = new JMenuItem();
		this.jMenuItemOeffnen = new JMenuItem();
		this.jMenuItemSpeichern = new JMenuItem();
		this.jMenuItemSpeichernUnter = new JMenuItem();
		this.jSeparator1 = new JPopupMenu.Separator();
		this.jMenuItemDrucken = new JMenuItem();
		this.jSeparator2 = new JPopupMenu.Separator();
		this.jMenuItemBeenden = new JMenuItem();
		this.jMenuBearbeiten = new JMenu();
		this.jMenuItemRueckgaengig = new JMenuItem();
		this.jSeparator3 = new JPopupMenu.Separator();
		this.jMenuItemAusschneiden = new JMenuItem();
		this.jMenuItemKopieren = new JMenuItem();
		this.jMenuItemEinfuegen = new JMenuItem();
		this.jMenuFormat = new JMenu();
		this.jMenuHilfe = new JMenu();
		this.jMenuItemHTML = new JMenuItem();
		this.jMenu1 = new JMenu();
		this.jMenuItemRMSteuerung = new JMenuItem();
		this.jMenuItem1 = new JMenuItem();
		this.jMenuItemEditorHilfe = new JMenuItem();
		this.jMenu2 = new JMenu();
		this.jMenuItemWhileBsp = new JMenuItem();
		this.jMenuItemIfThenElseBsp = new JMenuItem();
		this.jMenuItemAddRekBsp = new JMenuItem();
		this.jMenuItemSummeBsp = new JMenuItem();
		this.jMenuItemListenEndeBsp = new JMenuItem();
		this.jMenuItemMaximumBsp = new JMenuItem();
		this.jMenuItemUeber = new JMenuItem();
		this.jMenuModdedInfo = new JMenuItem();

		setDefaultCloseOperation(3);
		setTitle("ReMaSp");

		this.jToolBar1.setFloatable(false);
		this.jToolBar1.setRollover(true);

		this.buttonNeu.setIcon(new ImageIcon(getClass().getResource("/remasp/images/New24.gif")));
		this.buttonNeu.setToolTipText("Erstelle neues Dokument");
		this.buttonNeu.setFocusable(false);
		this.buttonNeu.setHorizontalTextPosition(0);
		this.buttonNeu.setVerticalTextPosition(3);
		this.jToolBar1.add(this.buttonNeu);

		this.buttonOeffnen.setIcon(new ImageIcon(getClass().getResource("/remasp/images/Open24.gif")));
		this.buttonOeffnen.setToolTipText("Öffne Dokument");
		this.buttonOeffnen.setFocusable(false);
		this.buttonOeffnen.setHorizontalTextPosition(0);
		this.buttonOeffnen.setVerticalTextPosition(3);
		this.jToolBar1.add(this.buttonOeffnen);

		this.buttonSpeichern.setIcon(new ImageIcon(getClass().getResource("/remasp/images/Save24.gif")));
		this.buttonSpeichern.setToolTipText("Speichere aktuelles Dokument");
		this.buttonSpeichern.setFocusable(false);
		this.buttonSpeichern.setHorizontalTextPosition(0);
		this.buttonSpeichern.setVerticalTextPosition(3);
		this.jToolBar1.add(this.buttonSpeichern);

		this.buttonDrucken.setIcon(new ImageIcon(getClass().getResource("/remasp/images/Print24.gif")));
		this.buttonDrucken.setToolTipText("Drucke aktuelles Dokument");
		this.buttonDrucken.setFocusable(false);
		this.buttonDrucken.setHorizontalTextPosition(0);
		this.buttonDrucken.setVerticalTextPosition(3);
		this.jToolBar1.add(this.buttonDrucken);

		this.jTextPane.setFont(new Font("Consolas", 0, 14));
		this.jScrollPane1.setViewportView(this.jTextPane);
		this.jTable1.setFont(new Font("Consolas", 0, 18));

		this.jTable1.setModel(new RemaspTableModel());
		this.jTable1.setDefaultEditor(Long.class, new RemaspCellEditor());

		this.jTable1.setToolTipText(
				"<html>\nIn der rechten Spalte kann die Registerbelegung vorgenommen werden.<br>\nEs können nur natürliche Zahlen reingeschrieben werden. <br>\nDie Eingabe muss zweimal mit der Enter-Taste bestätigt werden.");
		this.jTable1.setGridColor(Color.black);
		this.jTable1.setRowHeight(20);
		this.jTable1.getTableHeader().setReorderingAllowed(false);
		this.jTable1.setUpdateSelectionOnSort(false);
		this.jScrollPane2.setViewportView(this.jTable1);
		if (this.jTable1.getColumnModel().getColumnCount() > 0) {
			this.jTable1.getColumnModel().getColumn(0).setMinWidth(1);
			this.jTable1.getColumnModel().getColumn(0).setPreferredWidth(80);
			this.jTable1.getColumnModel().getColumn(0).setCellRenderer(this.aCustomCellRenderer);
			this.jTable1.getColumnModel().getColumn(1).setCellRenderer(this.aCustomCellRenderer);
		}

		this.jButtonRegisterZuruecksetzen.setFont(new Font("Tahoma", 0, 18));
		this.jButtonRegisterZuruecksetzen.setText("Register auf 0 setzen");
		this.jButtonRegisterZuruecksetzen.setToolTipText("Setzt alle Register auf 0.");

		this.jLabel3.setFont(new Font("Tahoma", 0, 18));
		this.jLabel3.setText("Maschinenkonfiguration");

		this.jButtonStarteProgramm.setFont(new Font("Tahoma", 0, 18));
		this.jButtonStarteProgramm.setText("Starte Programm");
		this.jButtonStarteProgramm.setToolTipText(
				"<html> Hier klicken um das Programm zu starten. <br>\nDas Programm läuft automatisch durch.");
		this.jButtonStarteProgramm.setFocusable(false);
		this.jButtonStarteProgramm.setHorizontalTextPosition(0);
		this.jButtonStarteProgramm.setVerticalTextPosition(3);

		this.jButtonSetzeNeueAnzahlRegister.setFont(new Font("Tahoma", 0, 18));
		this.jButtonSetzeNeueAnzahlRegister.setText("Setze Anzahl Register");
		this.jButtonSetzeNeueAnzahlRegister.setToolTipText(
				"<html>\nSetzt die Anzahl der Register. <br>\nAlle Register werden mit 0 initialisiert. <br>\nGegebenenfalls den Registerinhalt zwischenspeichern.<br>\nDie Anzahl der Register sollte nicht über Hunderttausend liegen.");

		this.jLabel5.setFont(new Font("Tahoma", 0, 18));
		this.jLabel5.setText("Maschinensteuerung");

		this.jTextFieldNeueAnzahlRegister.setFont(new Font("Tahoma", 0, 18));
		this.jTextFieldNeueAnzahlRegister.setText("100");

		this.jLabelMilliSekProBefehl.setFont(new Font("Tahoma", 0, 18));
		this.jLabelMilliSekProBefehl.setText("Millisekunden pro Befehl");

		this.jSpinnerMilliSekProBefehl.setFont(new Font("Tahoma", 0, 18));
		this.jSpinnerMilliSekProBefehl.setModel(
				new SpinnerNumberModel(Integer.valueOf(1000), Integer.valueOf(1), null, Integer.valueOf(100)));

		this.jButtonProgrammAbbrechen.setFont(new Font("Tahoma", 0, 18));
		this.jButtonProgrammAbbrechen.setText("Programm abbrechen");
		this.jButtonProgrammAbbrechen.setToolTipText("<html>\nHier klicken um das Programm abzubrechen.");
		this.jButtonProgrammAbbrechen.setEnabled(false);

		this.jButtonEinzelSchritt.setIcon(new ImageIcon(getClass().getResource("/remasp/images/arrow.gif")));
		this.jButtonEinzelSchritt.setToolTipText("<html>\nHier klicken um den nächsten Befehl auszuführen.");
		this.jButtonEinzelSchritt.setEnabled(false);

		this.jButtonStarteEinzelschrittModus.setFont(new Font("Tahoma", 0, 18));
		this.jButtonStarteEinzelschrittModus.setText("Starte Einzelschrittmodus");
		this.jButtonStarteEinzelschrittModus.setToolTipText(
				"<html>\nHier klicken um das Programm im Einzelschritt-Modus zu starten. <br>\nMit der unteren Pfeiltaste werden dann die einzelnen Befehle manuell ausgeführt.");

		this.jButtonRegisterSpeichern.setFont(new Font("Tahoma", 0, 18));
		this.jButtonRegisterSpeichern.setText("Register zwischenspeichern");
		this.jButtonRegisterSpeichern.setToolTipText(
				"<html>\nSpeichert den aktuellen Inhalt aller Register zwischen. <br>\n(Der Register-Zwischenspeicher wird bei Programmende mit gelöscht.)");

		this.jButtonRegisterLaden.setFont(new Font("Tahoma", 0, 18));
		this.jButtonRegisterLaden.setText("Register laden");
		this.jButtonRegisterLaden.setToolTipText(
				"<html>\nLädt den zuletzt gespeicherten Registerinhalt aus dem Zwischenspeicher. <br>\nFalls nach dem letzten Speichervorgang die Anzahl der Register erhöht wurde (von n auf n+k), <br>\nso behalten die neu hinzugekommenen Register (Register n+1 bis n+k) ihren aktuellen Wert <br>\nund die \"alten\" Register (Register 1 bis n) werden durch die Registerinhalte des Zwischenspeichers ersetzt.<br>\nFalls nach dem letzten Speichervorgang die Anzahl der Register erniedrigt wurde (von n auf n-k),<br>\nso werden die Register 1 bis n-k durch den Registerinhalt des Zwischenspeichers ersetzt.");
		this.jButtonRegisterLaden.setEnabled(false);

		this.jLabelTabellenGroesse.setFont(new Font("Tahoma", 0, 18));
		this.jLabelTabellenGroesse.setText("Schriftgröße");

		this.jSpinnerTabellenGr.setFont(new Font("Tahoma", 0, 18));
		this.jSpinnerTabellenGr
				.setModel(new SpinnerListModel((Object[]) new String[] { "Klein", "Mittel", "Groß", "Monströs" }));
		this.jSpinnerTabellenGr.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent evt) {
				RemaspView.this.jSpinnerTabellenGrStateChanged(evt);
			}
		});
		this.jSpinnerTabellenGr.setValue("Mittel");

		this.jLabelRegister.setFont(new Font("Tahoma", 0, 18));
		this.jLabelRegister.setHorizontalAlignment(0);
		this.jLabelRegister.setText("Register");

		this.jMenuDatei.setText("Datei");
		this.jMenuDatei.setFont(new Font("Segoe UI", 0, 16));

		this.jMenuItemNeu.setAccelerator(KeyStroke.getKeyStroke(78, 2));
		this.jMenuItemNeu.setText("Neu");
		this.jMenuDatei.add(this.jMenuItemNeu);

		this.jMenuItemOeffnen.setAccelerator(KeyStroke.getKeyStroke(79, 2));
		this.jMenuItemOeffnen.setText("Öffnen...");
		this.jMenuDatei.add(this.jMenuItemOeffnen);

		this.jMenuItemSpeichern.setAccelerator(KeyStroke.getKeyStroke(83, 2));
		this.jMenuItemSpeichern.setText("Speichern");
		this.jMenuDatei.add(this.jMenuItemSpeichern);

		this.jMenuItemSpeichernUnter.setText("Speichern unter...");
		this.jMenuDatei.add(this.jMenuItemSpeichernUnter);
		this.jMenuDatei.add(this.jSeparator1);

		this.jMenuItemDrucken.setAccelerator(KeyStroke.getKeyStroke(80, 2));
		this.jMenuItemDrucken.setText("Drucken...");
		this.jMenuDatei.add(this.jMenuItemDrucken);
		this.jMenuDatei.add(this.jSeparator2);

		this.jMenuItemBeenden.setText("Beenden");
		this.jMenuDatei.add(this.jMenuItemBeenden);

		this.jMenuBar1.add(this.jMenuDatei);

		this.jMenuBearbeiten.setText("Bearbeiten");
		this.jMenuBearbeiten.setFont(new Font("Segoe UI", 0, 16));

		this.jMenuItemRueckgaengig.setAccelerator(KeyStroke.getKeyStroke(90, 2));
		this.jMenuItemRueckgaengig.setText("Rückgängig");
		this.jMenuBearbeiten.add(this.jMenuItemRueckgaengig);
		this.jMenuBearbeiten.add(this.jSeparator3);

		this.jMenuItemAusschneiden.setAccelerator(KeyStroke.getKeyStroke(88, 2));
		this.jMenuItemAusschneiden.setText("Ausschneiden");
		this.jMenuBearbeiten.add(this.jMenuItemAusschneiden);

		this.jMenuItemKopieren.setAccelerator(KeyStroke.getKeyStroke(67, 2));
		this.jMenuItemKopieren.setText("Kopieren");
		this.jMenuBearbeiten.add(this.jMenuItemKopieren);

		this.jMenuItemEinfuegen.setAccelerator(KeyStroke.getKeyStroke(86, 2));
		this.jMenuItemEinfuegen.setText("Einfügen");
		this.jMenuBearbeiten.add(this.jMenuItemEinfuegen);

		this.jMenuBar1.add(this.jMenuBearbeiten);

		this.jMenuFormat.setText("Format");
		this.jMenuFormat.setFont(new Font("Segoe UI", 0, 16));
		this.jMenuBar1.add(this.jMenuFormat);

		this.jMenuHilfe.setText("Hilfe");
		this.jMenuHilfe.setFont(new Font("Segoe UI", 0, 16));

		this.jMenuItemHTML.setText("Hilfe (im HTML Format)");
		this.jMenuItemHTML.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				RemaspView.this.jMenuItemHTMLActionPerformed(evt);
			}
		});
		this.jMenuHilfe.add(this.jMenuItemHTML);

		this.jMenu1.setText("Hilfe (einfaches Text Format)");

		this.jMenuItemRMSteuerung.setText("RM Steuerung");
		this.jMenuItemRMSteuerung.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				RemaspView.this.jMenuItemRMSteuerungActionPerformed(evt);
			}
		});
		this.jMenu1.add(this.jMenuItemRMSteuerung);

		this.jMenuItem1.setText("Befehlsübersicht");
		this.jMenuItem1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				RemaspView.this.jMenuItem1ActionPerformed(evt);
			}
		});
		this.jMenu1.add(this.jMenuItem1);

		this.jMenuItemEditorHilfe.setText("Editorbedienung");
		this.jMenuItemEditorHilfe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				RemaspView.this.jMenuItemEditorHilfeActionPerformed(evt);
			}
		});
		this.jMenu1.add(this.jMenuItemEditorHilfe);

		this.jMenuHilfe.add(this.jMenu1);

		this.jMenu2.setText("Beispielprogramme");

		this.jMenuItemWhileBsp.setText("While-Schleife");
		this.jMenuItemWhileBsp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				RemaspView.this.jMenuItemWhileBspActionPerformed(evt);
			}
		});
		this.jMenu2.add(this.jMenuItemWhileBsp);

		this.jMenuItemIfThenElseBsp.setText("If-Then-Else");
		this.jMenuItemIfThenElseBsp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				RemaspView.this.jMenuItemIfThenElseBspActionPerformed(evt);
			}
		});
		this.jMenu2.add(this.jMenuItemIfThenElseBsp);

		this.jMenuItemAddRekBsp.setText("rekursive Addition");
		this.jMenuItemAddRekBsp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				RemaspView.this.jMenuItemAddRekBspActionPerformed(evt);
			}
		});
		this.jMenu2.add(this.jMenuItemAddRekBsp);

		this.jMenuItemSummeBsp.setText("Summe zweier Zahlen");
		this.jMenuItemSummeBsp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				RemaspView.this.jMenuItemSummeBspActionPerformed(evt);
			}
		});
		this.jMenu2.add(this.jMenuItemSummeBsp);

		this.jMenuItemListenEndeBsp.setText("Listen Ende");
		this.jMenuItemListenEndeBsp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				RemaspView.this.jMenuItemListenEndeBspActionPerformed(evt);
			}
		});
		this.jMenu2.add(this.jMenuItemListenEndeBsp);

		this.jMenuItemMaximumBsp.setText("Maximum");
		this.jMenuItemMaximumBsp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				RemaspView.this.jMenuItemMaximumBspActionPerformed(evt);
			}
		});
		this.jMenu2.add(this.jMenuItemMaximumBsp);

		this.jMenuHilfe.add(this.jMenu2);

		this.jMenuItemUeber.setText("Über");
		this.jMenuHilfe.add(this.jMenuItemUeber);
		
		this.jMenuModdedInfo.setText("Modding Info");
		this.jMenuModdedInfo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				RemaspView.this.jMenuItemModdingInfoActionPerformed(e);				
			}
		});
		this.jMenuHilfe.add(this.jMenuModdedInfo);

		this.jMenuBar1.add(this.jMenuHilfe);

		setJMenuBar(this.jMenuBar1);

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addGroup(layout.createSequentialGroup().addComponent(this.jToolBar1, -2, 225, -2)
										.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED, 285, 32767)
										.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
												.addComponent(this.jLabelRegister, -2, 289, -2)
												.addGroup(layout.createSequentialGroup().addGap(28, 28, 28)
														.addComponent(this.jLabelTabellenGroesse)
														.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
														.addComponent(this.jSpinnerTabellenGr, -2, 112, -2)))
										.addGap(346, 346, 346))
								.addGroup(layout.createSequentialGroup().addComponent(this.jScrollPane1)
										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(this.jScrollPane2, -2, 260, -2).addGap(18, 18, 18)
										.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
												.addComponent(this.jLabel5)
												.addComponent(this.jButtonRegisterZuruecksetzen)
												.addComponent(this.jButtonStarteEinzelschrittModus)
												.addComponent(this.jButtonStarteProgramm)
												.addComponent(this.jButtonProgrammAbbrechen)
												.addGroup(layout.createSequentialGroup()
														.addComponent(this.jButtonSetzeNeueAnzahlRegister)
														.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
														.addComponent(this.jTextFieldNeueAnzahlRegister, -2, 64, -2))
												.addComponent(this.jButtonRegisterSpeichern)
												.addComponent(this.jButtonRegisterLaden)
												.addComponent(this.jButtonEinzelSchritt, -2, 85, -2)
												.addComponent(this.jLabel3)
												.addGroup(layout.createSequentialGroup()
														.addComponent(this.jLabelMilliSekProBefehl)
														.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
														.addComponent(this.jSpinnerMilliSekProBefehl, -2, 87, -2))
												.addComponent(this.jSeparator4, -2, 328, -2))))
						.addContainerGap()));

		layout.setVerticalGroup(
				layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
										.addGroup(layout.createSequentialGroup()
												.addComponent(this.jToolBar1, -2, 34, -2).addGap(19, 19, 19))
										.addGroup(layout.createSequentialGroup()
												.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
														.addComponent(this.jSpinnerTabellenGr)
														.addComponent(this.jLabelTabellenGroesse, -2, 29, -2))
												.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(this.jLabelRegister).addGap(1, 1, 1)))
								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
										.addComponent(this.jScrollPane2, GroupLayout.Alignment.TRAILING, -1, 635, 32767)
										.addComponent(this.jScrollPane1)
										.addGroup(layout.createSequentialGroup().addComponent(this.jLabel5)
												.addGap(18, 18, 18).addComponent(this.jButtonStarteProgramm)
												.addGap(18, 18, 18).addComponent(this.jButtonStarteEinzelschrittModus)
												.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
												.addComponent(this.jButtonEinzelSchritt).addGap(19, 19, 19)
												.addComponent(this.jButtonProgrammAbbrechen).addGap(8, 8, 8)
												.addComponent(this.jSeparator4, -2, 10, -2)
												.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(this.jLabel3)
												.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
												.addComponent(this.jButtonRegisterSpeichern)
												.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(this.jButtonRegisterLaden)
												.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(this.jButtonRegisterZuruecksetzen).addGap(18, 18, 18)
												.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
														.addComponent(this.jButtonSetzeNeueAnzahlRegister)
														.addComponent(this.jTextFieldNeueAnzahlRegister, -2, -1, -2))
												.addGap(62, 62, 62)
												.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
														.addComponent(this.jLabelMilliSekProBefehl)
														.addComponent(this.jSpinnerMilliSekProBefehl, -2, -1, -2))
												.addGap(0, 0, 32767)))
								.addContainerGap()));

		pack();
	}


	private void jSpinnerTabellenGrStateChanged(ChangeEvent evt) {
		String groesse = (String) this.jSpinnerTabellenGr.getValue();
		if (groesse.equals("Klein")) {
			this.jTable1.setFont(new Font("Consolas", 0, 14));
			this.jTable1.setRowHeight(20);
			((JTextField) this.jTable1.getDefaultEditor(Long.class)).setFont(new Font("Consolas", 0, 14));

			Font currentFont = this.jTextPane.getFont();
			this.jTextPane.setFont(new Font(currentFont.getFontName(), currentFont.getStyle(), 14));
			this.jTextPane.setText(this.jTextPane.getText());
		}

		if (groesse.equals("Mittel")) {
			this.jTable1.setFont(new Font("Consolas", 0, 18));
			this.jTable1.setRowHeight(23);
			((JTextField) this.jTable1.getDefaultEditor(Long.class)).setFont(new Font("Consolas", 0, 18));

			Font currentFont = this.jTextPane.getFont();
			this.jTextPane.setFont(new Font(currentFont.getFontName(), currentFont.getStyle(), 18));
			this.jTextPane.setText(this.jTextPane.getText());
		}
		if (groesse.equals("Groß")) {
			this.jTable1.setFont(new Font("Consolas", 0, 24));
			this.jTable1.setRowHeight(28);
			((JTextField) this.jTable1.getDefaultEditor(Long.class)).setFont(new Font("Consolas", 0, 24));

			Font currentFont = this.jTextPane.getFont();
			this.jTextPane.setFont(new Font(currentFont.getFontName(), currentFont.getStyle(), 24));
			this.jTextPane.setText(this.jTextPane.getText());
		}

		if (groesse.equals("Monströs")) {
			this.jTable1.setFont(new Font("Consolas", 0, 50));
			this.jTable1.setRowHeight(45);
			((JTextField) this.jTable1.getDefaultEditor(Long.class)).setFont(new Font("Consolas", 0, 50));

			Font currentFont = this.jTextPane.getFont();
			this.jTextPane.setFont(new Font(currentFont.getFontName(), currentFont.getStyle(), 50));
			this.jTextPane.setText(this.jTextPane.getText());
		}
	}

	private void jMenuItemRMSteuerungActionPerformed(ActionEvent evt) {
		final JTextPane anzeigeText = new JTextPane();
		anzeigeText.setContentType("text/html");
		anzeigeText.setText(
				"<html><h3>Allgemeine Anmerkungen</h3>\n<strong>Sprungmarken</strong>: Am Anfang jeder Befehlszeile kann eine Sprungmarke gesetzt werden. Der Name der Sprungmarke muss eindeutig unter allen Sprungmarkennamen sein und muss mit einem Doppelpunkt enden. Der Doppelpunkt selber gehört nicht zum Sprungmarkennamen.<br/>\nBsp.: <br>\n<code>\n...<br>\nSchleifenAnfang: Add 3 <br>\n....<br>\nGoto SchleifenAnfang<br>\n...\n </code> <br><strong>Groß- und Kleinschreibung</strong>: Befehlsnamen sind nicht \"case sensitive\". Es wird also nicht zwischen Groß- und Kleinschreibung unterschieden. <br>\nBsp.: </br>\nDie Wörter <code>Goto, goto oder auch GOTO </code> stellen alle den gleichen Befehl dar.<br><strong>Wertebereich der Register</strong>: Die größte natürliche Zahl, die ein Register speichern kann, ist 9223372036854775807 (=2^63 - 1). Wird im Programm versucht eine größere Zahl zu speichern, so wird das Programm automatisch abgebrochen.</br><h3>Maschinensteuerung</h3>\n        Über den Button <strong>\"Starte Programm\"</strong> wird das sich im Editor befindende Programm ausgeführt. Die Ausführungsgeschwindigkeit kann im Regler \"Millisekunden pro Befehl\" eingestellt werden.<br>\n        Über den Button <strong>\"Starte Einzelschrittmodus\"</strong> wird der Einzelschrittmodus aktiviert. In diesem Modus wird der aktuelle Befehl ausgeführt, falls der zugehörige Pfeilbutton betätigt wird. <br>\n        Über den Button <strong>\"Programm abbrechen\"</strong> wird die aktuelle Programmausführung abgebrochen. <br>\n\n        \n        <h3>Maschinenkonfiguration</h3>\n        <strong>\"Register zwischenspeichern\"</strong> speichert die aktuelle Registerbelegung zwischen. <br>\n        Über den Button <strong>\"Register laden\"</strong> kann eine zuvor gespeicherte Registerbelegung geladen werden. <br>\n        Über den Button <strong>\"Register auf 0 setzen\"</strong> werden alle Register auf 0 gesetzt. <br>\n        Über den Button <strong>\"Setze Anzahl Register\"</strong> kann die Anzahl der Register gesetzt werden. Es ist dabei zu beachten, \n        dass bei diesem Vorgang die Registerinhalte gelöscht werden, danach sind also alle Register mit 0 initialisiert. Die Anzahl der Register sollte nicht höher als 500.000 sein. <br>\n</html>");

		Font currentFont = anzeigeText.getFont();
		anzeigeText.setFont(new Font(currentFont.getFontName(), currentFont.getStyle(), currentFont.getSize() + 8));
		JScrollPane scrollPane = new JScrollPane(anzeigeText);
		anzeigeText.addHierarchyListener(new HierarchyListener() {
			public void hierarchyChanged(HierarchyEvent e) {
				Window window = SwingUtilities.getWindowAncestor(anzeigeText);
				if (window instanceof Dialog) {
					Dialog dialog = (Dialog) window;
					if (!dialog.isResizable()) {
						dialog.setResizable(true);
					}
				}
			}
		});

		scrollPane.setPreferredSize(new Dimension(1000, 500));
		scrollPane.setMinimumSize(new Dimension(10, 10));
		scrollPane.setVisible(true);
		JOptionPane.showMessageDialog(this, scrollPane);
	}

	private void jMenuItem1ActionPerformed(ActionEvent evt) {
		final JTextPane anzeigeText = new JTextPane();
		anzeigeText.setContentType("text/html");
		anzeigeText.setText(
				"<html><strong>LOAD #k</strong>: Lädt die Konstante k in den Akkumulator. <br><strong>LOAD i</strong>: Lädt den Inhalt von Register i in den Akkumulator.<br><strong>LOAD *i</strong>: Lädt den Inhalt von demjenigen Register in den Akkumulator, auf welches Register i zeigt (indirekte Adressierung).<br><strong>STORE i</strong>: Speichert den Inhalt des Akkumulators in Register i.<br><strong>STORE *i</strong>: Speichert den Inhalt des Akkumulators in das Register, worauf Register i zeigt (indirekte Adressierung). Register i darf hier nicht den Inhalt 0 haben, ansonsten wird das Programm abgebrochen.<br><strong>GOTO Marke</strong>: Die Befehlszeile, die mit 'Marke:' anfängt, wird als nächstes ausgeführt.<br><strong>JZERO Marke</strong>: Wenn der Inhalt des Akkukulators 0 ist, wird zu der Befehlszeile gesprungen, welche mit 'Marke:' anfängt.<br><strong>JNZERO Marke</strong>: Wenn der Inhalt des Akkukulators ungleich 0 ist, wird zu der Befehlszeile gesprungen, welche mit 'Marke:' anfängt.<br><strong>END</strong>: Das Programm wird beendet.<br> <br>Bei den folgenden Befehlen kann der Operand x eine Konstante (#k), eine Registernummer (i) oder auch eine indirekte Adresse (*i) bezeichnen.<br><br><strong>ADD x</strong>: Addiert zum Akkumulator den Wert des Operanden x hinzu.<br><strong>SUB x</strong>: Zieht vom Akkumulator den Wert des Operanden x ab. Falls das Ergebnis negativ sein sollte, wird der Akkumulator auf 0 gesetzt.<br><strong>MUL x</strong>: Multipliziert den Akkumulatorinhalt mit dem Wert des Operanden x.<br><strong>DIV x</strong>: Division: Der Akkumulatorinhalt wird durch den Wert von x dividiert. Falls der Wert von x 0 sein sollte, wird das Programm abgebrochen.</html>");

		Font currentFont = anzeigeText.getFont();
		anzeigeText.setFont(new Font(currentFont.getFontName(), currentFont.getStyle(), currentFont.getSize() + 8));
		JScrollPane scrollPane = new JScrollPane(anzeigeText);
		anzeigeText.addHierarchyListener(new HierarchyListener() {
			public void hierarchyChanged(HierarchyEvent e) {
				Window window = SwingUtilities.getWindowAncestor(anzeigeText);
				if (window instanceof Dialog) {
					Dialog dialog = (Dialog) window;
					if (!dialog.isResizable()) {
						dialog.setResizable(true);
					}
				}
			}
		});

		scrollPane.setPreferredSize(new Dimension(1000, 500));
		scrollPane.setMinimumSize(new Dimension(10, 10));
		scrollPane.setVisible(true);
		JOptionPane.showMessageDialog(this, scrollPane);
	}

	private void jMenuItemModdingInfoActionPerformed(ActionEvent e) {
		JTextPane moddingInfoText = new JTextPane();
		moddingInfoText.setContentType("text/html");
		moddingInfoText.setText(
				"<html>"
				+ "<h3>Diese JAR wurde gemodded um einige Features hinzuzufügen.</h3>"
				+ "<div>"
				+ "<h4>Verbesserte Register</h4>"
				+ "<ul>"
				+ "<li>Register Zeigen das ASCII-Zeichen für den entsprechenden Wert an</li>"
				+ "</ul>"
				+ "</div>"
				+ "<div>"
				+ "<h4>Negative Zahlen</h4>"
				+ "<ul>"
				+ "<li>Die Instruktion <code>ALLOWNEG</code> aktiviert negative Zahlen (per default deaktiviert)</li>"
				+ "<li><code>JNEG</code> - Ein Jump falls der Akkumulator negativ ist.</li>"
				+ "</ul>"
				+ "</div>"
				+ ""
				+ "<div>"
				+ "Der Sourcecode kann auf https://github.com/romangraef/remaspedit gefunden werden.</a>"
				+ "</div>"
				+ "</html>");
		Font currentFont = moddingInfoText.getFont();
		moddingInfoText.setFont(new Font(currentFont.getFontName(), currentFont.getStyle(), currentFont.getSize() + 8));

		JScrollPane scrollPane = new JScrollPane(moddingInfoText);
		moddingInfoText.addHierarchyListener(new HierarchyListener() {
			public void hierarchyChanged(HierarchyEvent e) {
				Window window = SwingUtilities.getWindowAncestor(moddingInfoText);
				if (window instanceof Dialog) {
					Dialog dialog = (Dialog) window;
					if (!dialog.isResizable()) {
						dialog.setResizable(true);
					}
				}
			}
		});

		scrollPane.setPreferredSize(new Dimension(1000, 500));
		scrollPane.setMinimumSize(new Dimension(10, 10));
		scrollPane.setVisible(true);
		JOptionPane.showMessageDialog(this, scrollPane);

	}
	private void jMenuItemEditorHilfeActionPerformed(ActionEvent evt) {
		final JTextPane anzeigeText2 = new JTextPane();
		anzeigeText2.setContentType("text/html");
		anzeigeText2.setText(
				"<html><h3>Datei-<em>Neu</em><a name=\"Datei-Neu\"></a></h3>\nErstellt ein neues, leeres Dokument. Gegebenenfalls wird gefragt, ob das aktuelle Dokument gespeichert werden soll.\n<br>Tastenkürzel: Strg+n\n\n<h3>Datei-<em>Öffnen</em><a name=\"Datei-Öffnen\"></a></h3>\nLädt eine beliebige Textdatei im Editor.\n<br>Tastenkürzel: Strg+o\n\n<h3>Datei-<em>Speichern</em> <a name=\"Datei-Speichern\"></a></h3>\nSpeichert das aktuelle Dokument. Falls noch kein Dateiname vergeben ist, wird nach einem gefragt.\n<br>Tastenkürzel: Strg+s\n\n<h3>Datei-<em>Speichern unter</em> <a name=\"Datei-SpeichernUnter\"></a></h3>\nSpeichert das Dokument unter einem angegebenen Namen.\n\n<h3>Datei-<em>Drucken</em> <a name=\"Datei-Drucken\"></a></h3>\nDie Datei kann über die im System vorhandenen Drucker gedruckt werden.\n<br>Tastenkürzel: Strg+p\n\n<h3>Datei-<em>Beenden</em> <a name=\"Datei-Beenden\"></a></h3>\nBeendet die komplette Simulationsumgebung.\n\n<h3>Bearbeiten-<em>Rückgängig</em> <a name=\"Bearbeiten-Rückgängig\"></a></h3>\nMacht die zuletzt getätigte Änderung im Editor rückgängig.\n<br>Tastenkürzel: Strg+z.\n\n<h3>Bearbeiten-<em>Ausschneiden</em>  <a name=\"Bearbeiten-Ausschneiden\"></a></h3>\nSchneidet die markierte Textstelle aus und kopiert sie in die Zwischenablage.\n<br>Tastenkürzel: Strg+x\n\n<h3> Bearbeiten-<em>Kopieren</em> <a name=\"Bearbeiten-Kopieren\"></a></h3>\nKopiert die markierte Textstelle in die Zwischenablage.\n<br>Tastenkürzel: Strg+c\n\n<h3> Bearbeiten-<em>Einfügen</em> <a name=\"Bearbeiten-Einfügen\"></a></h3>\nFügt den Inhalt der Zwischenablage an der aktuellen Cursorposition ein. Es kann nur Text eingefügt werden.\n<br>Tastenkürzel: Strg+v\n\n<h3>Format-<em>Fett, Kursiv, Unterstrichen</em>  <a name=\"Format-Schriftausrichtung\"></a></h3>\nFormatiert den ausgewählten Text fett, kursiv oder unterstrichen.\n\n<h3> Format-<em>Serif, SansSerif</em><a name=\"Format-Schriftart\"></a></h3>\nAls Schriftarten stehen Serif und SansSerif zur Verfügung.\n\n<h3>Format-<em>Rot, Grün, Blau, Schwarz</em> <a name=\"Format-Farbe\"></a></h3>\nLässt den ausgewählten Text in der entsprechenden Farbe erscheinen.\n\n\n</html>");

		Font currentFont = anzeigeText2.getFont();
		anzeigeText2.setFont(new Font(currentFont.getFontName(), currentFont.getStyle(), currentFont.getSize() + 8));

		JScrollPane scrollPane = new JScrollPane(anzeigeText2);
		anzeigeText2.addHierarchyListener(new HierarchyListener() {
			public void hierarchyChanged(HierarchyEvent e) {
				Window window = SwingUtilities.getWindowAncestor(anzeigeText2);
				if (window instanceof Dialog) {
					Dialog dialog = (Dialog) window;
					if (!dialog.isResizable()) {
						dialog.setResizable(true);
					}
				}
			}
		});

		scrollPane.setPreferredSize(new Dimension(1000, 500));
		scrollPane.setMinimumSize(new Dimension(10, 10));
		scrollPane.setVisible(true);
		JOptionPane.showMessageDialog(this, scrollPane);
	}

	private void jMenuItemHTMLActionPerformed(ActionEvent evt) {
		try {
			Path pathToHilfeDatei = Paths.get("Files/HilfeDatei.html", new String[0]);
			URI uri = pathToHilfeDatei.toUri();

			Desktop desktop = Desktop.getDesktop();

			desktop.browse(uri);
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(this,
					"Die Hilfe-Datei konnte nicht gefunden werden. \nStellen Sie sicher, dass der Ordner <<Files>>, in dem sich die Datei <<HilfeDatei.html>> befindet, \nim gleichen Ordner wie die remasp.jar Datei ist. ");

			Logger.getLogger(RemaspView.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private void jMenuItemWhileBspActionPerformed(ActionEvent evt) {
		getjTextPane().setText(
				"// WHILE Schleife : while (R1 > R2) {\n// inkrement R3;\n// dekrement R1;\n//}\n// Eingabe: R1, R2\n// Ausgabe: R0 = Abstand zwischen R1 und R2 \n\nM1: LOAD  1\n    SUB   2\n    JZERO M2\n    LOAD  3\n    ADD   #1\n    STORE 3\n    LOAD  1\n    SUB   #1\n    STORE 1\n    GOTO  M1\nM2: LOAD  3\n    END");
	}

	private void jMenuItemIfThenElseBspActionPerformed(ActionEvent evt) {
		getjTextPane().setText(
				"// IF Then Else\n// genauer: IF R1<=1 THEN R2++ ELSE R2--\n\n   LOAD  1\n   SUB #1\n   JZERO If-Zweig\n\n   //Else-Zweig\n   LOAD  2\n   SUB #1\n   STORE 2\n   GOTO  Ende\n\n\nIf-Zweig:  LOAD  2\n   ADD #1\n   STORE 2\n\nEnde:    END\n ");
	}

	private void jMenuItemAddRekBspActionPerformed(ActionEvent evt) {
		getjTextPane().setText(
				"// Berechnung von m+n rekursiv mittels eines Stacks\n// add(m,n) = n, falls m = 0\n// add(m,n) = add(m-1,n)+1, sonst\n// Eingabe: R4 = m, R5 = n\n// Ausgabe: R0 = m+n\n //Initialisiere Top und Anzahl\n   LOAD  #6\n   STORE 1\n   LOAD  #0\n   STORE 2\n\n //*****Stapel Aufbau******\n //push(m)\n   LOAD  1\n   ADD   #1\n   STORE 1\n   LOAD  4\n   STORE *1\n   LOAD  2\n   ADD #1\n   STORE 2\n //****Stapel Aufbau: Schleifenanfang***\n //peek()\nSchlfAnf:            LOAD  *1\n   STORE 3   //R3=\"aktuelles\" m\n   JZERO StplAbbau //Springe zu Stapel Abbau\n // push(m-1)\n   LOAD  1\n   ADD #1\n   STORE 1\n   LOAD  3\n   SUB #1\n   STORE *1\n   LOAD  2\n   ADD #1\n   STORE 2\n //Gehe zu \"Stapel Aufbau: Schleifenanfang\"\n   GOTO  SchlfAnf\n\n //****Stapel Abbau*****\n //speicher n in *top\nStplAbbau: LOAD  5\n   STORE *1\n //*****Stapel Abbau: Schleifenanfang*****\n //Addiere 1 zum zureuckgegebenen Ergebnis\nSchlf2Anf: LOAD  *1\n   ADD #1\n   STORE 3\n //Dekrementiere top und anzahl\n   LOAD  1\n   SUB #1\n   STORE 1\n   LOAD  2\n   SUB #1\n   STORE 2\n //Wenn der Stapel leer ist, springe zum Ende\n   JZERO Ende\n //Speicher Rückgabewert int *top\n   LOAD  3\n   STORE *1\n   GOTO  Schlf2Anf\nEnde:    LOAD  7\n   END");
	}

	private void jMenuItemSummeBspActionPerformed(ActionEvent evt) {
		getjTextPane().setText(
				"// Summe zweier Zahlen\n// Eingabe: R1 = a, R2 = b, \n// Ausgabe: R0 = a+b\n\n LOAD  1\n ADD 2\n END");
	}

	private void jMenuItemListenEndeBspActionPerformed(ActionEvent evt) {
		getjTextPane().setText(
				"// Das Listenende (letzte Element einer Liste) wird gesucht\n// genauer:   In Register 2i müssen Sie die zu speichernde Zahl schreiben, und in R 2i+1 den Zeiger auf das folgende Listenelement. i>=2\n//   Durch R2i+1=0 wird das Ende der Liste signalisiert.\n//   In R1 muss der Zeiger auf das erste Listenelement reingeschrieben werden.\n//     Die folgenden Instruktionen Speichern den Wert des letzten Listenelemntes in R0\n//   R2 und R3 müssen für Nebenrechnungen frei bleiben.\n\n \n   //Der Listenkopf wird in R3 geladen\n   LOAD  1\n   STORE 3\n\n   //while R3 ungleich 0 do ... od\nWhile-Kopf:  LOAD  3\n   JZERO After-While //nach While Schleife\n \n   //R2= Wert des aktuellen Listengliedes\n   LOAD  *3\n   STORE 2\n\n   //R3= Position des nächsten Listengliedes\n   LOAD  3\n   ADD #1\n   STORE 3\n   LOAD  *3\n   STORE 3\n\n   GOTO  While-Kopf\n\n   //Nach der While Schleife \nAfter-While: LOAD  2\n   END\n ");
	}

	private void jMenuItemMaximumBspActionPerformed(ActionEvent evt) {
		getjTextPane().setText(
				"// Berechnung des Maximums \n// Eingabe: R1 und R2\n// Ausgabe: R0 = max(R1, R2)\n\n   LOAD 1\n   SUB 2\n   JZERO R2-größer\n   // Fall R1 > R2\n   LOAD 1\n   GOTO Ende\n   // Fall R2 >= R1\nR2-größer: LOAD 2\nEnde:    END");
	}

	public JTextPane getjTextPane() {
		return this.jTextPane;
	}

	public JButton getButtonOeffnen() {
		return this.buttonOeffnen;
	}

	public JMenuItem getjMenuItemOeffnen() {
		return this.jMenuItemOeffnen;
	}

	public JButton getButtonDrucken() {
		return this.buttonDrucken;
	}

	public JButton getButtonNeu() {
		return this.buttonNeu;
	}

	public JMenuItem getjMenuItemAusschneiden() {
		return this.jMenuItemAusschneiden;
	}

	public JMenuItem getjMenuItemBeenden() {
		return this.jMenuItemBeenden;
	}

	public JMenuItem getjMenuItemDrucken() {
		return this.jMenuItemDrucken;
	}

	public JMenuItem getjMenuItemEinfuegen() {
		return this.jMenuItemEinfuegen;
	}

	public JMenuItem getjMenuItemKopieren() {
		return this.jMenuItemKopieren;
	}

	public JMenuItem getjMenuItemNeu() {
		return this.jMenuItemNeu;
	}

	public JMenuItem getjMenuItemRueckgaengig() {
		return this.jMenuItemRueckgaengig;
	}

	public JMenuItem getjMenuItemSpeichern() {
		return this.jMenuItemSpeichern;
	}

	public JMenuItem getjMenuItemSpeichernUnter() {
		return this.jMenuItemSpeichernUnter;
	}

	public JButton getButtonSpeichern() {
		return this.buttonSpeichern;
	}

	public JFileChooser getjFileChooser() {
		return this.jFileChooser;
	}

	public JMenu getjMenuBearbeiten() {
		return this.jMenuBearbeiten;
	}

	public JOptionPane getMeldungsFenster() {
		return this.meldungsFenster;
	}

	public JMenu getjMenuFormat() {
		return this.jMenuFormat;
	}

	public JTable getjTable1() {
		return this.jTable1;
	}

	public JButton getjButtonRegisterZuruecksetzen() {
		return this.jButtonRegisterZuruecksetzen;
	}

	public JButton getjButtonSetzeNeueAnzahlRegister() {
		return this.jButtonSetzeNeueAnzahlRegister;
	}

	public void setjTextFieldNeueAnzahlRegister(JTextField jTextFieldNeueAnzahlRegister) {
		this.jTextFieldNeueAnzahlRegister = jTextFieldNeueAnzahlRegister;
	}

	public JTextField getjTextFieldNeueAnzahlRegister() {
		return this.jTextFieldNeueAnzahlRegister;
	}

	public JButton getjButtonStarteProgramm() {
		return this.jButtonStarteProgramm;
	}

	public JSpinner getjSpinnerMilliSekProBefehl() {
		return this.jSpinnerMilliSekProBefehl;
	}

	public JMenu getjMenuDatei() {
		return this.jMenuDatei;
	}

	public JButton getjButtonProgrammAbbrechen() {
		return this.jButtonProgrammAbbrechen;
	}

	public JButton getjButtonEinzelSchritt() {
		return this.jButtonEinzelSchritt;
	}

	public JButton getjButtonStarteEinzelschrittModus() {
		return this.jButtonStarteEinzelschrittModus;
	}

	public JButton getjButtonRegisterLaden() {
		return this.jButtonRegisterLaden;
	}

	public JButton getjButtonRegisterSpeichern() {
		return this.jButtonRegisterSpeichern;
	}

	public JSpinner getjSpinnerTabellenGr() {
		return this.jSpinnerTabellenGr;
	}
}

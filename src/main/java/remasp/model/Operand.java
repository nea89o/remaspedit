package remasp.model;

import java.util.LinkedList;
import java.util.List;

public class Operand {
	public String getSprungMarke() {
		return this.sprungMarke;
	}

	boolean istIndirekteAdresse = false;

	private boolean istKonstante = false;

	private boolean istSprungMarke;

	long konstanterWert;

	int registerNr;
	private String sprungMarke;

	public boolean getIstSprungMarke() {
		return this.istSprungMarke;
	}

	public Operand(String EineOpZeichenkette, Konfiguration eineKonfiguration, boolean istSprungMarke)
			throws Exception {
		this.istSprungMarke = istSprungMarke;
		if (!istSprungMarke) {

			List<Character> zahlenAlphabet = new LinkedList<>();
			zahlenAlphabet.add(Character.valueOf('0'));
			zahlenAlphabet.add(Character.valueOf('-'));
			zahlenAlphabet.add(Character.valueOf('1'));
			zahlenAlphabet.add(Character.valueOf('2'));
			zahlenAlphabet.add(Character.valueOf('3'));
			zahlenAlphabet.add(Character.valueOf('4'));
			zahlenAlphabet.add(Character.valueOf('5'));
			zahlenAlphabet.add(Character.valueOf('6'));
			zahlenAlphabet.add(Character.valueOf('7'));
			zahlenAlphabet.add(Character.valueOf('8'));
			zahlenAlphabet.add(Character.valueOf('9'));

			char erstesZeichen = EineOpZeichenkette.charAt(0);

			if (erstesZeichen == '#' || erstesZeichen == '*'
					|| zahlenAlphabet.contains(Character.valueOf(erstesZeichen))) {
				if (erstesZeichen == '#') {
					this.istKonstante = true;
					this.konstanterWert = Long.decode(EineOpZeichenkette.substring(1)).longValue();
				}
				if (erstesZeichen == '*') {
					this.istIndirekteAdresse = true;
					this.registerNr = Integer.decode(EineOpZeichenkette.substring(1).replaceFirst("0*", "")).intValue();

					if (this.registerNr > eineKonfiguration.getRegisters().size()) {
						throw new Exception("Register Nr. " + this.registerNr + " existiert nicht.");
					}
				}

				if (zahlenAlphabet.contains(Character.valueOf(erstesZeichen))) {
					this.registerNr = Integer.decode(EineOpZeichenkette.replaceFirst("0*", "")).intValue();
				}
			} else {
				throw new Exception("Ungültiges Operanden Format: " + EineOpZeichenkette);
			}
		} else {
			this.sprungMarke = EineOpZeichenkette;
		}
	}

	public long getZahlenWert(Konfiguration eineKonfiguration) {
		if (getIstKonstante()) {
			return this.konstanterWert;
		}

		if (this.istIndirekteAdresse) {
			return eineKonfiguration.getRegister((int) eineKonfiguration.getRegister(this.registerNr));
		}
		return eineKonfiguration.getRegister(this.registerNr);
	}

	public int getRegisterNr() {
		return this.registerNr;
	}

	public boolean getIstKonstante() {
		return this.istKonstante;
	}
}

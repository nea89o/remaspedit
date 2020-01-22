package remasp.model;

public class Multiplikation extends Befehl implements Instruktion {
	public Multiplikation(Operand operand, String einLabel, int startOffset, int endOffset) {
		super(operand, einLabel, startOffset, endOffset);
	}

	public void eval(Konfiguration konfig) {
		long ergebnis = Math.multiplyExact(konfig.getRegister(0), this.operand.getZahlenWert(konfig));
		konfig.setRegister(0, ergebnis);
		konfig.incBz();
	}
}

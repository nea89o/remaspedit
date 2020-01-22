package remasp.model;

public class Addition extends Befehl implements Instruktion {
	public Addition(Operand operand, String einLabel, int startOffset, int endOffset) {
		super(operand, einLabel, startOffset, endOffset);
	}

	public void eval(Konfiguration konfig) {
		long ergebnis = Math.addExact(konfig.getRegister(0), this.operand.getZahlenWert(konfig));
		konfig.setRegister(0, ergebnis);
		konfig.incBz();
	}
}

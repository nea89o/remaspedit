package remasp.model;

public class Division extends Befehl implements Instruktion {
	public Division(Operand operand, String einLabel, int startOffset, int endOffset) {
		super(operand, einLabel, startOffset, endOffset);
	}

	public void eval(Konfiguration konfig) {
		if (this.operand.getZahlenWert(konfig) > 0L) {
			long ergebnis = Math.floorDiv(konfig.getRegister(0), this.operand.getZahlenWert(konfig));
			konfig.setRegister(0, ergebnis);
			konfig.incBz();
		} else {

			konfig.setBz(-1);
		}
	}
}

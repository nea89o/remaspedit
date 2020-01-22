package remasp.model;

public class Load extends Befehl implements Instruktion {
	public Load(Operand operand, String einLabel, int startOffset, int endOffset) {
		super(operand, einLabel, startOffset, endOffset);
	}

	public void eval(Konfiguration konfig) {
		konfig.setRegister(0, this.operand.getZahlenWert(konfig));
		konfig.incBz();
	}
}

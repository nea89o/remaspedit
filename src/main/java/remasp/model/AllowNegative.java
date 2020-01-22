package remasp.model;

public class AllowNegative extends Befehl implements Instruktion {
	public AllowNegative(Operand operand, String einLabel, int startOffset, int endOffset) {
		super(operand, einLabel, startOffset, endOffset);
	}

	public void eval(Konfiguration konfig) {
		konfig.allowNegative();
		konfig.incBz();
	}
}

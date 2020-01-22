package remasp.model;

public class End extends Befehl implements Instruktion {
	public End(Operand operand, String einLabel, int startOffset, int endOffset) {
		super(operand, einLabel, startOffset, endOffset);
	}

	public void eval(Konfiguration konfig) {
		konfig.setBz(-1);
	}
}

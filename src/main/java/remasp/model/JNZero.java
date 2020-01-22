package remasp.model;

public class JNZero extends Befehl implements Instruktion {
	public JNZero(Operand operand, String einLabel, int startOffset, int endOffset) {
		super(operand, einLabel, startOffset, endOffset);
	}

	public void eval(Konfiguration konfig) {
		if (konfig.getRegister(0) != 0L) {
			konfig.setBz(konfig.findeIndexVonBefehlMitLabel(this.operand.getSprungMarke()));
		} else {
			konfig.incBz();
		}
	}
}

package remasp.model;

public class Goto extends Befehl implements Instruktion {
	public Goto(Operand operand, String einLabel, int startOffset, int endOffset) {
		super(operand, einLabel, startOffset, endOffset);
	}

	public void eval(Konfiguration konfig) {
		int i = konfig.findeIndexVonBefehlMitLabel(this.operand.getSprungMarke());
		konfig.setBz(i);
	}
}

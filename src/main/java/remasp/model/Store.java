package remasp.model;

public class Store extends Befehl implements Instruktion {
	public Store(Operand operand, String einLabel, int startOffset, int endOffset) {
		super(operand, einLabel, startOffset, endOffset);
	}

	public void eval(Konfiguration konfig) {
		if (this.operand.istIndirekteAdresse) {
			if ((int) konfig.getRegister(this.operand.getRegisterNr()) >= 1) {
				konfig.setRegister((int) konfig.getRegister(this.operand.getRegisterNr()), konfig.getRegister(0));
			} else {
				konfig.setBz(-1);
				return;
			}
		} else {
			konfig.setRegister(this.operand.getRegisterNr(), konfig.getRegister(0));
		}
		konfig.incBz();
	}
}

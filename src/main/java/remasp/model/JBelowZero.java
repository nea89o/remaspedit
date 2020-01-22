package remasp.model;

public class JBelowZero extends Befehl implements Instruktion {

	public JBelowZero(Operand operand, String einLabel, int startOffset, int endOffset) {
		super(operand, einLabel, startOffset, endOffset);
	}
	
	public void eval(Konfiguration konfig) throws Exception {
		if(!konfig.isNegativeAllowed()) {
			throw new NegativeZahlenOderBefehleInNichtNegativenKontextException("JNEG kann nur in einem Kontext mit negativen Zahlen genutzt werden.\nNutze ALLOWNEG um diesen Kontext zu aktivieren.", this.getStartOffset(), this.getEndOffset());
		}
		if (konfig.getRegister(0) < 0L) {
			konfig.setBz(konfig.findeIndexVonBefehlMitLabel(this.operand.getSprungMarke()));
		} else {
			konfig.incBz();
		}

	}

}

package remasp.model;

public class Befehl {
	private String label;
	protected Operand operand;
	int startOffset;
	int endOffset;

	public Befehl(Operand operand, String einLabel, int startOffset, int endOffset) {
		this.operand = operand;
		this.label = einLabel;
		this.startOffset = startOffset;
		this.endOffset = endOffset;
	}

	public void eval(Konfiguration konfig) throws Exception {
	}

	public int getStartOffset() {
		return this.startOffset;
	}

	public int getEndOffset() {
		return this.endOffset;
	}

	public Operand getOperand() {
		return this.operand;
	}

	public String getLabel() {
		return this.label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
}

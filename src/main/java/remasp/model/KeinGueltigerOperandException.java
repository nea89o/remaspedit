package remasp.model;

public class KeinGueltigerOperandException extends ExecutionException {

	public KeinGueltigerOperandException(String string, int startOffset, int endOffset) {
		super(string, startOffset, endOffset);
	}
}

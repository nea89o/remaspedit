package remasp.model;

public class KeinGueltigerBefehlException extends ExecutionException {

	public KeinGueltigerBefehlException(String string, int startOffset, int endOffset) {
		super(string, startOffset, endOffset);
	}
	
}

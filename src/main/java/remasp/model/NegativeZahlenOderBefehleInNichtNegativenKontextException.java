package remasp.model;

public class NegativeZahlenOderBefehleInNichtNegativenKontextException extends ExecutionException {

	public NegativeZahlenOderBefehleInNichtNegativenKontextException(String string, int startOffset, int endOffset) {
		super(string, startOffset, endOffset);
	}
}

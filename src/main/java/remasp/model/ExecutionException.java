package remasp.model;

public class ExecutionException extends Exception {
	private int startOffset;
	private int endOffset;

	public int getStartOffset() {
		return this.startOffset;
	}

	public int getEndOffset() {
		return this.endOffset;
	}

	public ExecutionException(String string, int startOffset, int endOffset) {
		super(string);
		this.startOffset = startOffset;
		this.endOffset = endOffset;
	}

}

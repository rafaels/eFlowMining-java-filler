package entities;

public abstract class MethodException {
	private Method method;
	private Exception exception;
	private String kind;
	private int startOffSet;
	private int endOffSet;

	protected MethodException(Method method, String exception, String kind, int startOffSet, int endOffSet) {
		this.method = method;
		this.exception = Exception.getException(exception);
		this.kind = kind;
		this.startOffSet = startOffSet;
		this.endOffSet = endOffSet;
	}
}

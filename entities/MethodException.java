package entities;

public abstract class MethodException {
	protected Method method;
	protected Exception exception;
	protected String kind;
	protected int startOffSet;
	protected int endOffSet;

	protected MethodException(Method method, String exception, String kind, int startOffSet, int endOffSet) {
		this.method = method;
		this.exception = Exception.getException(exception);
		this.kind = kind;
		this.startOffSet = startOffSet;
		this.endOffSet = endOffSet;
	}
}

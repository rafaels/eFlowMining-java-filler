package entities;

import com.thoughtworks.xstream.annotations.XStreamOmitField;

public abstract class MethodException {
	protected @XStreamOmitField Method method;
	protected Exception exception;
	protected boolean isGeneric;
	protected String kind;
	protected int startOffSet;
	protected int endOffSet;

	protected MethodException(Method method, String exception, String exceptionBaseName, String kind, int startOffSet, int endOffSet) {
		this.method = method;
		this.exception = Exception.getException(exception, exceptionBaseName);
		this.kind = kind;
		this.startOffSet = startOffSet;
		this.endOffSet = endOffSet;
	}

	public void setIsGeneric(boolean isGeneric) {
		this.isGeneric = isGeneric;
	}
}

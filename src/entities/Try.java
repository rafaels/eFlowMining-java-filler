package entities;

public class Try extends MethodException {
	public Try(Method method, int startOffSet, int endOffSet) {
		super(method, "Try", "Try", "Try", startOffSet, endOffSet);
		this.setIsGeneric(true);
		method.addTry(this);
	}
}

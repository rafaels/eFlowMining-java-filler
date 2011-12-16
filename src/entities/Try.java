package entities;

public class Try extends MethodException {
	public Try(Method method, int startOffSet, int endOffSet) {
		super(method, "Try", "Try", startOffSet, endOffSet);
		this.setIsGeneric(true);
		method.addTry(this);
	}
	
	public String toString() {
		return "TRY:\n" +
				"	startOffSet: " + startOffSet + "\n" +
				"	endOffSet:   " + endOffSet + "\n";
	}
}

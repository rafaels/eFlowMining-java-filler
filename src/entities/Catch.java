package entities;

public class Catch extends MethodException {
	public Catch(Method method, String exception, int startOffSet, int endOffSet) {
		super(method, exception, "Catch", startOffSet, endOffSet);

		if (exception == "java.lang.Exception" || exception == "java.lang.Error") {
			this.setIsGeneric(true);
		} else {
			this.setIsGeneric(false);
		}

		method.addCatch(this);
	}
	
	public String toString() {
		return "CATCH:\n" +
				"	exception:   " + exception.toString() + "\n" +
				"	startOffSet: " + startOffSet + "\n" +
				"	endOffSet:   " + endOffSet + "\n";
	}
}

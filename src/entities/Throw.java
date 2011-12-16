package entities;

public class Throw extends MethodException {
	public Throw(Method method, String exception, int offSet) {
		super(method, exception, "Throw", offSet, offSet);

		if (exception == "java.lang.Exception" || exception == "java.lang.Error") {
			this.setIsGeneric(true);
		} else {
			this.setIsGeneric(false);
		}

		method.addThrow(this);
	}
	
	public String toString() {
		return "THROW:\n" +
				"	exception: " + exception.toString() + "\n" +
				"	offSet:    " + startOffSet + "\n";
	}
}

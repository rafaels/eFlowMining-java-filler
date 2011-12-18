package entities;

public class Throw extends MethodException {
	public Throw(Method method, String exception, String exceptionBaseName, int offSet) {
		super(method, exception, exceptionBaseName, "Throw", offSet, offSet);

		if (exception == "java.lang.Exception" || exception == "java.lang.Error") {
			this.setIsGeneric(true);
		} else {
			this.setIsGeneric(false);
		}

		method.addThrow(this);
	}
}

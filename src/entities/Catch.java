package entities;

public class Catch extends MethodException {
	public Catch(Method method, String exception, String exceptionBaseName, int startOffSet, int endOffSet) {
		super(method, exception, exceptionBaseName, "Catch", startOffSet, endOffSet);

		if (exception.equals("java.lang.Exception") || exception.equals("java.lang.Error")) {
			this.setIsGeneric(true);
		} else {
			this.setIsGeneric(false);
		}

		method.addCatch(this);
	}
}

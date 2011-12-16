package entities;

public class Throw extends MethodException {
	public Throw(Method method, String exception, int offSet) {
		super(method, exception, "Throw", offSet, offSet);
		method.addThrow(this);
	}
	
	public String toString() {
		return "THROW:\n" +
				"	exception: " + exception.toString() + "\n" +
				"	offSet:    " + startOffSet + "\n";
	}
}

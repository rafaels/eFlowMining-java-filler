package entities;

public class Throw extends MethodException {
	public Throw(Method method, String exception, int offSet) {
		super(method, exception, "Throw", offSet, offSet);
	}
}

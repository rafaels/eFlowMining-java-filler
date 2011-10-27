package entities;

public class Catch extends MethodException {
	public Catch(Method method, String exception, int startOffSet, int endOffSet) {
		super(method, exception, "Catch", endOffSet, endOffSet);
	}
}

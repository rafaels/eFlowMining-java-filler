package entities;

import java.util.ArrayList;
import java.util.Iterator;

public class MethodCall {
	private Assembly assembly;
	private Method methodSource; //exists into the source
	private Method methodTarget; //which method is being called
	private FakeMethod fakeMethodTarget;
	private int offSet; //where in methodSource is called
	private static final ArrayList<MethodCall> list = new ArrayList<MethodCall>();
	
	private MethodCall(Assembly assembly, Method methodSource, FakeMethod fakeMethodTarget, int offSet) {
		this.assembly = assembly;
		this.methodSource = methodSource;
		this.fakeMethodTarget = fakeMethodTarget;
		this.offSet = offSet;
	}
	
	public Method getMethodSource() {
		return methodSource;
	}

	public Method getMethodTarget() {
		return methodTarget;
	}

	public FakeMethod getFakeMethodTarget() {
		return fakeMethodTarget;
	}

	public int getOffSet() {
		return offSet;
	}

	public static MethodCall createWithFakeTarget(Assembly assembly, Method methodSource, FakeMethod fakeMethodTarget, int offSet) {
		MethodCall methodCall = new MethodCall(assembly, methodSource, fakeMethodTarget, offSet);
		
		list.add(methodCall);
		
		return methodCall;
	}
	
	public static void print() {
		for (Iterator<MethodCall> iteratorType = list.iterator(); iteratorType.hasNext();) {
			MethodCall methodCall = iteratorType.next();
			
			System.out.println("#############################################");
			System.out.printf("%s.%s\n", methodCall.getMethodSource().getType().getName(), methodCall.getMethodSource().getName());
			System.out.println("->");
			System.out.printf("%s.%s\n", methodCall.getFakeMethodTarget().getFakeType(), methodCall.getFakeMethodTarget().getFakeName());
			System.out.printf("on: %d", methodCall.getOffSet());
			
			
			System.out.println();
		}
	}
}

package entities;

import java.util.ArrayList;
import java.util.Iterator;

import com.thoughtworks.xstream.annotations.XStreamOmitField;

public class MethodCall {
	private Method methodSource; //exists into the source
	private Method methodTarget; //which method is being called
	private int offSet; //where in methodSource is called

	private @XStreamOmitField FakeMethod fakeMethodTarget;

	private static final ArrayList<MethodCall> list = new ArrayList<MethodCall>();
	private static final ArrayList<MethodCall> trackingActualTargetList = new ArrayList<MethodCall>();

	private MethodCall(Assembly assembly, Method methodSource, FakeMethod fakeMethodTarget, int offSet) {
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
	
	public void trackActualTarget() {
		methodTarget = Type.search(fakeMethodTarget.getFakeType(), fakeMethodTarget.getFakeName());
		if (methodTarget != null) {
			trackingActualTargetList.add(this);
		}
	}

	public static MethodCall createWithFakeTarget(Assembly assembly, Method methodSource, FakeMethod fakeMethodTarget, int offSet) {
		MethodCall methodCall = new MethodCall(assembly, methodSource, fakeMethodTarget, offSet);
		
		list.add(methodCall);
		
		return methodCall;
	}
	
	public static void trackActualTargets() {
		for (Iterator<MethodCall> iteratorType = list.iterator(); iteratorType.hasNext();) {
			MethodCall methodCall = iteratorType.next();
			methodCall.trackActualTarget();
		}

		Assembly.getInstance().setMethodCalls(trackingActualTargetList);
	}
	
	public static void print() {
		for (Iterator<MethodCall> iteratorType = trackingActualTargetList.iterator(); iteratorType.hasNext();) {
			MethodCall methodCall = iteratorType.next();
			
			System.out.println("#############################################");
			System.out.printf("%s.%s\n", methodCall.getMethodSource().getType().getName(), methodCall.getMethodSource().getName());
			System.out.println("->");
			System.out.printf("%s.%s\n", methodCall.getMethodTarget().getType().getName(), methodCall.getMethodTarget().getName());
			System.out.printf("on: %d", methodCall.getOffSet());
			
			
			System.out.println();
		}
	}
}

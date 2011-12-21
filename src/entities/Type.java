package entities;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;

import com.thoughtworks.xstream.annotations.XStreamOmitField;;

public class Type {
	private String name;
	private String fullName;
	private String kind;
	
	private @XStreamOmitField Hashtable<String, Method> methodsHash;
	private ArrayList<Method> methods;

	private static final Hashtable<String, Type> list = new Hashtable<String, Type>();

	public Type() {}

	public Type(Assembly assembly, String fullName, String kind) {
		this.fullName = fullName;

		String[] parts = fullName.split("\\.");
		this.name = parts[parts.length -1];

		this.kind = kind;
		methodsHash = new Hashtable<String, Method>();
		methods = new ArrayList<Method>();
		list.put(fullName, this);
		Assembly.getInstance().addType(this);
	}

	public String getName() {
		return name;
	}

	public void addMethod(Method method) {
		this.methodsHash.put(method.getFullName(), method);
		this.methods.add(method);
	}
	
	public static Method search(String type, String method) {
		if (list.containsKey(type)) {
			return list.get(type).methodsHash.get(method);
		}
		
		return null;
	}

	public int getQtdTry() {
		int sum = 0;
		for (Method method : methods) {
			sum += method.getQtdTry();
		}
		return sum;
	}
	public int getQtdCatch() {
		int sum = 0;
		for (Method method : methods) {
			sum += method.getQtdCatch();
		}
		return sum;
	}
	public int getQtdCatchGeneric() {
		int sum = 0;
		for (Method method : methods) {
			sum += method.getQtdCatchGeneric();
		}
		return sum;
	}

	public int getQtdCatchSpecialized() {
		int sum = 0;
		for (Method method : methods) {
			sum += method.getQtdCatchSpecialized();
		}
		return sum;
	}
	public int getQtdThrow() {
		int sum = 0;
		for (Method method : methods) {
			sum += method.getQtdThrow();
		}
		return sum;
	}
	public int getQtdFinally() {
		int sum = 0;
		for (Method method : methods) {
			sum += method.getQtdFinally();
		}
		return sum;
	}
}

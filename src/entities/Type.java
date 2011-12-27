package entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

import com.thoughtworks.xstream.annotations.XStreamOmitField;;

public class Type {
	private String name;
	private String fullName;
	private String kind;
	
	private @XStreamOmitField HashMap<String, Method> methodsHash;
	private ArrayList<Method> methods;

	private static final Hashtable<String, Type> list = new Hashtable<String, Type>();

	public Type() {}

	public Type(Assembly assembly, String fullName, String kind) {
		this.fullName = fullName;

		String[] parts = fullName.split("\\.");
		this.name = parts[parts.length -1];

		this.kind = kind;
		methodsHash = new HashMap<String, Method>();
		methods = new ArrayList<Method>();
		list.put(fullName, this);
	}

	public String getName() {
		return name;
	}

	public String getFullName() {
		return fullName;
	}

	public Method getMethod(String name, String fullname, String visibility) {
		if (this.methodsHash.containsKey(fullname)) {
			return methodsHash.get(fullname);
		} else {
			Method method = new Method(this, name, fullname, visibility);
			this.methodsHash.put(fullname, method);
			this.methods.add(method);
			return method;
		}
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

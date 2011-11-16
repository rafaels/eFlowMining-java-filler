package entities;

import java.util.Hashtable;
import java.util.Iterator;

public class Type {
	private Assembly assembly;
	private String name;
	private String kind;
	
	private Hashtable<String, Method> methods;
	private static final Hashtable<String, Type> list = new Hashtable<String, Type>();
	
	public Type(Assembly assembly, String name, String kind) {
		this.assembly = assembly;
		this.name = name;
		this.kind = kind;
		methods = new Hashtable<String, Method>();
		list.put(name, this);
	}

	public Assembly getAssembly() {
		return assembly;
	}

	public void setAssembly(Assembly assembly) {
		this.assembly = assembly;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public Iterator<Method> getMethodsIterator() {
		return methods.values().iterator();
	}

	public void addMethod(Method method) {
		this.methods.put(method.getName(), method);
	}
	
	public static Method search(String type, String method) {
		if (list.containsKey(type)) {
			return list.get(type).methods.get(method);
		}
		
		return null;
	}
	
	public static void print() {
		for (Iterator<Type> iteratorType = list.values().iterator(); iteratorType.hasNext();) {
			Type type = iteratorType.next();
			
			System.out.println(type.getName());
			
			for (Iterator<Method> iteratorMethod = type.getMethodsIterator(); iteratorMethod.hasNext();) {
				Method method = iteratorMethod.next();
				
                method.print();
			}
			
			System.out.println();
		}
	}
}

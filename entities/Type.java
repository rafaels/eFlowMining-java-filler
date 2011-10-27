package entities;

import java.util.ArrayList;
import java.util.Iterator;

public class Type {
	private Assembly assembly;
	private String name;
	private String kind;
	
	private ArrayList<Method> methods;
	private static final ArrayList<Type> list = new ArrayList<Type>();
	
	public Type(Assembly assembly, String name, String kind) {
		this.assembly = assembly;
		this.name = name;
		this.kind = kind;
		methods = new ArrayList<Method>();
		list.add(this);
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
		return methods.iterator();
	}

	public void addMethod(Method method) {
		this.methods.add(method);
	}
	
	public static void print() {
		for (Iterator<Type> iteratorType = list.iterator(); iteratorType.hasNext();) {
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

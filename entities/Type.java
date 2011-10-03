package entities;

import java.util.ArrayList;

public class Type {
	private Assembly assembly;
	private String name;
	private String kind;
	private ArrayList<Method> methods;
	
	public Type(Assembly assembly, String name, String kind) {
		this.assembly = assembly;
		this.name = name;
		this.kind = kind;
		methods = new ArrayList<Method>();
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

	public ArrayList<Method> getMethods() {
		return methods;
	}

	public void setMethods(ArrayList<Method> methods) {
		this.methods = methods;
	}
}

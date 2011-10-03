package entities;

import java.util.ArrayList;

import soot.coffi.array_element_value;

public class Type {
	private Assembly assembly;
	private String name;
	private String fullName;
	private String kind;
	private ArrayList<Method> methods;
	
	public Type() {
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

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
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

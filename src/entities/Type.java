package entities;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamOmitField;;

public class Type {
	private String name;
	private String kind;
	
	private @XStreamOmitField Hashtable<String, Method> methodsHash;
	private ArrayList<Method> methods;

	private static final Hashtable<String, Type> list = new Hashtable<String, Type>();

	public Type() {}

	public Type(Assembly assembly, String name, String kind) {
		this.name = name;
		this.kind = kind;
		methodsHash = new Hashtable<String, Method>();
		methods = new ArrayList<Method>();
		list.put(name, this);
		Assembly.getInstance().addType(this);
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
		return methodsHash.values().iterator();
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

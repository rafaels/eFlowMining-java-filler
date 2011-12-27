package entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import com.thoughtworks.xstream.annotations.XStreamOmitField;

public class Assembly {
	private String name;
	private String version;
	private Date createdAt;
	private String language;
	
	private ArrayList<Exception> exceptions;
	private ArrayList<Assembly> references;
	private ArrayList<Type> types;
	private ArrayList<MethodCall> methodCalls;
	private @XStreamOmitField HashMap<String, Type> typeMap;

	private static Assembly _instance;

	public Assembly() {}

	public static Assembly getInstance() {
		return _instance;
	}

	public static void setInstance(Assembly instance) {
		_instance = instance;
	}

	public Assembly(String name, String version, Date createdAt, String language) {
		this.name = name;
		this.version = version;
		this.createdAt = createdAt;
		this.language = language;
		references = new ArrayList<Assembly>();
		types = new ArrayList<Type>();
		exceptions = new ArrayList<Exception>();
		methodCalls = new ArrayList<MethodCall>();
		typeMap = new HashMap<String, Type>();
	}

	public void createDefaultRef() {
		Assembly defaultRef = new Assembly("default", "default", createdAt, language);
		references.add(defaultRef);
	}

	public Assembly getDefaultRef() {
		return references.get(0);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public ArrayList<Type> getTypes() {
		return types;
	}

	public void setTypes(ArrayList<Type> types) {
		this.types = types;
	}

	public Type getType(String fullname, String kind) {
		if (typeMap.containsKey(fullname)) {
			return typeMap.get(fullname);
		} else {
			Type type = new Type(this, fullname, kind);
			typeMap.put(fullname, type);
			types.add(type);
			return type;
		}
	}

	public void addException(Exception exception) {
		exceptions.add(exception);
	}

	public void setMethodCalls(ArrayList<MethodCall> methodCalls) {
		this.methodCalls = methodCalls;
	}
	public int getQtdTry() {
		int sum = 0;
		for (Type type : types) {
			sum += type.getQtdTry();
		}
		return sum;
	}
	public int getQtdCatch() {
		int sum = 0;
		for (Type type : types) {
			sum += type.getQtdCatch();
		}
		return sum;
	}
	public int getQtdCatchGeneric() {
		int sum = 0;
		for (Type type : types) {
			sum += type.getQtdCatchGeneric();
		}
		return sum;
	}

	public int getQtdCatchSpecialized() {
		int sum = 0;
		for (Type type : types) {
			sum += type.getQtdCatchSpecialized();
		}
		return sum;
	}
	public int getQtdThrow() {
		int sum = 0;
		for (Type type : types) {
			sum += type.getQtdThrow();
		}
		return sum;
	}
	public int getQtdFinally() {
		int sum = 0;
		for (Type type : types) {
			sum += type.getQtdFinally();
		}
		return sum;
	}
}

package entities;

import java.util.ArrayList;
import java.util.Date;

public class Assembly {
	private String name;
	private String version;
	private Date createdAt;
	private String language;
	
	private ArrayList<Exception> exceptions;
	private ArrayList<Type> types;
	private ArrayList<MethodCall> methodCalls;

	private static Assembly _instance;

	public Assembly() {}

	public static Assembly getInstance() {
		return _instance;
	}
	
	public Assembly(String name, String version, Date createdAt, String language) {
		this.name = name;
		this.version = version;
		this.createdAt = createdAt;
		this.language = language;
		types = new ArrayList<Type>();
		exceptions = new ArrayList<Exception>();
		methodCalls = new ArrayList<MethodCall>();
		_instance = this;
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

	public void addType(Type type) {
		types.add(type);
	}

	public void addException(Exception exception) {
		exceptions.add(exception);
	}

	public void setMethodCalls(ArrayList<MethodCall> methodCalls) {
		this.methodCalls = methodCalls;
	}
}

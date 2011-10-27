package entities;

import java.util.HashMap;

public class Exception {
	private String name;
	
	private static HashMap<String, Exception> exceptions = new HashMap<String, Exception>();
	
	private Exception(String name) {
		this.name = name;
	}
	
	public static Exception getException(String name) {
		Exception exception;
		
		if (exceptions.containsKey(name)) {
			exception = exceptions.get(name);
		} else {
			exception = new Exception(name);
			exceptions.put(name, exception);
		}
		
		return exception;
	}
	
	public String toString() {
		return name;
	}
}

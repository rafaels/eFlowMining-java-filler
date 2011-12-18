package entities;

import java.util.HashMap;

public class Exception {
	private String name;
	private String basename;

	private static HashMap<String, Exception> exceptions = new HashMap<String, Exception>();

	public Exception() {
		name = "";
		basename = "";
	}

	private Exception(String name, String basename) {
		this.name = name;
		this.basename = basename;
		Assembly.getInstance().addException(this);
	}

	public static Exception getException(String name, String basename) {
		Exception exception;

		if (name == "Try") {
			return new Exception(); //POG
		}

		if (exceptions.containsKey(name)) {
			exception = exceptions.get(name);
		} else {
			exception = new Exception(name, basename);
			exceptions.put(name, exception);
		}

		return exception;
	}
}

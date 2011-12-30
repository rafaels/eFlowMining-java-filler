package entities;

import java.util.HashMap;

public class Exception {
	private String name;
	private String basename;

	private static HashMap<String, Exception> exceptions = new HashMap<String, Exception>();

	private Exception(String name, String basename) {
		this.name = name;
		this.basename = basename;
		Assembly.getInstance().getDefaultRef().addException(this);
	}

	public static Exception getException(String name, String basename) {
		Exception exception;

		if (exceptions.containsKey(name)) {
			exception = exceptions.get(name);
		} else {
			exception = new Exception(name, basename);
			exceptions.put(name, exception);
		}

		return exception;
	}
}

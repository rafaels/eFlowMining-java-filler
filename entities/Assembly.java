package entities;

import java.util.ArrayList;
import java.util.Date;

public class Assembly {
	private String name;
	private String language;
	private String version;
	private Date createdAt;
	private ArrayList<Type> types;
	
	public Assembly() {
		types = new ArrayList<Type>();
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
}

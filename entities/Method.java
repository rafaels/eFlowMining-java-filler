package entities;

public class Method {
	private Type type;
	private String name;
	private String visibility;
	private int qtdTry;
	private int qtdCatch;
	private int qtdThrow;
	private int qtdFinally;
	
	public Method(Type type, String name, String visibility) {
		this.type = type;
		this.name = name;
		this.visibility = visibility;
	}
	
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getVisibility() {
		return visibility;
	}
	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}
	public int getQtdTry() {
		return qtdTry;
	}
	public void setQtdTry(int qtdTry) {
		this.qtdTry = qtdTry;
	}
	public int getQtdCatch() {
		return qtdCatch;
	}
	public void setQtdCatch(int qtdCatch) {
		this.qtdCatch = qtdCatch;
	}
	public int getQtdThrow() {
		return qtdThrow;
	}
	public void setQtdThrow(int qtdThrow) {
		this.qtdThrow = qtdThrow;
	}
	public int getQtdFinally() {
		return qtdFinally;
	}
	public void setQtdFinally(int qtdFinally) {
		this.qtdFinally = qtdFinally;
	}
}

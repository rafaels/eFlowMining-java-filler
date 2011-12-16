package entities;

import java.util.ArrayList;

import com.thoughtworks.xstream.annotations.XStreamOmitField;

public class Method {
	private @XStreamOmitField Type type;
	private String name;
	private String visibility;
	private int qtdTry;
	private int qtdCatch;
	private int qtdThrow;
	private int qtdFinally;

	private @XStreamOmitField ArrayList<Try> theTries = new ArrayList<Try>();
	private @XStreamOmitField ArrayList<Catch> theCatches = new ArrayList<Catch>();
	private @XStreamOmitField ArrayList<Throw> theThrows = new ArrayList<Throw>();

	private ArrayList<MethodException> methodExceptions = new ArrayList<MethodException>();
	
	public Method(Type type, String name, String visibility) {
		this.type = type;
		this.name = name;
		this.visibility = visibility;
		qtdTry = 0;
		qtdCatch = 0;
		qtdThrow = 0;
		qtdFinally = 0;
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
	public int getQtdCatch() {
		return qtdCatch;
	}
	public int getQtdThrow() {
		return qtdThrow;
	}
	public int getQtdFinally() {
		return qtdFinally;
	}
	public void setQtdFinally(int qtdFinally) {
		this.qtdFinally = qtdFinally;
	}
	
	public void addTry(Try aTry) {
		qtdTry++;
		theTries.add(aTry);
		methodExceptions.add(aTry);
	}
	
	public void addCatch(Catch aCatch) {
		qtdCatch++;
		theCatches.add(aCatch);
		methodExceptions.add(aCatch);
	}
	
	public void addThrow(Throw aThrow) {
		qtdThrow++;
		theThrows.add(aThrow);
		methodExceptions.add(aThrow);
	}
	
	public void print() {
		System.out.println(this.getName());
        System.out.println(this.getVisibility());
		System.out.printf("    Qtd Try: %d\n", this.getQtdTry());
		System.out.printf("  Qtd Catch: %d\n", this.getQtdCatch());
		System.out.printf("Qtd Finally: %d\n", this.getQtdFinally());
		System.out.printf("  Qtd Throw: %d\n", this.getQtdThrow());
		
		System.out.println(theTries);
		System.out.println(theCatches);
		System.out.println(theThrows);
	}
}

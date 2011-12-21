package entities;

import java.util.ArrayList;

import com.thoughtworks.xstream.annotations.XStreamOmitField;

public class Method {
	private @XStreamOmitField Type type;
	private String name;
	private String fullName;
	private String visibility;
	private int qtdTry;
	private int qtdCatch;
	private int qtdCatchGeneric;
	private int qtdCatchSpecialized;
	private int qtdThrow;
	private int qtdFinally;

	private @XStreamOmitField ArrayList<Try> theTries = new ArrayList<Try>();
	private @XStreamOmitField ArrayList<Catch> theCatches = new ArrayList<Catch>();
	private @XStreamOmitField ArrayList<Throw> theThrows = new ArrayList<Throw>();
	private @XStreamOmitField ArrayList<MethodCall> calls = new ArrayList<MethodCall>();

	private ArrayList<MethodException> methodExceptions = new ArrayList<MethodException>();
	
	public Method(Type type, String name, String fullName, String visibility) {
		this.type = type;
		this.name = name;
		this.fullName = fullName;
		this.visibility = visibility;
		qtdTry = 0;
		qtdCatch = 0;
		qtdCatchGeneric = 0;
		qtdCatchSpecialized = 0;
		qtdThrow = 0;
		qtdFinally = 0;
	}
	
	public Type getType() {
		return type;
	}
	public String getName() {
		return name;
	}
	public String getFullName() {
		return fullName;
	}
	public String getVisibility() {
		return visibility;
	}
	public int getQtdTry() {
		return qtdTry;
	}
	public int getQtdCatch() {
		return qtdCatch;
	}
	public int getQtdCatchGeneric() {
		return qtdCatchGeneric;
	}

	public int getQtdCatchSpecialized() {
		return qtdCatchSpecialized;
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

		if (aCatch.isGeneric) {
			qtdCatchGeneric++;
		} else {
			qtdCatchSpecialized++;
		}

		theCatches.add(aCatch);
		methodExceptions.add(aCatch);
	}
	
	public void addThrow(Throw aThrow) {
		qtdThrow++;
		theThrows.add(aThrow);
		methodExceptions.add(aThrow);
	}

	public void addMethodCall(MethodCall methodCall) {
		calls.add(methodCall);
		methodCall.setOrder(calls.size());
	}
}

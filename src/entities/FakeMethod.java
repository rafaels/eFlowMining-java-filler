package entities;

public class FakeMethod {
	private String fakeType;
	private String fakeName;
	
	public FakeMethod(String fakeType, String fakeName) {
		this.fakeType = fakeType;
		this.fakeName = fakeName;
	}

	public String getFakeType() {
		return fakeType;
	}

	public String getFakeName() {
		return fakeName;
	}
}

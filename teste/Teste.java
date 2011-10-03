package teste;

import java.awt.IllegalComponentStateException;

public class Teste {
	protected static int getInt(int a) {
		try {
			try {
				a = 2;
			} catch (IllegalComponentStateException e) {
				a = 4;
			} catch (Exception e) {
				a = 5;
			} finally {
				a = 6;
			}
			a = 7;
		} catch (IllegalArgumentException e) {
			a = 8;
		} catch (IllegalComponentStateException e) {
			a = 9;
		} catch (Exception e) {
			a = 10;
		} finally {
			a = 11;
		}
		
		try {
			a = 12;
		} finally {
			a = 13;
		}
		
		synchronized (Teste.class) { //isso cria um trap a mais
			if (a == 3) {
				throw new IllegalArgumentException(); 
			}	
		}
		
		return 9;
	}
	
	public void thrower() throws Exception {
		throw new Exception();
	}
}

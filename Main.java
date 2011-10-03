import java.io.IOException;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import soot.Scene;
import soot.SootClass;
import soot.SootMethod;
import soot.Trap;
import soot.Unit;
import soot.jimple.internal.JThrowStmt;
import soot.options.Options;
import soot.toolkits.graph.TrapUnitGraph;
import soot.toolkits.graph.UnitGraph;
import java.sql.Connection;
import java.sql.SQLException;

import entities.*;

public class Main {
	public static void main(String[] args) throws IOException {
		List<String> process_dir = new LinkedList<String>();
		process_dir.add("bin/");
		Options.v().set_process_dir(process_dir);

		Scene.v().loadNecessaryClasses();
		run();
	}
	
	public static void run() {
		Assembly assembly = new Assembly("passar nos parametros", "passar nos parametros", new Date(), "Java");
		
		//itera nas classes
		for (Iterator<SootClass> klassIt = Scene.v().getApplicationClasses().iterator(); klassIt.hasNext();) {
			final SootClass klass = (SootClass) klassIt.next();
			
			Type type = new Type(assembly, klass.getName(), "ver como pegar isso");
			System.out.println(type.getName());
			
			List<SootMethod> methods = klass.getMethods();
			//itera nos métodos
			for (Iterator<SootMethod> methodsIt = methods.iterator(); methodsIt.hasNext(); ) {
				SootMethod sootMethod = (SootMethod) methodsIt.next();
				
				Method method = new Method(type, sootMethod.getName(), getVisibility(sootMethod.getModifiers()));
				System.out.println(method.getName());
				System.out.println(method.getVisibility());
				
				sootMethod.retrieveActiveBody();
				
				ArrayList<Unit> listTry= new ArrayList<Unit>();
				int qtdCatch = 0; // não existem dois Traps pra um mesmo catch
				ArrayList<Unit> listFinally= new ArrayList<Unit>();
				int qtdThrow = 0;
				
				UnitGraph graph = new TrapUnitGraph(sootMethod.getActiveBody());
				for (Iterator<Unit> graphIt = graph.iterator(); graphIt.hasNext();) { //itera nos statements atrás de throws
					Unit unit = graphIt.next();
					if (unit instanceof JThrowStmt) {
						qtdThrow++;
					}
				}
				
				for (Iterator<Trap> i = sootMethod.getActiveBody().getTraps().iterator(); i.hasNext();) {
					Trap box = i.next();
					if (box instanceof Trap) {
						Trap trap = (Trap) box;
						
						if (trap.getException().getName().equals("java.lang.Throwable")) { //é um finally
							if (!listFinally.contains(trap.getHandlerUnit())) {
								listFinally.add(trap.getHandlerUnit());
								qtdThrow--;
							}
							if (trap.getHandlerUnit() == trap.getEndUnit()) { //é um novo try, sem catchs
								listTry.add(trap.getEndUnit());
							}
							//System.out.println("Finally");
						} else { //é um catch
							if (!listTry.contains(trap.getEndUnit())) {
								listTry.add(trap.getEndUnit());
							}
							qtdCatch++;
							//System.out.println(trap.getException());							
						}

						//System.out.println(box);
					}
				}
				
				method.setQtdTry(listTry.size());
				method.setQtdCatch(qtdCatch);
				method.setQtdFinally(listFinally.size());
				method.setQtdThrow(qtdThrow);
				System.out.printf("    Qtd Try: %d\n", method.getQtdTry());
				System.out.printf("  Qtd Catch: %d\n", method.getQtdCatch());
				System.out.printf("Qtd Finally: %d\n", method.getQtdFinally());
				System.out.printf("  Qtd Throw: %d\n", method.getQtdThrow());

				System.out.println();
	        }
		}
	}
	
	public static String getVisibility(int modifiers) {
		
		if ((modifiers | soot.Modifier.PUBLIC) == modifiers) {
			return "public";
		} else if ((modifiers | soot.Modifier.PRIVATE) == modifiers) {
			return "private";
		} else if ((modifiers | soot.Modifier.PROTECTED) == modifiers) {
			return "protected";
		} else {
			return "unknown";
		} 
	}
	protected static Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/flow_analysis?" + "user=root&password=456852");
			System.out.println("It seems I got the connection... :" + conn);
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
		return conn;
	}
}

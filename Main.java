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
		Assembly assembly = new Assembly();
		assembly.setName("passar nos parametros");
		assembly.setVersion("passar nos parametros");
		assembly.setCreatedAt(new Date());
		assembly.setLanguage("Java");
		
		//itera nas classes
		for (Iterator<SootClass> klassIt = Scene.v().getApplicationClasses().iterator(); klassIt.hasNext();) {
			final SootClass klass = (SootClass) klassIt.next();
			//if( klass.isPhantom() ) continue;
			System.out.println(klass.getName());
			
			List<SootMethod> methods = klass.getMethods();
			//itera nos métodos
			for (Iterator<SootMethod> methodsIt = methods.iterator(); methodsIt.hasNext(); ) {
				SootMethod method = (SootMethod) methodsIt.next();
				System.out.println(method.getName());
				System.out.println(method.getExceptions()); //exceções lançadas pelo método
				
				method.retrieveActiveBody();
				ArrayList<Unit> listTry= new ArrayList<Unit>();
				int qtdCatch = 0; // não existem dois Traps pra um mesmo catch
				ArrayList<Unit> listFinally= new ArrayList<Unit>();
				
				int qtdThrow = 0;
				UnitGraph graph = new TrapUnitGraph(method.getActiveBody());
				for (Iterator<Unit> graphIt = graph.iterator(); graphIt.hasNext();) {
					Unit unit = graphIt.next();
					if (unit instanceof JThrowStmt) {
						qtdThrow++;
						//JThrowStmt throwStmt = (JThrowStmt) unit;
						//System.out.println(throwStmt);
						//System.out.println(throwStmt.getOp().getType());
					}
					System.out.println(unit);
				}
				
				for (Iterator<Trap> i = method.getActiveBody().getTraps().iterator(); i.hasNext();) {
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
							System.out.println("Finally");
						} else { //é um catch
							if (!listTry.contains(trap.getEndUnit())) {
								listTry.add(trap.getEndUnit());
							}
							qtdCatch++;
							System.out.println(trap.getException());							
						}

						System.out.println(box);
					}
				}
				
				System.out.printf("    Qtd Try: %d\n", listTry.size());
				System.out.printf("  Qtd Catch: %d\n", qtdCatch);
				System.out.printf("Qtd Finally: %d\n", listFinally.size());
				System.out.printf("Qtd Throw: %d\n", qtdThrow);

				System.out.println();
	        }
		}
	}
	
	public static Connection getConnection() {
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

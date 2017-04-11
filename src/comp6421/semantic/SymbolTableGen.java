/**
 * 
 */
package comp6421.semantic;

import comp6421.Utils;
import comp6421.scanner.EType;
import comp6421.scanner.Token;
import comp6421.semantic.ExtendParser.ActionCallback;
import comp6421.semantic.migration.MigrationContext;
import comp6421.semantic.migration.MigrationStrategy;
import comp6421.semantic.strategy.TableStrategy;

/**
 * This class
 * 
 * @author Zhen Du
 * @date Mar 16, 2017
 */
public class SymbolTableGen implements ActionCallback {
	
	public static String SOURCE_FILE = "./res/symbol/program_symbol.txt";
	public static String OUTPUT = "./res/symbol/out/symbol_table.txt";
	public static String ERROR = "./res/symbol/out/error.txt";
	
	private String error = "";
	private String preAction = "";
//	private SymbolTable globalTable;
//	private HashMap<String, SymbolTable> functionTable;
//	private HashMap<String, SymbolTable> classTable;
//	private HashMap<String, SymbolTable> programTable;
	/**
	 * Constructors for
	 * 
	 */
	public SymbolTableGen(boolean migration) {
		TableContext.getInstance().showMigration = migration;
	}
	
	public SymbolTableGen() {
		TableContext.getInstance().init();
	}

	public SymbolTableGen(String file) {
		SOURCE_FILE = file;
		TableContext.getInstance().init();
	}

//	private void buildGlobal(String name){}
//	private void buildFunction(String name){}
//	private void buildClass(String name){}
//	private void buildProgram(String name){}
	/**
	 * This method
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SymbolTableGen g = new SymbolTableGen();
		g.genTable(g);
	}

	public void genTable(ActionCallback cb) {
		ExtendParser parser = new ExtendParser(false, cb);
		parser.doParser(SOURCE_FILE);
		printLog();
	}
	
	private void printLog() {
		STable s = TableContext.getCurrentScope();
		
		Utils.echo2File(OUTPUT, s.toString());
		if(error.length()>0) {
			System.out.println("Semantic Error");
		} else {
			System.out.println("Semantic Pass");
		}
		System.out.println("\nSymbol Table is:\n\n"+s.toString());
		Utils.echo2File(ERROR, error);
	}
	
//	@Override
//	public void createTable(SemanticAction action, Token t) {
//		try {
//			action.execute(t);
//		} catch (CompilerError e) {
//			System.out.println("Line "+t.getPosition() + ", " +e.getMessage());
//			error+="Line "+t.getPosition() + ", " +e.getMessage()+"\n";
//		}
//		System.out.println("Create table:" + action.getClass().getName() + ", Token:"+ t);
//	}
//
//	@Override
//	public void createEntry(SemanticAction action, Token t) {
//		try {
//			action.execute(t);
//		} catch (CompilerError e) {
//			System.out.println("Line "+t.getPosition() + ", " +e.getMessage());
//			error+="Line "+t.getPosition() + ", " +e.getMessage()+"\n";
//		}
//		System.out.println("\t"+"Insert entry:" + action.getClass().getName() + ", Token:"+ t);
//	}
	boolean recordMigration = false;
	String token = "";
	@Override
	public void createTable(String action, Token p, Token c) {
		if(("sem_PushVariableName".equals(preAction)||"sem_EndAdditionExpression".equals(preAction)) && "sem_PushVariableName".equals(action) && c.getTYPE()==EType.OPENPAR) {
			return;
		}
		if(action.startsWith("sym_")) {
			TableStrategy a = StrategyFactor.getSymbolStategy(action);
			if(action.startsWith("sym_CreateClass")) {
				System.out.println("Create Class :" + action.getClass().getName() + ", Token:"+ p);
			} else if(action.startsWith("sym_CreateFunction")) {
				System.out.println("Create Function:" + action.getClass().getName() + ", Token:"+ p);
			} else if(action.startsWith("sym_CreateProgram")) {
				System.out.println("Create Program:" + action.getClass().getName() + ", Token:"+ p);
			} else if(action.startsWith("sym_CreateVariable")) {
				System.out.println("\tInsert Variable:" + action.getClass().getName() + ", Token:"+ p);
			}
			try {
				a.execute(p);
			} catch (SemanticException e) {
				System.out.println("Line "+p.getPosition() + ", " +e.getMessage());
				error+="Line "+p.getPosition() + ", " +e.getMessage()+"\n";
			}
		} else {
			MigrationStrategy a = StrategyFactor.getMigrationStategy(action);
			if(a != null) {
				if(TableContext.getInstance().showMigration&&token.length()==0) {
					if("sem_StartRelationExpression".equals(action)&&p.getTYPE()==EType.ASSGN) {
						recordMigration = true;
						System.out.println("Start Attribute Migration:\n");
						System.out.println(String.format("%-70s", String.format("%-10s", "action")+"\t"+"stack")+"expr\n");
					}
					if(recordMigration) {
						String stack = preAction.substring(4,  14) + "\t" + MigrationContext.instance.getStackString();
						System.out.println(String.format("%-70s", stack)+""+token);
					}
				}
				if(MigrationContext.instance.getStackString().length()==0) {
					recordMigration = false;
				}
//			if(SymbolContext.getInstance().showMigration&&"sem_EndRelationExpression".equals(action)) {
//				recordMigration = false;
//			}
				try {
					a.execute(p);
				} catch (SemanticException e) {
					System.out.println("Line "+p.getPosition() + ", " +e.getMessage());
					error+="Line "+p.getPosition() + ", " +e.getMessage()+"\n";
				}
				if(TableContext.getInstance().showMigration) {
					if(recordMigration&&!token.endsWith(p.getValue())) {
						token+=p.getValue();
					}
					if("sem_StartRelationExpression".equals(action)&&p.getTYPE()==EType.ASSGN) {
						recordMigration = true;
					}
					if(recordMigration) {
						String stack = action.substring(4,  action.length()<14?action.length():14) + "\t" + MigrationContext.instance.getStackString();
						System.out.println(String.format("%-70s", stack)+""+token);
					}
				} 
			}
		}
		preAction = action;
	}

}

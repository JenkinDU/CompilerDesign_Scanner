/**
 * 
 */
package comp6421.semantic;

import java.util.HashMap;

import comp6421.Utils;
import comp6421.scanner.EType;
import comp6421.scanner.Token;
import comp6421.semantic.ExtendParser.ActionCallback;
import comp6421.semantic.perform.SemanticAction;

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
	public SymbolTableGen() {
		
	}
	
	public SymbolTableGen(String file) {
		SOURCE_FILE = file;
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
		SymbolTable s = SymbolContext.getCurrentScope();
		
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

	@Override
	public void createTable(String action, Token p, Token c) {
		if("sem_PushVariableName".equals(preAction) && "sem_PushVariableName".equals(action) && c.getTYPE()==EType.OPENPAR) {
			return;
		}
		SemanticAction a = StrategyFactor.getStategy(action);
		if(a != null) {
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
			} catch (CompilerError e) {
				System.out.println("Line "+p.getPosition() + ", " +e.getMessage());
				error+="Line "+p.getPosition() + ", " +e.getMessage()+"\n";
			}
		}
		preAction = action;
	}

}

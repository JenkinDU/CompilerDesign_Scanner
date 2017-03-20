/**
 * 
 */
package comp6421.semantic;

import java.util.HashMap;

import comp6421.Utils;
import comp6421.scanner.Token;
import comp6421.semantic.ExtendParser.STCallback;
import comp6421.semantic.perform.SymbolAction;

/**
 * This class
 * 
 * @author Zhen Du
 * @date Mar 16, 2017
 */
public class SymbolTableGen implements STCallback {
	
	public static String SOURCE_FILE = "./res/symbol/program_symbol.txt";
	public static String OUTPUT = "./res/symbol/out/symbol_table.txt";
	public static String ERROR = "./res/symbol/out/error.txt";
	
	private String error = "";
	private SymbolTable globalTable;
	private HashMap<String, SymbolTable> functionTable;
	private HashMap<String, SymbolTable> classTable;
	private HashMap<String, SymbolTable> programTable;
	/**
	 * Constructors for
	 * 
	 */
	public SymbolTableGen() {
		
	}
	
	private void buildGlobal(String name){}
	private void buildFunction(String name){}
	private void buildClass(String name){}
	private void buildProgram(String name){}
	/**
	 * This method
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SymbolTableGen g = new SymbolTableGen();
		ExtendParser parser = new ExtendParser(false, g);
		parser.doParser(SOURCE_FILE);
		g.printLog();
	}

	private void printLog() {
		SymbolTable s = SymbolContext.getCurrentScope();
		
		Utils.echo2File(OUTPUT, s.toString());
		System.out.println("\n\nSymbol Table is:\n\n"+s.toString());
		Utils.echo2File(ERROR, error);
	}
	
	@Override
	public void createTable(SymbolAction action, Token t) {
		try {
			action.execute(t);
		} catch (CompilerError e) {
			e.printStackTrace();
			error+=e.getMessage()+"\n";
		}
		System.out.println("Create table:" + action.getClass().getName() + ", Token:"+ t);
	}

	@Override
	public void createEntry(SymbolAction action, Token t) {
		try {
			action.execute(t);
		} catch (CompilerError e) {
			e.printStackTrace();
			error+=e.getMessage()+"\n";
		}
		System.out.println("\t"+"Insert entry:" + action.getClass().getName() + ", Token:"+ t);
	}

	@Override
	public void lookUpVariable(String name, String path) {
		System.out.println("lookUpVariable at" + name);
	}

}

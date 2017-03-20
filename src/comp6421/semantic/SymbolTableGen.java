/**
 * 
 */
package comp6421.semantic;

import java.util.HashMap;

import comp6421.Utils;
import comp6421.scanner.Token;
import comp6421.semantic.ExtendParser.STCallback;
import comp6421.semantic.IEntry.Kind;
import comp6421.semantic.perform.SymbolAction;

/**
 * This class
 * 
 * @author Zhen Du
 * @date Mar 16, 2017
 */
public class SymbolTableGen implements STCallback {
	
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
		parser.doParser("D:/program.txt");
		SymbolTable s = SymbolContext.getCurrentScope();
		Utils.echo2File("./res/symbol/out/symbol_table.txt", s.toString());
		System.out.println(s.toString());
	}

	@Override
	public void createTable(SymbolAction action, Token t) throws CompilerError {
		action.execute(t);
		System.out.println("Create table:" + action.toString() + ",Token:"+ t);
	}

	@Override
	public void createEntry(String name, Kind kind, Attribute type, 
			String link, String sign, String path) {
		System.out.println("Insert entry to table:" + name);
	}

	@Override
	public void lookUpVariable(String name, String path) {
		System.out.println("lookUpVariable at" + name);
	}

}

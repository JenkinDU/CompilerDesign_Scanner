/**
 * 
 */
package comp6421.semantic;

import java.util.HashMap;

import comp6421.semantic.ExtendParser.STCallback;
import comp6421.semantic.IEntry.Kind;
import comp6421.semantic.ITable.Scope;

/**
 * This class
 * 
 * @author Zhen Du
 * @date Mar 16, 2017
 */
public class Generator implements STCallback {
	
	private SymbolTable globalTable;
	private HashMap<String, SymbolTable> functionTable;
	private HashMap<String, SymbolTable> classTable;
	private HashMap<String, SymbolTable> programTable;
	/**
	 * Constructors for
	 * 
	 */
	public Generator() {
		
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
		Generator g = new Generator();
		ExtendParser parser = new ExtendParser(false, g);
		parser.doParser("./res/symbol/program_symbol.txt");
	}

	@Override
	public void createTable(String name, Scope scope) {
		System.out.println("Create table:" + name + ",scope:"+ scope);
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

/**
 * 
 */
package comp6421.semantic;

import java.util.HashMap;

/**
 * This class
 * 
 * @author Zhen Du
 * @date Mar 16, 2017
 */
public class Generator {

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

	}

}

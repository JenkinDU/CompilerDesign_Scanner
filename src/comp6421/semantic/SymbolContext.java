package comp6421.semantic;

import comp6421.semantic.entry.SymbolTableEntry;
import comp6421.semantic.entry.SymbolTableEntryType;
//import comp6421.semantic.expression.VariableExpressionFragment;

public final class SymbolContext {

//	final static SymbolContext instance;
//	
//	static {
//		instance = new SymbolContext();
//	}
	
	private static SymbolContext instance;

	public static synchronized SymbolContext getInstance() {
		if (instance == null) {
			instance = new SymbolContext();
		}
		return instance;
	} 
	 
	public SymbolTable currentSymbolTable;
	public SymbolTableEntryType storedType;
	public String storedId;
	public FunctionEntry storedFunction;
	public boolean skipNextCloseScope;
//	public VariableExpressionFragment variableBuilder;
	
	private SymbolContext(){
		init();
	}

	public void init() {
		currentSymbolTable = new SymbolTable(null);
		storedType = null;
		storedId = null;
		skipNextCloseScope = false;
	}
	
	public static void reset(){
		instance.init();
	}

	public static String printableString() {
		return instance.currentSymbolTable.toString();
	}

	public static SymbolTableEntry find(String name) {
		return instance.currentSymbolTable.find(name);
	}

	public static SymbolTable getCurrentScope() {
		return instance.currentSymbolTable;
	}
	

	
	
}

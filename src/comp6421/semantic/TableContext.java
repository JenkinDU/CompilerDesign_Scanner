package comp6421.semantic;

import comp6421.semantic.entry.STEntry;
import comp6421.semantic.migration.VariableExpressionFragment;
import comp6421.semantic.entry.EntryType;

public class TableContext {

	public boolean showMigration = false;
	private static TableContext instance;
	public STable current;
	public EntryType type;
	public String id;
	public FunctionEntry function;
	public boolean skip;
	public VariableExpressionFragment variableBuilder;
	
	public static synchronized TableContext getInstance() {
		if (instance == null) {
			instance = new TableContext();
		}
		return instance;
	}
	
	private TableContext(){
		init();
	}

	public void init() {
		current = new STable(null);
		type = null;
		id = null;
		skip = false;
	}

	public static String stringValue() {
		return getInstance().current.toString();
	}

	public static STEntry find(String name) {
		return getInstance().current.find(name);
	}

	public static STable getCurrentScope() {
		return getInstance().current;
	}
}

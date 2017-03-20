package comp6421.semantic.entry;

import comp6421.semantic.FunctionEntry;
import comp6421.semantic.SymbolTable;

public class MemberFunctionEntry extends FunctionEntry {

	public MemberFunctionEntry(String name, SymbolTableEntryType returnType, SymbolTable table) {
		super(name, returnType, table);
	}

}

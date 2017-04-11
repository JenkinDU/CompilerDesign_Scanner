package comp6421.semantic.entry;

import comp6421.semantic.FunctionEntry;
import comp6421.semantic.STable;

public class MemberFunctionEntry extends FunctionEntry {

	public MemberFunctionEntry(String name, EntryType returnType, STable table) {
		super(name, returnType, table);
	}

}

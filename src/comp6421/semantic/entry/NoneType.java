package comp6421.semantic.entry;

import comp6421.semantic.SymbolTable;

public class NoneType implements SymbolTableEntryType {

	@Override
	public int getSize() {
		return 0;
	}

	@Override
	public SymbolTable getScope() {
		return null;
	}

	@Override
	public String toString() {
		return " ";
	}
}

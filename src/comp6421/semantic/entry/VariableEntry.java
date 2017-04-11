package comp6421.semantic.entry;

import comp6421.semantic.IType;
import comp6421.semantic.STEntry;

public class VariableEntry extends STEntry {

	public VariableEntry(String name, IType type) {
		super(name, Kind.Variable, type, null);
	}

	@Override
	protected int getEntrySize() {
		return getType().getSize();
	}

}

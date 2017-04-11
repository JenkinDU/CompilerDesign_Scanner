package comp6421.semantic.entry;

import comp6421.semantic.IType;
import comp6421.semantic.STEntry;

public class ParameterEntry extends STEntry {

	public ParameterEntry(String name, IType type) {
		super(name, Kind.Parameter, type, null);
	}

	@Override
	protected int getEntrySize() {
		return 4;
	}

}

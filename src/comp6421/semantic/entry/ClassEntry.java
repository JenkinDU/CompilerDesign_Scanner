package comp6421.semantic.entry;

import comp6421.semantic.STable;
import comp6421.semantic.SemanticException;

public class ClassEntry extends STEntry {

	public ClassEntry(String name, STable scope) {
		super(name, Kind.Class, null, scope);
	}

	@Override
	protected int calculateSize() throws SemanticException {
		int size = 0;
		for (STEntry e : getScope().getEntries()) {
			if (e instanceof VariableEntry) {
				size += e.getSize();
			}
		}
		return size;
	}

	@Override
	public EntryType getType() {
		return new ClassType(this);
	}
}

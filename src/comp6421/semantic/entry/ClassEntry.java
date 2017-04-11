package comp6421.semantic.entry;

import comp6421.semantic.ClassType;
import comp6421.semantic.IType;
import comp6421.semantic.STEntry;
import comp6421.semantic.STable;

public class ClassEntry extends STEntry {

	public ClassEntry(String name, STable scope) {
		super(name, Kind.Class, null, scope);
	}

	@Override
	protected int getEntrySize() {
		int size = 0;
		for (STEntry e : getScope().getEntries()) {
			if (e instanceof VariableEntry) {
				size += e.getSize();
			}
		}
		return size;
	}

	@Override
	public IType getType() {
		return new ClassType(this);
	}
}

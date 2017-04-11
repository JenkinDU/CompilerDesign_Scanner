package comp6421.semantic.entry;

import comp6421.semantic.SemanticException;
import comp6421.semantic.STable;

public class ClassType implements EntryType {

	private ClassEntry classEntry;

	public ClassType(ClassEntry classEntry) {
		this.classEntry = classEntry;
	}

	@Override
	public int getSize() throws SemanticException {
		return classEntry.getSize();
	}

	@Override
	public String toString() {
		return classEntry.getName();
	}

	@Override
	public STable getScope() {
		return classEntry.getScope();
	}

	@Override
	public boolean equals(Object other) {
		return other instanceof ClassType && ((ClassType) other).classEntry.getName().equals(classEntry.getName());
	}

}

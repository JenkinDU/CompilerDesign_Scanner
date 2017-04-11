package comp6421.semantic;

import comp6421.semantic.entry.ClassEntry;

public class ClassType implements IType {

	private ClassEntry classEntry;

	public ClassType(ClassEntry classEntry) {
		this.classEntry = classEntry;
	}

	@Override
	public int getSize() {
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

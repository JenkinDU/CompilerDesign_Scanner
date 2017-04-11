package comp6421.semantic.entry;

import comp6421.semantic.STable;

public class NoneType implements EntryType {

	@Override
	public int getSize() {
		return 0;
	}

	@Override
	public STable getScope() {
		return null;
	}

	@Override
	public String toString() {
		return " ";
	}
}

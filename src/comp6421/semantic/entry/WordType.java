package comp6421.semantic.entry;

import comp6421.semantic.STable;

public class WordType implements EntryType {

	private final String name;

	public WordType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public boolean equals(Object other) {
		return other instanceof WordType && name.equals(((WordType) other).name);
	}

	@Override
	public int getSize() {
		return 4;
	}

	@Override
	public STable getScope() {
		return null;
	}
}

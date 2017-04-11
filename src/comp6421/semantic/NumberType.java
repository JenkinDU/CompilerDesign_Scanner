package comp6421.semantic;

public class NumberType implements IType {

	private final String name;

	public NumberType(String name) {
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
		return other instanceof NumberType && name.equals(((NumberType) other).name);
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

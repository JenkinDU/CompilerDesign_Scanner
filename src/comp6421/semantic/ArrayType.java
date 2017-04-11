package comp6421.semantic;

import java.util.List;

public class ArrayType implements IType {

	private final IType type;
	private final List<Integer> dimensions;

	public ArrayType(IType type, List<Integer> dimensions) {
		this.type = type;
		this.dimensions = dimensions;
	}

	public IType getType() {
		return type;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(type);
		for (Integer i : dimensions) {
			sb.append('[');
			sb.append(i);
			sb.append(']');
		}
		return sb.toString();
	}

	public void pushDimension(int dim) {
		dimensions.add(dim);
	}

	@Override
	public int getSize() {
		int size = getType().getSize();
		for (Integer i : dimensions) {
			size *= i;
		}
		return size;
	}

	public int getDimension(int i) {
		return dimensions.get(i);
	}

	@Override
	public STable getScope() {
		return null;
	}

}

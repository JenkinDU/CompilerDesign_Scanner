package comp6421.semantic.entry;

import java.util.Collections;
import java.util.List;

import comp6421.semantic.STable;

public class FunctionType implements EntryType {

	private EntryType returnType;
	private List<EntryType> argumentTypes;

	public FunctionType(EntryType returnType, List<EntryType> argumentTypes) {
		this.returnType = returnType;
		this.argumentTypes = argumentTypes;
	}

	public EntryType getReturnType() {
		return returnType;
	}

	public List<EntryType> getArgumentTypes() {
		return Collections.unmodifiableList(argumentTypes);
	}

	public void pushParameter(EntryType param) {
		argumentTypes.add(param);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append('(');

		if (argumentTypes.size() > 0) {
			// sb.append("this");//argumentTypes.get(0));
		}
		for (int i = 1; i < argumentTypes.size(); ++i) {
			if (i != 1)
				sb.append(", ");
			sb.append(argumentTypes.get(i));
		}
		sb.append(") :");
		sb.append(returnType==null?" ":returnType);
		sb.append("");

		return sb.toString();
	}

	@Override
	public boolean equals(Object other) {
		if (other instanceof FunctionType) {
			FunctionType o = (FunctionType) other;
			if (returnType.equals(o.returnType)) {
				if (argumentTypes.size() == o.argumentTypes.size()) {
					for (int i = 0; i < argumentTypes.size(); ++i) {
						if (!argumentTypes.get(i).equals(o.argumentTypes.get(i))) {
							return false;
						}
					}
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	@Override
	public int getSize() {
		throw new RuntimeException("Why do you want the size of a function type? Calling this makes no sense!");
	}

	@Override
	public STable getScope() {
		return null;
	}

}

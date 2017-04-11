package comp6421.semantic;

import java.util.Collections;
import java.util.List;

public class FunctionType implements IType {

	private IType returnType;
	private List<IType> argumentTypes;

	public FunctionType(IType returnType, List<IType> argumentTypes) {
		this.returnType = returnType;
		this.argumentTypes = argumentTypes;
	}

	public IType getReturnType() {
		return returnType;
	}

	public List<IType> getArgumentTypes() {
		return Collections.unmodifiableList(argumentTypes);
	}

	public void pushParameter(IType param) {
		argumentTypes.add(param);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append('(');

		for (int i = 1; i < argumentTypes.size(); ++i) {
			if (i != 1)
				sb.append(", ");
			sb.append(argumentTypes.get(i));
		}
		sb.append(") :");
		sb.append(returnType == null ? " " : returnType);
		sb.append("");

		return sb.toString();
	}

	@Override
	public int getSize() {
		return 0;
	}

	@Override
	public STable getScope() {
		return null;
	}

}

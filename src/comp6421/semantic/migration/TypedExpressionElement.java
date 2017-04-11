package comp6421.semantic.migration;

import comp6421.semantic.IType;
import comp6421.semantic.code.MathOperation;

public abstract class TypedExpressionElement extends Expression {

	public abstract IType getType();

	public TypedExpressionElement getFirst() {
		return null;
	};

	public TypedExpressionElement getSecond() {
		return null;
	};

	public MathOperation getOperation() {
		return null;
	};

}

package comp6421.semantic.migration;

import comp6421.semantic.code.MathOperation;
import comp6421.semantic.entry.EntryType;

public abstract class TypedExpressionElement extends ExpressionElement {

	public abstract EntryType getType();

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

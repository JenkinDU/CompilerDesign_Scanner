package comp6421.semantic.expression;

import comp6421.semantic.code.MathOperation;
import comp6421.semantic.entry.SymbolTableEntryType;

public abstract class TypedExpressionElement extends ExpressionElement {

	public abstract SymbolTableEntryType getType();
	public TypedExpressionElement getFirst(){return null;};
	public TypedExpressionElement getSecond(){return null;};
	public MathOperation getOperation(){return null;};
	
}

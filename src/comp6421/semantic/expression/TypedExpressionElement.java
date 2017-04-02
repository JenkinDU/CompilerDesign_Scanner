package comp6421.semantic.expression;

import comp6421.semantic.entry.SymbolTableEntryType;

public abstract class TypedExpressionElement extends ExpressionElement {

	public abstract SymbolTableEntryType getType();

}

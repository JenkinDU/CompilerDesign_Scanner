package comp6421.semantic.expression;

import comp6421.semantic.entry.PrimitiveType;
import comp6421.semantic.entry.SymbolTableEntryType;
import comp6421.semantic.value.StaticFloatValue;
import comp6421.semantic.value.Value;

public class FloatLiteralExpressionElement extends TypedExpressionElement {

	private final float i;
	
	public FloatLiteralExpressionElement(float i){
		this.i = i;
	}
	
	@Override
	public Value getValue() {
		return new StaticFloatValue(i);
	}

	@Override
	public SymbolTableEntryType getType() {
		return new PrimitiveType("float");
	}

}

package comp6421.semantic.migration;

import comp6421.semantic.entry.EntryType;
import comp6421.semantic.entry.NumberType;
import comp6421.semantic.value.StaticFloatValue;
import comp6421.semantic.value.Value;

public class FloatLiteralExpressionElement extends TypedExpressionElement {

	private final float i;

	public FloatLiteralExpressionElement(float i) {
		this.i = i;
	}

	@Override
	public Value getValue() {
		return new StaticFloatValue(i);
	}

	@Override
	public EntryType getType() {
		return new NumberType("float");
	}

}

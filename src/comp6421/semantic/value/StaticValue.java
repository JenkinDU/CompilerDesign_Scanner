package comp6421.semantic.value;

import comp6421.semantic.CompilerError;
import comp6421.semantic.code.CodeGenerationContext;

public abstract class StaticValue implements Value {

	public abstract int intValue();
	
	public abstract float floatValue();
	
	@Override
	public Value getUseableValue(CodeGenerationContext c) throws CompilerError {
		return this;
	}
	
	@Override
	public boolean isStatic() {
		return true;
	}
	
	@Override
	public boolean equals(Object obj) {
		return obj instanceof StaticValue && intValue() == ((StaticValue)obj).intValue();
	}

	
}

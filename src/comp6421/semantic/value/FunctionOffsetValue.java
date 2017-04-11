package comp6421.semantic.value;

import comp6421.semantic.SemanticException;
import comp6421.semantic.code.CodeGenerationContext;

public abstract class FunctionOffsetValue implements Value {

	public abstract Value get() throws SemanticException;
	
	@Override
	public Value getUseableValue(CodeGenerationContext c) throws SemanticException {
		return get().getUseableValue(c);
	}

	@Override
	public RegisterValue getRegisterValue(CodeGenerationContext c) throws SemanticException {
		return get().getRegisterValue(c);
	}

	@Override
	public boolean isStatic() {
		return false;
	}
}

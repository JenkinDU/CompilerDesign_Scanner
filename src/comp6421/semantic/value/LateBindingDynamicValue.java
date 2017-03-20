package comp6421.semantic.value;

import comp6421.semantic.CompilerError;
import comp6421.semantic.code.CodeGenerationContext;

public abstract class LateBindingDynamicValue extends DynamicValue {

	public abstract DynamicValue get() throws CompilerError;
	
	@Override
	public Value getUseableValue(CodeGenerationContext c) throws CompilerError {
		return get().getUseableValue(c);
	}

	@Override
	public RegisterValue getRegisterValue(CodeGenerationContext c) throws CompilerError {
		return get().getRegisterValue(c);
	}

}

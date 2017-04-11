package comp6421.semantic.value;

import comp6421.semantic.SemanticException;
import comp6421.semantic.code.CodeGenerationContext;

public abstract class LateBindingDynamicValue extends DynamicValue {

	public abstract DynamicValue get() throws SemanticException;
	
	@Override
	public Value getUseableValue(CodeGenerationContext c) throws SemanticException {
		return get().getUseableValue(c);
	}

	@Override
	public RegisterValue getRegisterValue(CodeGenerationContext c) throws SemanticException {
		return get().getRegisterValue(c);
	}

}

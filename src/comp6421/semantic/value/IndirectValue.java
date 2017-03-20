package comp6421.semantic.value;

import comp6421.semantic.CompilerError;
import comp6421.semantic.code.CodeGenerationContext;

public class IndirectValue extends DynamicValue {

	Value v;
	
	public IndirectValue(Value value) {
		this.v = value;
	}

	@Override
	public Value getUseableValue(CodeGenerationContext c) throws CompilerError {
		return v.getUseableValue(c);
	}

	@Override
	public RegisterValue getRegisterValue(CodeGenerationContext c) throws CompilerError {
		return v.getRegisterValue(c);
	}



}

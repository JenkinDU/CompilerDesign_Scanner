package comp6421.semantic.value;

import comp6421.semantic.CompilerError;
import comp6421.semantic.code.CodeGenerationContext;
import comp6421.semantic.code.Register;

public class RegisterValue extends DynamicValue {

	private final Register register;
	
	public RegisterValue(Register reg){
		this.register = reg;
	}

	public Register getRegister() {
		return register;
	}
	
	@Override
	public String toString() {
		return register.symbolicName;
	}
	
	@Override
	public boolean equals(Object other) {
		return other instanceof RegisterValue && ((RegisterValue)other).getRegister().equals(register);
	}

	@Override
	public Value getUseableValue(CodeGenerationContext c) {
		return this;
	}

	@Override
	public RegisterValue getRegisterValue(CodeGenerationContext c) throws CompilerError {
		return this;
	}
	
	
}

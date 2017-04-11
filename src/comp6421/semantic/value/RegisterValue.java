package comp6421.semantic.value;

import comp6421.semantic.SemanticException;
import comp6421.semantic.code.CodeGenerationContext;
import comp6421.semantic.code.Register;

public class RegisterValue implements Value {// extends DynamicValue {

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
	public RegisterValue getRegisterValue(CodeGenerationContext c) throws SemanticException {
		return this;
	}

	@Override
	public boolean isStatic() {
		return true;
	}
	
	
}

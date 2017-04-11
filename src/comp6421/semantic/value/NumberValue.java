package comp6421.semantic.value;

import comp6421.semantic.SemanticException;
import comp6421.semantic.code.AddWordImmediateInstruction;
import comp6421.semantic.code.CodeGenerationContext;
import comp6421.semantic.code.Register;

public class NumberValue implements Value {

	private final int v;

	public NumberValue() {
		this.v = 0;
	}

	public NumberValue(int v) {
		this.v = v;
	}

	public int intValue() {
		return v;
	}

	@Override
	public String toString() {
		return Integer.toString(v);
	}

	@Override
	public boolean equals(Object other) {
		return other instanceof NumberValue && ((NumberValue) other).intValue() == v;
	}

	@Override
	public RegisterValue getRegisterValue(CodeGenerationContext c) throws SemanticException {
		RegisterValue reg = new RegisterValue(c.getTemporaryRegister());

		c.appendInstruction(new AddWordImmediateInstruction(reg.getRegister(), Register.ZERO, v));

		return reg;
	}

	@Override
	public Value getUseableValue(CodeGenerationContext c) throws SemanticException {
		return this;
	}

	@Override
	public boolean isStatic() {
		return true;
	}
}

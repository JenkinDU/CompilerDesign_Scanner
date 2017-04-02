package comp6421.semantic.value;

import comp6421.semantic.CompilerError;
import comp6421.semantic.code.AddWordImmediateInstruction;
import comp6421.semantic.code.CodeGenerationContext;
import comp6421.semantic.code.Register;

public class StaticIntValue extends StaticValue implements Value {

	private final int v;
	
	public StaticIntValue(int v){
		this.v = v;
	}
	
	@Override
	public int intValue() {
		return v;
	}

	@Override
	public float floatValue() {
		return (float) v;
	}

	@Override
	public String toString() {
		return Integer.toString(v);
	}
	
	@Override
	public boolean equals(Object other) {
		return other instanceof StaticIntValue && ((StaticIntValue)other).intValue() == v;
	}

	@Override
	public RegisterValue getRegisterValue(CodeGenerationContext c) throws CompilerError {
		RegisterValue reg = new RegisterValue(c.getTemporaryRegister());
		
		c.appendInstruction(new AddWordImmediateInstruction(reg.getRegister(), Register.ZERO, v));
		
		return reg;
	}
	
}

package comp6421.semantic.value;

import comp6421.semantic.CompilerError;
import comp6421.semantic.code.AddWordImmediateInstruction;
import comp6421.semantic.code.CodeGenerationContext;
import comp6421.semantic.code.Register;

public class StaticFloatValue extends StaticValue implements Value {

	private final float v;
	
	public StaticFloatValue(float v){
		this.v = v;
	}
	
	@Override
	public float floatValue() {
		return (float) v;
	}

	@Override
	public String toString() {
		return Float.toString(v);
	}
	
	@Override
	public boolean equals(Object other) {
		return other instanceof StaticFloatValue && ((StaticFloatValue)other).floatValue() == v;
	}

	@Override
	public RegisterValue getRegisterValue(CodeGenerationContext c) throws CompilerError {
		RegisterValue reg = new RegisterValue(c.getTemporaryRegister());
		
		c.appendInstruction(new AddWordImmediateInstruction(reg.getRegister(), Register.ZERO, intValue()));
		
		return reg;
	}

	@Override
	public int intValue() {
		return (int)v;
	}
	
}

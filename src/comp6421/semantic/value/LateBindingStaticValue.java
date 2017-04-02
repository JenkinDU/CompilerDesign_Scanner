package comp6421.semantic.value;

import comp6421.semantic.CompilerError;
import comp6421.semantic.code.CodeGenerationContext;

public abstract class LateBindingStaticValue extends StaticValue {

	public abstract StaticValue get();
	
	@Override
	public Value getUseableValue(CodeGenerationContext c) throws CompilerError {
		return get();
	}

	@Override
	public RegisterValue getRegisterValue(CodeGenerationContext c) throws CompilerError {
		return get().getRegisterValue(c);
	}

	@Override
	public int intValue() {
		System.err.println("Warning: calling intValue of a LateBindigValue may have inconsistent results");
		return get().intValue();
	}

	@Override
	public float floatValue() {
		System.err.println("Warning: calling floatValue of a LateBindigValue may have inconsistent results");
		return get().floatValue();
	}

}

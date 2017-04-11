package comp6421.semantic.value;

import comp6421.semantic.SemanticException;
import comp6421.semantic.InternalCompilerError;
import comp6421.semantic.code.CodeGenerationContext;


public class VoidValue implements Value {

	private VoidValue(){}
	
	private static VoidValue instance = new VoidValue();
	
	public static VoidValue get(){
		return instance;
	}

	@Override
	public Value getUseableValue(CodeGenerationContext c) throws InternalCompilerError {
		throw new InternalCompilerError("Cannot get value of a Void Expression");
	}

	@Override
	public boolean isStatic() {
		return false;
	}

	@Override
	public RegisterValue getRegisterValue(CodeGenerationContext c) throws SemanticException {
		throw new InternalCompilerError("Cannot get register value of a Void Expression");
	}
}

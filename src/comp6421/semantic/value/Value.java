package comp6421.semantic.value;

import comp6421.semantic.SemanticException;
import comp6421.semantic.code.CodeGenerationContext;

public interface Value {

	public Value getUseableValue(CodeGenerationContext c) throws SemanticException;

	public boolean isStatic();

	/**
	 * Generate code to convert this value into a Register value.
	 * In the case of a dynamic (run-time) value, this is the same as getUseableValue
	 * 
	 * In the case of a static (compile-time) value, this is the same as calling
	 * getUseableValue and then storing the result in a register.
	 * 
	 * @param c The current code generation context
	 * 
	 * @return A dynamic version of this value
	 * @throws SemanticException 
	 */
	public RegisterValue getRegisterValue(CodeGenerationContext c) throws SemanticException;
	
}

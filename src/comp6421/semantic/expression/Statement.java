package comp6421.semantic.expression;

import comp6421.semantic.CompilerError;
import comp6421.semantic.code.CodeGenerationContext;

public interface Statement {

	public void generateCode(CodeGenerationContext c) throws CompilerError;
	
	public String pseudoCode();
	
}

package comp6421.semantic.migration;

import comp6421.semantic.SemanticException;
import comp6421.semantic.code.CodeGenerationContext;

public interface Statement {

	public void generateCode(CodeGenerationContext c) throws SemanticException;
	
	public String pseudoCode();
	
}

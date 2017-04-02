package comp6421.semantic.perform;

import comp6421.scanner.Token;
import comp6421.semantic.CompilerError;
import comp6421.semantic.FunctionEntry;
import comp6421.semantic.SymbolTable;
import comp6421.semantic.entry.NoneType;
import comp6421.semantic.expression.ExpressionContext;

public class CreateProgramAction extends SymbolAction {

	@Override
	public void execute(Token precedingToken) throws CompilerError {
		
		FunctionEntry program = new FunctionEntry("program", new NoneType(), new SymbolTable(context.currentSymbolTable));
		
		context.currentSymbolTable.add(program);
		
		context.currentSymbolTable = program.getScope();
		
		ExpressionContext.setCurrentFunction(program);
		
	}

}

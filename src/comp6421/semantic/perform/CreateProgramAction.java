package comp6421.semantic.perform;

import comp6421.scanner.Token;
import comp6421.semantic.SemanticException;
import comp6421.semantic.FunctionEntry;
import comp6421.semantic.STable;
import comp6421.semantic.entry.NoneType;
import comp6421.semantic.expression.ExpressionContext;

public class CreateProgramAction extends TableStrategy {

	@Override
	public void execute(Token precedingToken) throws SemanticException {
		
		FunctionEntry program = new FunctionEntry("program", new NoneType(), new STable(context.current));
		
		context.current.add(program);
		
		context.current = program.getScope();
		
		ExpressionContext.setCurrentFunction(program);
		
	}

}

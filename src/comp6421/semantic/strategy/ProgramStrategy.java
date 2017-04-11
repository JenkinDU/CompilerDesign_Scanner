package comp6421.semantic.strategy;

import comp6421.scanner.Token;
import comp6421.semantic.FunctionEntry;
import comp6421.semantic.STable;
import comp6421.semantic.SemanticException;
import comp6421.semantic.migration.MigrationContext;

public class ProgramStrategy extends TableStrategy {

	@Override
	public void execute(Token precedingToken) throws SemanticException {

		FunctionEntry program = new FunctionEntry("program", null, new STable(context.current));
		context.current.add(program);
		context.current = program.getScope();
		MigrationContext.setCurrentFunction(program);
	}
}

package comp6421.semantic.strategy;

import comp6421.scanner.Token;
import comp6421.semantic.SemanticException;
import comp6421.semantic.code.Register;
import comp6421.semantic.entry.VariableEntry;
import comp6421.semantic.entry.NumberType;
import comp6421.semantic.migration.MigrationContext;

public class FunctionStrategy extends TableStrategy {

	@Override
	public void execute(Token precedingToken) throws SemanticException {
		if (context.function != null) {
			context.current.add(context.function);
			context.current = context.function.getScope();

			MigrationContext.setCurrentFunction(context.function);
			VariableEntry returnPcAddr = new VariableEntry(Register.RETURN_ADDRESS_PARAMETER_NAME, new NumberType("int"));
			context.current.add(returnPcAddr);
			MigrationContext.setCurrentFunction(context.function);
		}
	}
}

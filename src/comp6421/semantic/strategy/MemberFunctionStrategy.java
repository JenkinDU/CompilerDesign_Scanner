package comp6421.semantic.strategy;

import comp6421.scanner.Token;
import comp6421.semantic.STable;
import comp6421.semantic.SemanticException;
import comp6421.semantic.code.Register;
import comp6421.semantic.entry.MemberFunctionEntry;
import comp6421.semantic.entry.ParameterEntry;

public class MemberFunctionStrategy extends TableStrategy {

	@Override
	public void execute(Token token) throws SemanticException {
		if (context.current.exists(context.id)) {
			context.function = null;
			context.skip = true;

			throw new SemanticException("Multiply function declaration: " + context.id);
		} else {
			STable table = new STable(context.current);

			ParameterEntry thisParam = new ParameterEntry(Register.THIS_POINTER_NAME,
					context.current.getEnclosingEntry().getType());

			context.function = new MemberFunctionEntry(context.id, context.type, table);
			context.function.addParameter(thisParam);

			table.setEnclosingEntry(context.function);

		}
	}

}

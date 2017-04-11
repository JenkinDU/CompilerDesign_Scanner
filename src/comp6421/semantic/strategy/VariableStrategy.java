package comp6421.semantic.strategy;

import comp6421.scanner.Token;
import comp6421.semantic.STEntry;
import comp6421.semantic.SemanticException;
import comp6421.semantic.entry.VariableEntry;

public class VariableStrategy extends TableStrategy {

	@Override
	public void execute(Token token) throws SemanticException {
		if (context.current.exists(context.id)) {
			throw new SemanticException("Multiply defined id: " + context.id);
		} else {
			STEntry entry = new VariableEntry(context.id, context.type);
			context.current.add(entry);
		}
	}

}

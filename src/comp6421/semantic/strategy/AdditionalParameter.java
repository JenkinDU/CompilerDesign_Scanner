package comp6421.semantic.strategy;

import comp6421.scanner.Token;
import comp6421.semantic.SemanticException;
import comp6421.semantic.entry.ParameterEntry;

public class AdditionalParameter extends TableStrategy {

	@Override
	public void execute(Token precedingToken) throws SemanticException {
		if (context.function != null) {
			context.function.addParameter(new ParameterEntry(context.id, context.type));
		}
	}

}

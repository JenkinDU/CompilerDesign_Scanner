package comp6421.semantic.perform;

import comp6421.scanner.Token;
import comp6421.semantic.SemanticException;
import comp6421.semantic.entry.ParameterEntry;

public class AddFunctionParameterAction extends TableStrategy {
	
	@Override
	public void execute(Token precedingToken) throws SemanticException {
		if (context.function != null) context.function.addParameter(new ParameterEntry(context.id, context.type));
	}

}

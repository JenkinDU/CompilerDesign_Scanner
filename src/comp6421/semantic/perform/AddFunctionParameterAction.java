package comp6421.semantic.perform;

import comp6421.scanner.Token;
import comp6421.semantic.CompilerError;
import comp6421.semantic.entry.ParameterEntry;

public class AddFunctionParameterAction extends SymbolAction {
	
	@Override
	public void execute(Token precedingToken) throws CompilerError {
		if (context.storedFunction != null) context.storedFunction.addParameter(new ParameterEntry(context.storedId, context.storedType));
	}

}

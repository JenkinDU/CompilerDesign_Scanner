package comp6421.semantic.perform;

import comp6421.scanner.Token;

public class StoreIdAction extends SymbolAction {
	
	@Override
	public void execute(Token precedingToken) {
		context.storedId = precedingToken.lexeme;
	}

}

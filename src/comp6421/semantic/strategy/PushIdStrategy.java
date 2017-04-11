package comp6421.semantic.strategy;

import comp6421.scanner.Token;

public class PushIdStrategy extends TableStrategy {

	@Override
	public void execute(Token precedingToken) {
		context.id = precedingToken.lexeme;
	}

}

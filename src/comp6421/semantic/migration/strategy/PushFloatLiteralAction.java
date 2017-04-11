package comp6421.semantic.migration.strategy;

import comp6421.scanner.Token;
import comp6421.semantic.SemanticException;
import comp6421.semantic.migration.MigrationStrategy;

public class PushFloatLiteralAction extends MigrationStrategy {

	@Override
	public void execute(Token precedingToken) throws SemanticException {
		try{
			context.getCurrent().pushFloatLiteral(Float.valueOf(precedingToken.lexeme));
		}catch(NumberFormatException e){
			throw new SemanticException("Invalid float literal: " + e.getMessage());
		}
	}

}

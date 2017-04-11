package comp6421.semantic.migration.strategy;

import comp6421.scanner.Token;
import comp6421.semantic.SemanticException;
import comp6421.semantic.migration.MigrationStrategy;

public class PushIntLiteralAction extends MigrationStrategy {

	@Override
	public void execute(Token precedingToken) throws SemanticException {
		try{
			context.getCurrent().pushIntLiteral(Integer.valueOf(precedingToken.getValue()));
		}catch(NumberFormatException e){
			throw new SemanticException("Invalid int literal: " + e.getMessage());
		}
	}

}

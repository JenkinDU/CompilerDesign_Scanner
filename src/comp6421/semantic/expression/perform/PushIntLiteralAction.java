package comp6421.semantic.expression.perform;

import comp6421.scanner.Token;
import comp6421.semantic.SemanticException;
import comp6421.semantic.expression.ExpressionAction;

public class PushIntLiteralAction extends ExpressionAction {

	@Override
	public void execute(Token precedingToken) throws SemanticException {
		try{
			context.getCurrent().pushIntLiteral(Integer.valueOf(precedingToken.lexeme));
		}catch(NumberFormatException e){
			throw new SemanticException("Invalid int literal: " + e.getMessage());
		}
	}

}

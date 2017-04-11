package comp6421.semantic.expression.perform;

import comp6421.scanner.Token;
import comp6421.semantic.SemanticException;
import comp6421.semantic.expression.ExpressionAction;

public class PushFloatLiteralAction extends ExpressionAction {

	@Override
	public void execute(Token precedingToken) throws SemanticException {
		try{
			context.getCurrent().pushFloatLiteral(Float.valueOf(precedingToken.lexeme));
		}catch(NumberFormatException e){
			throw new SemanticException("Invalid float literal: " + e.getMessage());
		}
	}

}

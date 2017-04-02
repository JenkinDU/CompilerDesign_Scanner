package comp6421.semantic.expression.perform;

import comp6421.scanner.Token;
import comp6421.semantic.CompilerError;
import comp6421.semantic.expression.ExpressionAction;

public class PushFloatLiteralAction extends ExpressionAction {

	@Override
	public void execute(Token precedingToken) throws CompilerError {
		try{
			context.getCurrent().pushFloatLiteral(Float.valueOf(precedingToken.lexeme));
		}catch(NumberFormatException e){
			throw new CompilerError("Invalid float literal: " + e.getMessage());
		}
	}

}

package comp6421.semantic.expression.perform;

import comp6421.scanner.Token;
import comp6421.semantic.CompilerError;
import comp6421.semantic.expression.ExpressionAction;

public class PushIntLiteralAction extends ExpressionAction {

	@Override
	public void execute(Token precedingToken) throws CompilerError {
		try{
			context.getCurrent().pushIntLiteral(Integer.valueOf(precedingToken.lexeme));
		}catch(NumberFormatException e){
			throw new CompilerError("Invalid int literal: " + e.getMessage());
		}
	}

}

package comp6421.semantic.expression.perform;

import comp6421.scanner.Token;
import comp6421.semantic.CompilerError;
import comp6421.semantic.code.MathOperation;
import comp6421.semantic.expression.ExpressionAction;

public class PushMultiplicationOperationAction extends ExpressionAction {

	@Override
	public void execute(Token precedingToken) throws CompilerError {
		context.getCurrent().pushMultiplicationOperator(
				MathOperation.fromToken(precedingToken.lexeme));
	}

}

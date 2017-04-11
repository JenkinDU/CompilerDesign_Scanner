package comp6421.semantic.expression.perform;

import comp6421.scanner.Token;
import comp6421.semantic.SemanticException;
import comp6421.semantic.code.MathOperation;
import comp6421.semantic.expression.ExpressionAction;

public class PushRelationOperationAction extends ExpressionAction {

	@Override
	public void execute(Token precedingToken) throws SemanticException {
		context.getCurrent().pushRelationOperator(
				MathOperation.fromToken(precedingToken.lexeme));
	}

}

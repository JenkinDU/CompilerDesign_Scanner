package comp6421.semantic.expression.perform;

import comp6421.scanner.Token;
import comp6421.semantic.SemanticException;
import comp6421.semantic.expression.ExpressionAction;
import comp6421.semantic.expression.ExpressionElement;
import comp6421.semantic.expression.MultiplicationExpressionFragment;

public class StartMultiplicationExpressionAction extends ExpressionAction {

	@Override
	public void execute(Token precedingToken) throws SemanticException {
		ExpressionElement e = new MultiplicationExpressionFragment();
		context.pushChild(e);
	}

}

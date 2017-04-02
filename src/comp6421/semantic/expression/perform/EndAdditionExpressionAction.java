package comp6421.semantic.expression.perform;

import comp6421.scanner.Token;
import comp6421.semantic.CompilerError;
import comp6421.semantic.expression.AdditionExpressionFragment;
import comp6421.semantic.expression.ExpressionAction;
import comp6421.semantic.expression.ExpressionElement;

public class EndAdditionExpressionAction extends ExpressionAction {

	@Override
	public void execute(Token precedingToken) throws CompilerError {
		ExpressionElement top = context.getCurrent();
		if(top instanceof AdditionExpressionFragment){
			context.finishTopElement();
		}else{
			// throw new InternalCompilerError("Expected " + AdditionExpressionFragment.class.getName() + " but was " + top.getClass().getName());
		}
	}

}

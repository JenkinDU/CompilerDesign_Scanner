package comp6421.semantic.expression.perform;

import comp6421.scanner.Token;
import comp6421.semantic.SemanticException;
import comp6421.semantic.expression.ExpressionAction;

public class FinishVariableAction extends ExpressionAction {

	@Override
	public void execute(Token precedingToken) throws SemanticException {

		try{
			context.finishTopElement();
		}catch(RuntimeException e){
			System.out.println("wooooooopsy " + e);
		}		
	}
}

package comp6421.semantic.expression.perform;

import comp6421.scanner.Token;
import comp6421.semantic.CompilerError;
import comp6421.semantic.expression.ExpressionAction;

public class FinishVariableAction extends ExpressionAction {

	@Override
	public void execute(Token precedingToken) throws CompilerError {

		try{
			context.finishTopElement();
		}catch(RuntimeException e){
			System.out.println("wooooooopsy " + e);
		}		
	}
}

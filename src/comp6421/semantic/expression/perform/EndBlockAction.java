package comp6421.semantic.expression.perform;

import comp6421.scanner.Token;
import comp6421.semantic.CompilerError;
import comp6421.semantic.InternalCompilerError;
import comp6421.semantic.expression.ExpressionAction;
import comp6421.semantic.expression.ExpressionElement;
import comp6421.semantic.expression.StatementBlock;

public class EndBlockAction extends ExpressionAction {

	@Override
	public void execute(Token precedingToken) throws CompilerError {
		ExpressionElement top = context.getCurrent();
		if(top instanceof StatementBlock){
			context.finishTopElement();
		}else{
			throw new InternalCompilerError("Expected " + StatementBlock.class.getName() + " but was " + top.getClass().getName());
		}
	}

}

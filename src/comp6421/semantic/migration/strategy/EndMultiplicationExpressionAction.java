package comp6421.semantic.migration.strategy;

import comp6421.scanner.Token;
import comp6421.semantic.SemanticException;
import comp6421.semantic.migration.ExpressionElement;
import comp6421.semantic.migration.MigrationStrategy;
import comp6421.semantic.migration.MultiplicationExpressionFragment;

public class EndMultiplicationExpressionAction extends MigrationStrategy {

	@Override
	public void execute(Token precedingToken) throws SemanticException {
		ExpressionElement top = context.getCurrent();
		if(top instanceof MultiplicationExpressionFragment){
			context.finishTopElement();
		}else{
			//throw new InternalCompilerError("Expected " + MultiplicationExpressionFragment.class.getName() + " but was " + top.getClass().getName());
		}
	}

}

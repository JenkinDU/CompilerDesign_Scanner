package comp6421.semantic.migration.strategy;

import comp6421.scanner.Token;
import comp6421.semantic.SemanticException;
import comp6421.semantic.migration.ExpressionElement;
import comp6421.semantic.migration.MigrationStrategy;
import comp6421.semantic.migration.MultiplicationExpressionFragment;

public class StartMultiplicationExpressionAction extends MigrationStrategy {

	@Override
	public void execute(Token precedingToken) throws SemanticException {
		ExpressionElement e = new MultiplicationExpressionFragment();
		context.pushChild(e);
	}

}

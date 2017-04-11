package comp6421.semantic.migration.strategy;

import comp6421.scanner.Token;
import comp6421.semantic.SemanticException;
import comp6421.semantic.migration.AdditionExpressionFragment;
import comp6421.semantic.migration.Expression;
import comp6421.semantic.migration.FunctionCallExpressionFragment;
import comp6421.semantic.migration.MigrationStrategy;
import comp6421.semantic.migration.MultiplicationExpressionFragment;
import comp6421.semantic.migration.RelationExpressionFragment;
import comp6421.semantic.migration.StatementBlock;

public class EndMigrationStrategy extends MigrationStrategy {

	@Override
	public void execute(Token precedingToken) throws SemanticException {
		Expression top = context.getCurrent();
		if (top instanceof AdditionExpressionFragment || top instanceof StatementBlock
				|| top instanceof FunctionCallExpressionFragment || top instanceof MultiplicationExpressionFragment
				|| top instanceof RelationExpressionFragment) {
			context.finishTopElement();
		}
	}

}

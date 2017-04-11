package comp6421.semantic.migration.strategy;

import comp6421.scanner.Token;
import comp6421.semantic.SemanticException;
import comp6421.semantic.migration.ExpressionElement;
import comp6421.semantic.migration.ForStatement;
import comp6421.semantic.migration.MigrationStrategy;

public class StartForStatementAction extends MigrationStrategy {

	@Override
	public void execute(Token precedingToken) throws SemanticException {
		ExpressionElement e = new ForStatement();
		context.pushChild(e);
	}

}

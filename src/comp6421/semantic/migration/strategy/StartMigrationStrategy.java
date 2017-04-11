package comp6421.semantic.migration.strategy;

import comp6421.scanner.Token;
import comp6421.semantic.SemanticException;
import comp6421.semantic.migration.ExpressionElement;
import comp6421.semantic.migration.MigrationStrategy;

public class StartMigrationStrategy extends MigrationStrategy {
	ExpressionElement express;
	public StartMigrationStrategy(ExpressionElement e) {
		express = e;
	}
	@Override
	public void execute(Token precedingToken) throws SemanticException {
		context.pushChild(express);
	}

}

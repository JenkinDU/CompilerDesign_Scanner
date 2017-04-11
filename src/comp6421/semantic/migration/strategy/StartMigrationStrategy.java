package comp6421.semantic.migration.strategy;

import comp6421.scanner.Token;
import comp6421.semantic.SemanticException;
import comp6421.semantic.migration.Expression;
import comp6421.semantic.migration.MigrationStrategy;

public class StartMigrationStrategy extends MigrationStrategy {
	Expression express;

	public StartMigrationStrategy(Expression e) {
		express = e;
	}

	@Override
	public void execute(Token precedingToken) throws SemanticException {
		context.pushChild(express);
	}

}

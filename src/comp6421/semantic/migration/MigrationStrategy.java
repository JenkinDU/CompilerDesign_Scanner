package comp6421.semantic.migration;

import comp6421.scanner.Token;
import comp6421.semantic.SemanticException;

public abstract class MigrationStrategy {
	protected MigrationContext context = MigrationContext.instance;

	public abstract void execute(Token precedingToken) throws SemanticException;
}

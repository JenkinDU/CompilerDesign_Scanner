package comp6421.semantic.strategy;

import comp6421.scanner.Token;
import comp6421.semantic.SemanticException;

public interface MigrationStrtegy {

	public abstract void execute(Token precedingToken) throws SemanticException;

}

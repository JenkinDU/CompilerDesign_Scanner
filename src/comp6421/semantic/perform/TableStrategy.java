package comp6421.semantic.perform;

import comp6421.scanner.Token;
import comp6421.semantic.SemanticException;
import comp6421.semantic.TableContext;

public abstract class TableStrategy {

	protected final static TableContext context = TableContext.getInstance();
	public abstract void execute(Token precedingToken) throws SemanticException;
}

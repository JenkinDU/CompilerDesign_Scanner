package comp6421.semantic.strategy;

import comp6421.scanner.EType;
import comp6421.scanner.Token;
import comp6421.semantic.STable;
import comp6421.semantic.SemanticException;
import comp6421.semantic.entry.ClassEntry;
import comp6421.semantic.entry.STEntry;

public class ClassStrategy extends TableStrategy {

	@Override
	public void execute(Token token) throws SemanticException {
		String name;
		if (token.getTYPE() == EType.ID) {
			name = token.getValue();
			if (context.current.exists(name)) {
				context.skip = true;
				throw new SemanticException("Multiply class declaration: " + name);
			} else {
				STable table = new STable(context.current);
				STEntry entry = new ClassEntry(name, table);
				context.current.add(entry);
				context.current = table;
			}
		}
	}
}

package comp6421.semantic.strategy;

import comp6421.scanner.Token;
import comp6421.semantic.ClassType;
import comp6421.semantic.NumberType;
import comp6421.semantic.STEntry;
import comp6421.semantic.SemanticException;
import comp6421.semantic.entry.ClassEntry;

public class PushTypeStrategy extends TableStrategy {

	@Override
	public void execute(Token precedingToken) throws SemanticException {
		String typeName = precedingToken.getValue();
		if (typeName.equals("int") || typeName.equals("float")) {
			context.type = new NumberType(precedingToken.getValue());
		} else {
			STEntry entry = context.current.find(typeName);
			if (entry instanceof ClassEntry) {
				context.type = new ClassType((ClassEntry) entry);
			} else {
				throw new SemanticException("Undefined type variable" + typeName);
			}
		}
	}

}

package comp6421.semantic.perform;

import comp6421.scanner.EType;
import comp6421.scanner.Token;
import comp6421.semantic.SemanticException;
import comp6421.semantic.STable;
import comp6421.semantic.entry.ClassEntry;
import comp6421.semantic.entry.STEntry;

public class CreateClassAction extends TableStrategy {

	
	@Override
	public void execute(Token token) throws SemanticException {
		String name;
		if(token.getTYPE() == EType.ID){
			name = token.lexeme;
			if( ! context.current.exists(name)){
				STable table = new STable(context.current);
				STEntry entry = new ClassEntry(name, table);
				context.current.add(entry);
				context.current = table;
				
			}else{
				context.skip = true;
				throw new SemanticException("Multiply class declaration: " + name);
			}
		}
	}

}

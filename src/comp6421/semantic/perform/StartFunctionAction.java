package comp6421.semantic.perform;

import comp6421.scanner.Token;
import comp6421.semantic.SemanticException;
import comp6421.semantic.FunctionEntry;
import comp6421.semantic.STable;

public class StartFunctionAction extends TableStrategy {
	
	@Override
	public void execute(Token token) throws SemanticException {
		if(context.current.exists(context.id)){
			context.function = null;
			context.skip = true;
			
			throw new SemanticException("Multiply function declaration: " + context.id);
		}else{
			STable table = new STable(context.current);
			
			context.function         = new FunctionEntry(context.id, context.type, table);
			
		}
	}

}

package comp6421.semantic.perform;

import comp6421.scanner.Token;
import comp6421.semantic.CompilerError;
import comp6421.semantic.FunctionEntry;
import comp6421.semantic.SymbolTable;

public class StartFunctionAction extends SymbolAction {
	
	@Override
	public void execute(Token token) throws CompilerError {
		if(context.currentSymbolTable.exists(context.storedId)){
			context.storedFunction = null;
			context.skipNextCloseScope = true;
			
			throw new CompilerError("Multiply function declaration: " + context.storedId);
		}else{
			SymbolTable table = new SymbolTable(context.currentSymbolTable);
			
			context.storedFunction         = new FunctionEntry(context.storedId, context.storedType, table);
			
		}
	}

}

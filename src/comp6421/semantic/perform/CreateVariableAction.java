package comp6421.semantic.perform;

import comp6421.scanner.Token;
import comp6421.semantic.CompilerError;
import comp6421.semantic.entry.SymbolTableEntry;
import comp6421.semantic.entry.VariableEntry;

public class CreateVariableAction extends SymbolAction {
	
	@Override
	public void execute(Token token) throws CompilerError {
		if(context.currentSymbolTable.exists(context.storedId)){
			throw new CompilerError("Multiply defined id: " + context.storedId);
		}else{
			SymbolTableEntry entry = new VariableEntry(context.storedId, context.storedType);
			context.currentSymbolTable.add(entry);
		}
	}

}

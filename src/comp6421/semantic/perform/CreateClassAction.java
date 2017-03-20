package comp6421.semantic.perform;

import comp6421.scanner.EType;
import comp6421.scanner.Token;
import comp6421.semantic.CompilerError;
import comp6421.semantic.SymbolTable;
import comp6421.semantic.entry.ClassEntry;
import comp6421.semantic.entry.SymbolTableEntry;

public class CreateClassAction extends SymbolAction {

	
	@Override
	public void execute(Token token) throws CompilerError {
		// verify that current scope allows class definition? 
		//  ^ or is this handled by the syntax
		//   ^ Pretty sure this is handled by syntax
		
		// verify that class does not already exist
		
		String name;
		if(token.getTYPE() == EType.ID){
			name = token.lexeme;
		}else{
			throw new RuntimeException("precedingToken must be an id for " + getClass().getName());
		}
		
		if( ! context.currentSymbolTable.exists(name)){
			// Create a new symbol table for the new Class
			SymbolTable table = new SymbolTable(context.currentSymbolTable);
			// Create a new Symbol table entry for the new class
			SymbolTableEntry entry = new ClassEntry(name, table);
			// Add the new entry to the current symbol table
			context.currentSymbolTable.add(entry);
			// Set the new table as the current table
			context.currentSymbolTable = table;
			
		}else{
			context.skipNextCloseScope = true;
			throw new CompilerError("Duplicate class declaration: " + name);
		}
	}

}

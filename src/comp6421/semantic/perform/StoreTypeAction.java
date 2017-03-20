package comp6421.semantic.perform;

import comp6421.scanner.Token;
import comp6421.semantic.CompilerError;
import comp6421.semantic.entry.ClassEntry;
import comp6421.semantic.entry.ClassType;
import comp6421.semantic.entry.PrimitiveType;
import comp6421.semantic.entry.SymbolTableEntry;

public class StoreTypeAction extends SymbolAction {

	@Override
	public void execute(Token precedingToken) throws CompilerError {
		String typeName = precedingToken.lexeme;
		if(typeName.equals("int") || typeName.equals("float")){
			context.storedType = new PrimitiveType(precedingToken.lexeme);
		}else{
			SymbolTableEntry entry = context.currentSymbolTable.find(typeName);
			if(entry instanceof ClassEntry){
				context.storedType = new ClassType((ClassEntry) entry);
			}else{
				throw new CompilerError("unknown type " + typeName);
			}
		}
	}

}

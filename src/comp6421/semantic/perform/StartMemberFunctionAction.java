package comp6421.semantic.perform;

import comp6421.scanner.Token;
import comp6421.semantic.CompilerError;
import comp6421.semantic.SymbolTable;
import comp6421.semantic.code.SpecialValues;
import comp6421.semantic.entry.MemberFunctionEntry;
import comp6421.semantic.entry.ParameterEntry;

public class StartMemberFunctionAction extends SymbolAction {
	
	@Override
	public void execute(Token token) throws CompilerError {
		if(context.currentSymbolTable.exists(context.storedId)){
			context.storedFunction = null;
			context.skipNextCloseScope = true;
			
			throw new CompilerError("Multiply function declaration: " + context.storedId);
		}else{
			SymbolTable table = new SymbolTable(context.currentSymbolTable);
			
			ParameterEntry thisParam = new ParameterEntry(SpecialValues.THIS_POINTER_NAME, context.currentSymbolTable.getEnclosingEntry().getType());
						
			context.storedFunction = new MemberFunctionEntry(context.storedId, context.storedType, table);
			context.storedFunction.addParameter(thisParam);
			
			table.setEnclosingEntry(context.storedFunction);
			
		}
	}

}

package comp6421.semantic.perform;

import comp6421.scanner.Token;
import comp6421.semantic.CompilerError;
//import comp6421.semantic.code.SpecialValues;
import comp6421.semantic.entry.PrimitiveType;
import comp6421.semantic.entry.VariableEntry;
//import comp6421.semantic.expression.ExpressionContext;

public class CreateFunctionAction extends SymbolAction {
	
	@Override
	public void execute(Token precedingToken) throws CompilerError {
		if(context.storedFunction != null){
			context.currentSymbolTable.add(context.storedFunction);
			context.currentSymbolTable = context.storedFunction.getScope();
			
//			ExpressionContext.setCurrentFunction(context.storedFunction);
			
			// Adding these pseudo-parameters ensures that all stack-frame offsets will make sense.
			//
			// '@' sign guarantees no name collisions
//			VariableEntry returnPcAddr    = new VariableEntry(SpecialValues.RETURN_ADDRESS_PARAMETER_NAME, new PrimitiveType("int"));

			//table.add(returnValueAddr);
//			context.currentSymbolTable.add(returnPcAddr);

//			ExpressionContext.setCurrentFunction(context.storedFunction);
		}
	}
}

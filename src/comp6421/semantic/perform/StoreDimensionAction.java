package comp6421.semantic.perform;

import java.util.ArrayList;

import comp6421.scanner.Token;
import comp6421.semantic.entry.ArrayType;

public class StoreDimensionAction extends SymbolAction {
	
	@Override
	public void execute(Token precedingToken) {

		ArrayType type;
		if( context.storedType instanceof ArrayType){
			type = (ArrayType) context.storedType;
		}else{
			type = new ArrayType(context.storedType, new ArrayList<Integer>());
			context.storedType = type;
		}
		
		type.pushDimension(Integer.parseInt(precedingToken.lexeme));
		
	}

}

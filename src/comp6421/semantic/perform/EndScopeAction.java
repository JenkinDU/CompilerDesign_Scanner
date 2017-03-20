package comp6421.semantic.perform;

import comp6421.scanner.Token;
import comp6421.semantic.SymbolTable;

public class EndScopeAction extends SymbolAction {
	
	@Override
	public void execute(Token token) {
		if(context.skipNextCloseScope){
			context.skipNextCloseScope = false;
		}else{
			SymbolTable parent = context.currentSymbolTable.getParent();
			if(parent != null){
				context.currentSymbolTable = parent;
			}else{
				throw new RuntimeException("Tried to close global scope");
			}
		}
	}

}

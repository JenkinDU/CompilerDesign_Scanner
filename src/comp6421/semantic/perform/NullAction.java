package comp6421.semantic.perform;

import comp6421.scanner.Token;

public class NullAction extends SymbolAction {

	private NullAction() {}
	
	public final static NullAction instance = new NullAction();
	
	@Override
	public void execute(Token precedingToken) {
		throw new RuntimeException(getClass().getName() + "Should never actually end up being called");
	}

}

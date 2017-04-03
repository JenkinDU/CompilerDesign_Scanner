package comp6421.semantic;

import comp6421.parser.Parser;
import comp6421.scanner.Token;

public class ExtendParser extends Parser {

	interface ActionCallback {
		void createTable(String action, Token p, Token c);
	}
	private ActionCallback cb;
	
	public ExtendParser(boolean show, ActionCallback cb) {
		super(show);
		this.cb = cb;
	}

	@Override
	protected void createSymbolTable(String action, Token p, Token c) {
		System.out.println(action);
		cb.createTable(action, p, c);
	}

}

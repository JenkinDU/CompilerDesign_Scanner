package comp6421.semantic;

import comp6421.parser.Parser;
import comp6421.scanner.Token;
import comp6421.semantic.IEntry.Kind;
import comp6421.semantic.ITable.Scope;

public class ExtendParser extends Parser {

	interface STCallback {
		void createTable(String name, Scope scope);
		void createEntry(String name, Kind kind, Attribute type, 
				String link, String sign, String path);
		void lookUpVariable(String name, String path);
	}
	
	private STCallback callback;
	public ExtendParser(boolean show, STCallback cb) {
		super(show);
		this.callback = cb;
	}

	@Override
	protected void createSymbolTable(String action, Token p, Token c) {
		if("sym_CreateClassScope".equals(action)) {
			this.callback.createTable("Global", Scope.GLOBAL);
		}
	}

}

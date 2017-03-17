package comp6421.semantic;

import java.util.LinkedList;

import comp6421.parser.Parser;
import comp6421.parser.Symbol;
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
	protected void createSymbolTable(LinkedList<Symbol> stack, String[] exValue) {
		Symbol top = stack.peek();
		if("prog".equals(top.getValue())) {
			this.callback.createTable("Global", Scope.GLOBAL);
		}
	}

}

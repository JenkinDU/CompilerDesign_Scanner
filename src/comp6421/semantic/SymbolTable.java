package comp6421.semantic;

import java.util.HashMap;

public class SymbolTable implements ITable {

	private HashMap<String, Entry> entry;
	private Scope scope = Scope.UNKNOWN;
	private String name = null;
	
	public SymbolTable() {
		
	}

	@Override
	public void create(String Tn) {

	}

	@Override
	public void search(String Tn, String i, String ptr, boolean found) {

	}

	@Override
	public void insert(String Tn, String i, String ptr) {

	}

	@Override
	public void delete(String Tn) {

	}

	@Override
	public void print(String Tn) {

	}

}

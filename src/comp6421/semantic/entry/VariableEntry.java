package comp6421.semantic.entry;

import comp6421.semantic.SemanticException;

public class VariableEntry extends STEntry {

	public VariableEntry(String name, EntryType type) {
		super(name, Kind.Variable, type, null);
	}

	@Override
	protected int calculateSize() throws SemanticException {
		try{
			return getType().getSize();
		}catch(Throwable e){
			return 0;
		}
	}
	
	
}

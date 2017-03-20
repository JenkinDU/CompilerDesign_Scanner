package comp6421.semantic.entry;

import comp6421.semantic.CompilerError;

public class VariableEntry extends SymbolTableEntry {

	public VariableEntry(String name, SymbolTableEntryType type) {
		super(name, Kind.Variable, type, null);
	}

	@Override
	protected int calculateSize() throws CompilerError {
		try{
			return getType().getSize();
		}catch(Throwable e){
			return 0;
		}
	}
	
	
}

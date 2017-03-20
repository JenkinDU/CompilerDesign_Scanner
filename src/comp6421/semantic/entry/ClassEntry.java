package comp6421.semantic.entry;

import comp6421.semantic.CompilerError;
import comp6421.semantic.SymbolTable;

public class ClassEntry extends SymbolTableEntry {

	public ClassEntry(String name, SymbolTable scope) {
		super(name, Kind.Class, null, scope);
	}

	@Override
	protected int calculateSize() throws CompilerError {
		int size = 0;
		for(SymbolTableEntry e : getScope().getEntries()){
			if(e instanceof VariableEntry){
				size += e.getSize();
			}
		}
		return size;
	}

	
	@Override
	public SymbolTableEntryType getType() {
		return new ClassType(this);
	}
}

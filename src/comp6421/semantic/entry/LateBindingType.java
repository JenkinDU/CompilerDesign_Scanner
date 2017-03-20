package comp6421.semantic.entry;

import comp6421.semantic.CompilerError;
import comp6421.semantic.SymbolTable;

public abstract class LateBindingType implements SymbolTableEntryType {

	public abstract SymbolTableEntryType get() throws CompilerError;
	
	@Override
	public int getSize() throws CompilerError {
		return get().getSize();
	}

	@Override
	public SymbolTable getScope() throws CompilerError {
		return get().getScope();
	}

}

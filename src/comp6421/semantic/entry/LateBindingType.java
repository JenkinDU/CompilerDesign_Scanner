package comp6421.semantic.entry;

import comp6421.semantic.SemanticException;
import comp6421.semantic.STable;

public abstract class LateBindingType implements EntryType {

	public abstract EntryType get() throws SemanticException;

	@Override
	public int getSize() throws SemanticException {
		return get().getSize();
	}

	@Override
	public STable getScope() throws SemanticException {
		return get().getScope();
	}

}

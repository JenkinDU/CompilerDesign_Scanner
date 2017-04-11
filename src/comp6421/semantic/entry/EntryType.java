package comp6421.semantic.entry;

import comp6421.semantic.SemanticException;
import comp6421.semantic.STable;

public interface EntryType {

	int getSize() throws SemanticException;

	STable getScope() throws SemanticException;

}

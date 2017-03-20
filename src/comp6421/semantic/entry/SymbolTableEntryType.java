package comp6421.semantic.entry;

import comp6421.semantic.CompilerError;
import comp6421.semantic.SymbolTable;

public interface SymbolTableEntryType {

	/**
	 * Get the amount of memory required to store a value of this type.
	 * 
	 * @return the size as described above in bytes
	 * @throws  
	 */
	int getSize() throws CompilerError;

	/**
	 * for a class type return that class type's scope, for all other types,
	 * returns `null`.
	 * @return The scope for this type
	 */
	SymbolTable getScope() throws CompilerError;

}

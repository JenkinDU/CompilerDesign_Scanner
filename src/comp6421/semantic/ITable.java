package comp6421.semantic;

import comp6421.semantic.entry.SymbolTableEntry;

public interface ITable {
	enum Scope {
		GLOBAL, FUNCTION, CLASS, PROGRAM, UNKNOWN
	}
	
	void create(String Tn);
	SymbolTableEntry find(String name);
	void add(SymbolTableEntry entry) throws CompilerError;
	void delete(String Tn);
	void print(String Tn);
}

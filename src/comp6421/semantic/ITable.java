package comp6421.semantic;

import comp6421.semantic.entry.STEntry;

public interface ITable {
	enum Scope {
		GLOBAL, FUNCTION, CLASS, PROGRAM, UNKNOWN
	}
	
	void create(String Tn);
	STEntry find(String name);
	void add(STEntry entry) throws SemanticException;
	void delete(String Tn);
	void print(String Tn);
}

package comp6421.semantic;

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

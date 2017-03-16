package comp6421.semantic;

public interface ITable {
	enum Scope {
		GLOBAL, FUNCTION, CLASS, PROGRAM, UNKNOWN
	}
	Scope scope = Scope.UNKNOWN;
	String name = null;
	void create(String Tn);
	void search(String Tn, String i, String ptr, boolean found);
	void insert(String Tn, String i, String ptr);
	void delete(String Tn);
	void print(String Tn);
}

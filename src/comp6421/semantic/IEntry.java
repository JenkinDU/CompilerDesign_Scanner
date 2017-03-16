package comp6421.semantic;

public interface IEntry {
	enum Kind {
		FUNCTION, CLASS, PROGRAM, PARAMETER, VARIABLE
	}
	ITable getParent();
	ITable getLink();
}

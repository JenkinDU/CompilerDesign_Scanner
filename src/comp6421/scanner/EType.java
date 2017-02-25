package comp6421.scanner;

public enum EType {
	ID, ALPHANUM, INTEGER, FLOAT,
	PLUS, MINUS, STAR, SLASH, EQUAL, AND, NOT, OR,
	ASSGN, NOTEQ, LT, GT, LESSEQ, GREATEQ, SEMICOLON, COMMA, DOT,
	OPENPAR, CLOSEPAR, OPENBRACE, CLOSEBRACE, OPENBRACKET, CLOSEBRACKET, CMT, OPENCMT, CLOSECMT,
	IF, THEN, ELSE, FOR, CLASS, INT, RESERVED_FLOAT, GET, PUT, RETURN, PROGRAM, DOLLAR,
	ERR
}

package comp6421.scanner;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import comp6421.Utils;

/**
 * Regular Expressions
 * 
 *	id:L(L|D|_)*
 *	alphanum:L|D|_
 *	letter:[a-z]|[A-Z]
 *	fraction:.[0-9]*[1-9]|.0
 *	digit:[0-9]
 *	nonzero:[1-9]
 *	integer:[1-9][0-9]*|0
 *	float:([1-9][0-9]*|0)(.[0-9]*[1-9]|.0)
 *	num:([1-9][0-9]*|0)|([1-9][0-9]*|0)(.[0-9]*[1-9]|.0)
 *
 * @author du_zhen
 */
public class Scanner {
	public static String TEST_FILE = "./res/program_full.txt";
	public static String TOKEN_FILE = "./res/scanner/out/token.txt";
	public static String ERROR_FILE = "./res/scanner/out/error.txt";
	
	private static final String NO_OPEN = "No Open Pair";
	private static final String NO_CLOSE = "";//"No Close Pair";
	private static final String UN_KNOW = "Unknown type";
	protected InputStreamReader input;
	protected int t = 0;
	protected ArrayList<Token> tokens;
	private int line = 1;
	private boolean back = false;
	private boolean backC = false;
	private boolean newLine = false;
	private int backChar = -1;
	private ArrayList<Token> pair;
	private HashMap<String, EType> map;
	
	public Scanner() {
		tokens = new ArrayList<Token>();
		pair = new ArrayList<Token>();
		initTypeList();
	}
	
	private void initTypeList() {
		map = new HashMap<String, EType>();
		map.put("if", EType.IF);
		map.put("then", EType.THEN);
		map.put("else", EType.ELSE);
		map.put("for", EType.FOR);
		map.put("class", EType.CLASS);
		map.put("int", EType.INT);
		map.put("float", EType.RESERVED_FLOAT);
		map.put("get", EType.GET);
		map.put("put", EType.PUT);
		map.put("return", EType.RETURN);
		map.put("program", EType.PROGRAM);
		map.put("and", EType.AND);
		map.put("not", EType.NOT);
		map.put("or", EType.OR);
	}
	
	private EType getTypeofWord(String w) {
		if(w==null || w.length() == 0)
			return null;
		EType type = map.get(w);
		if(type == null) {
			type = EType.ID;
		}
		return type;
	}
	
	/**
	 * Setup a bachup
	 */
	private void backupChar() {
		back = true;
	}
	
	private void backupChar(char c, char t) {
		backC = true;
		backChar = c;
		this.t = t;
	}
	/**
	 * Move the position to next char
	 */
	protected void next() {
		if(back) {
			back = false;
			return;
		}
		if(backC) {
			backC = false;
			t = backChar;
			return;
		}
		if(newLine) {
			newLine = false;
			t = backChar;
			return;
		}
		try {
			t = input.read();
			if (t == '\r') {
				line++;
				t = input.read();
				if (t != '\n') {
					backChar = t;
					newLine = true;
					t = '\r';
				}
			} else if (t == '\n') {
				line++;
			}
		} catch (IOException e) {
			t = -1;
			e.printStackTrace();
		}
	}
	
	/**
	 * Skip to next line
	 */
	private void nextLine() {
		try {
			t = input.read();
			while(t > 0) {
				if(t == '\r') {
					line++;
					t = input.read();
					if(t == '\n') {
						t = input.read();
					}
					break;
				} else if(t == '\n') {
					line++;
					t = input.read();
					break;
				}
				t = input.read();
			}
		} catch (IOException e) {
			t = -1;
			e.printStackTrace();
		}
	}
	
	/**
	 * Main logic to analyst the token
	 */
	public void lexer() {
		initLexer();
		try {
			File file = new File(TEST_FILE);
			if (file.isFile() && file.exists()) {
				input = new InputStreamReader(new FileInputStream(file));
				next();
				while (t > 0) {
					nextToken();
					next();
				}
				input.close();
			} else {
				System.out.println("can not find the file!");
			}
		} catch (Exception e) {
			System.out.println("unknow error when open file!");
			e.printStackTrace();
		}
		outPutToken();
	}

	protected void initLexer() {
		line = 1;
		backChar = -1;
		back = false;
		backC = false;
		newLine = false;
		tokens.clear();
		pair.clear();
	}

	protected void nextToken() {
		String value = "";
		String temp = "";
		if (t <= '9' && t >= '0') {
			int li = line;
			value += (char) t;// [0-9]
			if (t <= '9' && t >= '1') {// [1-9]
				while (t <= '9' && t >= '0') {
					next();
					if (t <= '9' && t >= '0') {
						value += (char) t;// [0-9]
					}
				}
			} else {
				next();
			}
			if (t == '.') {
				temp += (char) t;// .
				next();
				if (t <= '9' && t >= '0') {
					temp += (char) t;// .[0-9]
					value += temp;// [0|[1-9][0-9]*]+.[0-9]
					temp = "";
					while (t <= '9' && t >= '0') {
						next();
						if (t <= '9' && t >= '0') {
							temp += (char) t;// [0-9]
						}
						if (t <= '9' && t >= '1') {
							value += temp;// [0|[1-9][0-9]*]+.[0-9]
							temp = "";
						}
					}
					tokens.add(new Token(li, EType.FLOAT, value));// [0|[1-9][0-9]*]+(.0
																	// |
																	// .001)
					if (temp.length() != 0) {
						int l = temp.length();
						if (t == '.') {// 0.000100.
							l -= 1;// 0.00010
							backupChar('.', '0');
						}
						for (int j = 0; j < l; j++) {
							tokens.add(new Token(li, EType.INTEGER, temp.charAt(j) + ""));// 0
						}
					}
					backupChar();
				} else {
					tokens.add(new Token(line, EType.INTEGER, value));
					tokens.add(new Token(line, EType.DOT, temp));
					backupChar();
				}
			} else {
				tokens.add(new Token(li, EType.INTEGER, value));
				backupChar();
			}
		} else if (t <= 'z' && t >= 'a' || t <= 'Z' && t >= 'A') {
			int li = line;
			value += (char) t;
			while (t <= 'z' && t >= 'a' || t <= 'Z' && t >= 'A' || t <= '9' && t >= '0' || t == '_') {
				next();
				if (t <= 'z' && t >= 'a' || t <= 'Z' && t >= 'A' || t <= '9' && t >= '0' || t == '_') {
					value += (char) t;// [0-9]
				}
			}
			tokens.add(new Token(li, getTypeofWord(value), value));
			backupChar();
		} else if (t == '/') {
			int li = line;
			value += (char) t;
			next();
			if (t == '/') {
				value += (char) t;
				tokens.add(new Token(li, EType.CMT, value));
				nextLine();
				backupChar();
			} else if (t == '*') {
				value += (char) t;
				Token token = new Token(li, EType.OPENCMT, value, NO_CLOSE);
				tokens.add(token);
				next();
				while(t > 0) {
					if(t == '*') {
						next();
						if(t == '/') {
							token.setError("");
							tokens.add(new Token(line, EType.CLOSECMT, "*/"));
							break;
						}
					} else {
						next();
					}
				}
			} else {
				tokens.add(new Token(li, EType.SLASH, value));
				backupChar();
			}
		} else if (t == '*') {
			int li = line;
			value += (char) t;
			next();
			if (t == '/') {
				value += (char) t;
				tokens.add(new Token(li, EType.CLOSECMT, value, NO_OPEN));
			} else {
				tokens.add(new Token(li, EType.STAR, value));
				backupChar();
			}
		} else if (t == '=') {
			int li = line;
			value += (char) t;
			next();
			if (t == '=') {
				value += (char) t;
				tokens.add(new Token(li, EType.EQUAL, value));
			} else {
				tokens.add(new Token(li, EType.ASSGN, value));
				backupChar();
			}
		} else if (t == '<') {
			int li = line;
			value += (char) t;
			next();
			if (t == '=') {
				value += (char) t;
				tokens.add(new Token(li, EType.LESSEQ, value));
			} else if (t == '>') {
				value += (char) t;
				tokens.add(new Token(li, EType.NOTEQ, value));
			} else {
				tokens.add(new Token(li, EType.LT, value));
				backupChar();
			}
		} else if (t == '>') {
			int li = line;
			value += (char) t;
			next();
			if (t == '=') {
				value += (char) t;
				tokens.add(new Token(li, EType.GREATEQ, value));
			} else {
				tokens.add(new Token(li, EType.GT, value));
				backupChar();
			}
		} else if (t == '(') {
			Token token = new Token(line, EType.OPENPAR, (char) t + "", NO_CLOSE);
			tokens.add(token);
			pair.add(token);
		} else if (t == ')') {
			Token token = new Token(line, EType.CLOSEPAR, (char) t + "");
			String error = NO_OPEN;
			int size = pair.size();
			if(size == 0) {
				error = NO_OPEN;
			} else {
				int index = -1;
				for(int i=size-1;i>=0;i--) {
					Token t = pair.get(i);
					if(t.TYPE == EType.OPENPAR) {
						error = "";
						t.setError(error);
						index = i;
						break;
					}
				}
				if(index != -1) {
					do {
						pair.remove(pair.size()-1);
					} while(pair.size() != index);
				}
			}
			token.setError(error);
			tokens.add(token);
		} else if (t == '{') {
			Token token = new Token(line, EType.OPENBRACE, (char) t + "", NO_CLOSE);
			tokens.add(token);
			pair.add(token);
		} else if (t == '}') {
			Token token = new Token(line, EType.CLOSEBRACE, (char) t + "");
			String error = NO_OPEN;
			int size = pair.size();
			if(size == 0) {
				error = NO_OPEN;
			} else {
				int index = -1;
				for(int i=size-1;i>=0;i--) {
					Token t = pair.get(i);
					if(t.TYPE == EType.OPENBRACE) {
						error = "";
						t.setError(error);
						index = i;
						break;
					}
				}
				if(index != -1) {
					do {
						pair.remove(pair.size()-1);
					} while(pair.size() != index);
				}
			}
			token.setError(error);
			tokens.add(token);
		} else if (t == '[') {
			Token token = new Token(line, EType.OPENBRACKET, (char) t + "", NO_CLOSE);
			tokens.add(token);
			pair.add(token);
		} else if (t == ']') {
			Token token = new Token(line, EType.CLOSEBRACKET, (char) t + "");
			String error = NO_OPEN;
			int size = pair.size();
			if(size == 0) {
				error = NO_OPEN;
			} else {
				int index = -1;
				for(int i=size-1;i>=0;i--) {
					Token t = pair.get(i);
					if(t.TYPE == EType.OPENBRACKET) {
						error = "";
						t.setError(error);
						index = i;
						break;
					}
				}
				if(index != -1) {
					do {
						pair.remove(pair.size()-1);
					} while(pair.size() != index);
				}
			}
			token.setError(error);
			tokens.add(token);
		} else if (t == '.') {
			tokens.add(new Token(line, EType.DOT, (char) t + ""));
		} else if (t == '_') {
			tokens.add(new Token(line, EType.ALPHANUM, (char) t + ""));
		} else if (t == '+') {
			tokens.add(new Token(line, EType.PLUS, (char) t + ""));
		} else if (t == '-') {
			tokens.add(new Token(line, EType.MINUS, (char) t + ""));
		} else if (t == ';') {
			tokens.add(new Token(line, EType.SEMICOLON, (char) t + ""));
		} else if (t == ',') {
			tokens.add(new Token(line, EType.COMMA, (char) t + ""));
		} else if (t == ' ' || t == '\t' || t == '\r' || t == '\n') {

		} else {
			tokens.add(new Token(line, EType.ERR, (char) t + "", UN_KNOW));
		}
//		next();
	}
	
	protected void outPutToken() {
		outPutToken(true);
	}
	
	protected void outPutToken(boolean print) {
		int line = 0;
		int index = 0;
		String error = "";
		String token = "";
		for (Token t : tokens) {
			if(t.position != line) {
				line = t.position;
				index = 0;
			}
			if(t.TYPE == EType.ERR) {
				error += "Line " + t.position + ":" + ++index + " " + t.getTYPE() + " " +t.getValue() + " " + t.getError() + "\n";
			} else {
				token += "Line " + t.position + ":" + ++index + " " + t.getTYPE() + " " +t.getValue() + " " + t.getError() + "\n";
			}
//			System.out.println("Line " + t.position + ":" + ++index + " " + t.getTYPE() + " " +t.getValue() + " " + t.getError());
		}
		if(print) {
			System.out.println("Token:\n"+token);
			System.out.println("Error:\n"+error);
		}
		Utils.echo2File(TOKEN_FILE, token);
		Utils.echo2File(ERROR_FILE, error);
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner();
		scanner.lexer();
	}
}

package comp6421;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

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
public final class Scanner {
	private static final String TEST_FILE_0 = "./res/test.txt";
	private static final String OUTPUT_FILE = "./res/token.txt";
	
	private InputStreamReader input;
	private int t = 0;
	private ArrayList<Token> tokens;
	private int line = 1;
	private boolean back = false;
	private HashMap<String, EType> map;
	
	public Scanner() {
		tokens = new ArrayList<Token>();
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
	
	private void backupChar() {
		back = true;
	}
	
	private void next() {
		if(back) {
			back = false;
			return;
		}
		try {
			t = input.read();
			while(t == '\r' || t == '\n') {
				if(t == '\r') {
					line++;
					t = input.read();
					if(t == '\n') {
						t = input.read();
					}
				} else if(t == '\n') {
					line++;
					t = input.read();
				}
			}
		} catch (IOException e) {
			t = -1;
			e.printStackTrace();
		}
	}
	
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
	
	private void lexer() {
		try {
			File file = new File(TEST_FILE_0);
			if (file.isFile() && file.exists()) {
				input = new InputStreamReader(new FileInputStream(file));
				next();
				while (t > 0) {
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
						} else if (t == '*') {
							value += (char) t;
							tokens.add(new Token(li, EType.CMT, value));
							// to do
						} else {
							tokens.add(new Token(li, EType.SLASH, value));
							backupChar();
						}
					} else if (t == '*') {
						int li = line;
						// to do
						tokens.add(new Token(li, EType.STAR, (char) t + ""));
					}

					else if (t == '=') {
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
					} else if (t == ' ' || t == '\t') {

					} else {
						tokens.add(new Token(line, EType.ERR, (char) t + ""));
					}
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
		for (Token t : tokens) {
			System.out.print(t.position + ":");
			t.printType();
			t.printValue();
		}
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner();
		scanner.lexer();
	}
}

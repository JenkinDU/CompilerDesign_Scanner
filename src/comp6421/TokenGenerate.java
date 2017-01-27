package comp6421;

import java.util.ArrayList;
import java.util.HashMap;

import comp6421.util.Utils;

public class TokenGenerate {
	private static final String TEST_FILE_0 = "./res/test.txt";
	private static final String OUTPUT_FILE = "./res/token.txt";

	private ArrayList<String> lines;
	private Item input;
	private ArrayList<Item> phraseList;
	private HashMap<String, EType> map;
	ArrayList<Token> tokens;

	public TokenGenerate(Item l) {
		this.input = l;
		this.phraseList = new ArrayList<Item>();
		this.tokens = new ArrayList<Token>();
		lines = Utils.readFileLines(TEST_FILE_0);
		int i = 0;
		initTypeList();
		for (String s : lines) {
			input = new Item(++i, s);
			phraseSplit();
			generate();
		}
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
		if (w == null || w.length() == 0)
			return null;
		EType type = map.get(w);
		if (type == null) {
			type = EType.ID;
		}
		return type;
	}

	public void phraseSplit() {
		if (input != null && input.getValue() != null) {
			String[] split = input.getValue().trim().split("\\s+");
			if (split != null) {
				for (int i = 0; i < split.length; i++) {
					String phrase = split[i];
					if (phrase != null && phrase.length() > 0) {
						phraseList.add(new Item(input.getPosition(), phrase));
					}
				}
			}
		}
	}

	public ArrayList<Token> generate() {
		for (Item i : phraseList) {
			String phrase = i.getValue();
			int p = i.getPosition();
			// System.out.println(phrase);
			if (!atomSplit(p, phrase)) {
				break;
			}
		}
		for (Token t : tokens) {
			System.out.print(t.position + ":");
			t.printType();
			t.printValue();
		}
		return tokens;
	}

	private boolean atomSplit(int p, String phrase) {
		int confirm = -1;
		int peek = 0;
		int length = phrase.length();
		for (int i = 0; i < length; i++) {
			confirm = i - 1;
			peek = i;
			char c = phrase.charAt(peek);
			String value = "";
			String temp = "";
			if (c <= '9' && c >= '0') {// c<='9'&&c>='0'
				confirm++;
				value += c;// [0-9]
				if (c <= '9' && c >= '1') {// [1-9]
					while (c <= '9' && c >= '0' && peek < length - 1) {
						c = phrase.charAt(++peek);
						if (c <= '9' && c >= '0') {
							confirm++;
							value += c;// [0-9]
						}
					}
				} else {
					if (peek < length - 1) {
						c = phrase.charAt(++peek);
					}
				}
				if (c == '.') {
					temp += c;// .
					if (peek < length - 1) {
						c = phrase.charAt(++peek);
					}
					if (c <= '9' && c >= '0') {
						confirm = peek;
						temp += c;// .[0-9]
						value += temp;// [0|[1-9][0-9]*]+.[0-9]
						temp = "";
						while (c <= '9' && c >= '0' && peek < length - 1) {
							c = phrase.charAt(++peek);
							if (c <= '9' && c >= '0') {
								temp += c;// [0-9]
							}
							if (c <= '9' && c >= '1') {
								confirm = peek;
								value += temp;// [0|[1-9][0-9]*]+.[0-9]
								temp = "";
							}
						}
						tokens.add(new Token(p, EType.FLOAT, value));// [0|[1-9][0-9]*]+(.0
																		// |
																		// .001)
						if (temp.length() != 0) {
							int l = temp.length();
							if (c == '.') {// 0.000100.
								l -= 1;// 0.00010
							}
							for (int j = 0; j < l; j++) {
								tokens.add(new Token(p, EType.INTEGER, temp.charAt(j) + ""));// 0
								confirm++;
							}
						}
					} else {
						tokens.add(new Token(p, EType.INTEGER, value));
						tokens.add(new Token(p, EType.DOT, temp));
						confirm++;
					}
				} else {
					tokens.add(new Token(p, EType.INTEGER, value));
				}
			} else if (c <= 'z' && c >= 'a' || c <= 'Z' && c >= 'A') {
				confirm++;
				value += c;
				while ((c <= 'z' && c >= 'a' || c <= 'Z' && c >= 'A' || c <= '9' && c >= '0' || c == '_')
						&& peek < length - 1) {
					c = phrase.charAt(++peek);
					if (c <= 'z' && c >= 'a' || c <= 'Z' && c >= 'A' || c <= '9' && c >= '0' || c == '_') {
						confirm++;
						value += c;// [0-9]
					}
				}
				tokens.add(new Token(p, getTypeofWord(value), value));
			} else if (c == '/') {
				confirm++;
				value += c;
				if (peek < length - 1) {
					c = phrase.charAt(++peek);
					if (c == '/') {
						confirm++;
						value += c;
						tokens.add(new Token(p, EType.CMT, value));
						return false;
					} else if (c == '*') {
						confirm++;
						value += c;
						tokens.add(new Token(p, EType.CMT, value));
						return false;
					}
				} else {
					tokens.add(new Token(p, EType.SLASH, value));
				}
			} else if (c == '*') {
				tokens.add(new Token(p, EType.STAR, c + ""));
				confirm++;
			}

			else if (c == '=') {
				confirm++;
				value += c;
				if (peek < length - 1) {
					c = phrase.charAt(++peek);
					if (c == '=') {
						confirm++;
						value += c;
						tokens.add(new Token(p, EType.EQUAL, value));
					} else {
						tokens.add(new Token(p, EType.ASSGN, value));
					}
				} else {
					tokens.add(new Token(p, EType.ASSGN, value));
				}
			} else if (c == '<') {
				confirm++;
				value += c;
				if (peek < length - 1) {
					c = phrase.charAt(++peek);
					if (c == '=') {
						confirm++;
						value += c;
						tokens.add(new Token(p, EType.LESSEQ, value));
					} else if (c == '>') {
						confirm++;
						value += c;
						tokens.add(new Token(p, EType.NOTEQ, value));
					} else {
						tokens.add(new Token(p, EType.LT, value));
					}
				} else {
					tokens.add(new Token(p, EType.LT, value));
				}
			} else if (c == '>') {
				confirm++;
				value += c;
				if (peek < length - 1) {
					c = phrase.charAt(++peek);
					if (c == '=') {
						confirm++;
						value += c;
						tokens.add(new Token(p, EType.GREATEQ, value));
					} else {
						tokens.add(new Token(p, EType.GT, value));
					}
				} else {
					tokens.add(new Token(p, EType.GT, value));
				}
			} else if (c == '.') {
				tokens.add(new Token(p, EType.DOT, c + ""));
				confirm++;
			} else if (c == '_') {
				tokens.add(new Token(p, EType.ALPHANUM, c + ""));
				confirm++;
			} else if (c == '+') {
				tokens.add(new Token(p, EType.PLUS, c + ""));
				confirm++;
			} else if (c == '-') {
				tokens.add(new Token(p, EType.MINUS, c + ""));
				confirm++;
			} else if (c == ';') {
				tokens.add(new Token(p, EType.SEMICOLON, c + ""));
				confirm++;
			} else if (c == ',') {
				tokens.add(new Token(p, EType.COMMA, c + ""));
				confirm++;
			} else {
				tokens.add(new Token(p, EType.ERR, c + ""));
				confirm++;
			}
			i = confirm;
		}
		return true;
	}
}

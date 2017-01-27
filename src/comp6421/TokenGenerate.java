package comp6421;

import java.util.ArrayList;

public class TokenGenerate {

	private Item input;
	private ArrayList<Item> phraseList;
	
	public TokenGenerate(Item l) {
		this.input = l;
		this.phraseList = new ArrayList<Item>();
	}
	
	public void phraseSplit() {
		if(input!=null&&input.getValue()!=null) {
			String[] split = input.getValue().trim().split("\\s+");
			if(split!=null) {
				for(int i=0;i<split.length;i++) {
					String phrase = split[i];
					if(phrase!=null&&phrase.length()>0) {
						phraseList.add(new Item(input.getPosition(), phrase));
					}
				}
			}
		}
	}
	
	public ArrayList<Token> generate() {
		ArrayList<Token> token = new ArrayList<Token>();
		for(Item i : phraseList) {
			String phrase = i.getValue();
			int p = i.getPosition();
//			System.out.println(phrase);
			token.addAll(atomSplit(p, phrase));
		}
		for(Token t : token) {
			System.out.print(t.position+":");
			t.printType();
			t.printValue();
		}
		return token;
	}
	
	private ArrayList<Token> atomSplit(int p, String phrase) {
		int confirm = -1;
		int peek = 0;
		int length = phrase.length();
		ArrayList<Token> token = new ArrayList<Token>();
		for(int i=0;i<length;i++) {
			confirm = i-1;
			peek = i;
			char c = phrase.charAt(peek);
			String value = "";
			String temp = "";
			if(c<='9'&&c>='0') {//c<='9'&&c>='0'
				confirm++;
				value+=c;//[0-9]
//				temp+=c;
				//
				if(c<='9'&&c>='1') {//[1-9]
					while(c<='9'&&c>='0'&&peek < length-1) {
						c = phrase.charAt(++peek);
//						if(c=='.') {//111000.
//							break;
//						}
						if(c<='9'&&c>='0') {
							confirm++;
							value+=c;//[0-9]
						}
					}
				} else {
					if(peek < length-1) {
						c = phrase.charAt(++peek);
					}
				}
				
				///
				
				if(c=='.') {
					temp+=c;//.
					if(peek < length-1) {
						c = phrase.charAt(++peek);
					}
					if(c<='9'&&c>='0') {
						confirm = peek;
						temp+=c;//.[0-9]
						value+=temp;//[0|[1-9][0-9]*]+.[0-9]
//					value+=c;
					temp="";
//						if(c<='9'&&c>='1') {
//							confirm = peek;
//						}
////							value+=temp;
////							value+=c;
////							temp="";
//						} else {
							while(c<='9'&&c>='0'&&peek < length-1) {
								c = phrase.charAt(++peek);
								if(c<='9'&&c>='0') {
									temp+=c;//[0-9]
								}
								if(c<='9'&&c>='1') {
									confirm = peek;
									value+=temp;//[0|[1-9][0-9]*]+.[0-9]
									temp="";
								}
							}
//							if(value.equals("0")) {
//								if(temp.equals(".0")) {//.0
//									value+=temp;
//									token.add(new Token(p, EType.FLOAT, value));
//									confirm+=3;
//								} else {
//									token.add(new Token(p, EType.INTEGER, value));
//									token.add(new Token(p, EType.DOT, temp));
//									confirm+=2;
//								}
//							} else {
								token.add(new Token(p, EType.FLOAT, value));//[0|[1-9][0-9]*]+(.0 | .001)
								if(temp.length()!=0) {
									int l = temp.length();
									if(c=='.') {//0.000100.
										l-=1;//0.00010
									}
									for(int j=0;j<l;j++) {
										token.add(new Token(p, EType.INTEGER, temp.charAt(j)+""));//0
										confirm++;
									}
								}
//							}
//						}
					} else {
						token.add(new Token(p, EType.INTEGER, value));
						token.add(new Token(p, EType.DOT, temp));
						confirm++;
					}
				} else {
//					confirm++;
					token.add(new Token(p, EType.INTEGER, value));
				}
//			} else if(c<='9'&&c>='1') {
//				boolean dot = true;
//				while(dot&&(c<='9'&&c>='0'||c=='.')) {
//					value+=c;
//					confirm++;
//					c = phrase.charAt(++peek);
//					if(c=='.') {
//						dot = false;
//					}
//				}
//				--i;
			} else if(c=='.') {
				token.add(new Token(p, EType.DOT, c+""));
				confirm++;
			} else {
				confirm++;
			}
			i = confirm;
		}
		return token;
	}
}

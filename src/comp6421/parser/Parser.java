package comp6421.parser;

import java.util.ArrayList;
import java.util.LinkedList;

import org.json.JSONArray;

import comp6421.Utils;
import comp6421.scanner.EType;
import comp6421.scanner.Token;

/**
 *
 * This class
 * 
 * @author Zhen Du
 * @date Feb 24, 2017
 */
public class Parser {
	private static final String FIRST = "./res/parser/first_set.txt";
	private static final String FOLLOW = "./res/parser/follow_set.txt";
	private static final String PREDICT = "./res/parser/predict_set.txt";
	private static final String TABLE = "./res/parser/parsing_table.txt";
	
	public static String DERIVATION = "./res/parser/out/derivation.txt";
	public static String ERROR = "./res/parser/out/error.txt";
	
	private ExtendScanner scanner;
	private Table table;
	private LinkedList<Symbol> stack;
	private Token token;
	private String error = "";
	
	public Parser() {
		this.table = new Table();
		this.stack = new LinkedList<Symbol>();
		initFollowSet();
		initFirstSet();
		initPredict();
		initTable();
	}

	private void initFirstSet() {
		ArrayList<String> lines = Utils.readFileLines(FIRST);
		System.out.println("\n\nFIRST:");
		for(String l : lines) {
			String[] e = l.split("\t");
			if(Symbol.nonTerminal.contains(e[0])) {
				System.out.println("FIRST("+e[0]+") = "+e[1]);
			}
		}
		System.out.println("\n");
	}

	private void initFollowSet() {
		ArrayList<String> lines = Utils.readFileLines(FOLLOW);
		Symbol.nonTerminal.clear();
		int i = 0;
		System.out.println("FOLLOW:");
		for(String l : lines) {
			String[] e = l.split("\t");
			this.table.addPredict(e[0], ++i);
			this.table.addPredictOrder(i, e[0]);
			System.out.println("FOLLOW("+e[0]+") = "+(e.length>1?e[1]:""));
			Symbol.nonTerminal.add(e[0]);
		}
	}
	
	private void initPredict() {
		ArrayList<String> lines = Utils.readFileLines(PREDICT);
		for(String l : lines) {
			String[] e = l.split("\t");
			this.table.addExpression(Integer.valueOf(e[0]), e[1]);
			System.out.println("R"+e[0]+":"+e[1]);
		}
		this.table.addExpression(this.table.getExpressionSize()+1, "POP");
		this.table.addExpression(this.table.getExpressionSize()+1, "SCAN");
	}
	
	private void initTable() {
		ArrayList<String> lines = Utils.readFileLines(TABLE);
		String value = "";
		for(String l : lines) {
			value +=l;
		}
		
		JSONArray array = new JSONArray(value);
		for (int i=0;i<array.length();i++) {
			if(i == 0) {
				JSONArray a = array.getJSONArray(i);
				for(int j=1;j<a.length();j++) {
					String t = a.getString(j);
					this.table.addToken(t, j-1);
					Symbol.terminal.add(t);
//					System.out.println(j-1+":"+t);
				}
				this.table.initRules(Symbol.nonTerminal.size(), Symbol.terminal.size());
			} else if(i > 1) {
				JSONArray a = array.getJSONArray(i);
				for(int j=1;j<a.length();j++) {
					int r = a.getInt(j);
					this.table.addRules(i-2, j-1, r);
//					System.out.println((i-2)+"-"+(j-1)+" "+r);
				}
			}
		}
		System.out.println("\n\nPARSING TABLE:");
		String temp = "\t\t";
		for(String t : Symbol.terminal) {
			temp += (t+"\t");
		}
		System.out.println(temp);
		for(int i=0;i<Symbol.nonTerminal.size();i++) {
			temp = Symbol.nonTerminal.get(i);
			if(temp.length()>7) {
				temp+="\t";
			} else {
				temp+="\t\t";
			}
			for(int j=0;j<Symbol.terminal.size();j++) {
				temp += (this.table.getRule(i, j)+"\t");
			}
			System.out.println(temp);
		}
	}
	
	private void skipError(String action) {
		String e = "line "+token.getPosition()+" parser error " + token.getPosition() +" "+ token.toString();
		e = String.format("%-80s", e)+"\tRECOVERY: " + action;
		System.out.println(e);
		if("POP".equals(action)) {
			stack.pop();
		} else if("SCAN".equals(action)) {
			token = scanner.getNextToken();
		}
		error+=e+"\n";
	}
	
	public void doParser(String f) {
		scanner = new ExtendScanner(f);
		stack.clear();
		stack.push(Table.getDollar());
		stack.push(Table.getStart());
		token = scanner.getNextToken();
		boolean success = true;
		String derivation = Table.getStart().getValue();
		System.out.println("\n\nDERIVATION:");
		String file = "";
		
		while(stack.size()==2&&token.getTYPE()==EType.DOLLAR || stack.peek().getType() != Symbol.ESymbol.DOLLAR && token.getTYPE() != EType.DOLLAR) {
			Symbol top = stack.peek();
			if(top.getType() == Symbol.ESymbol.TERMINAL || top.getType() == Symbol.ESymbol.EPSILON) {
				String t = getGeneralValue();
				String log="";
				Object[] list = stack.toArray();
				for(int i=list.length-1;i>=0;i--) {
					log+=" "+((Symbol)list[i]).getValue();
				}
				log+=("\t\t"+t);
				System.out.println(log);
				file+=log+"\n";
				
				if(t.equals(top.getValue())) {
					stack.pop();
					token = scanner.getNextToken();
				} else {
					skipError("POP");
					success = false;
				}
			} else if(top.getType() == Symbol.ESymbol.NON_TERMINAL) {
				if(token.getError().length()>0) {
					skipError("SCAN");
					success = false;
				} else {
					int row = this.table.getPredict(top.getValue())-1;
					String t = getGeneralValue();
					int column = this.table.getToken(t);
					int rule = this.table.getRule(row, column);
					String ex = this.table.getExpression(rule);
					if("POP".equals(ex) || "SCAN".equals(ex)) {
						skipError(ex);
						success = false;
					} else {
						String log="";
						Object[] list = stack.toArray();
						for(int i=list.length-1;i>=0;i--) {
							log+=" "+((Symbol)list[i]).getValue();
						}
						log+=("\t\tr"+row+":"+ex);
						
						String[] exValue = this.table.getExpressionValue(rule);
						System.out.println(log);
						derivation = derivation.replaceFirst(exValue[0], "ε".equals(exValue[1])?"":exValue[1]);
						log+=("\t\t\t\t"+derivation.replaceAll("[ ]+", " "));	
						file+=log+"\n";
						
						stack.pop();
						ArrayList<Symbol> rules = this.table.getInverseRHSExpression(rule);
						if(rules!=null) {
							for(Symbol s : rules) {
								if(s.getType() != Symbol.ESymbol.EPSILON) {
									stack.push(s);
								}
							}
						}
					}
				}
			}
		}
		if(Table.getDollar().getValue().equals(token) || !success) {
			file += "\n\nParser Failed";
			System.out.println("\nParser Failed");
		} else {
			file += " $\n\nParser Success";
			System.out.println(" $");
			System.out.println("\nParser Success");
		}
		Utils.echo2File(ERROR, error.length()==0?"NO ERROR":error);
		Utils.echo2File(DERIVATION, file);
	}

	private String getGeneralValue() {
		String t = "";
		if(token.getTYPE() == EType.ID) {
			t = "id";
		} else if(token.getTYPE() == EType.INTEGER) {
			t = "integer";
		} else if(token.getTYPE() == EType.FLOAT) {
			t = "num";
		} else {
			t = token.getValue();
		}
		return t;
	}
	
	public static void main(String[] args) {
		Parser parser = new Parser();
		parser.doParser("./res/program_full.txt");
	}
}

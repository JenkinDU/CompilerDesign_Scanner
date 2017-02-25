package comp6421.parser;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * This class
 * 
 * @author Zhen Du
 * @date Feb 24, 2017
 */
public class Table {
	private HashMap<Integer, String> expression;
	private HashMap<Integer, String> predictOrder;
	private HashMap<String, Integer> predict;
	private HashMap<String, Integer> token;
	
	private int[][] rules;
	
	public Table() {
		expression = new HashMap<Integer, String>();
		predictOrder = new HashMap<Integer, String>();
		predict = new HashMap<String, Integer>();
		token = new HashMap<String, Integer>();
	}
	
	public String getExpression(int i) {
		return expression.get(i);
	}
	
	public String[] getExpressionValue(int i) {
		String ex = expression.get(i);
		if("POP".equals(ex) || "SCAN".equals(ex)) {
			return null;
		} else {
			String[] e = ex.split(" → ");
			return e;
		}
	}
	
	public ArrayList<Symbol> getInverseRHSExpression(int i) {
		ArrayList<Symbol> symbol = new ArrayList<Symbol>();
		String ex = expression.get(i);
		if("POP".equals(ex) || "SCAN".equals(ex)) {
			return null;
		} else {
			String[] e = ex.split(" → ");
			String[] rhs = e[1].split(" ");
			for(int j=rhs.length-1;j>=0;j--) {
				symbol.add(new Symbol(rhs[j]));
			}
		}
		return symbol;
	}
	
	public String getPredictOrder(int o) {
		return predictOrder.get(o);
	}
	
	public int getPredict(String p) {
		return predict.get(p);
	}
	
	public int getToken(String t) {
		return token.get(t);
	}
	
	public int getTokenSize() {
		return token.size();
	}
	
	public int getExpressionSize() {
		return expression.size();
	}
	
	public int getRule(int row, int column) {
		return rules[row][column];
	}
	
	public void addExpression(int i, String e) {
		expression.put(i, e);
	}
	
	public void addPredictOrder(int o, String p) {
		predictOrder.put(o, p);
	}
	
	public void addPredict(String p, int i) {
		predict.put(p, i);
	}
	
	public void addToken(String t, int i) {
		token.put(t, i);
	}
	
	public void initRules(int row, int column) {
		rules = new int[row][column];
	}
	
	public void addRules(int row, int column, int rule) {
		rules[row][column] = rule;
	}
	
	public static Symbol getDollar() {
		return new Symbol("$");
	}
	
	public static Symbol getStart() {
		return new Symbol("prog");
	}
}

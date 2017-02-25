package comp6421.parser;

import java.util.ArrayList;

/**
 *
 * This class
 * 
 * @author Zhen Du
 * @date Feb 24, 2017
 */
public class Symbol {
	public enum ESymbol {
		EPSILON, TERMINAL, NON_TERMINAL, DOLLAR, UNKNOW
	}
	public static ArrayList<String> terminal = new ArrayList<String>();
	public static ArrayList<String> nonTerminal= new ArrayList<String>();
	
	private ESymbol type;
	private String value;
	
	public Symbol(String v) {
		this.type = getSymbolType(v);
		this.value = v;
	}
	
	public Symbol(ESymbol t, String v) {
		this.type = t;
		this.value = v;
	}
	
	public ESymbol getType() {
		return type;
	}

	public String getValue() {
		return value;
	}

	private ESymbol getSymbolType(String v) {
		if("$".equals(v)) {
			return ESymbol.DOLLAR;
		} else if("ε".equals(v)) {
			return ESymbol.EPSILON;
		} else if(terminal.contains(v)) {
			return ESymbol.TERMINAL;
		} else if(nonTerminal.contains(v)) {
			return ESymbol.NON_TERMINAL;
		} else {
			return ESymbol.UNKNOW;
		}
	}
}

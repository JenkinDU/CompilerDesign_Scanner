package comp6421.parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import comp6421.scanner.EType;
import comp6421.scanner.Scanner;
import comp6421.scanner.Token;

/**
 *
 * This class
 * 
 * @author Zhen Du
 * @date Feb 24, 2017
 */
public class ExtendScanner extends Scanner {

	/**
	 * Constructors
	 * 
	 */
	public ExtendScanner(String file) {
		super();
		TEST_FILE = file;
		NO_CLOSE = "";
		initLexer();
	}
	
	@Override
	protected void initLexer() {
		super.initLexer();
		try {
			File file = new File(TEST_FILE);
			if (file.isFile() && file.exists()) {
				input = new InputStreamReader(new FileInputStream(file));
			} else {
				System.out.println("can not find the file!");
			}
		} catch (Exception e) {
			System.out.println("unknow error when open file!");
			e.printStackTrace();
		}
	}
	
	public Token getNextToken() {
		int s = super.tokens.size();
		if(s!=0) {
			Token token = tokens.get(s-1);
			if(token.getTYPE()==EType.DOLLAR) {
				return token;
			}
		}
		do {
			next();
		} while(t == ' ' || t == '\t' || t == '\r' || t == '\n');
		if (t > 0) {
			super.nextToken();
			int size = super.tokens.size();
			Token t = tokens.get(size-1);
			if(t.getTYPE() == EType.OPENCMT || t.getTYPE() == EType.CLOSECMT || t.getTYPE() == EType.CMT) {
				return getNextToken();
			} else {
				return t;
			}
		} else {
			try {
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			outPutToken(false);
			return new Token(-1, EType.DOLLAR, Table.getDollar().getValue());
		}
	}
}

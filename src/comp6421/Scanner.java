package comp6421;

import java.util.ArrayList;

import comp6421.util.Utils;

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
	
	private ArrayList<String> lines;
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner();
		scanner.readFile();
	}
	
	private void readFile() {
		lines = Utils.readFileLines(TEST_FILE_0);
		for(String s : lines) {
			System.out.println(s);
		}
	}
}

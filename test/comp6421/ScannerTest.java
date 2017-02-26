/**
 * 
 */
package comp6421;

import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import comp6421.scanner.Scanner;

/**
 * @author Jenkin
 *
 */
public class ScannerTest {
	
	private ArrayList<String> files;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		files = new ArrayList<String>();
		files.add("./res/scanner/test.cpp");
		files.add("./res/scanner/test.go");
		files.add("./res/scanner/test.oc");
		files.add("./res/scanner/test.py");
		files.add("./res/scanner/test.rb");
		files.add("./res/scanner/test.scala");
//		files.add("./src/comp6421/Scanner.java");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		Scanner scanner = new Scanner();
		for(String f : files) {
			System.out.println("To test file "+f+"\n");
			scanner.TEST_FILE = f;
			scanner.TOKEN_FILE = f.replace("scanner", "scanner/out")+"_token.txt";
			scanner.ERROR_FILE = f.replace("scanner", "scanner/out")+"_error.txt";
			scanner.lexer();
		}
		
	}
}

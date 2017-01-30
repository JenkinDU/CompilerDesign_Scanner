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
		files.add("./res/test.cpp");
		files.add("./res/test.go");
		files.add("./res/test.oc");
		files.add("./res/test.py");
		files.add("./res/test.rb");
		files.add("./res/test.scala");
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
			scanner.TOKEN_FILE = f+"_token.txt";
			scanner.ERROR_FILE = f+"_error.txt";
			scanner.lexer();
		}
		
	}
}

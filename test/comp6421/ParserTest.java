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

import comp6421.parser.Parser;
import comp6421.scanner.Scanner;

/**
 * @author Jenkin
 *
 */
public class ParserTest {
	
	private static ArrayList<String> files;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		files = new ArrayList<String>();
		files.add("./res/program.txt");
		files.add("./res/program_full.txt");
		files.add("./res/program_error_recovery.txt");
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
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testProgram() {
		Parser parser = new Parser();
		String f = files.get(0);
		System.out.println("To test file "+f+"\n");
		parser.DERIVATION = f.replace("res", "res/parser/out")+"_derivation.txt";
		parser.ERROR = f.replace("res", "res/parser/out")+"_error.txt";
		parser.doParser(f);
	}
	
	@Test
	public void testProgramFull() {
		Parser parser = new Parser();
		String f = files.get(1);
		System.out.println("To test file "+f+"\n");
		parser.DERIVATION = f.replace("res", "res/parser/out")+"_derivation.txt";
		parser.ERROR = f.replace("res", "res/parser/out")+"_error.txt";
		parser.doParser(f);
	}
	
	@Test
	public void testProgramErrorRecovery() {
		Parser parser = new Parser();
		String f = files.get(2);
		System.out.println("To test file "+f+"\n");
		parser.DERIVATION = f.replace("res", "res/parser/out")+"_derivation.txt";
		parser.ERROR = f.replace("res", "res/parser/out")+"_error.txt";
		parser.doParser(f);
	}
}

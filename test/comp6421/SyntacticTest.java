/**
 * 
 */
package comp6421;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import comp6421.parser.Parser;

/**
 * @author Jenkin
 *
 */
public class SyntacticTest {
	
	private static ArrayList<String> files;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		files = new ArrayList<String>();
		files.add("./res/parser/test/program.txt");
		files.add("./res/parser/test/program_full.txt");
		files.add("./res/parser/test/program_error_recovery.txt");
		System.out.println("***********Syntactic analysis***********");
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
		Parser parser = new Parser(true);
		String f = files.get(0);
		System.out.println("\nTest file "+f);
		parser.DERIVATION = f.replace("res/parser/test", "res/parser/out")+"_derivation.txt";
		parser.ERROR = f.replace("res/parser/test", "res/parser/out")+"_error.txt";
		parser.doParser(f);
		if(parser.getErrorNum()==0)
			System.out.println("NO ERROR\nDerivation in "+parser.DERIVATION);
		else
			System.out.println(parser.getErrorNum()+" errors\nDetail information in "+parser.ERROR);
	}
	
	@Test
	public void testProgramFull() {
		Parser parser = new Parser(true);
		String f = files.get(1);
		System.out.println("\nTest file "+f);
		parser.DERIVATION = f.replace("res/parser/test", "res/parser/out")+"_derivation.txt";
		parser.ERROR = f.replace("res/parser/test", "res/parser/out")+"_error.txt";
		parser.doParser(f);
		if(parser.getErrorNum()==0)
			System.out.println("NO ERROR\nDerivation in "+parser.DERIVATION);
		else
			System.out.println(parser.getErrorNum()+" errors\nDetail information in "+parser.ERROR);
	}
	
	@Test
	public void testProgramErrorRecovery() {
		Parser parser = new Parser(true);
		String f = files.get(2);
		System.out.println("\nTest file "+f);
		parser.DERIVATION = f.replace("res/parser/test", "res/parser/out")+"_derivation.txt";
		parser.ERROR = f.replace("res/parser/test", "res/parser/out")+"_error.txt";
		parser.doParser(f);
		if(parser.getErrorNum()==0)
			System.out.println("NO ERROR\nDerivation in "+parser.DERIVATION);
		else
			System.out.println(parser.getErrorNum()+" errors\nDetail information in "+parser.ERROR);
	}
}

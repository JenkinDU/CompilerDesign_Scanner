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

import comp6421.semantic.SymbolTableGen;

/**
 * @author Jenkin
 *
 */
public class SymbolTest {
	
	private static ArrayList<String> files;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		files = new ArrayList<String>();
		files.add("./res/symbol/program_symbol.txt");
		files.add("./res/symbol/program_symbol_undefined.txt");
		files.add("./res/symbol/program_symbol_multiply.txt");
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
	public void testNormal() {
		SymbolTableGen g = new SymbolTableGen();
		String f = files.get(0);
		System.out.println("To test file "+f+"\n");
		g.SOURCE_FILE = f;
		g.OUTPUT = f.replace("res/symbol", "res/symbol/out")+"_table.txt";
		g.ERROR = f.replace("res/symbol", "res/symbol/out")+"_error.txt";
		g.genTable(g);
	}
	
	@Test
	public void testUndefined() {
		SymbolTableGen g = new SymbolTableGen();
		String f = files.get(1);
		System.out.println("To test file "+f+"\n");
		g.SOURCE_FILE = f;
		g.OUTPUT = f.replace("res/symbol", "res/symbol/out")+"_table.txt";
		g.ERROR = f.replace("res/symbol", "res/symbol/out")+"_error.txt";
		g.genTable(g);
	}
	
	@Test
	public void testMultiply() {
		SymbolTableGen g = new SymbolTableGen();
		String f = files.get(2);
		System.out.println("To test file "+f+"\n");
		g.SOURCE_FILE = f;
		g.OUTPUT = f.replace("res/symbol", "res/symbol/out")+"_table.txt";
		g.ERROR = f.replace("res/symbol", "res/symbol/out")+"_error.txt";
		g.genTable(g);
	}
}

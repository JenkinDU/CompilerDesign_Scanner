/**
 * 
 */
package comp6421;

import static org.junit.Assert.assertTrue;

import java.io.File;
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
public class GenerationTest {
	
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
		files.add("./res/generation/memory_allocation.txt");
		files.add("./res/generation/loop_statement.txt");
		files.add("./res/generation/conditional_statement.txt");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testMalloc() {
		String f = files.get(0);
		Compiler.SOURCE_FILE = f;
		Compiler.OUTPUT = f.replace("generation", "generation/out")+"_table.txt";
		Compiler.ERROR = f.replace("generation", "generation/out")+"_error.txt";
		Compiler.run();
	}
	
	@Test
	public void testLoop() {
		String f = files.get(1);
		Compiler.SOURCE_FILE = f;
		Compiler.OUTPUT = f.replace("generation", "generation/out")+"_table.txt";
		Compiler.ERROR = f.replace("generation", "generation/out")+"_error.txt";
		Compiler.run();
	}
	
	@Test
	public void testCondition() {
		String f = files.get(2);
		Compiler.SOURCE_FILE = f;
		Compiler.OUTPUT = f.replace("generation", "generation/out")+"_table.txt";
		Compiler.ERROR = f.replace("generation", "generation/out")+"_error.txt";
		Compiler.run();
	}
}

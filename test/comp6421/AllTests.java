package comp6421;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 *
 * This class
 * 
 * @author Zhen Du
 * @date Apr 9, 2017
 */
@RunWith(Suite.class)
@SuiteClasses({ GenerationTest.class, LexicalTest.class, SemanticTest.class, SyntacticTest.class })
public class AllTests {

}

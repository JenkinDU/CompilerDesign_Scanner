package comp6421;

import java.io.FileNotFoundException;
import java.io.PrintStream;

import comp6421.semantic.SymbolTableGen;
import comp6421.semantic.code.CodeGenerator;

public class Compiler {
	public static String SOURCE_FILE = "./res/symbol/program_symbol.txt";
	public static String OUTPUT = "./res/symbol/out/symbol_table.txt";
	public static String ERROR = "./res/symbol/out/error.txt";
	
	public static void run() {
		PrintStream out = null;
		try {
			out = new PrintStream(SOURCE_FILE+".m");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		SymbolTableGen g = new SymbolTableGen(SOURCE_FILE);
		g.OUTPUT = OUTPUT;
		g.ERROR = ERROR;
		g.genTable(g);
		
		CodeGenerator codeGenerator = new CodeGenerator(out);
		codeGenerator.generate();
		
		out.close();
	}
	
	public static void main(String[] args) {
		run();
	}
}

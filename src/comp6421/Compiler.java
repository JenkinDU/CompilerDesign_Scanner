package comp6421;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

import comp6421.semantic.SymbolTableGen;
import comp6421.semantic.code.CodeGenerator;

/**
 *
 * This class
 * 
 * @author Zhen Du
 * @date Apr 1, 2017
 */
public class Compiler {
	public static String SOURCE_FILE = "./res/symbol/program_symbol.txt";
	
	private static void run() {
		PrintStream out = null;
		try {
			out = new PrintStream(SOURCE_FILE+".m");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		SymbolTableGen g = new SymbolTableGen(SOURCE_FILE);
		g.genTable(g);
		
		CodeGenerator codeGenerator = new CodeGenerator(out);
		
		codeGenerator.generate();
		
		if(codeGenerator.getNumErrors() > 0){
//			Log.logError("Errors during parsing second pass, aborting");
			System.exit(2);
		}
		
//		Log.closeMasm();
		
		System.out.println("~================<    Moon Output   >================~");
		
//		moonRun(SOURCE_FILE+".m");//Log.getMasmFile());
		
//		Log.close();
	}
	
	public static void moonRun(String file) {

		ProcessBuilder moon = new ProcessBuilder("./moon.exe", "+p", "+s", "+t", "+x", file);
		
		Process moonProc;
		try {
			moonProc = moon.start();
			moonProc.waitFor();
			
			BufferedReader r = new BufferedReader(new InputStreamReader(moonProc.getInputStream()));
			
			while(r.ready()){
				System.out.println(r.readLine());			
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (InterruptedException e) {
			
		}
	}
	public static void main(String[] args) {
		run();
	}

}

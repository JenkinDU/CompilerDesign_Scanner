package comp6421.semantic.code;

import java.io.PrintStream;

import comp6421.semantic.CompilerError;
import comp6421.semantic.FunctionEntry;
import comp6421.semantic.SymbolContext;
import comp6421.semantic.SymbolTable;
import comp6421.semantic.entry.ClassEntry;
import comp6421.semantic.entry.SymbolTableEntry;
import comp6421.semantic.expression.Statement;

public class CodeGenerator {
	
	private PrintStream output;
	
	private int nErrors;
	
	public CodeGenerator(PrintStream output){
		this.output = output;
		nErrors = 0;
	}
	
	public void generate() {
		
		SymbolTable s = SymbolContext.getCurrentScope();
		
		generate(s);
		
		SymbolTableEntry program = s.find("program");
		
		try{
			generateProgram((FunctionEntry) program);
		}catch(CompilerError x){
			++nErrors;
		}
		
	}
	
	private void generate(SymbolTable s) {
			for(SymbolTableEntry e : s.getEntries()){
				if(e instanceof FunctionEntry && e.getName() != "program"){
					try{
						generateFunction((FunctionEntry) e);
					}catch(CompilerError x){
						++nErrors;
					}
				}
				if(e instanceof ClassEntry){
					generate(e.getScope());
				}
			}
	}
	
	private void generateProgram(FunctionEntry program) throws CompilerError{
		
		CodeGenerationContext c = new CodeGenerationContext();
			
		String stackLabel = "stack";
		
		c.appendInstruction(new EntryInstruction().setComment("==== ENTRY ===="));
		c.appendInstruction(new AddWordImmediateInstruction(Register.STACK_POINTER, Register.ZERO, stackLabel));
		c.appendInstruction(new AddWordImmediateInstruction(Register.STACK_POINTER, Register.STACK_POINTER, program.getScope().getSize()).setComment("init stack pointer"));
		
		for(Statement s : program.getStatements()){
			c.commentNext(s.getClass().getSimpleName());
			s.generateCode(c);
		}
		
		c.appendInstruction(new HaltInstruction().setComment("==== END ===="));
		
		c.appendInstruction(new AlignInstruction().setLabel(stackLabel).setComment("start of stack"));
		c.printCode(output);
	}
	
	private void generateFunction(FunctionEntry func) throws CompilerError{
		CodeGenerationContext c = new CodeGenerationContext();
		SymbolTable s = func.getScope();
		
		int returnAddrMemOffset =  -s.getSize() + s.find(SpecialValues.RETURN_ADDRESS_PARAMETER_NAME).getOffset();
		
		c.labelNext(func.getLabel());
		c.commentNext(func.toString());
		
		c.appendInstruction(new StoreWordInstruction(Register.STACK_POINTER, returnAddrMemOffset, Register.RETURN_ADDRESS)
				.setComment("Save return address"));
		
		for(Statement stat : func.getStatements()){
			c.commentNext(stat.getClass().getSimpleName());
			stat.generateCode(c);
		}
				
		Register pcRegister = c.getTemporaryRegister();
		c.appendInstruction(new LoadWordInstruction(pcRegister, Register.STACK_POINTER, returnAddrMemOffset)
				.setComment("get return address"));
		// reset the stackPointer!!
		c.appendInstruction(new AddWordImmediateInstruction(Register.STACK_POINTER, Register.STACK_POINTER, -1 * s.getSize())
			.setComment("reset stack pointer"));
		c.appendInstruction(new JumpRegisterInstruction(pcRegister)
				.setComment("return"));
		
		c.printCode(output);
		
	}

	public int getNumErrors() {
		return nErrors;
	}
	
}

package comp6421.semantic.code;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import comp6421.semantic.InternalCompilerError;
import comp6421.semantic.SemanticException;

public class CodeGenerationContext {

	private static int uniqueLebelId;
	
	static {  uniqueLebelId = 0;  }
	
	private Set<Register> temporaryRegisters;
	private List<Instruction> instructions;
	private String nextLabel;
	private String nextComment;
	
	public int getUniqueLabelId(){
		return ++uniqueLebelId;
	}
	
	public CodeGenerationContext(){
		temporaryRegisters = new HashSet<Register>();
		temporaryRegisters.addAll(Register.unallocatedRegisters);
		
		instructions = new ArrayList<Instruction>();
		
		nextLabel = null;
		
	}

	public Register getTemporaryRegister() {
		Register temp = temporaryRegisters.iterator().next();
		temporaryRegisters.remove(temp);
		return temp;
	}
	
	public Register getTemporaryRegister(Register temp) {
		
		if(temp.reserved){
			temp = getTemporaryRegister();
		}
		
		return temp;
	}
	
	public void freeTemporaryRegister(Register temp) throws SemanticException{
		if(temp.reserved){
			return;
		}
		
		boolean alreadyExisted = ! temporaryRegisters.add(temp);
	}

	public void appendInstruction(Instruction instr) {
		instructions.add(instr);
		if(nextLabel != null){
			instr.setLabel(nextLabel);
			nextLabel = null;
		}
		if(nextComment != null){
			instr.setComment(nextComment);
			nextComment = null;
		}
	}

	public void printCode(PrintStream out) {
		for(Instruction i : instructions){
			out.println(i.getCode());
		}
	}

	public void labelNext(String label) {
		nextLabel = label;
	}

	public void commentNext(String comment) {
		nextComment = comment;
	}

}

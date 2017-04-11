package comp6421.semantic.code;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import comp6421.semantic.SemanticException;

public class CodeGenerationContext {

	private static int id = 0;

	private Set<Register> registers;
	private List<Instruction> instructions;
	private String label;
	private String comment;

	public int getId() {
		return ++id;
	}

	public CodeGenerationContext() {
		registers = new HashSet<Register>();
		registers.addAll(Register.registers);

		instructions = new ArrayList<Instruction>();

		label = null;

	}

	public Register getTemporaryRegister() {
		Register temp = registers.iterator().next();
		registers.remove(temp);
		return temp;
	}

	public Register getTemporaryRegister(Register temp) {

		if (temp.reserved) {
			temp = getTemporaryRegister();
		}

		return temp;
	}

	public void freeTemporaryRegister(Register temp) throws SemanticException {
		if (temp.reserved) {
			return;
		}
		registers.add(temp);
	}

	public void appendInstruction(Instruction instr) {
		instructions.add(instr);
		if (label != null) {
			instr.setLabel(label);
			label = null;
		}
		if (comment != null) {
			instr.setComment(comment);
			comment = null;
		}
	}

	public void printCode(PrintStream out) {
		for (Instruction i : instructions) {
			out.println(i.getCode());
		}
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}

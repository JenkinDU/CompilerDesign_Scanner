package comp6421.semantic.code;

import comp6421.semantic.SemanticException;

public class AddWordImmediateInstruction extends Instruction {

	private final Register d;
	private final Register r;
	private final String v;

	public AddWordImmediateInstruction(Register dest, Register regVal, int iVal) throws SemanticException {
		this.d = dest;
		this.r = regVal;
		this.v = Integer.toString(iVal);
	}

	public AddWordImmediateInstruction(Register dest, Register regVal, String label) {
		this.d = dest;
		this.r = regVal;
		this.v = label;
	}

	@Override
	protected String code() {
		return "addi" + '\t' + d.registerName + ", " + r.registerName + ", " + v;
	}

}

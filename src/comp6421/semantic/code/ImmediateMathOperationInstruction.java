package comp6421.semantic.code;

public class ImmediateMathOperationInstruction extends Instruction {

	private String opcode;
	private Register dest;
	private Register src;
	private int k;

	public ImmediateMathOperationInstruction(String opcode, Register dest, Register src, int k) {
		this.opcode = opcode;
		this.dest = dest;
		this.src = src;
		this.k = k;
	}

	@Override
	protected String code() {
		return opcode + '\t' + dest.registerName + ", " + src.registerName + ", " + k;
	}

}

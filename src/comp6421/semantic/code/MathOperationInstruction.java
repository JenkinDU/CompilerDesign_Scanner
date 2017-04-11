package comp6421.semantic.code;

public class MathOperationInstruction extends Instruction {

	private String opcode;
	private Register dest;
	private Register a;
	private Register b;

	public MathOperationInstruction(String opcode, Register dest, Register a, Register b) {
		this.opcode = opcode;
		this.dest = dest;
		this.a = a;
		this.b = b;
	}

	@Override
	protected String code() {
		return opcode + '\t' + dest.registerName + ", " + a.registerName + ", " + b.registerName;
	}

}

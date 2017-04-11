package comp6421.semantic.code;

public class JumpRegisterInstruction extends Instruction {

	private final Register r;

	public JumpRegisterInstruction(Register register) {
		this.r = register;
	}

	@Override
	protected String code() {
		return "jr\t" + r.registerName;
	}

}

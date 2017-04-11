package comp6421.semantic.code;

public class JumpAndLinkInstruction extends Instruction {

	private final Register linkRegister;
	private final String label;

	public JumpAndLinkInstruction(Register returnAddress, String callingLabel) {
		this.linkRegister = returnAddress;
		this.label = callingLabel;
	}

	@Override
	protected String code() {
		return "jl\t" + linkRegister.registerName + ", " + label;
	}

}

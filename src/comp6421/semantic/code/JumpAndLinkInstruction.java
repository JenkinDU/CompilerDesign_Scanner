package comp6421.semantic.code;

public class JumpAndLinkInstruction extends Instruction {

	private final Register link;
	private final String label;

	public JumpAndLinkInstruction(Register returnAddress, String callingLabel) {
		this.link = returnAddress;
		this.label = callingLabel;
	}

	@Override
	protected String code() {
		return "jl\t" + link.registerName + ", " + label;
	}

}

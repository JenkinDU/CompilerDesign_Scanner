package comp6421.semantic.code;

public class BranchOnZeroInstruction extends Instruction {

	private String label;
	private Register register;

	public BranchOnZeroInstruction(Register register, String label) {
		this.label = label;
		this.register = register;
	}

	@Override
	protected String _getCode() {
		return "bz" + '\t' + register.registerName + ", " + label;
	}

}

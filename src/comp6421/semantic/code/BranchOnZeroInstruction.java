package comp6421.semantic.code;

public class BranchOnZeroInstruction extends Instruction {

	private String l;
	private Register r;

	public BranchOnZeroInstruction(Register register, String label) {
		this.l = label;
		this.r = register;
	}

	@Override
	protected String code() {
		return "bz" + '\t' + r.registerName + ", " + l;
	}

}

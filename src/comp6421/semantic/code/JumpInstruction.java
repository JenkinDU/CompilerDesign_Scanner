package comp6421.semantic.code;

public class JumpInstruction extends Instruction {

	private String label;
	
	public JumpInstruction(String label) {
		this.label = label;
	}
	
	@Override
	protected String _getCode() {
		return "j\t" + label;
	}

}

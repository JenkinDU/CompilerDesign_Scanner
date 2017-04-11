package comp6421.semantic.code;

public class PutInstruction extends Instruction {

	private Register source;

	public PutInstruction(Register source) {
		this.source = source;
	}

	@Override
	protected String _getCode() {
		return "putc\t" + source.registerName;
	}

}

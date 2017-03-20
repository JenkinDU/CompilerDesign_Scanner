package comp6421.semantic.code;

public class NoopInstruction extends Instruction {

	@Override
	protected String _getCode() {
		return "nop";
	}

}

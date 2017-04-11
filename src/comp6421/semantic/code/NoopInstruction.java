package comp6421.semantic.code;

public class NoopInstruction extends Instruction {

	@Override
	protected String code() {
		return "nop";
	}

}

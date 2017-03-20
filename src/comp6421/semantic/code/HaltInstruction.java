package comp6421.semantic.code;

public class HaltInstruction extends Instruction {

	@Override
	protected String _getCode() {
		return "hlt";
	}

}

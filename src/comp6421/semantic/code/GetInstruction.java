package comp6421.semantic.code;

public class GetInstruction extends Instruction {

	Register destination;

	public GetInstruction(Register destination) {
		this.destination = destination;
	}

	@Override
	protected String _getCode() {
		return "getc\t" + destination.registerName;
	}

}

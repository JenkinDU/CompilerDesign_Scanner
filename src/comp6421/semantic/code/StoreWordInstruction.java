package comp6421.semantic.code;

public class StoreWordInstruction extends Instruction {

	private final Register d;
	private final int offset;
	private final Register v;

	public StoreWordInstruction(Register baseAddress, int offset, Register value) {
		this.d = baseAddress;
		this.offset = offset;
		this.v = value;
	}

	@Override
	protected String code() {
		return "sw" + '\t' + offset + "(" + d.registerName + "), " + v.registerName;
	}

}

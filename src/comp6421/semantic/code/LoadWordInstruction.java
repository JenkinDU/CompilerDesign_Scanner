package comp6421.semantic.code;

import comp6421.semantic.value.NumberValue;
import comp6421.semantic.value.RegisterValue;

public class LoadWordInstruction extends Instruction {

	private final Register d;
	private final Register src;
	private final int offset;

	public LoadWordInstruction(RegisterValue destination, RegisterValue baseAddress, NumberValue offsetValue) {
		d = destination.getRegister();
		src = baseAddress.getRegister();
		offset = offsetValue.intValue();
	}

	public LoadWordInstruction(Register destination, Register baseAddress, int offsetValue) {
		d = destination;
		src = baseAddress;
		offset = offsetValue;
	}

	@Override
	protected String code() {
		return "lw" + '\t' + d.registerName + ", " + offset + "(" + src.registerName + ")";
	}

}

package comp6421.semantic.code;

import comp6421.semantic.value.NumberValue;
import comp6421.semantic.value.RegisterValue;

public class LoadWordInstruction extends Instruction {

	private final Register destReg;
	private final Register sourceReg;
	private final int offset;

	public LoadWordInstruction(RegisterValue destination, RegisterValue baseAddress, NumberValue offsetValue) {
		destReg = destination.getRegister();
		sourceReg = baseAddress.getRegister();
		offset = offsetValue.intValue();
	}

	public LoadWordInstruction(Register destination, Register baseAddress, int offsetValue) {
		destReg = destination;
		sourceReg = baseAddress;
		offset = offsetValue;
	}

	@Override
	protected String code() {
		return "lw" + '\t' + destReg.registerName + ", " + offset + "(" + sourceReg.registerName + ")";
	}

}

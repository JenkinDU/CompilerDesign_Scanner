package comp6421.semantic.code;

import comp6421.semantic.value.RegisterValue;
import comp6421.semantic.value.StaticValue;

public class LoadWordInstruction extends Instruction {

	private final Register destReg;
	private final Register sourceReg;
	private final int 	   offset;
	
	public LoadWordInstruction(RegisterValue destination, RegisterValue baseAddress, StaticValue offsetValue) {
		destReg   = destination.getRegister();
		sourceReg = baseAddress.getRegister();
		offset   = offsetValue.intValue();
	}
	
	public LoadWordInstruction(Register destination, Register baseAddress, int offsetValue) {
		destReg   = destination;
		sourceReg = baseAddress;
		offset   = offsetValue;
	}

	@Override
	protected String _getCode() {
		return "lw" + '\t' + destReg.registerName + ", " + offset + "(" + sourceReg.registerName + ")";
	}

}

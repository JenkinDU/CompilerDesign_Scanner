package comp6421.semantic.code;

import comp6421.semantic.value.RegisterValue;

public class AddWordInstruction extends Instruction {

	private final Register d;
	private final Register a;
	private final Register b;

	public AddWordInstruction(RegisterValue dVal, RegisterValue aVal, RegisterValue bVal) {
		d = dVal.getRegister();
		a = aVal.getRegister();
		b = bVal.getRegister();
	}

	public AddWordInstruction(Register dVal, Register aVal, Register bVal) {
		d = dVal;
		a = aVal;
		b = bVal;
	}

	@Override
	protected String code() {
		return "add" + '\t' + d.registerName + ", " + a.registerName + ", " + b.registerName;
	}

}

package comp6421.semantic.code;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public enum Register {
	ZERO("R0"), STACK_POINTER("R1"), RETURN_ADDRESS(
			"R2"), R3, R4, R5, R6, R7, R8, R9, R10, R11, R12, R13, R14, R15, PROGRAM_COUNTER("PC"),;
	public final static String RETURN_ADDRESS_PARAMETER_NAME = "return";
	public final static String THIS_POINTER_NAME = "this->";
	public final static int TRUE = ~0;
	public final static int FALSE = 0;
	public final static Register RETURN_VALUE = RETURN_ADDRESS;

	public final String registerName;
	public final String symbolicName;
	public final boolean reserved;

	public final static Set<Register> registers;

	static {
		Set<Register> reg = new HashSet<Register>(16);

		for (Register r : values()) {
			if (!r.reserved) {
				reg.add(r);
			}
		}

		registers = Collections.unmodifiableSet(reg);
	}

	private Register(String name) {
		registerName = name;
		symbolicName = name + "(" + toString() + ")";
		reserved = true;
	}

	private Register() {
		registerName = this.toString();
		symbolicName = registerName;
		reserved = false;
	}

}

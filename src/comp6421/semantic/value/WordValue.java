package comp6421.semantic.value;

import comp6421.semantic.SemanticException;
import comp6421.semantic.code.CodeGenerationContext;

public class WordValue extends DynamicValue {

	private final Value offset;
	private final Value baseAddress;

	public WordValue(Value baseAddr, Value offset) {
		this.baseAddress = baseAddr;
		this.offset = offset;
	}

	public Value getOffset() {
		return offset;
	}

	public Value getBaseAddress() {
		return baseAddress;
	}

	@Override
	public String toString() {
		return "*(" + baseAddress + " + " + offset + ")";
	}

	@Override
	public boolean equals(Object other) {
		return other instanceof WordValue && ((WordValue) other).getBaseAddress().equals(baseAddress)
				&& ((WordValue) other).getOffset().equals(offset);
	}

	@Override
	public Value getUseableValue(CodeGenerationContext c) throws SemanticException {
		return getConcreteAddress(c).getUseableValue(c);
	}

	@Override
	public RegisterValue getRegisterValue(CodeGenerationContext c) throws SemanticException {
		return getConcreteAddress(c).getRegisterValue(c);
	}

	public ConcreteAddressValue getConcreteAddress(CodeGenerationContext c) throws SemanticException {
		Value useableOffset = offset.getUseableValue(c);
		RegisterValue baseAddrReg = baseAddress.getRegisterValue(c);

		if (useableOffset instanceof NumberValue) {

			return new ConcreteAddressValue(baseAddrReg, (NumberValue) useableOffset);

		}
		return null;
		// else
		// if(useableOffset instanceof RegisterValue){
		//
		// RegisterValue tempReg = new
		// RegisterValue(c.getTemporaryRegister(baseAddrReg.getRegister()));
		//
		// c.appendInstruction(new AddWordInstruction(tempReg, baseAddrReg,
		// (RegisterValue)useableOffset));
		//
		// Register useableOffsetRegister = ((RegisterValue)
		// useableOffset).getRegister();
		// if( ! useableOffsetRegister.reserved){
		// c.freeTemporaryRegister(useableOffsetRegister);
		// }
		//
		// return new ConcreteAddressValue(tempReg, new NumberValue(0));
		//
		// }else{
		// throw new SemanticException("getUseableValue for offset returned an
		// instance of " + useableOffset.getClass().getName());
		// }

	}

}

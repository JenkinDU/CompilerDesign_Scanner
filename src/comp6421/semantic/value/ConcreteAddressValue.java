package comp6421.semantic.value;

import comp6421.semantic.InternalCompilerError;
import comp6421.semantic.code.CodeGenerationContext;
import comp6421.semantic.code.LoadWordInstruction;
import comp6421.semantic.code.Register;

public class ConcreteAddressValue extends DynamicValue {

	private RegisterValue baseAddr;
	private NumberValue   offset;
	
	public ConcreteAddressValue(RegisterValue baseAddr, NumberValue offset) {
		this.baseAddr = baseAddr;
		this.offset   = offset;
	}

	public int getOffset(){
		return offset.intValue();
	}
	
	public Register getBaseAddress(){
		return baseAddr.getRegister();
	}
	
	@Override
	public Value getUseableValue(CodeGenerationContext c) throws InternalCompilerError {
		RegisterValue tempReg = new RegisterValue(c.getTemporaryRegister(baseAddr.getRegister()));
		
		c.appendInstruction(new LoadWordInstruction(tempReg, baseAddr, offset));
		
		return tempReg;
	}

	@Override
	public RegisterValue getRegisterValue(CodeGenerationContext c) throws InternalCompilerError {
		return (RegisterValue) getUseableValue(c);
	}
	
	@Override
	public boolean equals(Object obj) {
		return obj instanceof ConcreteAddressValue
				&& getBaseAddress().equals(((ConcreteAddressValue )obj).getBaseAddress())
				&&   getOffset() == (((ConcreteAddressValue )obj).getOffset());
	}

	@Override
	public String toString() {
		return "*(" + baseAddr + " + " + offset + ")";
	}
	
}

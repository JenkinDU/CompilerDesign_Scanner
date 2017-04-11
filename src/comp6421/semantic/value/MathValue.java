package comp6421.semantic.value;

import comp6421.semantic.SemanticException;
import comp6421.semantic.InternalCompilerError;
import comp6421.semantic.TableContext;
import comp6421.semantic.code.CodeGenerationContext;
import comp6421.semantic.code.ImmediateMathOperationInstruction;
import comp6421.semantic.code.MathOperation;
import comp6421.semantic.code.MathOperationInstruction;
import comp6421.semantic.code.Register;


public class MathValue extends DynamicValue {

	private final MathOperation operator;
	private final Value b;
	private final Value a;
	
	public MathValue(MathOperation operator, Value a, Value b) {
		this.operator = operator;
		this.a = a;
		this.b = b;
		if(TableContext.getInstance().showMigration) {
			System.out.println(this.toString());
		}
	}

	@Override
	public Value getUseableValue(CodeGenerationContext c) throws SemanticException {
		Value uA = a.getUseableValue(c);
		Value uB = b.getUseableValue(c);
		
		if(uA instanceof RegisterValue && uB instanceof RegisterValue){
			return _getUseableValue((RegisterValue)uA, (RegisterValue)uB, c);
		}
		
		if(uA instanceof NumberValue && uB instanceof RegisterValue){
			return _getUseableValue((NumberValue)uA, (RegisterValue)uB, c);
		}

		if(uA instanceof RegisterValue && uB instanceof NumberValue){
			return _getUseableValue((RegisterValue)uA, (NumberValue)uB, c);
		}

		if(uA instanceof NumberValue && uB instanceof NumberValue){
			return _getUseableValue((NumberValue)uA, (NumberValue)uB, c);
		}
		
		
		throw new InternalCompilerError("Unexpected combination of types for a and b: " + uA.getClass() + ", " + uB.getClass());
	}

	private Value _getUseableValue(NumberValue uA, NumberValue uB, CodeGenerationContext c) {
		// If both values are static, then we do the operation at compile time! We're so smart!
		return new NumberValue(operator.operate(uA.intValue(), uB.intValue()));
	}

	private Value _getUseableValue(RegisterValue uA, NumberValue uB, CodeGenerationContext c) throws SemanticException {
		Register aReg = uA.getRegister();
		Register temp = c.getTemporaryRegister(aReg);
		
		c.appendInstruction(new ImmediateMathOperationInstruction(operator.immediateOpcode, temp, aReg, uB.intValue()));

		return new RegisterValue(temp);
	}

	private Value _getUseableValue(NumberValue uA, RegisterValue uB, CodeGenerationContext c) throws SemanticException {
		if(operator.commutative){
			return _getUseableValue(uB, uA, c);
		}else{
			return _getUseableValue(uA.getRegisterValue(c), uB, c);
		}
	}

	private Value _getUseableValue(RegisterValue uA, RegisterValue uB, CodeGenerationContext c) throws SemanticException {
		Register aReg = uA.getRegister();
		Register bReg = uB.getRegister();
		Register temp;
		boolean free = false;
		if(aReg.reserved){
			temp = c.getTemporaryRegister(bReg);
		}else{
			temp = aReg;
			if(! bReg.reserved){
				free = true;
			}
		}
		
		c.appendInstruction(new MathOperationInstruction(operator.opcode, temp, aReg, bReg));
		
		if(free){
			c.freeTemporaryRegister(bReg);
		}
		
		return new RegisterValue(temp);
	}

	@Override
	public RegisterValue getRegisterValue(CodeGenerationContext c) throws SemanticException {
		return getUseableValue(c).getRegisterValue(c);
	}
	
	@Override
	public String toString() {
		return "(" + a + " " + operator.symbol + " " + b + ")";
	}
}

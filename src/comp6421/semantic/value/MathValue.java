package comp6421.semantic.value;

import comp6421.semantic.SemanticException;
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
		Value first = a.getUseableValue(c);
		Value second = b.getUseableValue(c);
		
		if(first instanceof RegisterValue && second instanceof RegisterValue){
			Register aReg = ((RegisterValue)first).getRegister();
			Register bReg = ((RegisterValue)second).getRegister();
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
		
		if(first instanceof NumberValue && second instanceof RegisterValue){
			if(operator.commutative){
				Register aReg = ((RegisterValue)second).getRegister();
				Register temp = c.getTemporaryRegister(aReg);
				
				c.appendInstruction(new ImmediateMathOperationInstruction(operator.immediateOpcode, temp, aReg, ((NumberValue)first).intValue()));

				return new RegisterValue(temp);
			}else{
				Register aReg = ((RegisterValue)first).getRegister();
				Register bReg = ((RegisterValue)second).getRegister();
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
		}

		if(first instanceof RegisterValue && second instanceof NumberValue){
			Register aReg = ((RegisterValue)first).getRegister();
			Register temp = c.getTemporaryRegister(aReg);
			
			c.appendInstruction(new ImmediateMathOperationInstruction(operator.immediateOpcode, temp, aReg, ((NumberValue)second).intValue()));

			return new RegisterValue(temp);
		}

		if(first instanceof NumberValue && second instanceof NumberValue){
			return new NumberValue(operator.operate(((NumberValue)first).intValue(), ((NumberValue)second).intValue()));
		}
		throw new SemanticException("Unexpected combination of types for a and b: " + first.getClass() + ", " + second.getClass());
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

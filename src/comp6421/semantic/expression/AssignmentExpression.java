package comp6421.semantic.expression;

import comp6421.semantic.InternalCompilerError;
import comp6421.semantic.SemanticException;
import comp6421.semantic.TableContext;
import comp6421.semantic.code.CodeGenerationContext;
import comp6421.semantic.code.Register;
import comp6421.semantic.code.StoreWordInstruction;
import comp6421.semantic.value.ConcreteAddressValue;
import comp6421.semantic.value.RegisterValue;
import comp6421.semantic.value.StoredValue;
import comp6421.semantic.value.Value;
import comp6421.semantic.value.VoidValue;

public class AssignmentExpression extends ExpressionElement implements Statement {

	private static enum State {
		INIT_LHS,
		LHS,
		INIT_RHS,
		RHS,
		DONE
	}
	
	private State currentState;
	private Value rhs;
	private Value lhs;
	
	public AssignmentExpression(){
		currentState = State.INIT_LHS;
	}
	
	@Override
	public void pushIdentifier(String id) throws SemanticException {
		if(currentState == State.INIT_LHS){
			currentState = State.LHS;
			context.pushChild(new VariableExpressionFragment(id));
		}else
		if(currentState == State.INIT_RHS){
			currentState = State.RHS;
			context.pushChild(new VariableExpressionFragment(id));
		}else{
			super.pushIdentifier(id);
		}
	}
	
	@Override
	public void acceptSubElement(ExpressionElement e) throws SemanticException {
		if(currentState == State.LHS){
			lhs = e.getValue();
			currentState = State.RHS;
		}else
		if(currentState == State.RHS){
			if(TableContext.getInstance().showMigration) {
				System.out.println("\nAttributes Migration from MathValue Tree:");
			}
			rhs = e.getValue();
			currentState = State.DONE;
			context.finishTopElement();
		}else{
			throw new InternalCompilerError("Unexpected " + e + " while in state " + currentState.toString());
		}		
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + "{ " + pseudoCode() + " }";
	}
	
	@Override
	public String pseudoCode() {
		return lhs + " = " + rhs;
	}

	@Override
	public Value getValue() {
		return VoidValue.get();
	}
	
	public Value getLhs() {
		return lhs;
	}
	
	public Value getRhs() {
		return rhs;
	}
	
	@Override
	public void generateCode(CodeGenerationContext c) throws SemanticException {
		if(currentState == State.DONE){
			
			if(lhs instanceof StoredValue){
				RegisterValue    rhsRegisterValue = rhs.getRegisterValue(c);
				ConcreteAddressValue lhsAddrValue = ((StoredValue)lhs).getConcreteAddress(c);
				
				c.appendInstruction(new StoreWordInstruction(lhsAddrValue.getBaseAddress(), lhsAddrValue.getOffset(), rhsRegisterValue.getRegister()));
				
				Register rhsReg = rhsRegisterValue.getRegister();
				if( ! rhsReg.reserved){
					c.freeTemporaryRegister(rhsReg);
				}
				
				Register lhsReg = lhsAddrValue.getBaseAddress();
				if( ! lhsReg.reserved){
					c.freeTemporaryRegister(lhsReg);
				}
			}else{
				throw new InternalCompilerError("Expected LHS to be a StoredValue, instead got " + lhs.getClass().getName());
			}
		}else{
			throw new InternalCompilerError("Tried to generate code from incomplete " + AssignmentExpression.class.getSimpleName() + " statement");
		}
	}
}

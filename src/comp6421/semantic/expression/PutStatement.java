package comp6421.semantic.expression;

import comp6421.semantic.CompilerError;
import comp6421.semantic.code.CodeGenerationContext;
import comp6421.semantic.code.PutInstruction;
import comp6421.semantic.code.Register;
import comp6421.semantic.value.RegisterValue;
import comp6421.semantic.value.Value;
import comp6421.semantic.value.VoidValue;

public class PutStatement extends ExpressionElement implements Statement {

	RelationExpressionFragment expr;
	
	@Override
	public void generateCode(CodeGenerationContext c) throws CompilerError {
		RegisterValue useableVal = expr.getValue().getRegisterValue(c);
		
		Register r = useableVal.getRegister();
		
//		c.appendInstruction(new ShiftLeftInstruction(r, r, 24))
		c.appendInstruction(new PutInstruction(r));
	
		
		c.freeTemporaryRegister(r);
	}

	@Override
	public void acceptSubElement(ExpressionElement e) throws CompilerError {
		if(e instanceof RelationExpressionFragment){
			expr = (RelationExpressionFragment) e;
			context.finishTopElement();
		}else{
			super.acceptSubElement(e);			
		}
	}
	
	@Override
	public String pseudoCode() {
		return "put " + expr;
	}

	@Override
	public Value getValue() throws CompilerError {
		return VoidValue.get();
	}

}

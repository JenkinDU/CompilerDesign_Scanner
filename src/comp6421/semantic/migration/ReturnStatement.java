package comp6421.semantic.migration;

import comp6421.semantic.InternalCompilerError;
import comp6421.semantic.SemanticException;
import comp6421.semantic.code.AddWordImmediateInstruction;
import comp6421.semantic.code.AddWordInstruction;
import comp6421.semantic.code.CodeGenerationContext;
import comp6421.semantic.code.Register;
import comp6421.semantic.value.NumberValue;
import comp6421.semantic.value.RegisterValue;
import comp6421.semantic.value.Value;
import comp6421.semantic.value.VoidValue;

public class ReturnStatement extends ExpressionElement implements Statement {

	Value returnValue;
	
	@Override
	public void generateCode(CodeGenerationContext c) throws SemanticException {
		Value useableReturnVal = returnValue.getUseableValue(c);
		if(useableReturnVal instanceof RegisterValue){
			c.appendInstruction(new AddWordInstruction(Register.RETURN_VALUE, Register.ZERO, ((RegisterValue) useableReturnVal).getRegister()));
		}else
		if(useableReturnVal instanceof NumberValue){
			c.appendInstruction(new AddWordImmediateInstruction(Register.RETURN_VALUE, Register.ZERO, ((NumberValue) useableReturnVal).intValue()));
		}else{
			throw new InternalCompilerError("Useable Value retured an unexpected type " + useableReturnVal.getClass());
		}
	}

	@Override
	public void acceptSubElement(ExpressionElement e) throws SemanticException {
		if(e instanceof RelationExpressionFragment){
			returnValue = e.getValue();
			context.finishTopElement();
		}else{
			super.acceptSubElement(e);			
		}
	}
	
	@Override
	public String pseudoCode() {
		return "return " + returnValue;
	}

	@Override
	public Value getValue() throws SemanticException {
		return VoidValue.get();
	}

}

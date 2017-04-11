package comp6421.semantic.value;

import java.util.ArrayList;
import java.util.List;

import comp6421.semantic.SemanticException;
import comp6421.semantic.FunctionEntry;
import comp6421.semantic.code.AddWordImmediateInstruction;
import comp6421.semantic.code.CodeGenerationContext;
import comp6421.semantic.code.JumpAndLinkInstruction;
import comp6421.semantic.code.Register;
import comp6421.semantic.code.StoreWordInstruction;
import comp6421.semantic.entry.FunctionType;
import comp6421.semantic.migration.TypedExpressionElement;
import comp6421.semantic.entry.EntryType;

public class FunctionCallValue extends DynamicValue implements Value {

	private List<Value> arguments;
	private int scopeSize;
	
	private String callingLabel;
	
	public FunctionCallValue(FunctionEntry entry, List<TypedExpressionElement> expressions) throws SemanticException {
		int nArgs = expressions.size();
		List<EntryType> argTypes = ((FunctionType)entry.getType()).getArgumentTypes();
		
		if(nArgs != argTypes.size()){
			throw new SemanticException("call function " + entry.getName() +" ("+(argTypes.size()-1)+" params) "+ "error, params size:" + (nArgs-1));
		}
		
		arguments = new ArrayList<Value>(expressions.size());
		
		for(int i = 0; i < expressions.size(); ++i){
			TypedExpressionElement exp    = expressions.get(i);
			EntryType expectedType = argTypes.get(i);
			EntryType type = exp.getType();
			
			if(! type.equals( expectedType )){
				throw new SemanticException("call function " + entry.getName() +" error:" + "param "+(i) +" should be " + expectedType + " but " + type);
			}
			
			Value arg = exp.getValue();

			arguments.add(arg);
		}
		
		callingLabel = entry.getLabel();
		scopeSize = entry.getScope().getSize();
	}

	@Override
	public Value getUseableValue(CodeGenerationContext c) throws SemanticException {
		// TODO Auto-generated method stub
		
		// Pass the parameters
		int offset = 0;
		for(Value arg : arguments){
			c.commentNext("storing argument");
			c.appendInstruction(new StoreWordInstruction(Register.STACK_POINTER, offset, arg.getRegisterValue(c).getRegister()));
			offset += 4;
		}
		
		c.appendInstruction(new AddWordImmediateInstruction(Register.STACK_POINTER, Register.STACK_POINTER, scopeSize)
				.setComment("set up new stack pointer"));
		c.appendInstruction(new JumpAndLinkInstruction(Register.RETURN_ADDRESS, callingLabel)
				.setComment("jump, storing return address in " + Register.RETURN_ADDRESS.registerName));
		
		return new RegisterValue(Register.RETURN_VALUE);
	}

	@Override
	public RegisterValue getRegisterValue(CodeGenerationContext c) throws SemanticException {
		return getUseableValue(c).getRegisterValue(c);
	}

}

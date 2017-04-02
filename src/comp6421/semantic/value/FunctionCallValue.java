package comp6421.semantic.value;

import java.util.ArrayList;
import java.util.List;

import comp6421.semantic.CompilerError;
import comp6421.semantic.FunctionEntry;
import comp6421.semantic.code.AddWordImmediateInstruction;
import comp6421.semantic.code.CodeGenerationContext;
import comp6421.semantic.code.JumpAndLinkInstruction;
import comp6421.semantic.code.Register;
import comp6421.semantic.code.StoreWordInstruction;
import comp6421.semantic.entry.FunctionType;
import comp6421.semantic.entry.SymbolTableEntryType;
//import comp6421.semantic.expression.TypedExpressionElement;

public class FunctionCallValue extends DynamicValue implements Value {

	private List<Value> arguments;
	private int scopeSize;
	
	private String callingLabel;
	
//	public FunctionCallValue(FunctionEntry entry, List<TypedExpressionElement> expressions) throws CompilerError {
//		int nArgs = expressions.size();
//		List<SymbolTableEntryType> argTypes = ((FunctionType)entry.getType()).getArgumentTypes();
//		
//		if(nArgs != argTypes.size()){
//			throw new CompilerError("wrong number of arguments for function " + entry.getName() + ", expected " + argTypes.size() + ", got " + nArgs);
//		}
//		
//		arguments = new ArrayList<Value>(expressions.size());
//		
//		for(int i = 0; i < expressions.size(); ++i){
//			TypedExpressionElement exp    = expressions.get(i);
//			SymbolTableEntryType expectedType = argTypes.get(i);
//			SymbolTableEntryType type = exp.getType();
//			
//			if(! type.equals( expectedType )){
//				throw new CompilerError("Argument type mismatch for argument " + (i+1) + " for call to '" + entry.getName() + "' expected '" + expectedType + "' but got '" + type + "'");
//			}
//			
//			Value arg = exp.getValue();
//
//			arguments.add(arg);
//		}
//		
//		callingLabel = entry.getLabel();
//		scopeSize = entry.getScope().getSize();
//	}

	@Override
	public Value getUseableValue(CodeGenerationContext c) throws CompilerError {
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
	public RegisterValue getRegisterValue(CodeGenerationContext c) throws CompilerError {
		return getUseableValue(c).getRegisterValue(c);
	}

}

package comp6421.semantic.migration;

import comp6421.semantic.SemanticException;
import comp6421.semantic.code.CodeGenerationContext;
import comp6421.semantic.code.GetInstruction;
import comp6421.semantic.code.Register;
import comp6421.semantic.code.StoreWordInstruction;
import comp6421.semantic.value.ConcreteAddressValue;
import comp6421.semantic.value.StoredValue;
import comp6421.semantic.value.Value;
import comp6421.semantic.value.VoidValue;

public class GetStatement extends ExpressionElement implements Statement {

	StoredValue destination;
	
	@Override
	public void generateCode(CodeGenerationContext c) throws SemanticException {
		Register r = c.getTemporaryRegister();
		
		ConcreteAddressValue addr = destination.getConcreteAddress(c);
		
		c.appendInstruction(new GetInstruction(r));
		
		c.appendInstruction(new StoreWordInstruction(addr.getBaseAddress(), addr.getOffset(), r));
		
		c.freeTemporaryRegister(r);
		c.freeTemporaryRegister(addr.getBaseAddress());
	}

	@Override
	public void pushIdentifier(String id) throws SemanticException {
		context.pushChild(new VariableExpressionFragment(id));
	}
	
	@Override
	public void acceptSubElement(ExpressionElement e) throws SemanticException {
		if(e instanceof VariableExpressionFragment){
			destination = (StoredValue)e.getValue();
			context.finishTopElement();
		}else{
			super.acceptSubElement(e);
		}
	}
	
	@Override
	public String pseudoCode() {
		return "get " + destination;
	}

	@Override
	public Value getValue() throws SemanticException {
		return VoidValue.get();
	}

}

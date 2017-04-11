package comp6421.semantic.migration;

import comp6421.semantic.SemanticException;
import comp6421.semantic.code.CodeGenerationContext;
import comp6421.semantic.code.PutInstruction;
import comp6421.semantic.code.Register;
import comp6421.semantic.value.RegisterValue;

public class PutStatement extends Expression implements Statement {

	RelationExpressionFragment expr;

	@Override
	public void generateCode(CodeGenerationContext c) throws SemanticException {
		RegisterValue useableVal = expr.getValue().getRegisterValue(c);
		Register r = useableVal.getRegister();
		c.appendInstruction(new PutInstruction(r));
		c.freeTemporaryRegister(r);
	}

	@Override
	public void acceptSubElement(Expression e) throws SemanticException {
		if (e instanceof RelationExpressionFragment) {
			expr = (RelationExpressionFragment) e;
			context.finishTopElement();
		} else {
			super.acceptSubElement(e);
		}
	}

	@Override
	public String pseudoCode() {
		return "put " + expr;
	}
}

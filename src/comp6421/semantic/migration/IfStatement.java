package comp6421.semantic.migration;

import comp6421.semantic.SemanticException;
import comp6421.semantic.code.BranchOnZeroInstruction;
import comp6421.semantic.code.CodeGenerationContext;
import comp6421.semantic.code.JumpInstruction;
import comp6421.semantic.code.NoopInstruction;
import comp6421.semantic.code.Register;
import comp6421.semantic.value.RegisterValue;

public class IfStatement extends Expression implements Statement {

	private Expression condition;

	private StatementBlock thenBlock;
	private StatementBlock elseBlock;

	private static enum State {
		CONDITION, THEN_BLOCK, ELSE_BLOCK, DONE,
	};

	private State state;

	public IfStatement() {
		state = State.CONDITION;
	}

	@Override
	public void acceptSubElement(Expression e) throws SemanticException {
		switch (state) {
		case CONDITION:
			if (e instanceof RelationExpressionFragment) {
				condition = e;
				state = State.THEN_BLOCK;
			} else {
				super.acceptSubElement(e);
			}
			break;
		case THEN_BLOCK:
			if (e instanceof StatementBlock) {
				thenBlock = (StatementBlock) e;
				state = State.ELSE_BLOCK;
			} else {
				super.acceptSubElement(e);
			}
			break;
		case ELSE_BLOCK:
			if (e instanceof StatementBlock) {
				elseBlock = (StatementBlock) e;
				state = State.DONE;
				context.finishTopElement();
			} else {
				super.acceptSubElement(e);
			}
			break;
		case DONE:
		default:
			super.acceptSubElement(e);
			break;
		}
	}

	@Override
	public void generateCode(CodeGenerationContext c) throws SemanticException {
		RegisterValue conditionValue = condition.getValue().getRegisterValue(c);
		Register r = conditionValue.getRegister();
		int labelId = c.getId();

		String elseLabel = "else_" + labelId;
		String endifLabel = "endif_" + labelId;

		c.appendInstruction(new BranchOnZeroInstruction(r, elseLabel));

		thenBlock.generateCode(c);

		c.appendInstruction(new JumpInstruction(endifLabel));
		c.setLabel(elseLabel);

		elseBlock.generateCode(c);

		c.appendInstruction(new NoopInstruction().setLabel(endifLabel));

	}

	@Override
	public String pseudoCode() {
		return "if statement";
	}
}

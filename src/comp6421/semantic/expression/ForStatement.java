package comp6421.semantic.expression;

import comp6421.semantic.SemanticException;
import comp6421.semantic.code.BranchOnZeroInstruction;
import comp6421.semantic.code.CodeGenerationContext;
import comp6421.semantic.code.JumpInstruction;
import comp6421.semantic.code.NoopInstruction;
import comp6421.semantic.code.Register;
import comp6421.semantic.value.Value;

public class ForStatement extends ExpressionElement implements Statement {

	private AssignmentExpression initializer;
	private ExpressionElement condition;
	private AssignmentExpression increment;
	
	private StatementBlock statements;
	
	private static enum State {
		INITIALIZER,
		CONDITION,
		INCREMENT,
		STATEMENTS,
		DONE
	};
	
	private State state;
	
	public ForStatement() {
		state = State.INITIALIZER;
	}
	
	@Override
	public void acceptSubElement(ExpressionElement e) throws SemanticException {
		
		try{
		switch(state){
		case INITIALIZER:
			if(e instanceof AssignmentExpression){
				state = State.CONDITION;
				initializer = (AssignmentExpression) e;
			}else{
				super.acceptSubElement(e);
			}
			break;
		case CONDITION:
			if(e instanceof RelationExpressionFragment){
				state = State.INCREMENT;
				condition = e;
			}else{
				super.acceptSubElement(e);
			}
			break;
		case INCREMENT:
			if(e instanceof AssignmentExpression){
				state = State.STATEMENTS;
				increment = (AssignmentExpression) e;
			}else{
				super.acceptSubElement(e);
			}
			break;
		case STATEMENTS:
			if(e instanceof StatementBlock){
				state = State.DONE;
				statements = (StatementBlock) e;
				context.finishTopElement();
			}else{
				super.acceptSubElement(e);
			}
			break;
		default:
			super.acceptSubElement(e);
			break;
		}
		}catch(SemanticException x){
			System.err.println(x);
		}
	}
	
	@Override
	public void generateCode(CodeGenerationContext c) throws SemanticException {

		int labelId = c.getUniqueLabelId();
		String loopTopLabel = "loop_top_" + labelId;
		String loopEndLabel = "loop_end_" + labelId;
		
		c.commentNext("for loop initializer");
		initializer.generateCode(c);
		
		c.labelNext(loopTopLabel);
		
		c.commentNext("evaluate condition");
		Register r = condition.getValue().getRegisterValue(c).getRegister();
		c.appendInstruction(new BranchOnZeroInstruction(r, loopEndLabel)
				.setComment("break out of loop"));
		if(!r.reserved) c.freeTemporaryRegister(r);
		
		statements.generateCode(c);
		
		c.commentNext("for loop incrementation");
		increment.generateCode(c);
		
		c.appendInstruction(new JumpInstruction(loopTopLabel));
		c.appendInstruction(new NoopInstruction().setLabel(loopEndLabel));
	}

	@Override
	public String pseudoCode() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Value getValue() throws SemanticException {
		// TODO Auto-generated method stub
		return null;
	}

}

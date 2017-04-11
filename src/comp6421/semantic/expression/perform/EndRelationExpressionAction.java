package comp6421.semantic.expression.perform;

import comp6421.scanner.Token;
import comp6421.semantic.SemanticException;
import comp6421.semantic.expression.ExpressionAction;
import comp6421.semantic.expression.ExpressionElement;
import comp6421.semantic.expression.RelationExpressionFragment;

public class EndRelationExpressionAction extends ExpressionAction {

	@Override
	public void execute(Token precedingToken) throws SemanticException {
		ExpressionElement top = context.getCurrent();
		if(top instanceof RelationExpressionFragment){
			context.finishTopElement();
		}else{
			// TODO - this throws a lot ...
			// after the 'condition' part of the for loop
			// this is not really an error .... throw new InternalCompilerError("Expected " + RelationExpressionFragment.class.getName() + " but was " + top.getClass().getName());
		}
	}

}

package comp6421.semantic.migration.strategy;

import comp6421.scanner.Token;
import comp6421.semantic.SemanticException;
import comp6421.semantic.migration.ExpressionElement;
import comp6421.semantic.migration.MigrationStrategy;
import comp6421.semantic.migration.RelationExpressionFragment;

public class EndRelationExpressionAction extends MigrationStrategy {

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

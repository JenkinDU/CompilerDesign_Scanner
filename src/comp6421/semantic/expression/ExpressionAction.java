package comp6421.semantic.expression;

import comp6421.semantic.perform.SemanticAction;

public abstract class ExpressionAction implements SemanticAction {

	protected ExpressionContext context = ExpressionContext.instance;
	
}

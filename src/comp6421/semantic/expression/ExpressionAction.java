package comp6421.semantic.expression;

import comp6421.semantic.strategy.MigrationStrtegy;

public abstract class ExpressionAction implements MigrationStrtegy {

	protected ExpressionContext context = ExpressionContext.instance;
	
}

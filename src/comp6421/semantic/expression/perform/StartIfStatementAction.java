package comp6421.semantic.expression.perform;

import comp6421.scanner.Token;
import comp6421.semantic.CompilerError;
import comp6421.semantic.expression.ExpressionAction;
import comp6421.semantic.expression.ExpressionElement;
import comp6421.semantic.expression.IfStatement;

public class StartIfStatementAction extends ExpressionAction {

	@Override
	public void execute(Token precedingToken) throws CompilerError {		
		ExpressionElement e = new IfStatement();
		context.pushChild(e);
	}

}

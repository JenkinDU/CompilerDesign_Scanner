package comp6421.semantic.expression;

import comp6421.semantic.SemanticException;
import comp6421.semantic.code.MathOperation;
import comp6421.semantic.value.Value;

public abstract class ExpressionElement {

	protected final ExpressionContext context = ExpressionContext.instance;
	
	public void acceptSubElement(ExpressionElement e) throws SemanticException {
	}

	public void pushIdentifier(String id) throws SemanticException {
	}
	
	public abstract Value getValue() throws SemanticException;

	public void pushIntLiteral(int i) throws SemanticException {
	}

	public void pushFloatLiteral(float f) throws SemanticException {
		pushFloatLiteral(f);
	}

	public void pushAdditionOperator(MathOperation operator) throws SemanticException {
	}

	public void pushMultiplicationOperator(MathOperation operator) throws SemanticException {
	}

	public void pushRelationOperator(MathOperation operator) throws SemanticException {
	}
	
}

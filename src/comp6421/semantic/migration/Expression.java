package comp6421.semantic.migration;

import comp6421.semantic.SemanticException;
import comp6421.semantic.code.MathOperation;
import comp6421.semantic.value.Value;

public abstract class Expression {

	protected final MigrationContext context = MigrationContext.instance;

	public void acceptSubElement(Expression e) throws SemanticException {
	}

	public void pushIdentifier(String id) throws SemanticException {
	}

	public Value getValue() throws SemanticException {
		return null;
	}

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

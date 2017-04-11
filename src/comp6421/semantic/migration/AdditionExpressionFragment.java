package comp6421.semantic.migration;

import comp6421.semantic.IType;
import comp6421.semantic.SemanticException;
import comp6421.semantic.code.MathOperation;
import comp6421.semantic.value.OperatorValue;
import comp6421.semantic.value.Value;

public class AdditionExpressionFragment extends TypedExpressionElement {

	private static enum State {
		WAITING_FOR_FIRST, WAITING_FOR_OP, WAITING_FOR_SECOND, DONE
	};

	private State state;
	private TypedExpressionElement first;
	private TypedExpressionElement second;
	private MathOperation operator;

	public AdditionExpressionFragment() {
		state = State.WAITING_FOR_FIRST;
	}

	@Override
	public void pushAdditionOperator(MathOperation operator) throws SemanticException {
		if (this.state == State.WAITING_FOR_OP) {
			this.operator = operator;
			this.state = State.WAITING_FOR_SECOND;
		}
	}

	@Override
	public void acceptSubElement(Expression e) throws SemanticException {

		if (e instanceof MultiplicationExpressionFragment || e instanceof AdditionExpressionFragment) {
			switch (state) {
			case WAITING_FOR_FIRST:
				first = (TypedExpressionElement) e;
				state = State.WAITING_FOR_OP;
				break;
			case WAITING_FOR_SECOND:
				second = (TypedExpressionElement) e;

				IType firstType = first.getType();
				IType secondType = second.getType();

				if (!firstType.equals(secondType)) {
					throw new SemanticException("Type mismatch: " + firstType + " is not compatible with " + secondType
							+ " for operator '" + operator.symbol + "'");
				}

				state = State.DONE;
				context.finishTopElement();
				break;
			default:
				super.acceptSubElement(e);
				break;
			}
		} else {
			super.acceptSubElement(e);
		}

	}

	@Override
	public Value getValue() throws SemanticException {
		if (state == State.WAITING_FOR_OP) {
			return first.getValue();
		} else {
			return new OperatorValue(operator, first.getValue(), second.getValue());
		}
	}

	@Override
	public IType getType() {
		return first.getType();
	}

	public TypedExpressionElement getFirst() {
		return first;
	};

	public TypedExpressionElement getSecond() {
		return second;
	};

	public MathOperation getOperation() {
		return operator;
	};
}

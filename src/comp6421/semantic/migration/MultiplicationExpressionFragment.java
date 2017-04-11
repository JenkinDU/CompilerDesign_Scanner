package comp6421.semantic.migration;

import comp6421.semantic.SemanticException;
import comp6421.semantic.code.MathOperation;
import comp6421.semantic.entry.EntryType;
import comp6421.semantic.entry.NumberType;
import comp6421.semantic.value.OperatorValue;
import comp6421.semantic.value.NumberValue;
import comp6421.semantic.value.Value;

public class MultiplicationExpressionFragment extends TypedExpressionElement {

	private static enum State {
		INIT_FIRST, FIRST, WAITING_FOR_OP, INIT_SECOND, SECOND, DONE
	};

	private State state;
	private TypedExpressionElement first;
	private TypedExpressionElement second;
	private MathOperation operator;

	public MultiplicationExpressionFragment() {
		state = State.INIT_FIRST;
	}

	@Override
	public void acceptSubElement(ExpressionElement e) throws SemanticException {

		if (e instanceof MultiplicationExpressionFragment || e instanceof VariableExpressionFragment
				|| e instanceof AdditionExpressionFragment || e instanceof FunctionCallExpressionFragment) {

			switch (state) {
			case INIT_FIRST:
			case FIRST:
				state = State.WAITING_FOR_OP;
				first = (TypedExpressionElement) e;
				break;
			case INIT_SECOND:
			case SECOND:
				second = (TypedExpressionElement) e;

				EntryType firstType = first.getType();
				EntryType secondType = second.getType();

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
	public void pushIdentifier(String id) throws SemanticException {
		switch (state) {
		case INIT_FIRST:
			state = State.FIRST;
			context.pushChild(new VariableExpressionFragment(id));
			break;
		case INIT_SECOND:
			state = State.SECOND;
			context.pushChild(new VariableExpressionFragment(id));
			break;
		default:
			super.pushIdentifier(id);
			break;
		}
	}

	@Override
	public void pushIntLiteral(int i) throws SemanticException {
		switch (state) {
		case INIT_FIRST:
			state = State.WAITING_FOR_OP;
			first = new IntLiteralExpressionElement(i);
			break;
		case INIT_SECOND:
			state = State.DONE;
			second = new IntLiteralExpressionElement(i);
			context.finishTopElement();
			break;
		default:
			super.pushIntLiteral(i);
			break;
		}

	}

	@Override
	public void pushFloatLiteral(float i) throws SemanticException {
		switch (state) {
		case INIT_FIRST:
			state = State.WAITING_FOR_OP;
			first = new FloatLiteralExpressionElement(i);
			break;
		case INIT_SECOND:
			state = State.DONE;
			second = new FloatLiteralExpressionElement(i);
			context.finishTopElement();
			break;
		default:
			super.pushFloatLiteral(i);
			break;
		}

	}

	@Override
	public void pushMultiplicationOperator(MathOperation operator) throws SemanticException {
		if (state == State.WAITING_FOR_OP) {
			state = State.INIT_SECOND;
			this.operator = operator;
		} else {
			super.pushMultiplicationOperator(operator);
		}
	}

	@Override
	public Value getValue() throws SemanticException {
		try {
			if (state == State.WAITING_FOR_OP) {
				return first.getValue();
			} else {
				return new OperatorValue(operator, first.getValue(), second.getValue());
			}
		} catch (Throwable e) {
			return new NumberValue(0);
		}
	}

	@Override
	public EntryType getType() {
		try {
			return first.getType();
		} catch (Throwable e) {
			return new NumberType("int");
		}
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

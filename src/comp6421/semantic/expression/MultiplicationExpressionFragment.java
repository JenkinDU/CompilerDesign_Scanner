package comp6421.semantic.expression;

import comp6421.semantic.CompilerError;
import comp6421.semantic.code.MathOperation;
import comp6421.semantic.entry.PrimitiveType;
import comp6421.semantic.entry.SymbolTableEntryType;
import comp6421.semantic.value.MathValue;
import comp6421.semantic.value.StaticIntValue;
import comp6421.semantic.value.Value;

public class MultiplicationExpressionFragment extends TypedExpressionElement {

	private static enum State {
		INIT_FIRST,
		FIRST,
		WAITING_FOR_OP,
		INIT_SECOND,
		SECOND,
		DONE
	};
	
	private State state;
	private TypedExpressionElement first;
	private TypedExpressionElement second;
	private MathOperation operator;
	
	public MultiplicationExpressionFragment() {
		state = State.INIT_FIRST;
	}
	
	@Override
	public void acceptSubElement(ExpressionElement e) throws CompilerError {
		
		if(e instanceof MultiplicationExpressionFragment
		|| e instanceof VariableExpressionFragment
		|| e instanceof AdditionExpressionFragment
		|| e instanceof FunctionCallExpressionFragment){
			
			switch(state){
			case INIT_FIRST:
			case FIRST:
				state = State.WAITING_FOR_OP;
				first = (TypedExpressionElement) e;
				break;
			case INIT_SECOND:
			case SECOND:
				second = (TypedExpressionElement) e;

				SymbolTableEntryType firstType  = first.getType();
				SymbolTableEntryType secondType = second.getType();
				
				if( ! firstType.equals(secondType) ){
					throw new CompilerError("Type mismatch: " + firstType + " is not compatible with " + secondType + " for operator '" + operator.symbol + "'");	
				}

				state = State.DONE;
				context.finishTopElement();
				break;
			default:
				super.acceptSubElement(e);
				break;
			
			}	
		}else{
			super.acceptSubElement(e);
		}	
	}
	
	@Override
	public void pushIdentifier(String id) throws CompilerError {
		switch(state){
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
	public void pushIntLiteral(int i) throws CompilerError {
		switch(state){
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
	public void pushFloatLiteral(float i) throws CompilerError {
		switch(state){
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
	public void pushMultiplicationOperator(MathOperation operator) throws CompilerError {
		if(state == State.WAITING_FOR_OP){
			state = State.INIT_SECOND;
			this.operator = operator;
		}else{
			super.pushMultiplicationOperator(operator);
		}
	}
	
	@Override
	public Value getValue() throws CompilerError {
		try{
			if(state == State.WAITING_FOR_OP){
				return first.getValue();
			}else{
				return new MathValue(operator, first.getValue(), second.getValue());
			}
		}catch(Throwable e){
			return new StaticIntValue(0);
		}
	}

	@Override
	public SymbolTableEntryType getType() {
		try{
			return first.getType();
		}catch(Throwable e){
			return new PrimitiveType("int");
		}
	}

}

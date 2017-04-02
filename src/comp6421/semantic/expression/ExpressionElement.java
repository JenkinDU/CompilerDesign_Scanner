package comp6421.semantic.expression;

import comp6421.semantic.CompilerError;
import comp6421.semantic.code.MathOperation;
import comp6421.semantic.value.Value;

public abstract class ExpressionElement {

	protected final ExpressionContext context = ExpressionContext.instance;
	
	public void acceptSubElement(ExpressionElement e) throws CompilerError {
		// throw TODO unsilence if debuggingnew InternalCompilerError("Unexpected " + e.getClass().getSimpleName() + " inside " + this.getClass().getSimpleName());
	}

	public void pushIdentifier(String id) throws CompilerError {
		// throw TODO unsilence if debuggingnew InternalCompilerError("Unexpected identifier " + id + " inside " + this.getClass().getSimpleName());
	}
	
	public abstract Value getValue() throws CompilerError;

	public void pushIntLiteral(int i) throws CompilerError {
		// throw TODO unsilence if debuggingnew InternalCompilerError("Unexpected int literal " + i + " inside " + this.getClass().getSimpleName());		
	}

	public void pushFloatLiteral(float f) throws CompilerError {
		pushIntLiteral((int)f); // for now we just cast them
		//// throw TODO unsilence if debuggingnew InternalCompilerError("Unexpected float literal " + f + " inside " + this.getClass().getSimpleName());		
	}

	public void pushAdditionOperator(MathOperation operator) throws CompilerError {
		// throw TODO unsilence if debuggingnew InternalCompilerError("Unexpected addition operation " + operator + " inside " + this.getClass().getSimpleName());				
	}

	public void pushMultiplicationOperator(MathOperation operator) throws CompilerError {
		// throw TODO unsilence if debuggingnew InternalCompilerError("Unexpected multiplication operation " + operator + " inside " + this.getClass().getSimpleName());				
	}

	public void pushRelationOperator(MathOperation operator) throws CompilerError {
		// throw TODO unsilence if debuggingnew InternalCompilerError("Unexpected relation operation " + operator + " inside " + this.getClass().getSimpleName());				
	}
	
}

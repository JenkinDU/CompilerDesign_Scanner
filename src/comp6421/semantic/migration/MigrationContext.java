package comp6421.semantic.migration;

import java.util.Iterator;
import java.util.Stack;

import comp6421.semantic.FunctionEntry;
import comp6421.semantic.SemanticException;

public class MigrationContext {

	public static MigrationContext instance = new MigrationContext();

	private final Stack<ExpressionElement> expressionStack;

	private FunctionEntry currentFunction;

	private MigrationContext() {
		expressionStack = new Stack<ExpressionElement>();
	}

	public void pushChild(ExpressionElement child) {
		expressionStack.push(child);
	}

	// TODO better name
	public void finishTopElement() throws SemanticException {
		ExpressionElement child = null;

		if (!expressionStack.isEmpty()) {
			child = expressionStack.pop();
		}

		if (expressionStack.isEmpty()) {
			if (child instanceof Statement && currentFunction != null) {
				currentFunction.appendStatement((Statement) child);
			} else {
				// TODO - this is not an error, more a warning throw new
				// InternalCompilerError("Emptied stack with non-" +
				// Statement.class.getSimpleName() + " element: " + child);
			}
		} else {
			expressionStack.peek().acceptSubElement(child);
		}

	}

	public ExpressionElement getCurrent() throws SemanticException {
		if (expressionStack.isEmpty()) {
			return null;
		} else {
			return expressionStack.peek();
		}
	}

	public void popChild() {
		expressionStack.pop();
	}

	public static void setCurrentFunction(FunctionEntry storedFunction) {
		instance.currentFunction = storedFunction;
	}

	public String getStackString() {
		String stack = "";
		Iterator<ExpressionElement> i = expressionStack.iterator();
		while (i.hasNext()) {
			ExpressionElement e = i.next();
			stack += e.getClass().getSimpleName().substring(0, 6) + "/";
			// if(e instanceof AdditionExpressionFragment) {
			// stack += e.getClass().getSimpleName().substring(0, 6)
			//// +"("+((AdditionExpressionFragment)e).getFirst()==null?"null":((AdditionExpressionFragment)e).toString()//.getFirst().toString()
			//// +((AdditionExpressionFragment)e).getOperation()==null?"null":((AdditionExpressionFragment)e).toString()//.getOperation().toString()
			//// +((AdditionExpressionFragment)e).getSecond()==null?"null":((AdditionExpressionFragment)e).toString()//.getSecond().toString()
			//// +")"
			// +"/";
			//
			// } else if(e instanceof MultiplicationExpressionFragment) {
			// stack += e.getClass().getSimpleName().substring(0, 6)
			//// +"("+((MultiplicationExpressionFragment)e).getFirst()==null?"null":((MultiplicationExpressionFragment)e).toString()//.getFirst().toString()
			//// +((MultiplicationExpressionFragment)e).getOperation()==null?"null":((MultiplicationExpressionFragment)e).toString()//.getOperation().toString()
			//// +((MultiplicationExpressionFragment)e).getSecond()==null?"null":((MultiplicationExpressionFragment)e).toString()//.getSecond().toString()
			//// +")"
			// +"/";
			//// stack += e.getClass().getSimpleName().substring(0, 6)+"/";
			// }
		}
		return stack;
	}
}

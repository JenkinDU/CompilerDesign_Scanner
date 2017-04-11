package comp6421.semantic.migration;

import java.util.List;

import comp6421.semantic.STable;
import comp6421.semantic.SemanticException;
import comp6421.semantic.TableContext;
import comp6421.semantic.code.MathOperation;
import comp6421.semantic.code.Register;
import comp6421.semantic.entry.ClassType;
import comp6421.semantic.entry.EntryType;
import comp6421.semantic.entry.MemberFunctionEntry;
import comp6421.semantic.entry.ParameterEntry;
import comp6421.semantic.entry.WordType;
import comp6421.semantic.entry.STEntry;
import comp6421.semantic.entry.VariableEntry;
import comp6421.semantic.value.DynamicValue;
import comp6421.semantic.value.FunctionCallValue;
import comp6421.semantic.value.IndirectValue;
import comp6421.semantic.value.FunctionOffsetValue;
import comp6421.semantic.value.MathValue;
import comp6421.semantic.value.NumberValue;
import comp6421.semantic.value.RegisterValue;
import comp6421.semantic.value.WordValue;
import comp6421.semantic.value.Value;

public class VariableExpressionFragment extends TypedExpressionElement {

	private final STable enclosingScope;
	private STable currentScope;

	private Value offset;
	private Value baseAddr;

	private Value memberFunctionCallValue;

	private EntryType currentType;

	private boolean isReference;
	private boolean functionCall;

	public VariableExpressionFragment(String id) throws SemanticException {
		this(id, TableContext.getCurrentScope());
	}

	public VariableExpressionFragment(String id, STable scope) throws SemanticException {
		this.currentScope = scope;
		this.enclosingScope = currentScope;
		this.currentType = null;
		this.functionCall = false;

		final STEntry e = getEntry(id);

		if (enclosingScope.getEnclosingEntry() instanceof MemberFunctionEntry) {
			STable outerScope = enclosingScope.getParent();
			if (outerScope.exists(id) && !enclosingScope.exists(id)) {
				init(getEntry(Register.THIS_POINTER_NAME));
				pushIdentifier(id);
			} else {
				init(e);
			}
		} else {
			init(e);
		}

	}

	private void init(final STEntry e) throws SemanticException {
		Value offsetValue = new FunctionOffsetValue() {
			@Override
			public Value get() throws SemanticException {
				return new MathValue(MathOperation.SUBTRACT, new NumberValue(e.getOffset()),
						new NumberValue(enclosingScope.getSize()));
			}
		};

		if (e instanceof VariableEntry || e.getType() instanceof WordType) {
			isReference = false;
			baseAddr = new RegisterValue(Register.STACK_POINTER);
			offset = offsetValue;
		} else if (e instanceof ParameterEntry) {
			isReference = true;
			baseAddr = new WordValue(new RegisterValue(Register.STACK_POINTER), offsetValue);
			offset = new NumberValue(0);
		}

		currentType = e.getType();
		currentScope = currentType.getScope();
	}

	private STEntry getEntry(String id) throws SemanticException {
		if (currentScope == null) {
			throw new SemanticException("Cannot access property " + id + " in " + currentType);
		}

		STEntry e = currentScope.find(id);

		if (e == null) {
			throw new SemanticException(
					"Id " + id + " not found in scope: " + currentScope.getEnclosingEntry().getName());
		}

		return e;
	}

	@Override
	public void pushIdentifier(String id) throws SemanticException {

		STEntry e = getEntry(id);

		offset = new MathValue(MathOperation.ADD, offset, new NumberValue(e.getOffset()));
		currentType = e.getType();
		currentScope = currentType.getScope();

		isReference = false;
	}

	@Override
	public void acceptSubElement(ExpressionElement e) throws SemanticException {
		if (e instanceof FunctionCallExpressionFragment) {
			FunctionCallExpressionFragment f = (FunctionCallExpressionFragment) e;
			if (currentType instanceof ClassType) {
				ClassType currentClass = (ClassType) currentType;

				if (currentScope.exists(f.getId())) {
					STEntry entry = currentScope.find(f.getId());
					if (entry instanceof MemberFunctionEntry) {
						MemberFunctionEntry function = (MemberFunctionEntry) entry;
						List<TypedExpressionElement> expressions = f.getExpressions();
						
						expressions.add(0, this);
						memberFunctionCallValue = new FunctionCallValue(function, expressions);
						functionCall = true;
						currentType = function.getType();
						context.finishTopElement();
					}
				} else {
					throw new SemanticException("Cannot find method " + f.getId() + " in class: " + currentClass);
				}
			}
		} else {
			super.acceptSubElement(e);
		}
	}

	@Override
	public Value getValue() {

		if (getType() instanceof WordType) {
			return new WordValue(baseAddr, offset);
		}
		if (functionCall) {
			return memberFunctionCallValue;
		} else {
			if (isReference) {
				return baseAddr;
			} else {
				return new IndirectValue(new MathValue(MathOperation.ADD, baseAddr, offset));
			}
		}

	}

	@Override
	public EntryType getType() {
		return currentType;
	}

}

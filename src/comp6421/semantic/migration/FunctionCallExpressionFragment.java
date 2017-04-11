package comp6421.semantic.migration;

import java.util.ArrayList;
import java.util.List;

import comp6421.semantic.FunctionEntry;
import comp6421.semantic.STable;
import comp6421.semantic.SemanticException;
import comp6421.semantic.TableContext;
import comp6421.semantic.code.Register;
import comp6421.semantic.entry.EntryType;
import comp6421.semantic.entry.FunctionType;
import comp6421.semantic.entry.LateBindingType;
import comp6421.semantic.entry.MemberFunctionEntry;
import comp6421.semantic.entry.STEntry;
import comp6421.semantic.value.DynamicValue;
import comp6421.semantic.value.FunctionCallValue;
import comp6421.semantic.value.FunctionOffsetValue;
import comp6421.semantic.value.Value;

public class FunctionCallExpressionFragment extends TypedExpressionElement {

	private String id;

	private List<TypedExpressionElement> expressions;

	private STable surroundingScope;

	public FunctionCallExpressionFragment(String id) {
		this.id = id;
		this.expressions = new ArrayList<TypedExpressionElement>();
		this.surroundingScope = TableContext.getCurrentScope();
	}

	@Override
	public void acceptSubElement(ExpressionElement e) throws SemanticException {

		if (e instanceof RelationExpressionFragment) {
			expressions.add((RelationExpressionFragment) e);
		} else {
			super.acceptSubElement(e);
		}
	}

	@Override
	public Value getValue() throws SemanticException {
		return null;
	}

	@Override
	public EntryType getType() {
		return new LateBindingType() {
			@Override
			public EntryType get() throws SemanticException {
				STEntry entry = surroundingScope.find(id);

				if (entry instanceof FunctionEntry) {
					return ((FunctionType) ((FunctionEntry) entry).getType()).getReturnType();
				} else {
					throw new SemanticException("Could not find function " + id);
				}

			}
		};
	}

	public String getId() {
		return id;
	}

	public List<TypedExpressionElement> getExpressions() {
		return expressions;
	}

}

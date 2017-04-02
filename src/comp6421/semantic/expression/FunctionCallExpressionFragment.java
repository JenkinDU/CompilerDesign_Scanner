package comp6421.semantic.expression;

import java.util.ArrayList;
import java.util.List;

import comp6421.semantic.CompilerError;
import comp6421.semantic.FunctionEntry;
import comp6421.semantic.SymbolContext;
import comp6421.semantic.SymbolTable;
import comp6421.semantic.code.SpecialValues;
import comp6421.semantic.entry.FunctionType;
import comp6421.semantic.entry.LateBindingType;
import comp6421.semantic.entry.MemberFunctionEntry;
import comp6421.semantic.entry.SymbolTableEntry;
import comp6421.semantic.entry.SymbolTableEntryType;
import comp6421.semantic.value.DynamicValue;
import comp6421.semantic.value.FunctionCallValue;
import comp6421.semantic.value.LateBindingDynamicValue;
import comp6421.semantic.value.Value;

public class FunctionCallExpressionFragment extends TypedExpressionElement {

	private String id;
	
	private List<TypedExpressionElement> expressions;
	
	private SymbolTable surroundingScope;
	
	public FunctionCallExpressionFragment(String id){
		this.id = id;
		this.expressions = new ArrayList<TypedExpressionElement>();
		this.surroundingScope = SymbolContext.getCurrentScope();
	}
	
	@Override
	public void acceptSubElement(ExpressionElement e) throws CompilerError {
		
		if(e instanceof RelationExpressionFragment){
			expressions.add((RelationExpressionFragment) e);
		}else{
			super.acceptSubElement(e);			
		}		
	}
	
	@Override
	public Value getValue() throws CompilerError {
		return new LateBindingDynamicValue() {
			
			@Override
			public DynamicValue get() throws CompilerError {
				SymbolTableEntry entry = surroundingScope.find(id);
				SymbolTable outerScope = surroundingScope.getParent();
				if(entry instanceof MemberFunctionEntry){
					if(outerScope.exists(id)){
						expressions.add(0, new VariableExpressionFragment(SpecialValues.THIS_POINTER_NAME, surroundingScope));
						return new FunctionCallValue((FunctionEntry) entry, expressions);
					}else{
						throw new CompilerError("Can not find member function " + id);
					}
				}if(entry instanceof FunctionEntry){
					return new FunctionCallValue((FunctionEntry) entry, expressions);
				}else{
					throw new CompilerError("Could not find function " + id);
				}
			}
		};
	}

	@Override
	public SymbolTableEntryType getType() {
		return new LateBindingType(){
			@Override
			public SymbolTableEntryType get() throws CompilerError {
				SymbolTableEntry entry = surroundingScope.find(id);
				
				if(entry instanceof FunctionEntry){
					return ((FunctionType)((FunctionEntry) entry).getType()).getReturnType();
				}else{
					throw new CompilerError("Could not find function " + id);
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

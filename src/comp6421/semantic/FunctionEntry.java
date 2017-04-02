package comp6421.semantic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import comp6421.semantic.entry.FunctionType;
import comp6421.semantic.entry.ParameterEntry;
import comp6421.semantic.entry.SymbolTableEntry;
import comp6421.semantic.entry.SymbolTableEntryType;
import comp6421.semantic.entry.VariableEntry;
import comp6421.semantic.expression.Statement;

public class FunctionEntry extends SymbolTableEntry {
	
	private List<Statement> statements; 
	
	private final String label;
	
	public FunctionEntry(String name, SymbolTableEntryType returnType, SymbolTable table) {
		super(name, Kind.Function, new FunctionType(returnType, new ArrayList<SymbolTableEntryType>()), table);
		statements = new ArrayList<Statement>();
		this.label = name + Integer.toString(hashCode());
	}

	public void addParameter(ParameterEntry param) throws CompilerError {
		FunctionType type = (FunctionType)getType();
		type.pushParameter(param.getType());
		
		getScope().add(param);
	}

	@Override
	protected int calculateSize() throws CompilerError {
		int size = 0;
		for(SymbolTableEntry e : getScope().getEntries()){
			if(e instanceof ParameterEntry || e instanceof VariableEntry){
				size += e.getSize();
			}
		}
		return size;
	}
	
	public void appendStatement(Statement s){
		statements.add(s);
	}

	public List<Statement> getStatements() {
		return Collections.unmodifiableList(statements);
	}

	public String getLabel() {
		return label;
	}
}

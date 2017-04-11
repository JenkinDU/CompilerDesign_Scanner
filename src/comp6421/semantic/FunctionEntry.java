package comp6421.semantic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import comp6421.semantic.entry.ParameterEntry;
import comp6421.semantic.entry.VariableEntry;
import comp6421.semantic.migration.Statement;

public class FunctionEntry extends STEntry {

	private List<Statement> statements;

	private final String label;

	public FunctionEntry(String name, IType returnType, STable table) {
		super(name, Kind.Function, new FunctionType(returnType, new ArrayList<IType>()), table);
		statements = new ArrayList<Statement>();
		this.label = name + Integer.toString(hashCode());
	}

	public void addParameter(ParameterEntry param) throws SemanticException {
		FunctionType type = (FunctionType) getType();
		type.pushParameter(param.getType());

		getScope().add(param);
	}

	@Override
	protected int getEntrySize() {
		int size = 0;
		for (STEntry e : getScope().getEntries()) {
			if (e instanceof ParameterEntry || e instanceof VariableEntry) {
				size += e.getSize();
			}
		}
		return size;
	}

	public void appendStatement(Statement s) {
		statements.add(s);
	}

	public List<Statement> getStatements() {
		return Collections.unmodifiableList(statements);
	}

	public String getLabel() {
		return label;
	}
}

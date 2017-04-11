package comp6421.semantic.migration;

import java.util.ArrayList;
import java.util.List;

import comp6421.semantic.SemanticException;
import comp6421.semantic.code.CodeGenerationContext;

public class StatementBlock extends Expression implements Statement {

	private List<Statement> statements;

	public StatementBlock() {
		statements = new ArrayList<Statement>();
	}

	@Override
	public void acceptSubElement(Expression e) throws SemanticException {
		if (e instanceof Statement) {
			statements.add((Statement) e);
		} else {
			super.acceptSubElement(e);
		}
	}

	@Override
	public void generateCode(CodeGenerationContext c) throws SemanticException {
		for (Statement s : statements) {
			c.setComment(s.getClass().getSimpleName());
			s.generateCode(c);
		}
	}

	@Override
	public String pseudoCode() {
		StringBuilder sb = new StringBuilder();
		for (Statement s : statements) {
			sb.append(s.pseudoCode());
		}
		return sb.toString();
	}
}

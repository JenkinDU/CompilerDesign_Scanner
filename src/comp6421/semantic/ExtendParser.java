package comp6421.semantic;

import comp6421.parser.Parser;
import comp6421.scanner.Token;
import comp6421.semantic.expression.perform.EndAdditionExpressionAction;
import comp6421.semantic.expression.perform.EndBlockAction;
import comp6421.semantic.expression.perform.EndFunctionCallAction;
import comp6421.semantic.expression.perform.EndMultiplicationExpressionAction;
import comp6421.semantic.expression.perform.EndRelationExpressionAction;
import comp6421.semantic.expression.perform.FinishVariableAction;
import comp6421.semantic.expression.perform.PushAdditionOperationAction;
import comp6421.semantic.expression.perform.PushFloatLiteralAction;
import comp6421.semantic.expression.perform.PushIntLiteralAction;
import comp6421.semantic.expression.perform.PushMultiplicationOperationAction;
import comp6421.semantic.expression.perform.PushRelationOperationAction;
import comp6421.semantic.expression.perform.PushVariableNameAction;
import comp6421.semantic.expression.perform.StartAdditionExpressionAction;
import comp6421.semantic.expression.perform.StartAssignmentStatementAction;
import comp6421.semantic.expression.perform.StartBlockAction;
import comp6421.semantic.expression.perform.StartForStatementAction;
import comp6421.semantic.expression.perform.StartFunctionCallAction;
import comp6421.semantic.expression.perform.StartGetStatementAction;
import comp6421.semantic.expression.perform.StartIfStatementAction;
import comp6421.semantic.expression.perform.StartMultiplicationExpressionAction;
import comp6421.semantic.expression.perform.StartPutStatementAction;
import comp6421.semantic.expression.perform.StartRelationExpressionAction;
import comp6421.semantic.expression.perform.StartReturnStatementAction;
import comp6421.semantic.perform.AddFunctionParameterAction;
import comp6421.semantic.perform.CreateClassAction;
import comp6421.semantic.perform.CreateFunctionAction;
import comp6421.semantic.perform.CreateProgramAction;
import comp6421.semantic.perform.CreateVariableAction;
import comp6421.semantic.perform.EndScopeAction;
import comp6421.semantic.perform.SemanticAction;
import comp6421.semantic.perform.StartFunctionAction;
import comp6421.semantic.perform.StartMemberFunctionAction;
import comp6421.semantic.perform.StoreDimensionAction;
import comp6421.semantic.perform.StoreIdAction;
import comp6421.semantic.perform.StoreTypeAction;

public class ExtendParser extends Parser {

	interface STCallback {
		void createTable(SemanticAction action, Token t);

		void createEntry(SemanticAction action, Token t);

		void lookUpVariable(String name, String path);
	}

	private STCallback callback;

	public ExtendParser(boolean show, STCallback cb) {
		super(show);
		this.callback = cb;
	}

	@Override
	protected void createSymbolTable(String action, Token p, Token c) {
		if ("sym_CreateProgram".equals(action)) {
			this.callback.createTable(new CreateProgramAction(), p);
		} else if ("sym_CreateClassScope".equals(action)) {
			this.callback.createTable(new CreateClassAction(), p);
		} else if ("sym_StartFunction".equals(action)) {
			this.callback.createTable(new StartFunctionAction(), p);
		} else if ("sym_StartMemberFunction".equals(action)) {
			this.callback.createTable(new StartMemberFunctionAction(), p);
		} else if ("sym_AddFunctionParameter".equals(action)) {
			this.callback.createEntry(new AddFunctionParameterAction(), p);
		} else if ("sym_CreateFunction".equals(action)) {
			this.callback.createTable(new CreateFunctionAction(), p);
		} else if ("sym_StoreType".equals(action)) {
			this.callback.createEntry(new StoreTypeAction(), p);
		} else if ("sym_StoreId".equals(action)) {
			this.callback.createEntry(new StoreIdAction(), p);
		} else if ("sym_StoreDimension".equals(action)) {
			this.callback.createEntry(new StoreDimensionAction(), p);
		} else if ("sym_CreateVariable".equals(action)) {
			this.callback.createEntry(new CreateVariableAction(), p);
		} else if ("sym_EndScope".equals(action)) {
			this.callback.createTable(new EndScopeAction(), p);
		} else if ("sem_PushVariableName".equals(action)) {
			this.callback.createTable(new PushVariableNameAction(), p);
		} else if ("sem_FinishVariable".equals(action)) {
			this.callback.createTable(new FinishVariableAction(), p);
		} else if ("sem_StartAssignmentStatment".equals(action)) {
			this.callback.createTable(new StartAssignmentStatementAction(), p);
		} else if ("sem_StartRelationExpression".equals(action)) {
			this.callback.createTable(new StartRelationExpressionAction(), p);
		} else if ("sem_EndRelationExpression".equals(action)) {
			this.callback.createTable(new EndRelationExpressionAction(), p);
		} else if ("sem_PushRelationOperation".equals(action)) {
			this.callback.createTable(new PushRelationOperationAction(), p);
		} else if ("sem_StartAdditionExpression".equals(action)) {
			this.callback.createTable(new StartAdditionExpressionAction(), p);
		} else if ("sem_EndAdditionExpression".equals(action)) {
			this.callback.createTable(new EndAdditionExpressionAction(), p);
		} else if ("sem_StartMultiplicationExpression".equals(action)) {
			this.callback.createTable(new StartMultiplicationExpressionAction(), p);
		} else if ("sem_EndMultiplicationExpression".equals(action)) {
			this.callback.createTable(new EndMultiplicationExpressionAction(), p);
		} else if ("sem_PushIntLiteral".equals(action)) {
			this.callback.createTable(new PushIntLiteralAction(), p);
		} else if ("sem_PushFloatLiteral".equals(action)) {
			this.callback.createTable(new PushFloatLiteralAction(), p);
		} else if ("sem_PushAdditionOperation".equals(action)) {
			this.callback.createTable(new PushAdditionOperationAction(), p);
		} else if ("sem_PushMultiplicationOperation".equals(action)) {
			this.callback.createTable(new PushMultiplicationOperationAction(), p);
		} else if ("sem_StartIfStatement".equals(action)) {
			this.callback.createTable(new StartIfStatementAction(), p);
		} else if ("sem_StartForStatement".equals(action)) {
			this.callback.createTable(new StartForStatementAction(), p);
		} else if ("sem_StartBlock".equals(action)) {
			this.callback.createTable(new StartBlockAction(), p);
		} else if ("sem_EndBlock".equals(action)) {
			this.callback.createTable(new EndBlockAction(), p);
		} else if ("sem_StartFunctionCall".equals(action)) {
			this.callback.createTable(new StartFunctionCallAction(), p);
		} else if ("sem_EndFunctionCall".equals(action)) {
			this.callback.createTable(new EndFunctionCallAction(), p);
		} else if ("sem_StartReturnStatement".equals(action)) {
			this.callback.createTable(new StartReturnStatementAction(), p);
		} else if ("sem_StartGetStatement".equals(action)) {
			this.callback.createTable(new StartGetStatementAction(), p);
		} else if ("sem_StartPutStatement".equals(action)) {
			this.callback.createTable(new StartPutStatementAction(), p);
		}

	}

}

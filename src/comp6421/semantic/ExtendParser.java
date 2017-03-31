package comp6421.semantic;

import comp6421.parser.Parser;
import comp6421.scanner.Token;
import comp6421.semantic.perform.AddFunctionParameterAction;
import comp6421.semantic.perform.CreateClassAction;
import comp6421.semantic.perform.CreateFunctionAction;
import comp6421.semantic.perform.CreateProgramAction;
import comp6421.semantic.perform.CreateVariableAction;
import comp6421.semantic.perform.EndScopeAction;
import comp6421.semantic.perform.StartFunctionAction;
import comp6421.semantic.perform.StartMemberFunctionAction;
import comp6421.semantic.perform.StoreDimensionAction;
import comp6421.semantic.perform.StoreIdAction;
import comp6421.semantic.perform.StoreTypeAction;
import comp6421.semantic.perform.SymbolAction;

public class ExtendParser extends Parser {

	interface STCallback {
		void createTable(SymbolAction action, Token t);

		void createEntry(SymbolAction action, Token t);

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
		} 
		//
		// sym_CreateProgram(new CreateProgramAction()),
		// sym_CreateClassScope(new CreateClassAction()),
		// sym_StartFunction(new StartFunctionAction()),
		// sym_StartMemberFunction(new StartMemberFunctionAction()),
		// sym_AddFunctionParameter(new AddFunctionParameterAction()),
		// sym_CreateFunction(new CreateFunctionAction()),
		// sym_StoreType(new StoreTypeAction()),
		// sym_StoreId(new StoreIdAction()),
		// sym_StoreDimension(new StoreDimensionAction()),
		// sym_CreateVariable(new CreateVariableAction()),
		// sym_EndScope(new EndScopeAction()),
		
		// sem_PushVariableName(new PushVariableNameAction()),
		// sem_FinishVariable(new FinishVariableAction()),
		//
		// sem_StartAssignmentStatment(new StartAssignmentStatementAction()),
		//
		// sem_StartRelationExpression(new StartRelationExpressionAction()),
		// sem_EndRelationExpression(new EndRelationExpressionAction()),
		// sem_PushRelationOperation(new PushRelationOperationAction()),
		//
		// sem_StartAdditionExpression(new StartAdditionExpressionAction()),
		// sem_EndAdditionExpression(new EndAdditionExpressionAction()),
		//
		// sem_StartMultiplicationExpression(new
		// StartMultiplicationExpressionAction()),
		// sem_EndMultiplicationExpression(new
		// EndMultiplicationExpressionAction()),
		//
		// sem_PushIntLiteral(new PushIntLiteralAction()),
		// sem_PushFloatLiteral(new PushFloatLiteralAction()),
		//
		// sem_PushAdditionOperation(new PushAdditionOperationAction()),
		// sem_PushMultiplicationOperation(new
		// PushMultiplicationOperationAction()),
		//
		// sem_StartIfStatement(new StartIfStatementAction()),
		//
		// sem_StartForStatement(new StartForStatementAction()),
		//
		// sem_StartBlock(new StartBlockAction()),
		// sem_EndBlock(new EndBlockAction()),
		//
		// sem_StartFunctionCall(new StartFunctionCallAction()),
		// sem_EndFunctionCall(new EndFunctionCallAction()),
		//
		// sem_StartReturnStatement(new StartReturnStatementAction()),
		//
		// sem_StartGetStatement(new StartGetStatementAction()),
		// sem_StartPutStatement(new StartPutStatementAction()),
		//
	}

}

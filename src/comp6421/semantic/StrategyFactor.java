package comp6421.semantic;

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
import comp6421.semantic.perform.TableStrategy;

/**
 *
 * This class
 * 
 * @author Zhen Du
 * @date Apr 2, 2017
 */
public class StrategyFactor {
	
	public static TableStrategy getSymbolStategy(String action) {
		if ("sym_CreateProgram".equals(action)) {
			return new CreateProgramAction();
		} else if ("sym_CreateClassScope".equals(action)) {
			return new CreateClassAction();
		} else if ("sym_StartFunction".equals(action)) {
			return new StartFunctionAction();
		} else if ("sym_StartMemberFunction".equals(action)) {
			return new StartMemberFunctionAction();
		} else if ("sym_AddFunctionParameter".equals(action)) {
			return new AddFunctionParameterAction();
		} else if ("sym_CreateFunction".equals(action)) {
			return new CreateFunctionAction();
		} else if ("sym_StoreType".equals(action)) {
			return new StoreTypeAction();
		} else if ("sym_StoreId".equals(action)) {
			return new StoreIdAction();
		} else if ("sym_StoreDimension".equals(action)) {
			return new StoreDimensionAction();
		} else if ("sym_CreateVariable".equals(action)) {
			return new CreateVariableAction();
		} else if ("sym_EndScope".equals(action)) {
			return new EndScopeAction();
		} 
		return null;
	}
	public static SemanticAction getMigrationStategy(String action) {
		if ("sem_PushVariableName".equals(action)) {
			return new PushVariableNameAction();
		} else if ("sem_FinishVariable".equals(action)) {
			return new FinishVariableAction();
		} else if ("sem_StartAssignmentStatment".equals(action)) {
			return new StartAssignmentStatementAction();
		} else if ("sem_StartRelationExpression".equals(action)) {
			return new StartRelationExpressionAction();
		} else if ("sem_EndRelationExpression".equals(action)) {
			return new EndRelationExpressionAction();
		} else if ("sem_PushRelationOperation".equals(action)) {
			return new PushRelationOperationAction();
		} else if ("sem_StartAdditionExpression".equals(action)) {
			return new StartAdditionExpressionAction();
		} else if ("sem_EndAdditionExpression".equals(action)) {
			return new EndAdditionExpressionAction();
		} else if ("sem_StartMultiplicationExpression".equals(action)) {
			return new StartMultiplicationExpressionAction();
		} else if ("sem_EndMultiplicationExpression".equals(action)) {
			return new EndMultiplicationExpressionAction();
		} else if ("sem_PushIntLiteral".equals(action)) {
			return new PushIntLiteralAction();
		} else if ("sem_PushFloatLiteral".equals(action)) {
			return new PushFloatLiteralAction();
		} else if ("sem_PushAdditionOperation".equals(action)) {
			return new PushAdditionOperationAction();
		} else if ("sem_PushMultiplicationOperation".equals(action)) {
			return new PushMultiplicationOperationAction();
		} else if ("sem_StartIfStatement".equals(action)) {
			return new StartIfStatementAction();
		} else if ("sem_StartForStatement".equals(action)) {
			return new StartForStatementAction();
		} else if ("sem_StartBlock".equals(action)) {
			return new StartBlockAction();
		} else if ("sem_EndBlock".equals(action)) {
			return new EndBlockAction();
		} else if ("sem_StartFunctionCall".equals(action)) {
			return new StartFunctionCallAction();
		} else if ("sem_EndFunctionCall".equals(action)) {
			return new EndFunctionCallAction();
		} else if ("sem_StartReturnStatement".equals(action)) {
			return new StartReturnStatementAction();
		} else if ("sem_StartGetStatement".equals(action)) {
			return new StartGetStatementAction();
		} else if ("sem_StartPutStatement".equals(action)) {
			return new StartPutStatementAction();
		}
		return null;
	}
}

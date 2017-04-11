package comp6421.semantic;

import comp6421.semantic.migration.MigrationStrategy;
import comp6421.semantic.migration.strategy.EndAdditionExpressionAction;
import comp6421.semantic.migration.strategy.EndBlockAction;
import comp6421.semantic.migration.strategy.EndFunctionCallAction;
import comp6421.semantic.migration.strategy.EndMultiplicationExpressionAction;
import comp6421.semantic.migration.strategy.EndRelationExpressionAction;
import comp6421.semantic.migration.strategy.FinishVariableAction;
import comp6421.semantic.migration.strategy.PushAdditionOperationAction;
import comp6421.semantic.migration.strategy.PushFloatLiteralAction;
import comp6421.semantic.migration.strategy.PushIntLiteralAction;
import comp6421.semantic.migration.strategy.PushMultiplicationOperationAction;
import comp6421.semantic.migration.strategy.PushRelationOperationAction;
import comp6421.semantic.migration.strategy.PushVariableNameAction;
import comp6421.semantic.migration.strategy.StartAdditionExpressionAction;
import comp6421.semantic.migration.strategy.StartAssignmentStatementAction;
import comp6421.semantic.migration.strategy.StartBlockAction;
import comp6421.semantic.migration.strategy.StartForStatementAction;
import comp6421.semantic.migration.strategy.StartFunctionCallAction;
import comp6421.semantic.migration.strategy.StartGetStatementAction;
import comp6421.semantic.migration.strategy.StartIfStatementAction;
import comp6421.semantic.migration.strategy.StartMultiplicationExpressionAction;
import comp6421.semantic.migration.strategy.StartPutStatementAction;
import comp6421.semantic.migration.strategy.StartRelationExpressionAction;
import comp6421.semantic.migration.strategy.StartReturnStatementAction;
import comp6421.semantic.strategy.AdditionalParameter;
import comp6421.semantic.strategy.ClassStrategy;
import comp6421.semantic.strategy.DimensionStrategy;
import comp6421.semantic.strategy.FinishScope;
import comp6421.semantic.strategy.FunctionStrategy;
import comp6421.semantic.strategy.MemberFunctionStrategy;
import comp6421.semantic.strategy.ProgramStrategy;
import comp6421.semantic.strategy.PushIdStrategy;
import comp6421.semantic.strategy.PushTypeStrategy;
import comp6421.semantic.strategy.StartFunctionStrategy;
import comp6421.semantic.strategy.TableStrategy;
import comp6421.semantic.strategy.VariableStrategy;

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
			return new ProgramStrategy();
		} else if ("sym_CreateClassScope".equals(action)) {
			return new ClassStrategy();
		} else if ("sym_StartFunction".equals(action)) {
			return new StartFunctionStrategy();
		} else if ("sym_StartMemberFunction".equals(action)) {
			return new MemberFunctionStrategy();
		} else if ("sym_AddFunctionParameter".equals(action)) {
			return new AdditionalParameter();
		} else if ("sym_CreateFunction".equals(action)) {
			return new FunctionStrategy();
		} else if ("sym_StoreType".equals(action)) {
			return new PushTypeStrategy();
		} else if ("sym_StoreId".equals(action)) {
			return new PushIdStrategy();
		} else if ("sym_StoreDimension".equals(action)) {
			return new DimensionStrategy();
		} else if ("sym_CreateVariable".equals(action)) {
			return new VariableStrategy();
		} else if ("sym_EndScope".equals(action)) {
			return new FinishScope();
		} 
		return null;
	}
	public static MigrationStrategy getMigrationStategy(String action) {
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

package comp6421.semantic;

import comp6421.semantic.migration.AdditionExpressionFragment;
import comp6421.semantic.migration.AssignmentExpression;
import comp6421.semantic.migration.ForStatement;
import comp6421.semantic.migration.FunctionCallExpressionFragment;
import comp6421.semantic.migration.GetStatement;
import comp6421.semantic.migration.IfStatement;
import comp6421.semantic.migration.MigrationStrategy;
import comp6421.semantic.migration.MultiplicationExpressionFragment;
import comp6421.semantic.migration.PutStatement;
import comp6421.semantic.migration.RelationExpressionFragment;
import comp6421.semantic.migration.ReturnStatement;
import comp6421.semantic.migration.StatementBlock;
import comp6421.semantic.migration.strategy.EndMigrationStrategy;
import comp6421.semantic.migration.strategy.FinishVariableAction;
import comp6421.semantic.migration.strategy.PushAdditionOperationAction;
import comp6421.semantic.migration.strategy.PushFloatLiteralAction;
import comp6421.semantic.migration.strategy.PushIntLiteralAction;
import comp6421.semantic.migration.strategy.PushMultiplicationOperationAction;
import comp6421.semantic.migration.strategy.PushRelationOperationAction;
import comp6421.semantic.migration.strategy.PushVariableNameAction;
import comp6421.semantic.migration.strategy.StartMigrationStrategy;
import comp6421.semantic.strategy.AdditionalParameter;
import comp6421.semantic.strategy.ClassStrategy;
import comp6421.semantic.strategy.DimensionStrategy;
import comp6421.semantic.strategy.FinishScope;
import comp6421.semantic.strategy.FunctionStrategy;
import comp6421.semantic.strategy.ClassFunctionStrategy;
import comp6421.semantic.strategy.ProgramStrategy;
import comp6421.semantic.strategy.PushIdStrategy;
import comp6421.semantic.strategy.PushTypeStrategy;
import comp6421.semantic.strategy.StartFunctionStrategy;
import comp6421.semantic.strategy.TableStrategy;
import comp6421.semantic.strategy.VariableStrategy;

public class StrategyFactor {

	public static TableStrategy getSymbolStategy(String action) {
		if ("sym_CreateProgram".equals(action)) {
			return new ProgramStrategy();
		} else if ("sym_CreateClassScope".equals(action)) {
			return new ClassStrategy();
		} else if ("sym_StartFunction".equals(action)) {
			return new StartFunctionStrategy();
		} else if ("sym_StartMemberFunction".equals(action)) {
			return new ClassFunctionStrategy();
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

	public static MigrationStrategy getMigrationStategy(String action, String token) {
		if ("sem_PushVariableName".equals(action)) {
			return new PushVariableNameAction();
		} else if ("sem_FinishVariable".equals(action)) {
			return new FinishVariableAction();
		} else if ("sem_StartAssignmentStatment".equals(action)) {
			return new StartMigrationStrategy(new AssignmentExpression());
		} else if ("sem_StartRelationExpression".equals(action)) {
			return new StartMigrationStrategy(new RelationExpressionFragment());
		} else if ("sem_EndRelationExpression".equals(action)) {
			return new EndMigrationStrategy();
		} else if ("sem_PushRelationOperation".equals(action)) {
			return new PushRelationOperationAction();
		} else if ("sem_StartAdditionExpression".equals(action)) {
			return new StartMigrationStrategy(new AdditionExpressionFragment());
		} else if ("sem_EndAdditionExpression".equals(action)) {
			return new EndMigrationStrategy();
		} else if ("sem_StartMultiplicationExpression".equals(action)) {
			return new StartMigrationStrategy(new MultiplicationExpressionFragment());
		} else if ("sem_EndMultiplicationExpression".equals(action)) {
			return new EndMigrationStrategy();
		} else if ("sem_PushIntLiteral".equals(action)) {
			return new PushIntLiteralAction();
		} else if ("sem_PushFloatLiteral".equals(action)) {
			return new PushFloatLiteralAction();
		} else if ("sem_PushAdditionOperation".equals(action)) {
			return new PushAdditionOperationAction();
		} else if ("sem_PushMultiplicationOperation".equals(action)) {
			return new PushMultiplicationOperationAction();
		} else if ("sem_StartIfStatement".equals(action)) {
			return new StartMigrationStrategy(new IfStatement());
		} else if ("sem_StartForStatement".equals(action)) {
			return new StartMigrationStrategy(new ForStatement());
		} else if ("sem_StartBlock".equals(action)) {
			return new StartMigrationStrategy(new StatementBlock());
		} else if ("sem_EndBlock".equals(action)) {
			return new EndMigrationStrategy();
		} else if ("sem_StartFunctionCall".equals(action)) {
			return new StartMigrationStrategy(new FunctionCallExpressionFragment(token));
		} else if ("sem_EndFunctionCall".equals(action)) {
			return new EndMigrationStrategy();
		} else if ("sem_StartReturnStatement".equals(action)) {
			return new StartMigrationStrategy(new ReturnStatement());
		} else if ("sem_StartGetStatement".equals(action)) {
			return new StartMigrationStrategy(new GetStatement());
		} else if ("sem_StartPutStatement".equals(action)) {
			return new StartMigrationStrategy(new PutStatement());
		}
		return null;
	}
}

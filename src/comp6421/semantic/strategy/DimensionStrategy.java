package comp6421.semantic.strategy;

import java.util.ArrayList;

import comp6421.scanner.Token;
import comp6421.semantic.entry.ArrayType;

public class DimensionStrategy extends TableStrategy {

	@Override
	public void execute(Token precedingToken) {

		ArrayType type;
		if (context.type instanceof ArrayType) {
			type = (ArrayType) context.type;
		} else {
			// to new a Array
			type = new ArrayType(context.type, new ArrayList<Integer>());
			context.type = type;
		}
		type.pushDimension(Integer.parseInt(precedingToken.getValue()));
	}
}

package comp6421.semantic.strategy;

import comp6421.scanner.Token;
import comp6421.semantic.STable;

public class FinishScope extends TableStrategy {

	@Override
	public void execute(Token token) {
		if (context.skip) {
			context.skip = false;
		} else {
			STable parent = context.current.getParent();
			if (parent != null) {
				context.current = parent;
			}
		}
	}

}

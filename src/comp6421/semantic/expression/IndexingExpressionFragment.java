package comp6421.semantic.expression;

import java.util.Collections;
import java.util.List;

import comp6421.semantic.CompilerError;
import comp6421.semantic.code.MathOperation;
import comp6421.semantic.entry.ArrayType;
import comp6421.semantic.value.MathValue;
import comp6421.semantic.value.StaticIntValue;
import comp6421.semantic.value.Value;

public class IndexingExpressionFragment extends ExpressionElement {
	
	private Value offset;
	private int currentIndex;
	private int nextOffsetSize;
	private List<Integer> dimensions;
	
	
	public IndexingExpressionFragment(ArrayType t) throws CompilerError{
		dimensions = t.getDimensions();
		currentIndex = 0;
		nextOffsetSize = t.getType().getSize();
		offset = new StaticIntValue(0);
	}
	
	public IndexingExpressionFragment(){
		dimensions = Collections.emptyList();
		currentIndex = 0;
		nextOffsetSize = 0;
		offset = new StaticIntValue(0);
	}
	
	@Override
	public void pushIdentifier(String id) throws CompilerError {
		throw new CompilerError("Not enough indices, got " + currentIndex + ", expected " + dimensions.size());
	}
	
	/*
	@Override
	public void pushIndex(String iString) throws CompilerError{
		int i;
		try{
			i = Integer.valueOf(iString);
			i -= 1; // we will internally use zero-based indexing
		}catch(NumberFormatException e){
			throw new InternalCompilerError("Invalid index: " + e.getMessage());
		}
		
		if(i >= dimensions.get(currentIndex)){
			throw new CompilerError("Index " + i + " out of bounds");
		}else{
			offset += i * nextOffsetSize;
			nextOffsetSize *= dimensions.get(currentIndex);
			++currentIndex;
			if(currentIndex == dimensions.size()){
				context.finishTopElement();
			}
		}
	}*/
	
	@Override
	public void acceptSubElement(ExpressionElement e) throws CompilerError {
		if(e instanceof AdditionExpressionFragment){
			// Convert from one-based to zero-based indexing;
			Value i = new MathValue(MathOperation.SUBTRACT, e.getValue(), new StaticIntValue(1));

			// TODO - array bounds checking ....
			i      = new MathValue(MathOperation.MULTIPLY, i, new StaticIntValue(nextOffsetSize));
			offset = new MathValue(MathOperation.ADD, offset, i);
			
			nextOffsetSize *= dimensions.get(currentIndex);
			++currentIndex;
			if(currentIndex == dimensions.size()){
				context.finishTopElement();
			}
			
		}else{
			super.acceptSubElement(e);
		}
	}
	
	@Override
	public Value getValue() throws CompilerError {
		if(currentIndex != dimensions.size()){
			throw new CompilerError("Can not put a array value to non-array");
		}else{
			return offset;
		}
	}
}

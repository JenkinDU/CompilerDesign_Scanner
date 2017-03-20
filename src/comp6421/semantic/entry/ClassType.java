package comp6421.semantic.entry;

import comp6421.semantic.CompilerError;
import comp6421.semantic.SymbolTable;

public class ClassType implements SymbolTableEntryType {

	private ClassEntry classEntry;
	
	public ClassType(ClassEntry classEntry){
		this.classEntry = classEntry;
	}
	
	@Override
	public int getSize() throws CompilerError {
		return classEntry.getSize();
	}
	
	@Override
	public String toString(){
		return classEntry.getName();
	}
	
	@Override
	public SymbolTable getScope(){
		return classEntry.getScope();
	}
	
	@Override
	public boolean equals(Object other){
		return other instanceof ClassType && ((ClassType)other).classEntry.getName().equals(classEntry.getName());
	}

}

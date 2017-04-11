package comp6421.semantic.code;

import comp6421.semantic.SemanticException;

public class AddWordImmediateInstruction extends Instruction {

	private final Register dest;
	private final Register regVal;
	private final String      iVal;
	
	public AddWordImmediateInstruction(Register dest, Register regVal, int iVal) throws SemanticException{
		this.dest   = dest;
		this.regVal = regVal;
		this.iVal   = Integer.toString(iVal);
		
		if(iVal >= (1<<16)){
			throw new SemanticException("Immediate values greater than " + ((1<<16)-1) + " are not supported");
		}
	}

	public AddWordImmediateInstruction(Register dest, Register regVal, String label) {
		this.dest   = dest;
		this.regVal = regVal;
		this.iVal   = label;		
	}
	
	@Override
	protected String _getCode() {
		return "addi" + '\t' + dest.registerName + ", " + regVal.registerName + ", " + iVal;
	}

}

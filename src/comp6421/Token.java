package comp6421;

abstract public class Token {
	protected EType TYPE = null;
	protected String value=null;
	
	public void printType() {
		if(TYPE == null) {
			System.out.println("Erro Type");
		} else {
			System.out.println("Type is:"+TYPE.name());
		}
	}
	public void printValue() {
		if(value == null) {
			System.out.println("Erro Value");
		} else {
			System.out.println("Value is:"+value);
		}
	}
}

package comp6421;

public class Token {
	protected EType TYPE = null;
	protected String value = null;
	protected int position = 0;
	
	public Token(int p, EType type, String v) {
		this.position = p;
		this.TYPE = type;
		this.value = v;
	}
	
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

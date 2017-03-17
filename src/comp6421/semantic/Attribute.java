package comp6421.semantic;

public class Attribute {
	
	enum EType {
		INTEGER, FLOAT, CLASS
	}
	enum Structure {
		SIMPLE, ARRAY
	}
	private EType type;//INTEGER, FLOAT, CLASS 
	private Structure structure;//SIMPLE, ARRAY
	private String value;
	private EType returnType;
	public static String dimension = "";
	
	public Attribute() {
		
	}
}

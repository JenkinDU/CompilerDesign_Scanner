package comp6421.semantic;

public class Entry implements IEntry {
	private String name;
	private Kind kind;
	private Attribute type;
	private String link;
	private String sign;
	private String path;
	
	public Entry() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ITable getParent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ITable getLink() {
		// TODO Auto-generated method stub
		return null;
	}

}

package comp6421;

public class Item {
	private int position;
	private String value;
	
	public Item(int p, String v) {
		this.position = p;
		this.value = v;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
}

package comp6421.semantic;

public abstract class STEntry {

	public static enum Kind {
		Function, Class, Parameter, Variable,
	};

	private String name;
	private Kind kind;
	private IType type;
	private STable scope;

	private int offset;

	public STEntry(String name, Kind kind, IType type, STable scope) {
		this.name = name;
		this.kind = kind;
		this.type = type;
		this.scope = scope;

		if (scope != null) {
			scope.setEnclosingEntry(this);
		}

		this.offset = -1;
	}

	protected abstract int getEntrySize();

	public final int getSize() {
		return getEntrySize();
	}

	public String getName() {
		return name;
	}

	public Kind getKind() {
		return kind;
	}

	public IType getType() {
		return type;
	}

	public STable getScope() {
		return scope;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setKind(Kind kind) {
		this.kind = kind;
	}

	public void setType(IType type) {
		this.type = type;
	}

	public void setScope(STable scope) {
		this.scope = scope;
	}

	@Override
	public String toString() {
		String v = "";
		String type = getType().toString();
		boolean add = false;
		if (type.contains(":")) {
			add = true;
			String t[] = type.split(":");
			String param = t[0];
			String re = t[1];
			v = (re.equals(" ") ? "" : re + " ") + getKind() + " " + getName() + param;
		} else {
			v = getKind() + " " + getName() + " " + getType();
		}
		v = String.format("%-" + (v.length() < 50 ? 50 : v.length()) + "s", v);
		String off = "";
		off = "[Size:" + getSize() + " Add:" + (add ? 0 : offset) + "]";
		return v + off;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getOffset() {
		return offset;
	}
}

package comp6421.semantic.entry;

import comp6421.semantic.STable;
import comp6421.semantic.SemanticException;

public abstract class STEntry {

	public static enum Kind {
		Function, Class, Parameter, Variable,
	};

	private String name;
	private Kind kind;
	private EntryType type;
	private STable scope;

	private int offset;

	public STEntry(String name, Kind kind, EntryType type, STable scope) {
		this.name = name;
		this.kind = kind;
		this.type = type;
		this.scope = scope;

		if (scope != null) {
			scope.setEnclosingEntry(this);
		}

		this.offset = -1;
	}

	protected abstract int calculateSize() throws SemanticException;

	public final int getSize() throws SemanticException {
		return calculateSize();
	}

	public String getName() {
		return name;
	}

	public Kind getKind() {
		return kind;
	}

	public EntryType getType() {
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

	public void setType(EntryType type) {
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
		try {
			off = "[Size:" + getSize() + " Add:" + (add ? 0 : offset) + "]";
		} catch (SemanticException e) {
			e.printStackTrace();
		}
		return v + off;
	}

	@Override
	public boolean equals(Object other) {
		if (other instanceof STEntry) {
			STEntry e = (STEntry) other;
			return ((e.getKind() == null && getKind() == null) || e.getKind().equals(getKind()))
					&& ((e.getType() == null && getType() == null) || e.getType().equals(getType()))
					&& ((e.getName() == null && getName() == null) || e.getName().equals(getName()));
		} else {
			return false;
		}
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getOffset() {
		return offset;
	}
}

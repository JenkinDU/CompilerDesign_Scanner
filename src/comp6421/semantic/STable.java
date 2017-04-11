package comp6421.semantic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import comp6421.semantic.entry.STEntry;

public class STable implements ITable {

	private Map<String, STEntry> entries;
	// private HashMap<String, Entry> entry;
	// private Scope scope = Scope.UNKNOWN;
	// private String name = null;

	@Override
	public void create(String Tn) {

	}

	@Override
	public void delete(String Tn) {

	}

	@Override
	public void print(String Tn) {
		find(Tn).toString();
	}

	int currentOffset;

	private final STable parent;

	private STEntry enclosingEntry;

	public STable(STable parent) {
		this.parent = parent;
		this.entries = new HashMap<String, STEntry>();
		this.currentOffset = 0;
	}

	public boolean exists(String name) {
		return entries.get(name) != null;
	}

	public STEntry find(String name) {
		STEntry e = entries.get(name);
		if (e != null) {
			return e;
		} else {
			if (parent != null) {
				return parent.find(name);
			} else {
				return null;
			}
		}
	}

	public void add(STEntry entry) throws SemanticException {
		// Note that for functions, parameters are added in order
		entries.put(entry.getName(), entry);

		entry.setOffset(currentOffset);
		currentOffset += entry.getSize();
	}

	public int getSize() {
		return currentOffset;
	}

	public STable getParent() {
		return parent;
	}

	@Override
	public String toString() {
		return new SymbolTableStringifier().toString(this);
	}

	private class SymbolTableStringifier {

		private int indent = 0;
		private StringBuilder sb = new StringBuilder();

		public String toString(STable s) {
			if (s.enclosingEntry instanceof FunctionEntry) {
				List<Entry<String, STEntry>> list = new ArrayList<Entry<String, STEntry>>(s.entries.entrySet());
				Collections.sort(list, new Comparator<Entry<String, STEntry>>() {

					@Override
					public int compare(Entry<String, STEntry> arg0, Entry<String, STEntry> arg1) {
						return arg1.getValue().getOffset() - arg0.getValue().getOffset();
					}
				});
				for (Entry<String, STEntry> e : list) {
					STEntry ste = e.getValue();
					sb.append(ste);
					if (ste.getScope() != null) {
						++indent;
						newline();
						toString(ste.getScope());
						--indent;
					}
					newline();
				}
				return sb.toString();
			}
			for (Entry<String, STEntry> e : s.entries.entrySet()) {
				STEntry ste = e.getValue();
				sb.append(ste);
				if (ste.getScope() != null) {
					++indent;
					newline();
					toString(ste.getScope());
					--indent;
				}
				newline();
			}
			return sb.toString();
		}

		private void newline() {
			sb.append('\n');
			for (int i = 0; i < indent; ++i) {
				sb.append("   ");
			}
		}
	}

	public Collection<STEntry> getEntries() {
		return Collections.unmodifiableCollection(entries.values());
	}

	public STEntry getEnclosingEntry() {
		return enclosingEntry;
	}

	public void setEnclosingEntry(STEntry enclosingEntry) {
		this.enclosingEntry = enclosingEntry;
	}
}

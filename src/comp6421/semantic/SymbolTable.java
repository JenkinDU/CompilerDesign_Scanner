package comp6421.semantic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import comp6421.semantic.entry.SymbolTableEntry;

public class SymbolTable implements ITable {
	
	private Map<String, SymbolTableEntry> entries;
//	private HashMap<String, Entry> entry;
//	private Scope scope = Scope.UNKNOWN;
//	private String name = null;

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
	
	private final SymbolTable parent;
	
	private SymbolTableEntry enclosingEntry;
	
	public SymbolTable(SymbolTable parent){
		this.parent = parent;
		this.entries = new HashMap<String, SymbolTableEntry>();
		this.currentOffset = 0;
	}

	public boolean exists(String name) {
		return entries.get(name) != null;
	}
	
	public SymbolTableEntry find(String name){
		SymbolTableEntry e = entries.get(name);
		if(e != null){
			return e;
		}else{
			if(parent != null){
				return parent.find(name);
			}else{
				return null;
			}
		}
	}

	public void add(SymbolTableEntry entry) throws CompilerError {
		// Note that for functions, parameters are added in order
		entries.put(entry.getName(), entry);
		
		entry.setOffset(currentOffset);
		currentOffset += entry.getSize();
	}

	public int getSize(){
		return currentOffset;
	}
	
	public SymbolTable getParent() {
		return parent;
	}
	
	@Override
	public String toString(){
		return new SymbolTableStringifier().toString(this);
	}
	
	private class SymbolTableStringifier {

		private int indent = 0;
		private StringBuilder sb = new StringBuilder();
		
		public String toString(SymbolTable s){
			if(s.enclosingEntry instanceof FunctionEntry) {
				List<Entry<String, SymbolTableEntry>> list = new ArrayList<Entry<String, SymbolTableEntry>>(s.entries.entrySet());
				Collections.sort(list, new Comparator<Entry<String, SymbolTableEntry>>() {

					@Override
					public int compare(Entry<String, SymbolTableEntry> arg0, Entry<String, SymbolTableEntry> arg1) {
						return arg1.getValue().getOffset() - arg0.getValue().getOffset();
					}
				});
				for(Entry<String, SymbolTableEntry> e : list){
					SymbolTableEntry ste = e.getValue();
					sb.append(ste);
					if(ste.getScope() != null){
						++indent;
						newline();
						toString(ste.getScope());
						--indent;
					}
					newline();
				}
				return sb.toString();
			}
			for(Entry<String, SymbolTableEntry> e : s.entries.entrySet()){
				SymbolTableEntry ste = e.getValue();
				sb.append(ste);
				if(ste.getScope() != null){
					++indent;
					newline();
					toString(ste.getScope());
					--indent;
				}
				newline();
			}
			return sb.toString();
		}
		
		private void newline(){
			sb.append('\n');
			for(int i = 0; i < indent; ++i){
				sb.append("   ");
			}
		}
	}

	public Collection<SymbolTableEntry> getEntries() {
		return Collections.unmodifiableCollection(entries.values());
	}

	public SymbolTableEntry getEnclosingEntry() {
		return enclosingEntry;
	}

	public void setEnclosingEntry(SymbolTableEntry enclosingEntry) {
		this.enclosingEntry = enclosingEntry;
	}
}

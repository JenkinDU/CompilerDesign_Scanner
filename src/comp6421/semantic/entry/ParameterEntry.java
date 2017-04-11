package comp6421.semantic.entry;

public class ParameterEntry extends STEntry {

	public ParameterEntry(String name, EntryType type) {
		super(name, Kind.Parameter, type, null);
	}

	@Override
	protected int calculateSize() {
		// Parameters are stored by value for primitives and by reference for composite types
		return 4;
	}
	
	
	
}

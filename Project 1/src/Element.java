public class Element<KeyType, ValueType>{
	
	private KeyType key;
	private ValueType value;
	

	public Element(KeyType key, ValueType value) {
		this.key = key;
		this.value = value;
	}
	
	public KeyType key() {
		return key;
	}
	
	public ValueType value() {
		return value;
	}
}

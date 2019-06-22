package application;

import javafx.beans.property.SimpleStringProperty;

public final class Parameter {
	private final SimpleStringProperty id = new SimpleStringProperty();
	private final SimpleStringProperty name = new SimpleStringProperty();
	private final SimpleStringProperty type = new SimpleStringProperty();
	private final SimpleStringProperty value = new SimpleStringProperty();
	
	public Parameter(String id,String name, String type, String value) {
		setId(id);
		setName(name);
		setType(type);
		setValue(value);
	}
	
	
	public String getName() {
		return name.get();
	}
	public void setName(String name) {
		this.name.set(name);
	}

	public String getId() {
		return id.get();
	}
	public void setId(String id) {
		this.id.set(id);

	}
	public String getType() {
		return type.get();
	}
	public void setType(String type) {
		this.type.set(type);
	}
	public String getValue() {
		return value.get();
	}
	public void setValue(String value) {
		this.value.set(value);
	}
	
}

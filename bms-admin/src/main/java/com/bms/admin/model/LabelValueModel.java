package com.bms.admin.model;

public class LabelValueModel<T> {

	private String label;
	private T value;
	
	public LabelValueModel() {
		
	}
	
	public LabelValueModel(String label, T value) {
		this();
		this.label = label;
		this.value = value;
	}

	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public T getValue() {
		return value;
	}
	public void setValue(T value) {
		this.value = value;
	}
}

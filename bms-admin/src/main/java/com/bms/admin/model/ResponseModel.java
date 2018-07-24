package com.bms.admin.model;

import java.util.ArrayList;
import java.util.List;

public class ResponseModel<T> {

	private boolean status;
	private List<String> errors;
	private List<T> datas;
	
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public List<String> getErrors() {
		return errors;
	}
	public void setErrors(List<String> errors) {
		this.errors = errors;
	}
	public List<T> getDatas() {
		return datas;
	}
	public void setDatas(List<T> datas) {
		this.datas = datas;
	}
	
	public void addError(String error) {
		if(this.errors == null) {
			this.errors = new ArrayList<>();
		}
		this.errors.add(error);
	}
	public void addErrors(List<String> errors) {
		if(this.errors == null) {
			this.errors = new ArrayList<>();
		}
		this.errors.addAll(errors);
	}
	public void addData(T data) {
		if(this.datas == null) {
			this.datas = new ArrayList<>();
		}
		this.datas.add(data);
	}
	public void addDatas(List<T> datas) {
		if(this.datas == null) {
			this.datas = new ArrayList<>();
		}
		this.datas.addAll(datas);
	}
}

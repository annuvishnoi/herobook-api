package com.galvanize.herobook.response;

import java.util.ArrayList;
import java.util.List;

public class HerobookResponse {

	private Object data;
	List<String> errorMessages = new ArrayList<>();

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public List<String> getErrorMessages() {
		return errorMessages;
	}	

	public void addErrorMessage(String errorMessage) {
		this.errorMessages.add(errorMessage);
	}

}

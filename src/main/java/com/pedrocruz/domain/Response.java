package com.pedrocruz.domain;

public class Response<T> {

	private T response;
	
	
	public T getResponse() {
		return response;
	}

	public void setResponse(T data) {
		this.response = data;
	}

}

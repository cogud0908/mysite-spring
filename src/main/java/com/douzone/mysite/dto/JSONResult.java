package com.douzone.mysite.dto;

public class JSONResult {

	private String result; // "success", "fail"
	private String message; // success: "null" , fail: "errormessage"
	private Object data; // success: data 객체 , fail: null
	
	private JSONResult( String result, String message, Object data) {
		this.result = result;
		this.message = message;
		this.data = data;
	}
	
	public static JSONResult success(Object data) {
		return new JSONResult("success", null, data);
	}
	
	public static JSONResult fail(String message) {
		return new JSONResult("fail", message, null);
	}
	
	public String getResult() {
		return result;
	}
	public String getMessage() {
		return message;
	}
	public Object getData() {
		return data;
	}
	
	@Override
	public String toString() {
		return "JSONResult [result=" + result + ", message=" + message + ", data=" + data + "]";
	}	
}

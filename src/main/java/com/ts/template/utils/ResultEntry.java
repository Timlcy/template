package com.ts.template.utils;

public class ResultEntry<T> {

//	@ApiModelProperty(value="状态码")
	private int statusCode;
//	@ApiModelProperty(value="返回类型")
	private String contentType;
//	@ApiModelProperty(value="返回的数据")
	private T result;


	private static final String CONTENTTYPE = "application/json";

	public ResultEntry() {
		this.contentType = CONTENTTYPE;
	}

	public ResultEntry(T result) {
		super();
		this.statusCode = 200;
		this.result = result;
		this.contentType = CONTENTTYPE;
	}

	public ResultEntry(int statusCode, T result) {
		super();
		this.statusCode = statusCode;
		this.result = result;
		this.contentType = CONTENTTYPE;
	}

	public ResultEntry(int statusCode, String contentType, T result) {
		this.statusCode = statusCode;
		this.result = result;
		String type =  contentType.split(":")[1].trim();
		this.contentType = type;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		String type =  contentType.split(":")[1].trim();
		this.contentType = type;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "ResultEntry [statusCode=" + statusCode + ", contentType=" + contentType + ", result=" + result + "]";
	}

}

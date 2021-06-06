package com.ts.template.utils;

import io.swagger.v3.oas.annotations.media.Schema;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Schema(title = "返回信息")
public class ResponseMessage<T> {
	@Schema(title = "是否成功")
	private boolean success;
	@Schema(title = "数据")
	private T data;
	@Schema(title = "错误码")
	private String errorCode;
	@Schema(title = "错误信息")
	private String errorMessage;
	// error display type： 0 silent; 1 message.warn; 2 message.error; 4 notification; 9 page
	@Schema(title = "错误信息显示类型")
	private int showType;
	// Convenient for back-end Troubleshooting: unique request ID
	@Schema(title = "惟一请求ID")
	private  String traceId;
	// onvenient for backend Troubleshooting: host of current access server
	@Schema(title = "当前访问服务器的主机")
	private  String host;


	public ResponseMessage(boolean success, T data, String errorCode, String errorMessage,
						   int showType) {
		this.success = success;
		this.data = data;
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.showType = showType;
		this.traceId = IdFactory.getSnowflake();
		this.host = getIp();
	}


	public ResponseMessage(boolean success, T data) {
		this.success = success;
		this.data = data;
		this.traceId = IdFactory.getSnowflake();
		this.host = getIp();
	}

	private String getIp() {
		InetAddress addr = null;
		try {
			addr = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return addr.getHostAddress();
	}
}

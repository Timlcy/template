package com.ts.template.utils;

import lombok.Data;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 返回信息
 * @author timlcy
 */
@Data
public class ResponseMessage<T> {


	/**
	 * 是否成功
	 */
	private boolean success;
	/**
	 * 数据
	 */
	private T data;
	/**
	 * 数据
	 */
	private String errorCode;
	/**
	 * 错误信息
	 *
	 */
	private String errorMessage;
	//
	/**
	 * 错误信息显示类型
	 * error display type： 0 silent; 1 message.warn; 2 message.error; 4 notification; 9 page
	 */
	private int showType;
	/**
	 * 惟一请求ID
	 * Convenient for back-end Troubleshooting: unique request ID
	 */
	private  String traceId;
	/**
	 * 当前访问服务器的主机
	 * onvenient for backend Troubleshooting: host of current access server
	 */
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

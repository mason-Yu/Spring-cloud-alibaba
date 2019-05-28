package com.nacos.alibaba.utils;

import java.util.LinkedHashMap;

/**
 * 服务层，之间及跨层数据传递的，公共map封装类
 * @author brucewang
 *
 */
public class ServiceMap {
	
	private boolean success = true;// 是否成功
	private String errorCode = "-1";//错误代码  默认成功
	private String msg = "操作成功";// 提示信息
	private LinkedHashMap<String, Object> body = new LinkedHashMap<String, Object>();//封装的map
	
	public LinkedHashMap<String, Object> getBody() {
		return body;
	}

	public void setBody(LinkedHashMap<String, Object> body) {
		this.body = body;
	}

	public void put(String key, Object value){
		body.put(key, value);
	}
	
	public void remove(String key){
		body.remove(key);
	}
	
	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}


	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorCode() {
		return errorCode;
	}
}
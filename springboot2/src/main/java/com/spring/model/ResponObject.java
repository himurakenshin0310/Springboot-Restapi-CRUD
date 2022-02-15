package com.spring.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponObject {
	private String status;
	private String msg;
	private Object data;
	
	public static ResponObject form(String status, String msg, Object data) {
		ResponObject rs = new ResponObject();
		rs.setStatus(status);
		rs.setData(data);
		rs.setMsg(msg);
		return rs;
	}
}

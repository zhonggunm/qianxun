package com.qianxun.qxtag.models;

import java.io.Serializable;

public class Sysinfo implements Serializable {

	private short apiversion = 1;

	public Sysinfo(){}
	
	public short getApiversion() {
		return apiversion;
	}

	public void setApiversion(short apiversion) {
		this.apiversion = apiversion;
	}

}

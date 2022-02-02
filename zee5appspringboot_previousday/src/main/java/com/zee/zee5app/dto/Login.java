package com.zee.zee5app.dto;

import lombok.Data;

@Data

public class Login implements Comparable<Login>{
	private String userName;
	private String password;
	private String regId;
	private ROLE role;
	
	public Login(String userName, String password, String regId, ROLE role) {
		super();
		this.userName = userName;
		this.password = password;
		this.regId = regId;
		this.role = role;
	}

	public Login() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int compareTo(Login o) {
		// TODO Auto-generated method stub
		return this.regId.compareTo(o.getRegId());
	}
	
 

}

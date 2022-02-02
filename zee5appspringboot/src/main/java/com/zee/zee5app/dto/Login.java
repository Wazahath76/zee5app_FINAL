package com.zee.zee5app.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data

@Entity
@Table(name = "login")
public class Login implements Comparable<Login>{
	
	@Id
	@Column(name = "username")
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

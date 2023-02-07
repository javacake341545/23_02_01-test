package com.javacake341545.java.test;

public class User {
	int user_id;
	String loginID;
	String password;
	String regDate;
	String updateDate;
	String name;
	
	User(String loginID, String password, String updateDate, String regDate, String name) {
		this.loginID = loginID;
		this.password = password;
		this.regDate = regDate;
		this.name = name;
	}
}


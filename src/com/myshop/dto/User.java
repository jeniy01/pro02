package com.myshop.dto;

import java.util.Date;
//회원
public class User {
	private String id;
	private String pw;
	private String hpw;
	private String name;
	private String tel;
	private String addr;
	private String email;
	private String rdate;
	private int point;
	private int visited;
	
	public User(){
		Date now = new Date();
		this.rdate = now.toString();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRdate() {
		return rdate;
	}

	public void setRdate(String rdate) {
		this.rdate = rdate;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public int getVisited() {
		return visited;
	}

	public void setVisited(int visited) {
		this.visited = visited;
	}

	public String getHpw() {
		return hpw;
	}

	public void setHpw(String hpw) {
		this.hpw = hpw;
	}
	
	
}

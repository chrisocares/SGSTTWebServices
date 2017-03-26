package com.programmingfree.springservice.domain;

public class User {

	private int userid;
	private String firstName;
	private String lastName;	
	private String email;
	private String direcc;
	private String telf;
	private String cell;
	private String text;
	
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getdirecc() {
		return direcc;
	}
	public void setdirecc(String direcc) {
		this.direcc = direcc;
	}
	
	public String gettelf() {
		return telf;
	}
	public void settelf(String telf) {
		this.telf = telf;
	}
	
	public String getcell() {
		return cell;
	}
	public void setcell(String cell) {
		this.cell = cell;
	}
	
	
	@Override
	public String toString() {
		return "User [userid=" + userid + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", email="
				+ email + "]";
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
		
		
}


package org.edupoll.model.dto.request;



public class UserJoinData {
	String id;
	String pass;
	String nick;
	String authority;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	
	public UserJoinData(String id, String pass, String nick, String authority) {
		super();
		this.id = id;
		this.pass = pass;
		this.nick = nick;
		this.authority = "ROLE_NORMAL";
	}
	
	
	public UserJoinData() {
		super();
	}
	
	
	
	
}

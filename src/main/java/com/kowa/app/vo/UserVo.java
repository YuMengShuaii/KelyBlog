package com.kowa.app.vo;

import javax.persistence.Column;

import com.kowa.app.po.UserPo;

public class UserVo {
	
    public UserVo(UserPo user) {
		this.name = user.getName();
		this.face = user.getFace();
		this.username = user.getUsername();
		this.phone = user.getPhone();
		this.sex = user.getSex();
		this.content = user.getContent();
	}


	private String name;
    
    
    private String face;
    
      
    private String username;
  
    
    private String phone;
    
    
    private int sex;   
  
    
    private String content;


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getFace() {
		return face;
	}


	public void setFace(String face) {
		this.face = face;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public int getSex() {
		return sex;
	}


	public void setSex(int sex) {
		this.sex = sex;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}
    
    
}

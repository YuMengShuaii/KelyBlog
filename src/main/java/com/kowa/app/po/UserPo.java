package com.kowa.app.po;  
import javax.persistence.*;  
import javax.validation.constraints.NotNull;  
import java.math.BigDecimal;  
import java.util.Date;  
@Entity  
@Table(name = "user")  
public class UserPo {  
    @Id  
    @GeneratedValue(strategy = GenerationType.AUTO)  
    @Column(name = "id")  
    private int id;  
  
    @Column(name = "name", nullable = true, length = 30)  
    private String name;
    
    @Column(name = "face", nullable = true, length = 100)  
    private String face;
    
    @Column(name = "username", nullable = false, length = 30)  
    private String username;
  
    @Column(name = "password", nullable = false, length = 30)  
    private String password;
    
    @Column(name = "phone", nullable = true, length = 15)  
    private String phone;
    
    @Column(name = "sex")  
    private int sex;   
 
    @Column(name = "content", nullable = true, length = 200)  
    private String content;
    
  
    public UserPo() { }

    

	public String getFace() {
		return face;
	}



	public void setFace(String face) {
		this.face = face;
	}



	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
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
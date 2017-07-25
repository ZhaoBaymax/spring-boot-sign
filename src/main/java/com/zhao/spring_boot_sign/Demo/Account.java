package com.zhao.spring_boot_sign.Demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity()
@Table(name="user_acc")
public class Account {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long id;
	public String username;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserpaw() {
		return userpaw;
	}
	public void setUserpaw(String userpaw) {
		this.userpaw = userpaw;
	}
	public String userpaw;
	
}

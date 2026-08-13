package com.expense.entity;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name =  "user", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class user {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(name="First_Name")
	private String firstName;
	@Column(name="Last_Name")
	private String lastName;
	private String email;
	private String password;
	
	// many to manu mapping between user and roles with fetch type eager not on demand for lazy to map whenever accessed with all cascading
	@ManyToMany(fetch= FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinTable(
			name="user_roles", 
					joinColumns = @JoinColumn(
							// user_id as foreign key in third table user_roles
				            name = "user_id", referencedColumnName = "id"),
					inverseJoinColumns = @JoinColumn(
							// role_id as foreign key in third table user_roles
						            name = "role_id", referencedColumnName = "id"))
	Collection<Role> roles;
	
	public user() {
		
	}
	public user(String firstName, String lastName, String email, String password, Collection<Role> roles) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.roles = roles;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Collection<Role> getRoles() {
		return roles;
	}
	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}
	
	
	
	
	
	
	

}

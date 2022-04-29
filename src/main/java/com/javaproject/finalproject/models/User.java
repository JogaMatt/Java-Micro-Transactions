package com.javaproject.finalproject.models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="users")
public class User
{
//	MEMBER VARIABLES
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "First name is required")
	@Size(min=2, message = "First name must be longer than 2 characters")
	private String first_name;
	
	@NotEmpty(message = "Last name is required")
	@Size(min=2, message = "Last name must be longer than 2 characters")
	private String last_name;
	
	@NotEmpty(message = "Username is required")
	@Size(min=5, message = "Username must be longer than 5 characters")
	private String username;
	
	@NotEmpty(message = "Email is required")
	@Email(message = "Please enter a valid email!")
	private String email;
	
	@NotEmpty(message = "Password is required")
	@Size(min = 8, max = 128, message = "Password must be between 8 and 128 characters")
	private String password;
	
	@Transient
	@NotEmpty(message = "Confirm Password is required")
	@Size(min = 8, max = 128, message = "Confirm Password must be between 8 and 128 characters")
	private String confirm;
	
	@Column(updatable=false)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createdAt;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date updatedAt;
	
//	RELATIONSHIPS
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private List<UsersEquipment> purchasedEquipment;
	
	@OneToOne(mappedBy="user", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    private Wallet wallet;
	
	@OneToMany(mappedBy="user", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    private List<UsersPrizes> prize;
	
//	CONSTRUCTORS
	
	public User() {
		super();
	}
	
	public User(String first_name, String last_name, String username, String email,
			String password, String confirm, List<UsersEquipment> purchasedEquipment, Wallet wallet, List<UsersPrizes> prize) {
		super();
		this.first_name = first_name;
		this.last_name = last_name;
		this.username = username;
		this.email = email;
		this.password = password;
		this.confirm = confirm;
		this.purchasedEquipment = purchasedEquipment;
		this.wallet = wallet;
		this.prize = prize;
	}



//	GETTERS / SETTERS / OTHER METHODS
	
	@PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
   

	@PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
	public String getConfirm() {
		return confirm;
	}
	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	public List<UsersEquipment> getPurchasedEquipment() {
		return purchasedEquipment;
	}
	public void setPurchasedEquipment(List<UsersEquipment> purchasedEquipment) {
		this.purchasedEquipment = purchasedEquipment;
	}

	public Wallet getWallet() {
		return wallet;
	}

	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}

	public List<UsersPrizes> getPrize() {
		return prize;
	}

	public void setPrize(List<UsersPrizes> prize) {
		this.prize = prize;
	}

	
	
	
    
}

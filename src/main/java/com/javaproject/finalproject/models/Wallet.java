package com.javaproject.finalproject.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="wallets")
public class Wallet
{
//	MEMBER VARIABLES
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Long coins = (long) 1000;
	
	private Long diamonds = (long) 5;
	
	@Column(updatable=false)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createdAt;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date updatedAt;
	
//	RELATIONSHIPS
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
    private User user;

//	CONSTRUCTORS
	
	public Wallet()
	{
		super();
	}
	
	public Wallet(Long coins, Long diamonds, User user) {
		super();
		this.coins = coins;
		this.diamonds = diamonds;
		this.user = user;
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

	public Long getCoins() {
		return coins;
	}

	public void setCoins(Long coins) {
		this.coins = coins;
	}

	public Long getDiamonds() {
		return diamonds;
	}

	public void setDiamonds(Long diamonds) {
		this.diamonds = diamonds;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}

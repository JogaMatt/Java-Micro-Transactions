package com.javaproject.finalproject.models;

import java.time.Instant;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="usersequipment")
public class UsersEquipment
{
//	MEMBER VARIABLES
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "Name is required")
	@Size(min=5, message = "Name must be longer than 5 characters")
	private String name;
	
	@NotNull(message = "Price is required")
	@Min(0)
	private Integer price;
	
	@NotNull(message = "Sell price is required")
	@Min(0)
	private Integer sellPrice;
	
	@NotNull(message = "Generation value is required")
	@Min(0)
	private Integer generationVal;
	
	@NotEmpty(message = "Image is required")
	@Size(min=5)
	private String image;
	
	private Instant startTime;
	
	@Column(updatable=false)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createdAt;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date updatedAt;
	
	
	
//	RELATIONSHIPS
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User user;

	
//	CONSTRUCTORS
	
	public UsersEquipment() {
		super();
	}


	public UsersEquipment(String name, Integer price, Integer sellPrice, Integer generationVal, String image, User user) {
		super();
		this.name = name;
		this.price = price;
		this.sellPrice = sellPrice;
		this.generationVal = generationVal;
		this.image = image;
		this.user = user;
	}
	
//	GETTERS / SETTERS / OTHER METHODS

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Integer getPrice() {
		return price;
	}


	public void setPrice(Integer price) {
		this.price = price;
	}


	public Integer getSellPrice() {
		return sellPrice;
	}


	public void setSellPrice(Integer sellPrice) {
		this.sellPrice = sellPrice;
	}


	public Integer getGenerationVal() {
		return generationVal;
	}


	public void setGenerationVal(Integer generationVal) {
		this.generationVal = generationVal;
	}

	public String getImage() {
		return image;
	}


	public void setImage(String image) {
		this.image = image;
	}


	public Instant getStartTime() {
		return startTime;
	}


	public void setStartTime(Instant startTime) {
		this.startTime = startTime;
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
	
	@PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
   

	@PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }
}

package com.javaproject.finalproject.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="prizes")
public class Prize
{
//	MEMBER VARIABLES
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "Name is required")
	@Size(min=2, message = "Name must be longer than 2 characters")
	private String name;
	
	@NotNull(message = "Price is required")
	@Min(0)
	private Integer coinPrice;
	
	@NotNull(message = "Sell price is required")
	@Min(0)
	private Integer sellCoinPrice;
	
	@NotNull(message = "Price is required")
	@Min(0)
	private Integer diamondPrice;
	
	@NotNull(message = "Diamond sell price is required")
	@Min(0)
	private Integer sellDiamondPrice;
	
	@NotEmpty(message = "Image is required")
	@Size(min=5)
	private String image;
	
	@Column(updatable=false)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createdAt;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date updatedAt;

	public Prize() {
		super();
	}

	public Prize(String name, Integer coinPrice, Integer sellCoinPrice, Integer diamondPrice, Integer sellDiamondPrice, String image) {
		super();
		this.name = name;
		this.coinPrice = coinPrice;
		this.sellCoinPrice = sellCoinPrice;
		this.diamondPrice = diamondPrice;
		this.sellDiamondPrice = sellDiamondPrice;
		this.image = image;
	}



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

	

	public Integer getCoinPrice() {
		return coinPrice;
	}

	public void setCoinPrice(Integer coinPrice) {
		this.coinPrice = coinPrice;
	}

	public Integer getSellCoinPrice() {
		return sellCoinPrice;
	}

	public void setSellCoinPrice(Integer sellCoinPrice) {
		this.sellCoinPrice = sellCoinPrice;
	}

	public Integer getSellDiamondPrice() {
		return sellDiamondPrice;
	}

	public void setSellDiamondPrice(Integer sellDiamondPrice) {
		this.sellDiamondPrice = sellDiamondPrice;
	}

	public Integer getDiamondPrice() {
		return diamondPrice;
	}

	public void setDiamondPrice(Integer diamondPrice) {
		this.diamondPrice = diamondPrice;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
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
	
	

	
}

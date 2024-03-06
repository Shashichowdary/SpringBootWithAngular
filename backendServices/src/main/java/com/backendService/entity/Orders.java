package com.backendService.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="Orders")
public class Orders {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "OrderId")
	private long orderId;
	
	@Column(name="ItemName")
	private String itemName;
	
	@Column(name="Price")
	private float price;
	
	@Column(name="Quantity")
	private int quantity;
	
	@Column(name="UserName")
	private String userName;

	public Orders() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Orders(long orderId, String itemName, float price, int quantity, String userName) {
		super();
		this.orderId = orderId;
		this.itemName = itemName;
		this.price = price;
		this.quantity = quantity;
		this.userName = userName;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}

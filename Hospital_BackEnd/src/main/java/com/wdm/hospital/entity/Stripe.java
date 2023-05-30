package com.wdm.hospital.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Stripe")
public class Stripe {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "NAME")
	private String name;

	@Column(name = "EMAIL", unique = true)
	private String email;
	
	@Column(name = "AMOUNT")
	private int amount;
	
	@Column(name = "STATUS")
	private String status;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	

	public Stripe() {

	}


	public Stripe(long id, String name, String email, int amount, String status) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.amount = amount;
		this.status = status;
	}
	
	

}

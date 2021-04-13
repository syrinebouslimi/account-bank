package com.bank.account.model;

import java.util.Currency;
import java.util.Locale;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String name;

    private String clientId;

    private double amount;

    private boolean allowNegativeAmount = true;


    private Currency currency = Currency.getInstance(Locale.getDefault());

    
    
	public Account() {
		super();
	}



	public Account(String id, String name, String clientId) {
		super();
		this.id = id;
		this.name = name;
		this.clientId = clientId;
		
	}
	
	 
	 
	public Account(String name, double amount) {
		super();
		this.name = name;
		this.amount = amount;
	}



	public Account(String name, String clientId) {
		super();
		this.name = name;
		this.clientId = clientId;
	}
	public Account(String id, String name,  double amount) {
		super();
		this.id = id;
		this.name = name;
		this.amount = amount;
	}



	public Account(String id, String name, boolean allowNegativeAmount,  double amount,String clientId) {
		super();
		this.id = id;
		this.name = name;
		this.clientId = clientId;
		this.amount = amount;
		this.allowNegativeAmount = allowNegativeAmount;
	}
	
	public Account(String id, boolean allowNegativeAmount,  String name,double amount) {
		super();
		this.id = id;
		this.name = name;
		this.amount = amount;
		this.allowNegativeAmount = allowNegativeAmount;
	}

	public boolean isAllowNegativeAmount() {
		return allowNegativeAmount;
	}

	public void setAllowNegativeAmount(boolean allowNegativeAmount) {
		this.allowNegativeAmount = allowNegativeAmount;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}
    
    

}

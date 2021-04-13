package com.bank.account.model;

import java.time.Instant;
import java.util.Currency;
import java.util.Locale;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection = "operation")
public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String accountId;

    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public Instant getDate() {
		return date;
	}

	public void setDate(Instant date) {
		this.date = date;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
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

    private Instant date = Instant.now();

    private String label;

    private double amount;

    private Currency currency = Currency.getInstance(Locale.getDefault());

    
	public Operation() {
		super();
	}

	public Operation(String accountId, String label,double amount) {
		super();
		this.accountId = accountId;
		this.label = label;
		this.amount = amount;
	}

	public Operation(String accountId, Instant date, String label, double amount) {
		super();
		this.accountId = accountId;
		this.date = date;
		this.label = label;
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Operation [id=" + id + ", accountId=" + accountId + ", date=" + date + ", label=" + label + ", amount="
				+ amount + ", currency=" + currency + "]";
	}
    
	
	

}

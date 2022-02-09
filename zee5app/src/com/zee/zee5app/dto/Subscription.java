package com.zee.zee5app.dto;

import com.zee.zee5app.exception.InvalidAmountException;
import com.zee.zee5app.exception.InvalidIdLengthException;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//@Data
@Setter
@Getter
@EqualsAndHashCode
@ToString
public class Subscription implements Comparable<Subscription>{
	
	public Subscription(){
		
	}

	public Subscription(String type, String dateOfPurchase, String id, String paymentMode, int amount,
			String status, String autoRenewal, String expiryDate) throws InvalidIdLengthException, InvalidAmountException {
		super();
		this.setId(id);
		this.setDateOfPurchase(dateOfPurchase);
		this.setExpiryDate(expiryDate);
		this.setAmount(amount);
		this.setPaymentMode(paymentMode);
		this.setStatus(status);
		this.setType(type);
	}

	@Setter(value = AccessLevel.NONE)
	private String id;
	private String type;
    private String dateOfPurchase;
    private String paymentMode;
    
    @Setter(value = AccessLevel.NONE)
    private int amount;
    
    private String status;
    private String autoRenewal;
    private String expiryDate;



	@Override
	public int compareTo(Subscription o) {
		// TODO Auto-generated method stub
		return this.id.compareTo(o.getId());
	}

	public void setId(String id) throws InvalidIdLengthException {
		if(id.length()<6)
			throw new InvalidIdLengthException("Id length cannot be less than 6");
		this.id = id;

	}

	public void setAmount(int amount) throws InvalidAmountException {
		if(amount < 1000)
			throw new InvalidAmountException("Amount cannot be less than 1000");
		this.amount = amount;
	}

	


}

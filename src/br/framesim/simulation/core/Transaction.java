package br.framesim.simulation.core;

import java.util.Date;

/**
 * It represents each buying or selling of stock.
 * 
 * @author Alâynne Moreira
 * @since Version 1.0
 */

public class Transaction {
	
	private int type;
	private int quantityOfShares;
	private Date date;
	private double price;
	private double totalTradingFee;

	private static final int BUYING_TRANSACTION = 0;
	private static final int SELLING_TRANSACTION = 1;
	
	private Transaction(int type, int quantityOfShares, Date date, double price,
			double totalTradingFee) {
	
		this.type = type;
		this.quantityOfShares = quantityOfShares;
		this.date = date;
		this.price = price;
		this.totalTradingFee = totalTradingFee;
	
	}
	
	public static Transaction buildBuyingTransaction(int quantityOfShares, Date date, 
			double price, double totalTradingFee) {
		
		return new Transaction(BUYING_TRANSACTION, quantityOfShares, date, price,
				totalTradingFee);
	
	}
	
	public static Transaction buildSellingTransaction(int quantityOfShares, Date date, 
			double price, double totalTradingFee) {
		
		return new Transaction(SELLING_TRANSACTION, quantityOfShares, date, price,
				totalTradingFee);
	
	}
	
	public int getQuantityOfShares() {
		return quantityOfShares;
	}

	public Date getDate() {
		return date;
	}
	
	public double getPrice() {
		return price;
	}
		
	public double getTotalTradingFee() {
		return totalTradingFee;
	}
	
	public double getTotalValue() {
		
		if (type == BUYING_TRANSACTION) {
			return (quantityOfShares * price) + totalTradingFee;
		}
		
		return (quantityOfShares * price) - totalTradingFee;
		
	}
	
	public boolean isBuyingTransaction() {
		return (type == BUYING_TRANSACTION);
	}

	public boolean isSellingTransaction() {
		return (type == SELLING_TRANSACTION);
	}
	
	public String getTypeToString() {
		
		if (type == BUYING_TRANSACTION) {
			return "Buying";
		}
		
		return "Selling";
	}

}

package br.framesim.simulation.core;

import java.util.Set;

/**
 * Market for trading stocks.
 * 
 * @author Alâynne Moreira
 * @since Version 1.0
 */

public abstract class StockMarket {

	protected Set<TradingFee> tradingFees;
	
	public StockMarket(Set<TradingFee> tradingFees) {
		this.tradingFees = tradingFees;
	}
	
	public Set<TradingFee> getTradingFees() {
		return tradingFees;
	}
	
	public void setTradingFees(Set<TradingFee> tradingFees) {
		this.tradingFees = tradingFees;
	}
	
	/**
	 * It calculates the total trading fee over the transaction whose value is specified.
	 * 
	 * @param transactionValue
	 */
	public double calculateTotalTradingFee(double transactionValue) {
		
		double totalTradingFee = 0;
		
		for (TradingFee tradingFee : tradingFees) {
			totalTradingFee += tradingFee.calculateFee(transactionValue);
		}
		
		return totalTradingFee;
		
	}
	
	/**
	 * It calculates how many shares of the specified stock can be bought, considering 
	 * the specified buying price and available value.
	 * 
	 * @param stock
	 * @param buyingPrice
	 * @param availableValue
	 */
	public abstract int calculateQuantityOfSharesForBuying(Stock stock, 
			double buyingPrice, double availableValue);
	
	public abstract String getType();
		
}

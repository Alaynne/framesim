package br.framesim.simulation.stockportfolio;

import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

import br.framesim.simulation.core.Stock;
import br.framesim.simulation.core.Price;
import br.framesim.simulation.core.Periodicity;
import br.framesim.simulation.core.Business;
import br.framesim.simulation.core.Transaction;
import br.framesim.simulation.table.Table;
import br.framesim.util.DBException;

/**
 * Stock portfolio.
 * 
 * @author Alâynne Moreira
 * @author Rodrigo Paes
 * @since Version 1.0
 */

public abstract class StockPortfolio {
	
	protected Date openingDate ;
	
	protected Date closingDate;
	
	protected List<Stock> tradedStocks;
	
	protected Hashtable<Stock, List<Transaction>> stockPatrimony;
	
	protected double moneyPatrimony;
	
	protected double initialValue;
	
	protected double finalValue;
	
	protected List<Business> businessHistory;
	
	protected String transactionPriceType;
	
	protected Periodicity periodicity;
	
	// It identifies that the type of price used in the transactions is the open price.
	public static final String TRANSACTION_OPEN_PRICE = "Open Price";
	
	// It identifies that the type of price used in the transactions is the close price.
	public static final String TRANSACTION_CLOSE_PRICE = "Close Price";

	// It identifies that the type of price used in the transactions is the middle price.
	public static final String TRANSACTION_MIDDLE_PRICE = "Middle Price";
	
	public StockPortfolio (Date openingDate) {
		this.openingDate = openingDate;
	}

	public Date getOpeningDate() {
		return openingDate;
	}
	
	public Date getClosingDate() {
		return closingDate;
	}

	public List<Stock> getTradedStocks() {
		return tradedStocks;
	}
	
	public Hashtable<Stock, List<Transaction>> getStockPatrimony() {
		return stockPatrimony;
	}
	
	public double getMoneyPatrimony() {
		return moneyPatrimony;
	}
	
	public double getInitialValue() {
		return initialValue;
	}
	
	public double getFinalValue() {
		return finalValue;
	}
	
	public List<Business> getBusinessHistory() {
		return businessHistory;
	}

	public String getTransactionPriceType() {
		return transactionPriceType;
	}
	
	public Periodicity getPeriodicity() {
		return periodicity;
	}
	
	public double getAppreciation() {
		return ((finalValue / initialValue) - 1);
	}

	public void setClosingDate(Date closingDate) {
		this.closingDate = closingDate;
	}
		
	public void setInitialValue(double initialValue) {
		this.initialValue = initialValue;
	}
	
	public void setFinalValue(double finalValue) {
		this.finalValue = finalValue;
	}
	
	public void setBusinessHistory(List<Business> businessHistory) {
		this.businessHistory = businessHistory;
	}

	public void setTransactionPriceType(String transactionPriceType) {
		this.transactionPriceType = transactionPriceType;
	}
	
	public void setPeriodicity(Periodicity periodicity) {
		this.periodicity = periodicity;
	}
	
	public boolean hasStockPatrimony() {
		
		if (stockPatrimony != null) {
			if (!stockPatrimony.isEmpty()) {
				return true;
			}
		}
		
		return false;
		
	}

	/**
	 * It checks if the portfolio has shares of the specified stock.
	 * 
	 * @param stock
	 */
	public boolean hasShares(Stock stock) {
		return stockPatrimony.containsKey(stock);
	}

	public Set<Stock> getStocks() {
		return stockPatrimony.keySet();
    }	
	
	public List<Business> getBusinessHistory(Stock stock) {
		
		List<Business> businessHistory = new ArrayList<Business>();
		
		for (Business business : this.businessHistory) {
			if (business.getStock().getCode().equals(stock.getCode())) {
				businessHistory.add(business);
			}
		}
		
		return businessHistory;
		
	}
	
	public List<Transaction> getBuyingTransactionHistory(Stock stock) {
		
		List<Transaction> buyingTransactionHistory = new ArrayList<Transaction>();
		
		for (Business business : businessHistory) {
			if (business.getStock().equals(stock)) {
				buyingTransactionHistory.add(business.getBuyingTransaction());
			}
		}
		
		if (hasShares(stock)) {
			for (Transaction buyingTransaction : stockPatrimony.get(stock)) {
				buyingTransactionHistory.add(buyingTransaction);
			}
		}
		
		return buyingTransactionHistory;
		
	}
	
	public List<Transaction> getSellingTransactionHistory(Stock stock) {
		
		List<Transaction> sellingTransactionHistory = new ArrayList<Transaction>();
		
		for (Business business : businessHistory) {
			if (business.getStock().equals(stock)) {
				sellingTransactionHistory.add(business.getSellingTransaction());
			}
		}
		
		return sellingTransactionHistory;
		
	}
	
	/**
	 * It checks if the specified stock is traded by the portfolio.
	 * 
	 * @param stock
	 */
	public boolean isTraded(Stock stock) {
		return tradedStocks.contains(stock);
	}
	
	protected double getTransactionPrice(Stock stock, Date tradingSessionDate) 
			throws DBException {

		Price price = stock.getPrice(tradingSessionDate, periodicity);

		if (price != null) {
			if (TRANSACTION_OPEN_PRICE.equals(transactionPriceType)) {
				return price.getOpenPrice();
			} else if (TRANSACTION_CLOSE_PRICE.equals(transactionPriceType)) {
				return price.getClosePrice();
			} else if (TRANSACTION_MIDDLE_PRICE.equals(transactionPriceType)) {
				return price.getMiddlePrice();
			}
		}

		return 0;

	}
	
	/**
	 * It buys shares of the specified stock, in the specified date. 
	 * 
	 * @param stock
	 * @param tradingSessionDate
	 * 
	 * @return true, if the buying transaction is executed, or false, otherwise.
	 * 
	 * @throws DBException, if a database error occurs. 
	 */
	public abstract boolean buy(Stock stock, Date tradingSessionDate) throws DBException;
	
	/**
	 * It sells shares of the specified stock, in the specified date. 
	 * 
	 * @param stock
	 * @param tradingSessionDate
	 * 
	 * @return true, if the selling transaction is executed, or false, otherwise.
	 * 
	 * @throws DBException, if a database error occurs. 
	 */
	public abstract boolean sell(Stock stock, Date tradingSessionDate) throws DBException;
	
	public abstract void close(Date closingDate) throws DBException;
	
	public abstract StockPortfolio clone();
	
	public abstract Table historyToTable(Stock stock);

}

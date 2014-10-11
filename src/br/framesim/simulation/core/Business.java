package br.framesim.simulation.core;

/**
 * A business is composed by two transactions: a buying transaction of a stock and the 
 * respective selling transaction of that stock.
 * 
 * @author Alâynne Moreira
 * @author Rodrigo Paes
 * @since Version 1.0
 */
public class Business {

	private Stock stock;
	private Transaction buyingTransaction;
	private Transaction sellingTransaction;
	
	public Business(Stock stock, Transaction buyingTransaction, 
			Transaction sellingTransaction) {
		
		this.stock = stock;
		this.buyingTransaction = buyingTransaction;
		this.sellingTransaction = sellingTransaction;
	
	}
	
	public Stock getStock() {
		return stock;
	}
	
	public Transaction getBuyingTransaction() {
		return buyingTransaction;
	}

	public Transaction getSellingTransaction() {
		return sellingTransaction;
	}

}
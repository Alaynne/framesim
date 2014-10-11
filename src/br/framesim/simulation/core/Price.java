package br.framesim.simulation.core;

import java.text.ParseException;
import java.util.Date;

import br.framesim.simulation.core.Stock;
import br.framesim.util.Util;

/**
 * Price of a stock in a market. It implements Value Object(VO) design pattern.
 * 
 * @author Alâynne Moreira
 * @author Rodrigo Paes
 * @since Version 1.0
 */

public class Price {
	
	private long id;
	private Stock stock;
	private Date tradingSessionDate;
	private double openPrice, highPrice, lowPrice, middlePrice, 
			closePrice, priceOfBestBuyingTransaction, priceOfBestSellingTransaction;
	private long quantityOfShares, totalVolume;
	private Periodicity periodicity;
		
	public Price() {
		
	}
	
	public Price(double openPrice, double highPrice, double lowPrice, double closePrice) {
		
		this.openPrice = openPrice;
		this.highPrice = highPrice;
		this.lowPrice = lowPrice;
		this.closePrice = closePrice;
		
	}
		
	public long getId() {
		return id;
	}
	
	public Stock getStock() {
		return stock;
	}

	public Date getTradingSessionDate() {
		return tradingSessionDate;
	}

	public double getOpenPrice() {
		return openPrice;
	}

	public double getHighPrice() {
		return highPrice;
	}
	
	public double getLowPrice() {
		return lowPrice;
	}
	
	public double getMiddlePrice() {
		return middlePrice;
	}
	
	public double getClosePrice() {
		return closePrice;
	}
	
	public double getPriceOfBestBuyingTransaction() {
		return priceOfBestBuyingTransaction;
	}

	public double getPriceOfBestSellingTransaction() {
		return priceOfBestSellingTransaction;
	}

	public long getQuantityOfShares() {
		return quantityOfShares;
	}
	
	public long getTotalVolume() {
		return totalVolume;
	}

	public Periodicity getPeriodicity() {
		return periodicity;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public void setTradingSessionDate(Date tradingSessionDate) {
		this.tradingSessionDate = tradingSessionDate;
	}
	
	public void setOpenPrice(double openPrice) {
		this.openPrice = openPrice;
	}

	public void setHighPrice(double highPrice) {
		this.highPrice = highPrice;
	}
	
	public void setLowPrice(double lowPrice) {
		this.lowPrice = lowPrice;
	}
	
	public void setMiddlePrice(double middlePrice) {
		this.middlePrice = middlePrice;
	}
	
	public void setClosePrice(double closePrice) {
		this.closePrice = closePrice;
	}
	
	public void setPriceOfBestBuyingTransaction(double priceOfBestBuyingTransaction) {
		this.priceOfBestBuyingTransaction = priceOfBestBuyingTransaction;
	}

	public void setPriceOfBestSellingTransaction(double priceOfBestSellingTransaction) {
		this.priceOfBestSellingTransaction = priceOfBestSellingTransaction;
	}

	public void setQuantityOfShares(long quantityOfShares) {
		this.quantityOfShares = quantityOfShares;
	}
	
	public void setTotalVolume(long totalVolume) {
		this.totalVolume = totalVolume;
	}
	
	public void setPeriodicity(Periodicity periodicity) {
		this.periodicity = periodicity;
	}
	
	/**
	 * It normalizes the price using the specified ratio.
	 * 
	 * @param ratio
	 */
	public void normalize(double ratio) {

		try {
			openPrice = Util.roundOffValue(openPrice * ratio);
			highPrice = Util.roundOffValue(highPrice * ratio);
			middlePrice = Util.roundOffValue(middlePrice * ratio);
			priceOfBestBuyingTransaction = Util.roundOffValue(priceOfBestBuyingTransaction 
					* ratio);
			priceOfBestSellingTransaction = Util.roundOffValue(priceOfBestSellingTransaction 
					* ratio);
			lowPrice = Util.roundOffValue(lowPrice * ratio);
			closePrice = Util.roundOffValue(closePrice * ratio);
		} catch (ParseException e) {}
	
	}
	
	// It checks if the price corresponds to a white candlestick.
	public boolean isWhiteCandlestick() {
		return (openPrice < closePrice);
	}

	// It checks if the price corresponds to a black candlestick.
	public boolean isBlackCandlestick(){
		return (openPrice > closePrice);
	}
	
	// It checks if the price corresponds to a DOJI candlestick.
	public boolean isDOJICandlestick() {
		return (openPrice == closePrice);
	}
		
	// It returns the real body size of the candlestick which corresponds to the price.
	public double getRealBody() {
		
		double realBody = -1;
		
		try {
			if (isWhiteCandlestick()) {
				realBody = Util.roundOffValue(closePrice - openPrice);
			} else {
				realBody = Util.roundOffValue(openPrice - closePrice);
			}
		} catch (ParseException e) {}
		
		return realBody;
		
	}
	
	// It returns the lower shadow size of the candlestick which corresponds to the price.
	public double getLowerShadow() {
		
		double lowerShadow = -1;
		
		try {
			if (isWhiteCandlestick()) {
				lowerShadow = Util.roundOffValue(openPrice - lowPrice);
			} else {
				lowerShadow = Util.roundOffValue(closePrice - lowPrice);
			}

		} catch (ParseException e) {}
		
		return lowerShadow;
		
	}
	
	// It returns the upper shadow size of the candlestick which corresponds to the price.
	public double getUpperShadow() {
		
		double upperShadow = -1;
		
		try {
			if (isWhiteCandlestick()) {
				upperShadow = Util.roundOffValue(highPrice - closePrice);
			} else {
				upperShadow = Util.roundOffValue(highPrice - openPrice);
			}
		} catch (ParseException e) {}
	
		return upperShadow;
		
	}
		
}

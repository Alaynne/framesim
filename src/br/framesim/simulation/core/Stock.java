package br.framesim.simulation.core;

import java.util.Date;

import br.framesim.dataaccess.DBManager;
import br.framesim.simulation.core.Price;
import br.framesim.util.DBException;

/**
 * Stock traded in a market. It implements Value Object(VO) design pattern.
 * 
 * @author Alâynne Moreira
 * @author Rodrigo Paes
 * @since Version 1.0
 */

public class Stock {

	private long id;
	private String code;
	private String shortName;
	private StockMarket stockMarket;
	private int roundLot;
	private int quotingFactor;
	
	public long getId() {
		return id;
	}

	public String getCode() {
		return code;
	}

	public String getShortName() {
		return shortName;
	}

	public StockMarket getStockMarket() {
		return stockMarket;
	}

	public int getRoundLot() {
		return roundLot;
	}

	public int getQuotingFactor() {
		return quotingFactor;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public void setCode(String code) {
		this.code = code.trim();
	}
	
	public void setShortName(String shortName) {
		this.shortName = shortName.trim();
	}
	
	public void setStockMarket(StockMarket stockMarket) {
		this.stockMarket = stockMarket;
	}
	
	public void setRoundLot(int roundLot) {
		this.roundLot = roundLot;
	}

	public void setQuotingFactor(int quotingFactor) {
		this.quotingFactor = quotingFactor;
	}
	
	public boolean equals(Object obj) {
		
		Stock other;
		
		if (this == obj)
			return true;
		
		if (obj == null)
			return false;
		
		if (getClass() != obj.getClass())
			return false;
		
		other = (Stock) obj;
		
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		
		if (id != other.id)
			return false;
		
		if (shortName == null) {
			if (other.shortName != null)
				return false;
		} else if (!shortName.equals(other.shortName))
			return false;
		
		if (stockMarket == null) {
			if (other.stockMarket != null)
				return false;
		} else if (!stockMarket.getType().equals(other.stockMarket.getType()))
			return false;
		
		if (roundLot != other.roundLot)
			return false;
		
		if (quotingFactor != other.quotingFactor)
			return false;
		
		return true;
	
	}
	
	public Price getPrice(Date tradingSessionDate, Periodicity periodicity) 
			throws DBException {
		
		return DBManager.getInstance().
				getPrice(code, tradingSessionDate, periodicity);
	
	}
		
}

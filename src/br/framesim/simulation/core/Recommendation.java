package br.framesim.simulation.core;

/**
 * It can be a recommendation to buy, sell, or hold a stock.
 * 
 * @author Alâynne Moreira
 * @author Rodrigo Paes
 * @since Version 1.0
 */

public class Recommendation {

	private int type;
	private String justification;

	private static final int HOLD = 0;
	private static final int BUY = 1;
	private static final int SELL = 2;
	
	private Recommendation(int type, String justification) {
		
		this.type = type;
		this.justification = justification;
		
	}

	public static Recommendation buildHoldRecommendation(String justification) {	
		return new Recommendation(HOLD, justification);
	}

	public static Recommendation buildBuyRecommendation(String justification) {
		return new Recommendation(BUY, justification);
	}

	public static Recommendation buildSellRecommendation(String justification) {
		return new Recommendation(SELL, justification);
	}
	
	public String getJustification() {
		return justification;
	}
	
	public void setJustification(String justification) {
		this.justification = justification;
	}
	
	public boolean isHoldRecommendation() {
		return (type == HOLD);
	}

	public boolean isBuyRecommendation() {
		return (type == BUY);
	}

	public boolean isSellRecommendation() {
		return (type == SELL);
	}

	public String toString() {
		
		String str;
		
		if (type == HOLD) {
			str = "Hold - ";
		} else if (type == BUY) {
			str = "Buy - ";
		} else {
			str = "Sell - ";
		}
		
		str += justification;
		
		return str;
		
	}
	
}

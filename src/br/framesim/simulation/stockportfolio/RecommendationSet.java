package br.framesim.simulation.stockportfolio;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import br.framesim.simulation.core.Stock;
import br.framesim.simulation.core.Recommendation;

/**
 * Investment recommendation set.
 * 
 * @author Alâynne Moreira
 * @author Rodrigo Paes
 * @since Version 1.0
 */

public class RecommendationSet {
	
	private Hashtable<Stock, Recommendation> recommendations; 
	
	public RecommendationSet() {
		recommendations = new Hashtable<Stock, Recommendation>();
	}
	
	public Hashtable<Stock, Recommendation> getRecommendations() {
		return recommendations;
	}
	
	public void addRecommendation(Stock stock, Recommendation recommendation) {
		recommendations.put(stock, recommendation);
	}
	
	/**
	 * It checks if the recommendation for the specified stock is a buy recommendation.
	 * 
	 * @param stock
	 */
	public boolean isBuyRecommendation(Stock stock) {
		
		Recommendation recommendation = recommendations.get(stock);
		
		if (recommendation != null){
			return recommendation.isBuyRecommendation();
		}
		
		return false;
		
	}
	
	/**
	 * It checks if the recommendation for the specified stock is a sell recommendation.
	 * 
	 * @param stock
	 */
	public boolean isSellRecommendation(Stock stock) {
		
		Recommendation recommendation = recommendations.get(stock);
		
		if (recommendation != null){
			return recommendation.isSellRecommendation();
		}
		
		return false;
		
	}

	// It checks if there is at least one buy recommendation in the recommendation set.
	public boolean hasBuyRecommendation() {
		
		for (Recommendation recommendation : recommendations.values()) {
			if (recommendation.isBuyRecommendation()) {
				return true;
			}
		}
		
		return false;
		
	}

	// It checks if there is at least one sell recommendation in the recommendation set.
	public boolean hasSellRecommendation() {
		
		for (Recommendation recommendation : recommendations.values()) {
			if (recommendation.isSellRecommendation()) {
				return true;
			}
		}
		
		return false;
		
	}
	
	// It returns all stocks which have buy recommendation.
	public List<Stock> getStocksWithBuyRecommendation() {
		
		List<Stock> stocksWithBuyRecommendation = new ArrayList<Stock>();
		
		for (Stock stock : recommendations.keySet()) {
			if (recommendations.get(stock).isBuyRecommendation()) {
				stocksWithBuyRecommendation.add(stock);
			}
		}
		
		return stocksWithBuyRecommendation;
	
	}
	
	// It returns all stocks which have sell recommendation.
	public List<Stock> getStocksWithSellRecommendation() {
		
		List<Stock> stocksWithSellRecommendation = new ArrayList<Stock>();
		
		for (Stock stock : recommendations.keySet()) {
			if (recommendations.get(stock).isSellRecommendation()) {
				stocksWithSellRecommendation.add(stock);
			}
		}
		
		return stocksWithSellRecommendation;
	
	}

}

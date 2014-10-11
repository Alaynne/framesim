package br.framesim.simulation.ta.strategy;

import java.util.Date;
import java.util.Hashtable;

import br.framesim.simulation.chart.Chart;
import br.framesim.simulation.core.Stock;
import br.framesim.simulation.core.Periodicity;
import br.framesim.simulation.stockportfolio.StockPortfolio;
import br.framesim.simulation.stockportfolio.RecommendationSet;
import br.framesim.util.DBException;

/**
 * Technical analysis strategy.
 * 
 * @author Alâynne Moreira
 * @since Version 1.0
 */

public abstract class TAStrategy {

	protected AlgorithmFactory algorithmFactory;
	
	protected Hashtable<Stock, TAAlgorithm> taAlgorithms;

	public TAAlgorithm getTAAlgorithm(Stock stock) {
		return taAlgorithms.get(stock);
	}

	public abstract RecommendationSet 
			getRecommendation(StockPortfolio stockPortfolio, Date tradingSessionDate,
					Periodicity periodicity) throws DBException;
	
	public Chart historyToChart(Stock stock) {
		
		TAAlgorithm taAlgorithm = taAlgorithms.get(stock);
		Chart chart;
		
		if (taAlgorithm == null) {
			return null;
		}
		
		if ((chart = taAlgorithm.historyToChart()) != null) {
			chart.setTitle(stock.getCode());
		}
		
		return chart;
		
	}
	
}

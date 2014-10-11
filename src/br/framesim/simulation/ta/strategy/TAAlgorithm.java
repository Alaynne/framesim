package br.framesim.simulation.ta.strategy;

import java.util.Date;
import java.util.List;

import br.framesim.simulation.chart.Chart;
import br.framesim.simulation.core.Price;
import br.framesim.simulation.core.Recommendation;
import br.framesim.simulation.ta.method.TAMethod;
import br.framesim.util.InsufficientDataException;

/**
 * Technical analysis algorithm. 
 * 
 * @author Alâynne Moreira
 * @author Rodrigo Paes
 * @since Version 1.0
 */

public abstract class TAAlgorithm {

	protected List<TAMethod> taMethods;
	
	protected List<Price> priceHistory;
	
	protected Date lastTradingSessionDate;

	public TAAlgorithm() {
		
		priceHistory = null;
		lastTradingSessionDate = null;
		
	}
	
	public Date getLastTradingSessionDate() {
		return lastTradingSessionDate;
	}

	public List<Price> getPriceHistory() {
		return priceHistory;
	}
	
	public void startup(List<Price> priceHistory) throws InsufficientDataException {
		
		if (priceHistory == null) {
			throw new InsufficientDataException("Price history not" +
				" informed.");
		} else if (priceHistory.size() < getMinimalHistorySize()) {
			throw new InsufficientDataException("Price history not " 
				+ "initiated: History size informed = " +
					priceHistory.size() + " ; minimal size"
						+ " = "	+ getMinimalHistorySize() + ".");
		} 
		
		this.priceHistory = priceHistory;
		lastTradingSessionDate = priceHistory.get(priceHistory.size() - 1).
				getTradingSessionDate();
		finishStartup();

	}

	// It checks if the algorithm is initialized.
	public boolean isInitialized() {
		return priceHistory != null;
	}

	public abstract Chart historyToChart();
	
	public abstract int getMinimalHistorySize();
	
	protected abstract void finishStartup();
	
	protected abstract void update(Price newPrice);

	public abstract Recommendation getRecommendation(Price newPrice, 
			boolean isLongPosition);
	
}

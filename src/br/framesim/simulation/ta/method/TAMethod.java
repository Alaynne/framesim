package br.framesim.simulation.ta.method;

import br.framesim.simulation.core.MarketSignal;

/**
 * It defines an interface for a technical analysis method.
 * 
 * @author Alâynne Moreira
 * @since Version 1.0
 */

public interface TAMethod {

	public MarketSignal getMarketSignal();
	
	// It returns the necessary number of prices for capturing a market signal.
	public int getNecessaryNumOfPrices();
	
}

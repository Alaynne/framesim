package br.framesim.simulation.simulator;

import br.framesim.simulation.stockportfolio.StockPortfolio;
import br.framesim.simulation.ta.strategy.TAStrategy;

/**
 * Simulation element.
 * 
 * @author Alâynne Moreira
 * @since Version 1.0
 */

public class SimulationElement {
	
	private TAStrategy taStrategy;
	
	private StockPortfolio stockPortfolio;
	
	// Number of times that the strategy gave investment recommendations.
	private int seriesSize;
	
	// Number of times in long position.
	private int longPosition;
	
	// Number of times in short position.
	private int shortPosition;
	
	public SimulationElement(TAStrategy taStrategy, StockPortfolio stockPortfolio) {
		
		this.taStrategy = taStrategy;
		this.stockPortfolio = stockPortfolio;
		seriesSize = 0;
		longPosition = 0;
		shortPosition = 0;
		
	}

	public TAStrategy getTAStrategy() {
		return taStrategy;
	}
	
	public StockPortfolio getStockPortfolio() {
		return stockPortfolio;
	}
	
	public int getSeriesSize() {
		return seriesSize;
	}
	
	public int getLongPosition() {
		return longPosition;
	}
	
	public int getShortPosition() {
		return shortPosition;
	}
	
	public void setSeriesSize(int seriesSize) {
		this.seriesSize = seriesSize;
	}
	
	public void setLongPosition(int longPosition) {
		this.longPosition = longPosition;
	}
		
	public void setShortPosition(int shortPosition) {
		this.shortPosition = shortPosition;
	}
	
	public void increaseSeriesSize() {
		seriesSize++;
	}
	
	public void increaseLongPosition() {
		longPosition++;
	}
	
	public void increaseShortPosition() {
		shortPosition++;
	}
	
}

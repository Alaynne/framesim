package br.framesim.simulation.core;

/**
 * It defines an interface for a trading fee.
 * 
 * @author Alâynne Moreira
 * @since Version 1.0
 */

public interface TradingFee {

	public double calculateFee(double transactionValue);
	
	public String getType();
	
}

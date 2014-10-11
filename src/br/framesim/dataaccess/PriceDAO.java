package br.framesim.dataaccess;

import java.util.List;

import br.framesim.simulation.core.Price;
import br.framesim.simulation.core.Periodicity;
import br.framesim.util.DBException;

/**
 * It defines an interface for the direct access to the database to retrieve the 
 * persistent data of stock prices. It implements DAO (Data Access Object) design pattern.
 * 
 * @author Alâynne Moreira
 * @since Version 1.0
 */

public interface PriceDAO {

	public boolean createPrice(Price price) throws DBException;
	
	/**
	 * It returns all prices, in the specified periodicity, for the stock whose code is
	 * specified.
	 * 
	 * @param stockCode
	 * @param periodicity
	 * 
	 * @throws DBException, if a database error occurs.
	 */
	public List<Price> getAllPrices(String stockCode, 
					Periodicity periodicity) throws DBException;
	
	/**
	 * It returns all prices for the stock whose code is specified, in the specified
	 * timeframe and periodicity.
	 * 
	 * @param stockCode
	 * @param startingDate
	 * @param endingDate
	 * @param periodicity
	 * 
	 * @throws DBException, if a database error occurs.
	 */
	public List<Price> getAllPrices(String stockCode,
			java.util.Date startingDate, java.util.Date endingDate, 
					Periodicity periodicity) throws DBException;
	
	public Price getPrice(String stockCode, java.util.Date date,
			Periodicity periodicity) throws DBException;
	
	public void updatePrice(Price price) throws DBException;
	
	public void deleteAllPrices() throws DBException;
	
	/**
	 * It checks if there was trading session in the specified date and periodicity.
	 * 
	 * @param date
	 * @param periodicity
	 * 
	 * @throws DBException, if a database error occurs.
	 */
	public boolean hasTradingSession(java.util.Date date, 
			Periodicity periodicity) throws DBException;
	
}

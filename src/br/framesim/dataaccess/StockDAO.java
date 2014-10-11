package br.framesim.dataaccess;

import java.util.List;

import br.framesim.simulation.core.StockMarket;
import br.framesim.simulation.core.Stock;
import br.framesim.util.DBException;

/**
 * It defines an interface for the direct access to the database to retrieve the 
 * persistent data of stocks. It implements DAO (Data Access Object) design pattern.
 * 
 * @author Alâynne Moreira
 * @since Version 1.0
 */

public interface StockDAO {

	public void createStock(Stock stock) throws DBException;
	
	public long findStockId(String stockCode) throws DBException;

	public Stock getStock(String stockCode) throws DBException;
	
	public List<String> getAllStockCodes() throws DBException;
	
	/**
	 * It returns all stock codes which are traded in the specified stock market.
	 * 
	 * @param stockMarket
	 * 
	 * @throws DBException, if a database error occurs. 
	 */
	public List<String> getAllStockCodes(StockMarket stockMarket) 
			throws DBException;
	
	public void updateStock(Stock stock) throws DBException;
	
	public void deleteAllStocks() throws DBException;
	
}

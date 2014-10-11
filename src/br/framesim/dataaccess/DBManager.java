package br.framesim.dataaccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import org.apache.log4j.Logger;

import br.framesim.simulation.core.StockMarket;
import br.framesim.simulation.core.Stock;
import br.framesim.simulation.core.Price;
import br.framesim.simulation.core.Periodicity;
import br.framesim.util.DBException;

/**
 * Database Manager (DB manager) is a facade that intermediates all access to the 
 * necessary persistent data. It implements Singleton and Facade design patterns.
 * 
 * @author Alâynne Moreira
 * @author Rodrigo Paes
 * @since Version 1.0
 */

public class DBManager {
	
	// Logger for this class.
	private static final Logger logger = Logger.getLogger(DBManager.class);

	private static DBManager singletonInstance = null;

	private Connection connection;
	private StockDAO stockDAO;
	private PriceDAO priceDAO;
	
	private DBManager() {

		connection = null;
		stockDAO = null;
		priceDAO = null;
		
	}

	// It implements Singleton design pattern.
	public static DBManager getInstance() {
		
		if (singletonInstance == null) {
			singletonInstance = new DBManager();
		}
		
		return singletonInstance;
	
	}

	/**
	 * It connects the database.
	 * 
	 * @param jdbcDriver
	 * @param url
	 * @param username
	 * @param password
	 * 
	 * @throws DBException, if a connection error occurs.
	 */
	public void connect(String jdbcDriver, String url, String username, 
			String password) throws DBException {
		
		try {
			Class.forName(jdbcDriver);
			connection = DriverManager.getConnection(url, username, password);
			if (logger.isInfoEnabled()) {
				logger.
					info("connect(String, String, String, String) - " +
						"Using " + url + ".");
			}
		} catch (Exception e) {
			logger.error("connect(String, String, String, String)", e);
			throw new DBException(e.getMessage());
		}
		
	}
	
	// It checks if any database is connected.
	public boolean isConnected() {
		return (connection != null);
	}

	// It returns the database connection.
	public Connection getConnection() {
		return connection;
	}
	
	public void setStockDAO (StockDAO stockDAO) { 
		this.stockDAO = stockDAO;
	}

	public void setPriceDAO (PriceDAO priceDAO) { 
		this.priceDAO = priceDAO;
	}
	
	public void createStock(Stock stock) throws DBException {
		
		stockDAO.createStock(stock);
		
	}

	public long findStockId(String stockCode) throws DBException {
		
		return stockDAO.findStockId(stockCode);
		
	}
		
	public boolean createPrice(Price price) throws DBException {
		
		return priceDAO.createPrice(price);
		
	}
	
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
					Periodicity periodicity) throws DBException {
		
		return priceDAO.getAllPrices(stockCode, periodicity);
	
	}

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
					Periodicity periodicity) throws DBException {
		
		return priceDAO.getAllPrices(stockCode, startingDate, endingDate, 
				periodicity);
	
	}

	public Price getPrice(String stockCode, java.util.Date date,
			Periodicity periodicity) throws DBException {
		
		return priceDAO.getPrice(stockCode, date, periodicity);
	
	}

	public Stock getStock(String stockCode) throws DBException {
		
		return stockDAO.getStock(stockCode);
		
	}

	public List<String> getAllStockCodes() throws DBException {
		
		return stockDAO.getAllStockCodes();
	
	}
	
	/**
	 * It returns all stock codes which are traded in the specified stock market.
	 * 
	 * @param stockMarket
	 * 
	 * @throws DBException, if a database error occurs. 
	 */
	public List<String> getAllStockCodes(StockMarket stockMarket) 
			throws DBException {

		return stockDAO.getAllStockCodes(stockMarket);
		
	}
	
	public void updateStock(Stock stock) throws DBException {
				
		stockDAO.updateStock(stock);
		
	}

	public void updatePrice(Price price) throws DBException {
		
		priceDAO.updatePrice(price);
		
	}
	
	public void deleteAllStocks() throws DBException {
		
		stockDAO.deleteAllStocks();
		
	}

	public void deleteAllPrices() throws DBException {

		priceDAO.deleteAllPrices();
		
	}

	/**
	 * It checks if there was trading session in the specified date and periodicity.
	 * 
	 * @param date
	 * @param periodicity
	 * 
	 * @throws DBException, if a database error occurs.
	 */
	public boolean hasTradingSession(java.util.Date date, 
			Periodicity periodicity) throws DBException {
		
		return priceDAO.hasTradingSession(date, periodicity);
	
	}

}

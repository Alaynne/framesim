package br.framesim.simulation.simulator;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import br.framesim.dataaccess.DBManager;
import br.framesim.simulation.chart.Chart;
import br.framesim.simulation.core.Stock;
import br.framesim.simulation.core.Periodicity;
import br.framesim.simulation.core.Transaction;
import br.framesim.simulation.performancemeasurement.PerformanceMeasurement;
import br.framesim.simulation.stockportfolio.StockPortfolio;
import br.framesim.simulation.stockportfolio.RecommendationSet;
import br.framesim.simulation.ta.strategy.TAStrategy;
import br.framesim.simulation.table.Table;
import br.framesim.util.DBException;
import br.framesim.util.Util;

/**
 * Simulator of technical analysis strategies.
 *  
 * @author Alâynne Moreira
 * @author Rodrigo Paes
 * @since Version 1.0
 */

public class Simulator {

	private Date startingDate;
	private Date endingDate;
	private List<SimulationElement> simulationElements;
	private int numberOfTradingSessions;
	private List<PerformanceMeasurement> performanceMeasurements;
	private Periodicity periodicity;
		
	public Simulator(Date startingDate, Date endingDate, List<TAStrategy> taStrategies,
			StockPortfolio stockPortfolio, List<PerformanceMeasurement> 
			performanceMeasurements, Periodicity periodicity) {
		
		this.startingDate = startingDate;
		this.endingDate = endingDate;
		numberOfTradingSessions = 0;
		this.performanceMeasurements = performanceMeasurements;
		this.periodicity = periodicity;
		buildSimulationElements(taStrategies, stockPortfolio);
		
	}
	
	private void buildSimulationElements(List<TAStrategy> taStrategies, 
			StockPortfolio stockPortfolio) {
		
		SimulationElement simulationElement;
		StockPortfolio stockPortfolioClone;
		
		simulationElements = new ArrayList<SimulationElement>();
		
		for (TAStrategy taStrategy : taStrategies) {
			stockPortfolioClone = stockPortfolio.clone();
			stockPortfolioClone.setPeriodicity(periodicity);
			simulationElement = new SimulationElement(taStrategy, stockPortfolioClone);
			simulationElements.add(simulationElement);
		}
		
	}
	
	private void updateSimulationElement(SimulationElement simulationElement, 
			RecommendationSet recommendationSet, Date tradingSessionDate)
				throws DBException {

		StockPortfolio stockPortfolio = simulationElement.getStockPortfolio();
		List<Stock> stocksForBuying, stocksForSelling;

		if (recommendationSet.hasSellRecommendation()) {
			stocksForSelling = recommendationSet.getStocksWithSellRecommendation();
			for (Stock stock : stocksForSelling) {
				stockPortfolio.sell(stock, tradingSessionDate);			
			}
		}

		if (recommendationSet.hasBuyRecommendation()) {
			stocksForBuying = recommendationSet.getStocksWithBuyRecommendation();
			for (Stock stock : stocksForBuying) {
				stockPortfolio.buy(stock, tradingSessionDate);
			}
		}

		simulationElement.increaseSeriesSize();
		if (stockPortfolio.hasStockPatrimony()) {
			simulationElement.increaseLongPosition();
		}

	}
	
	private String buildSimulationDescription() {
		
		GregorianCalendar calendar = new GregorianCalendar();
		
		return "Simulation executed on: " + Util.formatDate(calendar.getTime()) +
			"\nSimulation Timeframe: From " + Util.formatDate(startingDate) + 
				" Until " + Util.formatDate(endingDate) + "\nNumber of Simulated Trading "
				+ "Sessions: " + numberOfTradingSessions + " Trading Sessions";
		
	}
	
	private List<PerformanceMeasurement> clonePerformanceMeasurements() {
		
		List<PerformanceMeasurement> cloneOfPerformanceMeasurements = 
				new ArrayList<PerformanceMeasurement>();
		
		for (PerformanceMeasurement performanceMeasurement: performanceMeasurements) {
			try {
				cloneOfPerformanceMeasurements.add(performanceMeasurement.getClass().
						newInstance());
			} catch (Exception e) {}
		}
		
		return cloneOfPerformanceMeasurements;
		
	}
	
	private void concludeSimulation(GregorianCalendar lastTradingSessionDate) 
			throws DBException {
		
		if (numberOfTradingSessions != 0) {
			for (SimulationElement simulationElement : simulationElements) {
				simulationElement.getStockPortfolio().
						close(lastTradingSessionDate.getTime());
			}
			numberOfTradingSessions++;
		} else {
			for (SimulationElement simulationElement : simulationElements) {
				simulationElement.getStockPortfolio().close(endingDate);
			}
		}
		
	}
	
	private SimulationResult buildSimulationResult() {
		
		SimulationResult simulationResult = new SimulationResult();
		String simulationDescription;
		MeasurementReport measurementReport;
		Chart chart;
		List<Transaction> buyingTransactions, sellingTransactions;
		Table table;
		
		simulationDescription = buildSimulationDescription();
		simulationResult.setSimulationDescription(simulationDescription);
		
		for (SimulationElement simulationElement : simulationElements) {		
			measurementReport = MeasurementReport.
					buildMeasurementReport(clonePerformanceMeasurements(), 
							simulationElement);
			simulationResult.
					addMeasurementReport(simulationElement, measurementReport);
			for (Stock stock : simulationElement.getStockPortfolio().getTradedStocks()) {
				chart = simulationElement.getTAStrategy().historyToChart(stock);
				buyingTransactions = simulationElement.getStockPortfolio().
						getBuyingTransactionHistory(stock);
				sellingTransactions = simulationElement.getStockPortfolio().
						getSellingTransactionHistory(stock);
				if (chart != null ) {
					if (buyingTransactions != null) {
						chart.addBuyingTransactions(buyingTransactions);
					}
					if (sellingTransactions != null) {
						chart.addSellingTransactions(sellingTransactions);
					}	
					simulationResult.addChart(simulationElement, chart);
				
					table = simulationElement.getStockPortfolio().historyToTable(stock);
					simulationResult.addTable(simulationElement,table);
				}				
			}
		}
		
		return simulationResult;
		
	}
	
	/**
	 * It simulates the strategies.
	 * 
	 * @return the simulation result.
	 * 
	 * @throws DBException, if a database error occurs.
	 */
	public SimulationResult simulate() throws DBException {
		
		GregorianCalendar currentTradingSessionDate = new GregorianCalendar();
		GregorianCalendar lastTradingSessionDate = new GregorianCalendar();
		TAStrategy taStrategy;
		StockPortfolio stockPortfolio;
		RecommendationSet recommendationSet;
		
		lastTradingSessionDate.setTime(endingDate);
		while ((!DBManager.getInstance().
				hasTradingSession(lastTradingSessionDate.getTime(), periodicity)) && 
						(lastTradingSessionDate.getTime().after(startingDate))) {
			lastTradingSessionDate.setTime(periodicity.
					getPreviousDate(lastTradingSessionDate.getTime()));
		}
		
		currentTradingSessionDate.setTime(startingDate);

		while (currentTradingSessionDate.getTime().before(lastTradingSessionDate.
				getTime())) {
			
			if(DBManager.getInstance().hasTradingSession(currentTradingSessionDate.
					getTime(), periodicity)){
				
				for (SimulationElement simulationElement : simulationElements) {
					taStrategy = simulationElement.getTAStrategy();
					stockPortfolio = simulationElement.getStockPortfolio();
					
					recommendationSet = taStrategy.
						getRecommendation(stockPortfolio, currentTradingSessionDate.
								getTime(), periodicity);
					updateSimulationElement(simulationElement, recommendationSet,
							currentTradingSessionDate.getTime());
				}
				numberOfTradingSessions++;
			}
			currentTradingSessionDate.setTime(periodicity.
					getLaterDate(currentTradingSessionDate.getTime()));
		}
		
		concludeSimulation(lastTradingSessionDate);
		
		return buildSimulationResult();
		
	}
	
	public Date getStartingDate() {
		return startingDate;
	}

	public Date getEndingDate() {
		return endingDate;
	}
	
	public List<SimulationElement> getSimulationElements() {
		return simulationElements;
	}
	
	public int getNumberOfTradingSessions() {
		return numberOfTradingSessions;
	}
	
	public List<PerformanceMeasurement> getPerformanceMeasurements() {
		return performanceMeasurements;
	}
	
}

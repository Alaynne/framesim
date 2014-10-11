package br.framesim.simulation.chart;

import java.util.Collection;

import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import br.framesim.simulation.core.Transaction;

/**
 * Chart for the presentation of results.
 * 
 * @author Alâynne Moreira
 * @since Version 1.0
 */

public abstract class Chart {

	protected String title;
	
	protected JFreeChart chart;
	
	protected ChartPanel panel;
	
	public Chart(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}
	
	public JPanel getPanel() {
		return panel;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	protected abstract void createPanel();
	
	/**
	 * It adds the specified buying transactions to the chart.
	 * 
	 * @param buyingTransactions
	 */
	public abstract void addBuyingTransactions(Collection<Transaction> buyingTransactions);

	/**
	 * It adds the specified selling transactions to the chart.
	 * 
	 * @param sellingTransactions
	 */
	public abstract void addSellingTransactions(Collection<Transaction> sellingTransactions);
	
}

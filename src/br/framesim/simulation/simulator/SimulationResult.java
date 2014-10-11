package br.framesim.simulation.simulator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.framesim.simulation.chart.Chart;
import br.framesim.simulation.table.Table;

/**
 * The simulation result contains, for each simulation element, a measurement report, and
 * some charts and tables to describe the performance of the strategy associated with that
 * element.
 * 
 * @author Alâynne Moreira
 * @author Rodrigo Paes
 * @since Version 1.0
 */

public class SimulationResult {

	private String simulationDescription;
	private List<SimulationElement> simulationElements;
	private List<MeasurementReport> measurementReports; 
	private HashMap<SimulationElement,List<Chart>> charts;
	private HashMap<SimulationElement,List<Table>> tables;

	private int size;
	
	public SimulationResult() {
		
		simulationElements = new ArrayList<SimulationElement>();
		measurementReports = new ArrayList<MeasurementReport>();
		charts = new HashMap<SimulationElement, List<Chart>>();
		tables = new HashMap<SimulationElement,List<Table>>();
		size = 0;
		
	}
	
	public void setSimulationDescription(String simulationDescription) {
		this.simulationDescription = simulationDescription;	
	}
	
	public void addMeasurementReport(SimulationElement simulationElement, 
			MeasurementReport measurementReport) {
		
		simulationElements.add(simulationElement);
		measurementReports.add(measurementReport);
		charts.put(simulationElement, new ArrayList<Chart>());
		tables.put(simulationElement, new ArrayList<Table>());
		size++;
	
	}
	
	public void addChart(SimulationElement simulationElement, Chart chart) {
		
		List<Chart> elementCharts = this.charts.get(simulationElement);
		
		if (elementCharts != null) {
			elementCharts.add(chart);
			this.charts.put(simulationElement, elementCharts);
		}
		
	}

	public void addTable(SimulationElement simulationElement, Table table) {
		
		List<Table> elementTables = this.tables.get(simulationElement);
		
		if (elementTables != null) {
			elementTables.add(table);
			this.tables.put(simulationElement, elementTables);
		}
		
	}

	public String getSimulationDescription() {
		return simulationDescription;
	}
	
	public List<SimulationElement> getSimulationElements() {
		return simulationElements;
	}
	
	public List<MeasurementReport> getMeasurementReports() {
		return measurementReports;
	}
	
	public HashMap<SimulationElement,List<Chart>> getCharts() {
		return charts;
	}
	
	public HashMap<SimulationElement,List<Table>> getTables() {
		return tables;
	}
	
	public int getSize() {
		return size;
	}

}

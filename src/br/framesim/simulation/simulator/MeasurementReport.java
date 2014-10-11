package br.framesim.simulation.simulator;

import java.util.List;

import br.framesim.simulation.performancemeasurement.PerformanceMeasurement;

/**
 * Performance measurement report of a simulated investment strategy.
 * 
 * @author Alâynne Moreira
 * @author Rodrigo Paes
 * @since Version 1.0
 */

public class MeasurementReport {

	private List<PerformanceMeasurement> performanceMeasurements;
	
	private SimulationElement simulationElement;

	private MeasurementReport(List<PerformanceMeasurement> 
			performanceMeasurements, SimulationElement simulationElement) {
		
		this.performanceMeasurements = performanceMeasurements;
		this.simulationElement = simulationElement;
		
	}
	
	public static MeasurementReport 
			buildMeasurementReport(List<PerformanceMeasurement> 
			performanceMeasurements, SimulationElement simulationElement) {
		
		MeasurementReport report = 
				new MeasurementReport(performanceMeasurements, 
						simulationElement);
		
		for (PerformanceMeasurement measurement: report.
				getPerformanceMeasurements()) {
			measurement.calculateResult(report.getSimulationElement());
		}
		
		return report;
		
	}
	
	public List<PerformanceMeasurement> getPerformanceMeasurements() {
		return performanceMeasurements;
	}

	public SimulationElement getSimulationElement() {
		return simulationElement;
	}
	
}

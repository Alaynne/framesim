package br.framesim.simulation.performancemeasurement;

import br.framesim.simulation.simulator.SimulationElement;

/**
 * It defines an interface for a performance measurement.
 * 
 * @author Alâynne Moreira
 * @since Version 1.0
 */

public interface PerformanceMeasurement {

	public void calculateResult(SimulationElement simulationElement);
	
	// It checks if the result of the performance measurement could be calculated.
	public boolean hasResult();
	
	public String getName();
	
	public Object getResult();

	public String formatResult();
	
}

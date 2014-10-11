package br.framesim.simulation.ta.strategy;

/**
 * Interface for an algorithm factory. It implements Factory design pattern.
 * 
 * @author Alâynne Moreira
 * @author Rodrigo Paes
 * @since Version 1.0
 */

public interface AlgorithmFactory {

	//	It is a factory method.
	public TAAlgorithm buildAlgorithm();

}

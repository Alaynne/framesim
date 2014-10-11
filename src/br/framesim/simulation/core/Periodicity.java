package br.framesim.simulation.core;

import java.util.Date;

/**
 * It defines an interface for a price periodicity (trading session periodicity).
 * 
 * @author Alâynne Moreira
 * @since Version 1.0
 */

public interface Periodicity {

	public Date getPreviousDate(Date date); 

	public Date getLaterDate(Date date); 
	
	public String getType();
	
}

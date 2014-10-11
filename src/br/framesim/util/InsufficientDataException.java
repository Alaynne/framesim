package br.framesim.util;

/**
 * For insufficient data.
 * 
 * @author Rodrigo Paes
 * @since Version 1.0
 */

@SuppressWarnings("serial")
public class InsufficientDataException extends Exception {

	public InsufficientDataException(String msg) {
		super(msg);
	}
	
}

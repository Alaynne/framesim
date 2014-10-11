package br.framesim.util;

/**
 * For database errors.
 * 
 * @author Al�ynne Moreira
 * @since Version 1.0
 */

@SuppressWarnings("serial")
public class DBException extends Exception {

	public DBException(String msg) {
		super(msg);
	}

}

package br.framesim.simulation.table;

import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 * Table for the presentation of results. 
 * 
 * @author Alâynne Moreira
 * @since Version 1.0
 */

public abstract class Table {

	protected JTable table;
	
	protected JScrollPane pane;
			
	public JScrollPane getPane() {
		return pane;
	}
		
	protected abstract void buildPane();
	
}

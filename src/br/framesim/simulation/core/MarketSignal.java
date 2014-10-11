package br.framesim.simulation.core;

/**
 * It can be an uptrend alert, a downtrend alert, an uptrend signal, or a downtrend 
 * signal. Each market signal can have a strength (very weak, weak, medium, strong, or 
 * very strong).
 * 
 * @author Alâynne Moreira
 * @since Version 1.0
 */

public class MarketSignal {
	
	private static final String UPTREND_ALERT = "Uptrend Alert";
	private static final String UPTREND_SIGNAL = "Uptrend Signal";
	private static final String DOWNTREND_ALERT = "Downtrend Alert";
	private static final String DOWNTREND_SIGNAL = "Downtrend Signal";
	
	// It identifies a market signal without strength.
	public static final int NO_STRENGTH = 0;
	
	public static final int VERY_WEAK = 1;
	
	public static final int WEAK = 2;
	
	public static final int MEDIUM = 3;
	
	public static final int STRONG = 4;
	
	public static final int VERY_STRONG = 5;
	
	private String type;
	private int strength;
	private String justification;

	private MarketSignal(String type, int strength, String justification) {
		
		this.type = type;
		this.strength = strength;
		this.justification = justification;
		
	}
		
	public static MarketSignal buildUptrendAlert(int strength, String justification) {
		
		return new MarketSignal(UPTREND_ALERT, strength, justification); 
		
	}

	public static MarketSignal buildUptrendAlert(String justification) {
		
		return new MarketSignal(UPTREND_ALERT, NO_STRENGTH, justification); 
		
	}
	
	public static MarketSignal buildUptrendSignal(int strength, String justification) {
		
		return new MarketSignal(UPTREND_SIGNAL, strength, justification); 
		
	}

	public static MarketSignal buildUptrendSignal(String justification) {
		
		return new MarketSignal(UPTREND_SIGNAL, NO_STRENGTH, justification); 
		
	}
	
	public static MarketSignal buildDowntrendAlert(int strength, String justification) {
		
		return new MarketSignal(DOWNTREND_ALERT, strength, justification); 
		
	}

	public static MarketSignal buildDowntrendAlert(String justification) {
		
		return new MarketSignal(DOWNTREND_ALERT, NO_STRENGTH, justification); 
		
	}
	
	public static MarketSignal buildDowntrendSignal(int strength, String justification) {
		
		return new MarketSignal(DOWNTREND_SIGNAL, strength, justification); 
		
	}
	
	public static MarketSignal buildDowntrendSignal(String justification) {
		
		return new MarketSignal(DOWNTREND_SIGNAL, NO_STRENGTH, justification); 
		
	}
	
	public int getStrength() {
		return strength;
	}
	
	public String getJustification() {
		return justification;
	}
	
	public void setStrength(int strength) {
		this.strength = strength;
	}

	public void setJustification(String justification) {
		this.justification = justification;
	}
	
	public boolean isUptrendAlert() {
		return (UPTREND_ALERT.equals(type));
	}

	public boolean isUptrendSignal() {
		return (UPTREND_SIGNAL.equals(type));
	}
	
	public boolean isDowntrendAlert() {
		return (DOWNTREND_ALERT.equals(type));
	}
	
	public boolean isDowntrendSignal() {
		return (DOWNTREND_SIGNAL.equals(type));
	}
	
	/**
	 * It checks if the market signal has strength lower than the specified strength.
	 * 
	 * @param otherStrength
	 */
	public boolean hasStrengthLowerThan(int otherStrength) {
		return (strength < otherStrength);
	}
	
	public void increaseStrength() {
		
		if (strength < MarketSignal.VERY_STRONG) {
			strength++;
		}
		
	}
	
	public String strengthToString() {
		
		if (strength == VERY_WEAK) {
			return "VERY WEAK";
		} else if (strength == WEAK) {
			return "WEAK";
		} else if (strength == MEDIUM) {
			return "MEDIUM";
		} else if (strength == STRONG) {
			return "STRONG";
		} else if (strength == VERY_STRONG) {
			return "VERY STRONG";
		} else {
			return "";
		}
		
	}
	
	public String toString() {
	
		if (strength == NO_STRENGTH) {
			return type + ": " + justification;
		}
		
		return type + " - Strength: " + strengthToString() + ": " +
				justification;
		
	}

}

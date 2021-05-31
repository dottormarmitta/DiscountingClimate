package com.guglielmoDelSarto.general.time;

/**
 * This interface is intend to store as doubles the subdivision 
 * of the time interval [t, T] in n periods.
 * 
 * @since Version 1.0
 * @author Guglielmo Del Sarto
 */
public interface TenorDiscretization {
	
	/**
	 * Return the length of the period [t, T]
	 * 
	 * @return timeLenmgth the difference t - T
	 */
	public double getTotalLength();
	
	/**
	 * Return the time corresponding to the tenor index for the structure
	 * of the kind
	 * <br>
	 * [t = t<sub>0</sub>, t<sub>1</sub>, ..., t<sub>n-1</sub> = T]
	 * 
	 * @param tenorIndex the index i
	 * @return t<sub>i</sub> the corresponding time
	 */
	public double getTime(int tenorIndex);
	
	/**
	 * Return the time step between the two tenor indexes specified
	 * for the structure of the kind
	 * <br>
	 * [t = t<sub>0</sub>, t<sub>1</sub>, ..., t<sub>n-1</sub> = T]
	 * 
	 * @param initialIndex the index i
	 * @param finalIndex the index j
	 * @return t<sub>j-i</sub> the corresponding time step
	 */
	public double getTimeStep(int initialIndex, int finalIndex);
	
	/**
	 * Return the time step between the specified time and its
	 * preceding time, for the structure of the kind:
	 * <br>
	 * [t = t<sub>0</sub>, t<sub>1</sub>, ..., t<sub>n-1</sub> = T]
	 * 
	 * @param finalIndex the time index
	 * @return t<sub>i-(i-1)</sub> the time step
	 */
	public default double getTimeStep(int finalIndex) {
		if (finalIndex == 0) {
			throw new IllegalArgumentException("Index must be positive in this case.");
		}
		return getTimeStep(finalIndex - 1, finalIndex);
	}
	
	/**
	 * Return the length of the tenor discretization, which is n
	 * for the structure of the kind
	 * <br>
	 * [t = t<sub>0</sub>, t<sub>1</sub>, ..., t<sub>n-1</sub> = T]
	 * 
	 * @return n the length of tenor array
	 */
	public int getLength();

}

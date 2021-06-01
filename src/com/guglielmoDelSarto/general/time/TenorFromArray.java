package com.guglielmoDelSarto.general.time;

/**
 * Class representing TenorDiscretization starting from an array of double
 * 
 * @since Version 1.0
 * @author Guglielmo Del Sarto
 */
public class TenorFromArray implements TenorDiscretization {
	
	private final double[] tenor;
	private final int n;
	
	/**
	 * Create a tenor structure, starting from an array containing:
	 * <br>
	 * [t = t<sub>0</sub>, t<sub>1</sub>, ..., t<sub>n-1</sub> = T]
	 * 
	 * @param times the array of time subdivisions
	 */
	public TenorFromArray(double[] times) {
		this.tenor = times;
		this.n = times.length;
	}

	@Override
	public double getTotalLength() {
		return tenor[n-1] - tenor[0];
	}

	@Override
	public double getTime(int tenorIndex) {
		return tenor[tenorIndex];
	}

	@Override
	public double getTimeStep(int initialIndex, int finalIndex) {
		return tenor[finalIndex] - tenor[initialIndex];
	}
	
	@Override
	public double getLastTime() {
		return tenor[tenor.length - 1];
	}

	@Override
	public int getLength() {
		return n;
	}

}

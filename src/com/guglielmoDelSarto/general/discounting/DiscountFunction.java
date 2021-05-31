package com.guglielmoDelSarto.general.discounting;

import com.guglielmoDelSarto.general.time.TenorDiscretization;
import com.guglielmoDelSarto.general.time.TenorFromTimes;

/**
 * Discount function to be specified by implementing classes
 * 
 * @since Version 1.0
 * @author Guglielmo Del Sarto
 */
public interface DiscountFunction {
	
	/**
	 * Set the relevant tenor for the discount function
	 * 
	 * @param tenor the object representing the tenor
	 */
	public void setTenor(TenorDiscretization tenor);
	
	/**
	 * Set the relevant tenor discretization of [t, T]
	 * for the discount function
	 * 
	 * @param initialTime the initial time t of the interval
	 * @param finalTime the final time T of the interval
	 * @param n the number of total time steps
	 */
	public default void setTenor(double initialTime, double finalTime, int n) {
		setTenor(new TenorFromTimes(initialTime, finalTime, n));
	}
	
	/**
	 * Return the discounted value
	 * 
	 * @param value the value to be discounted
	 * @param tenorIndex the tenorIndex
	 * @return discountedValue the discounted value
	 */
	public double getDiscountedValue(double value, int tenorIndex);
	
	/**
	 * Return the discounted value function derivative
	 * evaluated in the relevant point
	 * 
	 * @param value the valuation point
	 * @param tenorIndex the tenorIndex
	 * @return derivative the derivative of df on value
	 */
	public double getDiscountedValueDerivative(double value, int tenorIndex);

}

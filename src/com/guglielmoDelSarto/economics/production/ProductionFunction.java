package com.guglielmoDelSarto.economics.production;

/**
 * This interface is intended to represent functions which
 * allow the agents to transfer their wealth:
 * <br>
 * p : w &rarr; c <sub>T</sub>
 * 
 * @since Version 1.0
 * @author Guglielmo Del Sarto
 */
public interface ProductionFunction {
	
	/**
	 * Get production c <sub>T</sub> starting from an investment
	 * made at time t
	 * 
	 * @param investment the allocated wealth
	 * @return c <sub>T</sub> the produced good
	 */
	public double getProduction(double investment);
	
	/**
	 * Calculates the first derivative of production function
	 * in the requested point
	 * 
	 * @param investment the evaluation point
	 * @return derivative the first derivative of production function
	 */
	public double getFirstDerivative(double investment);
	
	/**
	 * Calculates the second derivative of production function
	 * in the requested point
	 * 
	 * @param investment the evaluation point
	 * @return derivative the second derivative of production function
	 */
	public double getSecondDerivative(double investment);

}

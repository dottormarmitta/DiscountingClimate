package com.guglielmoDelSarto.economics.utilityFunctions;

/**
 * This interface is intended to be implemented by classes
 * representing different utility functions:
 * <br>
 * U(c<sub>1</sub>, ..., c<sub>n</sub>)
 * 
 * @since Version 1.0
 * @author Guglielmo Del Sarto
 */
public interface MultidimensionalUtilityFunction {
	
	/**
	 * Get the perceived utility, given a consumption plan
	 * 
	 * @param consumptionAllocation the allocation given to the user
	 * @return perceivedUtility quantitative measure of the utility
	 */
	public double getUtility(double[] consumptionAllocation);
	
	/**
	 * Get the marginal utilities evaluated in the point given in input
	 * <br>
	 * Starting from the function U(c<sub>1</sub>, ..., c<sub>n</sub>) this
	 * returns the array composed by:
	 * <br>
	 * [dU(&middot;)/dc<sub>1</sub>, ..., dU(&middot;)/dc<sub>1</sub>]
	 * 
	 * @param consumptionAllocation the allocation given to the user
	 * @return partialDerivatives array filled with all partial derivatives
	 */
	public double[] getPartialDerviatives(double[] consumptionAllocation);
	
	/**
	 * Returns dimension of utility function domain
	 * 
	 * @return n the domain dimension
	 */
	public int getDimension();

}

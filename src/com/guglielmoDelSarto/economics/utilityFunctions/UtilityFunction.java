package com.guglielmoDelSarto.economics.utilityFunctions;

/**
 * This interface is intended to be implemented by classes
 * representing different utility functions:
 * <br>
 * u(c)
 * 
 * @since Version 1.0
 * @author Guglielmo Del Sarto
 */
public interface UtilityFunction {
	
	/**
	 * Get the perceived utility, given a consumption plan
	 * 
	 * @param consumption the allocation given to the user
	 * @return perceivedUtility quantitative measure of the utility
	 */
	public double getUtility(double consumption);
	
	/**
	 * Get the marginal utility evaluated in the point given in input
	 * <br>
	 * Starting from the function U(c) this
	 * returns:
	 * <br>
	 * du(c)/dc
	 * 
	 * @param consumptionAllocation the allocation given to the user
	 * @return derivative representing marginal utility
	 */
	public double getPartialDerviative(double consumptionAllocation);
	
	/**
	 * Returns dimension of utility function domain
	 * 
	 * @return n the domain dimension
	 */
	public default int getDimension() {
		return 1;
	};

}


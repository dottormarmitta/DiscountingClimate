package com.guglielmoDelSarto.economics.optimalPlan;

import com.guglielmoDelSarto.economics.production.InvestmentFunction;
import com.guglielmoDelSarto.economics.production.ProductionFunction;
import com.guglielmoDelSarto.economics.utilityFunctions.MultidimensionalUtilityFunction;

/**
 * Represent mathematical tool to find optimal consumption allocation
 * 
 * @since Version 1.0
 * @author Guglielmo Del Sarto
 */
public interface OptimalFinder {
	
	/**
	 * Set the utility function of the user
	 * 
	 * @param utilityFunc the utility function used
	 */
	public void setUtilityFunction(MultidimensionalUtilityFunction utilityFunc);
	
	/**
	 * Set the production function available in the economy
	 * 
	 * @param production the production function
	 */
	public void setProductionFunction(ProductionFunction production);
	
	/**
	 * Set the investment function of the agent
	 * 
	 * @param investment the investment function
	 */
	public void setInvestmentFunction(InvestmentFunction investment);
	
	/**
	 * Get the optimal consumption plan chosen by the agents
	 * of the economy. It is calculated imposing the equality:
	 * <br>
	 * [p(f())]' = U<sup>c<sub>t</sub></sup> / U<sup>c<sub>T</sub></sup>
	 * 
	 * @return optimalConsumption an array consisting of consumptions allocation
	 */
	public double[] getOptimalConsumption();

}

package com.guglielmoDelSarto.economics.production;

/**
 * This function is intended to represent a map from consumption
 * level to wealth
 * 
 * @since Version 1.0
 * @author Guglielmo Del Sarto
 */
public interface InvestmentFunction {
	
	/**
	 * Set the initial endowment of the agents
	 * 
	 * @param wealth the initial endowment w
	 */
	public void setTotalWealth(double wealth);
	
	/**
	 * Get the level of investment, given a certain consumption
	 * 
	 * @param currentConsumption the level of c<sub>t</sub>
	 * @return investmentLevel the difference w - c<sub>t</sub>
	 */
	public double getInvestment(double currentConsumption);
	
	/**
	 * Get the derivative of investment function evaluated in cc
	 * 
	 * @param currentConsumption the level of current consumption
	 * @return derivative the first derivative of inv wrt consumption
	 */
	public double getDerivative(double currentConsumption);

}

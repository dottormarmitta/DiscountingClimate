package com.guglielmoDelSarto.economics.production;

/**
 * Represent the mapping:
 * <br>
 * p(w - c<sub>t</sub>)
 * 
 * @since Version 1.0
 * @author Guglielmo Del Sarto
 */
public class LinearInvestment implements InvestmentFunction {
	
	private double w;
	
	@Override
	public void setTotalWealth(double wealth) {
		w = wealth;
	}

	@Override
	public double getInvestment(double currentConsumption) {
		return w - currentConsumption;
	}

	@Override
	public double getDerivative(double currentConsumption) {
		return -1.0;
	}

}

package com.guglielmoDelSarto.economics.optimalPlan;

import com.guglielmoDelSarto.economics.production.InvestmentFunction;
import com.guglielmoDelSarto.economics.production.ProductionFunction;
import com.guglielmoDelSarto.economics.utilityFunctions.MultidimensionalUtilityFunction;

/**
 * Root finder for utility function. Uses Binary Search algorithm.
 * Maximum number of iterations is capped at 50Mln
 * 
 * @since Version 1.0
 * @author Guglielmo Del Sarto
 */
public class BinarySearchFinder implements OptimalFinder {
	
	private double todayConsumptionMin = 0.0;
	private double todayConsumptionMax = 0.0;
	private MultidimensionalUtilityFunction utility;
	private ProductionFunction p;
	private InvestmentFunction f;
	private final double tolerance;
	
	/**
	 * Build the root finder using two parameters.
	 * 
	 * @param tolerance the tolerance needed
	 * @param wealth the initial endowment of agents
	 */
	public BinarySearchFinder(double tolerance, double wealth) {
		this.tolerance = tolerance;
		this.todayConsumptionMax = wealth;
	}

	@Override
	public void setUtilityFunction(MultidimensionalUtilityFunction utilityFunc) {
		this.utility = utilityFunc;
	}

	@Override
	public void setProductionFunction(ProductionFunction production) {
		this.p = production;
	}

	@Override
	public void setInvestmentFunction(InvestmentFunction investment) {
		this.f = investment;
	}

	@Override
	public double[] getOptimalConsumption() {
		int iterations = 0;
		double median = (todayConsumptionMin + todayConsumptionMax)/2.0;
		double[] allocationMedian = {median, p.getProduction(
				f.getInvestment(median))};
		while(Math.abs(measureUtility(allocationMedian, median)) > tolerance &&
				iterations < 5000000) {
			if (measureUtility(allocationMedian, median) > 0) {
				todayConsumptionMin = median;
			} else {
				todayConsumptionMax = median;
			}
			median = (todayConsumptionMin + todayConsumptionMax)/2.0;
			double[] newAllocation = {median, p.getProduction(
					f.getInvestment(median))};
			allocationMedian = newAllocation;
			iterations++;
		}
		return allocationMedian;
	}
	
	/*
	 * Auxiliary method to find root to eqn. (4) in the pdf
	 */
	private double measureUtility(double[] allocation, double ct) {
		return utility.getPartialDerviatives(allocation)[0] + 
		utility.getPartialDerviatives(allocation)[1]*
		p.getFirstDerivative(f.getInvestment(ct))*
		f.getDerivative(ct);
	}

}

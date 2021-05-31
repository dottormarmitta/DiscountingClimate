package com.guglielmoDelSarto.economics.optimalPlan;

import com.guglielmoDelSarto.economics.production.InvestmentFunction;
import com.guglielmoDelSarto.economics.production.ProductionFunction;
import com.guglielmoDelSarto.economics.utilityFunctions.MultidimensionalUtilityFunction;

public class BinarySearchFinder implements OptimalFinder {
	
	private double todayConsumptionMin = 0.0;
	private double todayConsumptionMax = 0.0;
	private MultidimensionalUtilityFunction utility;
	private ProductionFunction production;
	private InvestmentFunction investment;
	private final double tolerance;
	
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
		this.production = production;
	}

	@Override
	public void setInvestmentFunction(InvestmentFunction investment) {
		this.investment = investment;
	}

	@Override
	public double[] getOptimalConsumption() {
		int iterations = 0;
		double median = (todayConsumptionMin + todayConsumptionMax)/2.0;
		double[] allocationMedian = {median, production.getProduction(
				investment.getInvestment(median))};
		while(Math.abs(measureUtility(allocationMedian, median)) > tolerance &&
				iterations < 50000000) {
			if (measureUtility(allocationMedian, median) > 0) {
				todayConsumptionMin = median;
			} else {
				todayConsumptionMax = median;
			}
			median = (todayConsumptionMin + todayConsumptionMax)/2.0;
			double[] newAllocation = {median, production.getProduction(
					investment.getInvestment(median))};
			allocationMedian = newAllocation;
			iterations++;
		}
		return new double[] {median, 
				production.getProduction(
						investment.getInvestment(median))};
	}
	
	/*
	 * Auxiliary method to find root to eqn. (4) in the pdf
	 */
	private double measureUtility(double[] allocation, double ct) {
		return utility.getPartialDerviatives(allocation)[0] + 
		utility.getPartialDerviatives(allocation)[1]*
		production.getFirstDerivative(investment.getInvestment(ct))*
		investment.getDerivative(ct);
	}

}

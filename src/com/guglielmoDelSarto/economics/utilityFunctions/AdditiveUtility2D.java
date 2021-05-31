package com.guglielmoDelSarto.economics.utilityFunctions;

import com.guglielmoDelSarto.general.discounting.DiscountFunction;

public class AdditiveUtility2D implements MultidimensionalUtilityFunction {
	
	private final UtilityFunction uFunction;
	private final DiscountFunction df;
	private final int dimension = 2;

	public AdditiveUtility2D(UtilityFunction preferenceFunction, DiscountFunction discount) {
		this.uFunction = preferenceFunction;
		this.df = discount;
	}

	@Override
	public double getUtility(double[] consumptionAllocation) {
		if (consumptionAllocation.length != dimension) {
			throw new IllegalArgumentException("Consumption plans do not match length 2");
		}
		return uFunction.getUtility(consumptionAllocation[0]) + 
				df.getDiscountedValue(
						uFunction.getUtility(consumptionAllocation[1]), 1);
	}

	@Override
	public double[] getPartialDerviatives(double[] consumptionAllocation) {
		if (consumptionAllocation.length != dimension) {
			throw new IllegalArgumentException("Consumption plans do not match length 2");
		}
		double partialC1 = uFunction.getPartialDerviative(consumptionAllocation[0]);
		double partialC2 = uFunction.getPartialDerviative(consumptionAllocation[1]);
		partialC2 *= df.getDiscountedValueDerivative(uFunction.getUtility(consumptionAllocation[1]), 1);
		return new double[] {partialC1, partialC2};
	}

	@Override
	public int getDimension() {
		return dimension;
	}

}

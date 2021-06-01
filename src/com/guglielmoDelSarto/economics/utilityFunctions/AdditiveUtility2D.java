package com.guglielmoDelSarto.economics.utilityFunctions;

import com.guglielmoDelSarto.general.discounting.DiscountFunction;

/**
 * This class represent the function used in section two of the thesis
 * <br>
 * U(c<sub>t</sub>, c<sub>T</sub>) = u(c<sub>t</sub>) + v<sub>T</sub>(c<sub>T</sub>)
 * 
 * @since Version 1.0
 * @author Guglielmo Del Sarto
 */
public class AdditiveUtility2D implements MultidimensionalUtilityFunction {
	
	private final UtilityFunction uFunction;
	private final DiscountFunction df;
	private final int dimension = 2;
	
	/**
	 * Build a 2D utility function starting from discounting function and 1D utility
	 * 
	 * @param preferenceFunction the 1D utility function u()
	 * @param discount the discounting function
	 */
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

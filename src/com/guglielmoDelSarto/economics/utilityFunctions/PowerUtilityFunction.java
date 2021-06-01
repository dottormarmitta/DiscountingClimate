package com.guglielmoDelSarto.economics.utilityFunctions;

import java.util.function.DoubleUnaryOperator;

/**
 * This class represent the famous 1D power utility function:
 * <br>
 * u(c) = c<sup>1 - &gamma; </sup> / 1-&gamma;
 * 
 * @since Version 1.0
 * @author Guglielmo Del Sarto
 */
public class PowerUtilityFunction implements UtilityFunction {
	
	private final DoubleUnaryOperator utilityFunction;
	private final DoubleUnaryOperator utilityFunctionDerivative;
	private final double gamma;
	
	/**
	 * Build the power utility fucntion starting from its parameter &gamma;
	 * 
	 * @param gamma the parameter &gamma; of u()
	 */
	public PowerUtilityFunction(double gamma) {
		if (gamma < 0) {
			throw new IllegalArgumentException("Gamma parameter is not in [0, 1]");
		}
		this.gamma = gamma;
		if (gamma == 1) {
			this.utilityFunction = x -> Math.log(x);
			this.utilityFunctionDerivative = x -> 1.0/x;
		} else if (gamma == 0.0) {
			this.utilityFunction = x -> x;
			this.utilityFunctionDerivative = x -> 1.0;
		}else {
			this.utilityFunction = x -> (Math.pow(x, 1 - this.gamma))/(1 - this.gamma);
			this.utilityFunctionDerivative = x -> Math.pow(x, - this.gamma);
		}
	}

	@Override
	public double getUtility(double consumption) {
		return utilityFunction.applyAsDouble(consumption);
	}

	@Override
	public double getPartialDerviative(double consumptionAllocation) {
		return utilityFunctionDerivative.applyAsDouble(consumptionAllocation);
	}

}

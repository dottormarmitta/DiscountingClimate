package com.guglielmoDelSarto.economics.utilityFunctions;

import java.util.function.DoubleUnaryOperator;

public class PowerUtilityFunction implements UtilityFunction {
	
	private final DoubleUnaryOperator utilityFunction;
	private final DoubleUnaryOperator utilityFunctionDerivative;
	private final double gamma;
	
	public PowerUtilityFunction(double gamma) {
		if (gamma < 0 || gamma > 1) {
			throw new IllegalArgumentException("Gamma parameter is not in [0, 1]");
		}
		this.gamma = gamma;
		if (gamma == 1) {
			this.utilityFunction = x -> Math.log(x);
			this.utilityFunctionDerivative = x -> 1.0/x;
		} else {
			this.utilityFunction = x -> (Math.pow(x, 1.0 - this.gamma))/(1 - this.gamma);
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

package com.guglielmoDelSarto.economics.production;

/**
 * This class represent the famous CD production function:
 * <br>
 * p(k) = b * k<sup>&alpha;</sup>
 * 
 * @since Version 1.0
 * @author Guglielmo Del Sarto
 */
public class CobbDouglas1D implements ProductionFunction {
	
	private final double c;
	private final double b;
	
	/**
	 * Build a Cobb-Douglas starting from the two parameters
	 * 
	 * @param alpha the exponent
	 * @param b the multiplicative constant
	 */
	public CobbDouglas1D(double alpha, double b) {
		if (alpha <= 0.0 || alpha > 1.0) {
			throw new IllegalArgumentException("Parameter alpha has to be in [0, 1) to have concave"
					+ " production function");
		}
		this.c = alpha;
		this.b = b;
	}

	@Override
	public double getProduction(double investment) {
		return b*Math.pow(investment, c);
	}

	@Override
	public double getFirstDerivative(double investment) {
		return b*c*Math.pow(investment, c - 1.0);
	}

	@Override
	public double getSecondDerivative(double investment) {
		return b*(c-1.0)*c*Math.pow(investment, c - 2.0);
	}

}

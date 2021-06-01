package com.guglielmoDelSarto.economics.production;

import com.guglielmoDelSarto.general.time.TenorDiscretization;

public class ExponentialGrowth implements ProductionFunction {
	
	private final double g;
	private final TenorDiscretization tenor;

	public ExponentialGrowth(double growthRate, TenorDiscretization tenor) {
		this.g = growthRate;
		this.tenor = tenor;
	}

	@Override
	public double getProduction(double investment) {
		return investment*Math.exp(g*tenor.getLastTime());
	}

	@Override
	public double getFirstDerivative(double investment) {
		return Math.exp(g*tenor.getLastTime());
	}

	@Override
	public double getSecondDerivative(double investment) {
		return 0.0;
	}

}

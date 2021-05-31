package com.guglielmoDelSarto.general.discounting;

import com.guglielmoDelSarto.general.time.TenorDiscretization;

public class ExponentialDiscounting implements DiscountFunction {
	
	private final double r;
	private TenorDiscretization tenor;

	public ExponentialDiscounting(double rate) {
		this.r = rate;
	}

	@Override
	public void setTenor(TenorDiscretization tenor) {
		this.tenor = tenor;
	}

	@Override
	public double getDiscountedValue(double value, int tenorIndex) {
		if (tenor == null) {
			throw new IllegalArgumentException("Please, prior of doing any calculation," + 
		" initialize tenor.");
		}
		return value*Math.exp(-r*tenor.getTime(tenorIndex));
	}

	@Override
	public double getDiscountedValueDerivative(double value, int tenorIndex) {
		if (tenor == null) {
			throw new IllegalArgumentException("Please, prior of doing any calculation," + 
		" initialize tenor.");
		}
		return Math.exp(-r*tenor.getTime(tenorIndex));
	}

}

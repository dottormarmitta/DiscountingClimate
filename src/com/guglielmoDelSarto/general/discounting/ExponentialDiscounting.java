package com.guglielmoDelSarto.general.discounting;

import com.guglielmoDelSarto.general.time.TenorDiscretization;

/**
 * Class representing the usual discounting mechanism
 * <br>
 * df(t, r) = e<sup>-rt</sup>
 * 
 * @since Version 1.0
 * @author Guglielmo Del Sarto
 */
public class ExponentialDiscounting implements DiscountFunction {
	
	private final double r;
	private TenorDiscretization tenor;
	
	/**
	 * Build the discount curve starting from the constant rate
	 * 
	 * @param rate the constant discount rate
	 */
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

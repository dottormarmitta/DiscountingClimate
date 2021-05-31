package com.guglielmoDelSarto.general.time;

public class TenorFromTimes extends TenorFromArray implements TenorDiscretization {

	public TenorFromTimes(double initialTime, double finalTime, int n) {
		 super(buildTenor(initialTime, finalTime, n));
	}

	private static double[] buildTenor(double initialTime, double finalTime, int n) {
		double[] tenor = new double[n];
		tenor[0] = initialTime;
		tenor[n - 1] = finalTime;
		double dt = (finalTime - initialTime)/(n - 1.0);
		for (int t = 1; t < n - 1; t++) {
			tenor[t] = tenor[t-1] + dt;
		}
		return tenor;
	}

}

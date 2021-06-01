package com.guglielmoDelSarto.economics.experiments;

import java.util.Arrays;

import com.guglielmoDelSarto.economics.optimalPlan.BinarySearchFinder;
import com.guglielmoDelSarto.economics.optimalPlan.OptimalFinder;
import com.guglielmoDelSarto.economics.production.CobbDouglas1D;
import com.guglielmoDelSarto.economics.production.InvestmentFunction;
import com.guglielmoDelSarto.economics.production.LinearInvestment;
import com.guglielmoDelSarto.economics.production.ProductionFunction;
import com.guglielmoDelSarto.economics.utilityFunctions.AdditiveUtility2D;
import com.guglielmoDelSarto.economics.utilityFunctions.MultidimensionalUtilityFunction;
import com.guglielmoDelSarto.economics.utilityFunctions.PowerUtilityFunction;
import com.guglielmoDelSarto.economics.utilityFunctions.UtilityFunction;
import com.guglielmoDelSarto.general.discounting.DiscountFunction;
import com.guglielmoDelSarto.general.discounting.ExponentialDiscounting;
import com.guglielmoDelSarto.general.time.TenorDiscretization;
import com.guglielmoDelSarto.general.time.TenorFromTimes;

/**
 * Experiment-Class. It is used to look at the sensitivity of
 * agents' investment to discount rate at different maturities
 * 
 * @since Version 1.0
 * @author Guglielmo Del Sarto
 */
public class OptimalPoint {

	public static void main(String[] args) {

		System.out.println("-".repeat(80));
		System.out.println("");

		double future = 10;
		for(int t = 0; t < 3; t++) {
			/*
			 * Time structure
			 */
			double today  = 0.0;
			int periods = 2;
			TenorDiscretization tenor = new TenorFromTimes(today, future, periods);

			/*
			 * Wealth and production
			 */
			double wealth = 100.00;
			ProductionFunction p = new CobbDouglas1D(0.7, 3.5);
			InvestmentFunction f = new LinearInvestment();
			f.setTotalWealth(wealth);

			/*
			 * See the impact of discount rate on today's allocation
			 */
			double rate = 0.000;
			System.out.println("This experiment is intended to show how discount rate affect"
					+ " investment allocation.");
			System.out.println("Maturity is now at...: " + future + " years");
			System.out.println();
			for(int i = 0; i < 3; i++) {

				/*
				 * Discount
				 */
				DiscountFunction df = new ExponentialDiscounting(rate);
				df.setTenor(tenor);

				/*
				 * Utility functions
				 */
				UtilityFunction u = new PowerUtilityFunction(2.0);
				MultidimensionalUtilityFunction U = new AdditiveUtility2D(u, df);

				/*
				 * Optimizer
				 */
				double tolerance = 1E-15;
				OptimalFinder optimizer = new BinarySearchFinder(tolerance, wealth);
				optimizer.setInvestmentFunction(f);
				optimizer.setProductionFunction(p);
				optimizer.setUtilityFunction(U);

				/*
				 * Results
				 */
				double[] C = optimizer.getOptimalConsumption();
				System.out.println("Discount rate is.....: " + rate);
				System.out.println("Optimal allocation is: " + 
						Arrays.toString(C));
				System.out.println("Investment equals....: " + (wealth - C[0]));
				System.out.println("On optimal point we must have the equality: ");
				System.out.println("      p'(k) = - Uc_t / Uc_T       ");
				System.out.println("Is this the case?");
				System.out.println("p'(k) ..............: " + p.getFirstDerivative(
						f.getInvestment(C[0])
						)*f.getDerivative(C[0]));
				double[] partials = U.getPartialDerviatives(C);
				System.out.println("U'(c_t)/U'(c_T) ....: " + (-partials[0]/partials[1]));
				System.out.println();



				rate += 0.005;
			}
			System.out.println("-".repeat(80));
			System.out.println("");
			future *= 10;
		}

	}

}

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

public class OptimalPoint {

	public static void main(String[] args) {

		System.out.println("-".repeat(80));
		System.out.println("");

		double future = 1.0;
		for(int t = 0; t < 4; t++) {
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
			ProductionFunction p = new CobbDouglas1D(0.7, 2.0);
			InvestmentFunction f = new LinearInvestment();
			f.setTotalWealth(wealth);

			/*
			 * See the impact of discount rate on today's allocation
			 */
			double rfr = 0.000;
			System.out.println("This experiment is intended to show how discount rate affect"
					+ " investment allocation.");
			System.out.println("Maturity is now at...: " + future + " years");
			System.out.println();
			for(int i = 0; i < 4; i++) {

				/*
				 * Discount
				 */
				DiscountFunction df = new ExponentialDiscounting(rfr);
				df.setTenor(tenor);

				/*
				 * Utility functions
				 */
				UtilityFunction u = new PowerUtilityFunction(0.7);
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
				System.out.println("Discount rate is.....: " + rfr);
				System.out.println("Optimal allocation is: " + 
						Arrays.toString(optimizer.getOptimalConsumption()));
				System.out.println("Investment equals....: " + 
						(wealth - optimizer.getOptimalConsumption()[0]));
				System.out.println();



				rfr += 0.0005;
			}
			System.out.println("-".repeat(80));
			System.out.println("");
			future *= 10;
		}

	}

}

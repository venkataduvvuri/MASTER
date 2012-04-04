package popgen.models;

import popgen.*;

/**
 * A stochastic logistic model of population dynamics.  Uses
 * Moment objects to summarise an ensemble in terms of means
 * and variances.
 * 
 * @author Tim Vaughan
 *
 */
public class StochasticLogisticSummary {

	public static void main (String[] argv) {

		/*
		 * Assemble model:
		 */

		Model model = new Model();

		// Define populations:

		Population X = new Population("X");
		model.addPopulation(X);

		// Define reactions:

		// X -> 2X
		Reaction birth = new Reaction();
		birth.setReactantSchema(X);
		birth.setProductSchema(X,X);
		birth.setRate(1.0);
		model.addReaction(birth);

		// 2X -> X
		Reaction death = new Reaction();
		death.setReactantSchema(X,X);
		death.setProductSchema(X);
		death.setRate(0.01);
		model.addReaction(death);

		// Define moments:

		Moment mX = new Moment("X",X);

		/*
		 * Set initial state:
		 */

		State initState = new State(model);
		initState.set(X, 1.0);

		/*
		 * Define simulation:
		 */

		EnsembleSummarySpec spec = new EnsembleSummarySpec();

		spec.setModel(model);
		spec.setSimulationTime(100.0);
		spec.setnTimeSteps(10001);
		spec.setnSamples(1001);
		spec.setnTraj(1000);
		spec.setSeed(53);
		spec.setInitState(initState);
		spec.addMoment(mX);

		// Report on ensemble calculation progress:
		spec.setVerbosity(0);

		/*
		 * Generate summarised ensemble:
		 */

		EnsembleSummary ensemble = new EnsembleSummary(spec);

		/*
		 * Dump results (JSON):
		 */

		ensemble.dump();
	}
}

package edu.toronto.cs.se.ci.aggregators;

import edu.toronto.cs.se.ci.Aggregator;
import edu.toronto.cs.se.ci.data.Opinion;
import edu.toronto.cs.se.ci.data.Result;

/**
 * This Aggregator selects the single opinion with the highest trust value.
 * 
 * <p>The quality of the result is this trust value.
 * 
 * @author Michael Layzell
 *
 * @param <O>
 */
public class RankAggregator<O, T extends Comparable<T>> implements Aggregator<O, T, T> {

	@Override
	public Result<O, T> aggregate(Iterable<Opinion<O, T>> opinions) {
		T bestTrust = null;
		Opinion<O, T> bestOpinion = null;
		
		for (Opinion<O, T> opinion : opinions) {
			T trust = opinion.getTrust();
			if (bestTrust == null || trust.compareTo(bestTrust) > 0) {
				bestOpinion = opinion;
				bestTrust = trust;
			}
		}
		
		return new Result<O, T>(bestOpinion.getValue(), bestTrust);
	}

}

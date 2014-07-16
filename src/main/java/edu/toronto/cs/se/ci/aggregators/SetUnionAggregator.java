package edu.toronto.cs.se.ci.aggregators;

import java.util.HashSet;
import java.util.Set;

import edu.toronto.cs.se.ci.Aggregator;
import edu.toronto.cs.se.ci.data.Opinion;
import edu.toronto.cs.se.ci.data.Result;

/**
 * Aggregates opinions where each set approximates the target set. The target set
 * is generated by taking the union of every opinion set.
 * 
 * <p>The quality of the result is the ratio of intersections/total elements.
 * 
 * <p>NOTE: This Aggregator ignores the trust of the input Opinions.
 * 
 * @author Michael Layzell
 *
 * @param <O> Set element type
 */
public class SetUnionAggregator<O, T> implements Aggregator<Set<O>, T, Double> {

	@Override
	public Result<Set<O>, Double> aggregate(Iterable<Opinion<Set<O>, T>> opinions) {
		int intersectSize = 0;
		int totalSize = 0;
		Set<O> result = new HashSet<O>();
		
		for (Opinion<Set<O>, T> opinion : opinions) {
			Set<O> value = opinion.getValue();

			// Getting the intersection
			Set<O> intersection = new HashSet<O>(result);
			intersection.retainAll(value);

			// Values for determining quality
			intersectSize += intersection.size();
			totalSize += value.size();

			// Add to the result set
			result.addAll(value);
		}
		
		return new Result<Set<O>, Double>(result, ((double) intersectSize)/totalSize);
	}

}

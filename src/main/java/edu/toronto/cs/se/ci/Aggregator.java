package edu.toronto.cs.se.ci;

import java.util.List;

import com.google.common.base.Optional;

import edu.toronto.cs.se.ci.data.Opinion;
import edu.toronto.cs.se.ci.data.Result;

/**
 * The aggregator is a single-method interface. It is used to combine the opinions
 * returned by sources into a single unified result.
 * 
 * @author Michael Layzell
 *
 * @param <O>
 * @param <T>
 * @param <Q>
 */
public interface Aggregator<O, T, Q> {

	/**
	 * @param opinions The opinions provided by sources in the CI
	 * @return An aggregated result
	 */
	public Optional<Result<O, Q>> aggregate(List<Opinion<O, T>> opinions);

}

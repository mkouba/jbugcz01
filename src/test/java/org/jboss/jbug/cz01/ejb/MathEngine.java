package org.jboss.jbug.cz01.ejb;

import java.math.BigInteger;

import javax.ejb.Stateless;

/**
 * SLSB with no-interface view.
 * 
 * @author Martin Kouba
 */
@Stateless
public class MathEngine {

	/**
	 * 
	 * @param from
	 * @param to
	 * @return
	 */
	public BigInteger multiplySequence(long from, long to) {

		if (from <= 0 || to <= 0)
			throw new IllegalArgumentException("Must be positive");

		if (from > to)
			throw new IllegalArgumentException("from must be lower than to");

		BigInteger result = BigInteger.ONE;

		for (long i = from; i <= to; i++) {
			result = result.multiply(BigInteger.valueOf(i));
		}
		return result;
	}

}

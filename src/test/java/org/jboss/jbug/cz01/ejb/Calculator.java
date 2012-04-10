package org.jboss.jbug.cz01.ejb;

import java.math.BigInteger;

import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * SLSB with no-interface view.
 *  
 * @author Martin Kouba
 */
@Stateless
public class Calculator {
	
	@EJB
	private MathEngine mathEngine;
	
	public BigInteger factorial(long number) {
		return mathEngine.multiplySequence(1, number);
	}
	
}

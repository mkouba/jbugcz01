package org.jboss.jbug.cz01.ejb;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigInteger;

import javax.ejb.EJB;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Java EE technologies: EJB 3.1<br>
 * Arquillian: in-container test run mode, EJB test field enrichment
 * 
 * @author Martin Kouba
 */
@RunWith(Arquillian.class)
public class CalculatorTest {

	@Deployment
	public static JavaArchive createTestArchive() {
		return ShrinkWrap.create(JavaArchive.class, "test.jar").addClasses(
				Calculator.class, MathEngine.class);
	}

	@EJB
	Calculator calculator;

	@Test
	public void testFactorial() {
		assertNotNull(calculator);
		assertEquals(BigInteger.valueOf(1), calculator.factorial(1));
		assertEquals(BigInteger.valueOf(720), calculator.factorial(6));
	}

}

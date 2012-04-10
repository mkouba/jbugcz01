package org.jboss.jbug.cz01.validation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.constraints.NotNull;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Java EE technologies: Bean Validation, CDI<br>
 * 
 * @author Martin Kouba
 */
@RunWith(Arquillian.class)
public class ValidationTest {

	@Deployment
	public static JavaArchive createTestArchive() {
		return ShrinkWrap.create(JavaArchive.class).addClass(User.class)
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	// Inject CDI built-in bean
	@Inject
	private Validator validator;

	@Test
	public void testUserValidation() {

		assertNotNull(validator);

		assertTrue(validator.validate(new User("Martin")).isEmpty());

		Set<ConstraintViolation<User>> violations = validator
				.validate(new User("Too long name is here"));
		assertFalse(violations.isEmpty());
		assertEquals(1, violations.size());
		assertEquals("ERROR", violations.iterator().next().getMessage());

		violations = validator.validate(new User(null));
		assertFalse(violations.isEmpty());
		assertEquals(1, violations.size());
		assertEquals(NotNull.class, violations.iterator().next()
				.getConstraintDescriptor().getAnnotation().annotationType());
	}

}

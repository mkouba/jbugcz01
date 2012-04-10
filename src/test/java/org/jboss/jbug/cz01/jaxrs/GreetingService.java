package org.jboss.jbug.cz01.jaxrs;

/**
 * Dependent CDI bean.
 * 
 * @author Martin Kouba
 */
public class GreetingService {

	public String sayHello(String name) {
		return String.format("Hello %s!", name);
	}
	
	public String sayHi(String name) {
		return String.format("Hi %s!", name);
	}
	
}

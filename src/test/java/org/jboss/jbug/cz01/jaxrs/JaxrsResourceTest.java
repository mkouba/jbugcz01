package org.jboss.jbug.cz01.jaxrs;

import static org.junit.Assert.assertEquals;

import java.net.URL;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.descriptor.api.Descriptors;
import org.jboss.shrinkwrap.descriptor.api.webapp30.WebAppDescriptor;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.gargoylesoftware.htmlunit.TextPage;
import com.gargoylesoftware.htmlunit.WebClient;

/**
 * Java EE technologies: JAX-RS, CDI<br>
 * ShrinkWrap: Descriptors subproject<br>
 * Arquillian: mixed test run mode, test method argument enrichment,
 * ArquillianResource injection
 * 
 * @author Martin Kouba
 */
@RunWith(Arquillian.class)
public class JaxrsResourceTest {

	@Deployment
	public static WebArchive createTestArchive() {
		return ShrinkWrap
				.create(WebArchive.class)
				.addClasses(GreetingResource.class, GreetingService.class)
				.setWebXML(
						new StringAsset(Descriptors
								.create(WebAppDescriptor.class)
								.createServletMapping()
								.servletName("javax.ws.rs.core.Application")
								.urlPattern("/resources/*").up()
								.exportAsString()))
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@RunAsClient
	@Test
	public void testHelloResource(@ArquillianResource URL contextPath)
			throws Exception {
		WebClient webClient = new WebClient();
		TextPage page = webClient.getPage(contextPath
				+ "resources/greeting/hello?name=Martin");
		assertEquals("Hello Martin!", page.getContent());
	}

	@RunAsClient
	@Test
	public void testHiResource(@ArquillianResource URL contextPath)
			throws Exception {
		WebClient webClient = new WebClient();
		TextPage page = webClient.getPage(contextPath
				+ "resources/greeting/hi/Ales");
		assertEquals("Hi Ales!", page.getContent());
	}

	@Test
	public void testGreetingService(GreetingService greetingService) {
		assertEquals("Hello Pete!", greetingService.sayHello("Pete"));
		assertEquals("Hi Martin!", greetingService.sayHi("Martin"));
	}

}

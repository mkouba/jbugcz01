package org.jboss.jbug.cz01.servlet;

import static org.junit.Assert.assertEquals;

import java.net.URL;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.DependencyResolvers;
import org.jboss.shrinkwrap.resolver.api.maven.EffectivePomMavenDependencyResolver;
import org.jboss.shrinkwrap.resolver.api.maven.MavenDependencyResolver;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.gargoylesoftware.htmlunit.TextPage;
import com.gargoylesoftware.htmlunit.WebClient;

/**
 * Java EE technologies: Servlet 3.0<br>
 * ShrinkWrap: Resolvers subproject<br>
 * Arquillian: as-client test run mode, ArquillianResource injection
 * 
 * @author Martin Kouba
 */
@RunAsClient
@RunWith(Arquillian.class)
public class ServletTest {

	@ArquillianResource
	private URL contextPath;

	@Deployment
	public static WebArchive createTestArchive() {
		
		// Use Maven resolver to include commons-lang library
		EffectivePomMavenDependencyResolver resolver = DependencyResolvers.use(
				MavenDependencyResolver.class).loadEffectivePom("pom.xml");
		
		return ShrinkWrap
				.create(WebArchive.class)
				.addClasses(GreetingServlet.class, GreetingFilter.class)
				.addAsLibraries(
						resolver.artifact("org.apache.commons:commons-lang3")
								.resolveAsFiles());
	}

	@Test
	public void testHello() throws Exception {
		
		WebClient client = new WebClient();
		client.setThrowExceptionOnFailingStatusCode(true);

		TextPage page = client.getPage(contextPath + "hello");
		assertEquals(
				"What's your name? My name is " + System.getProperty("os.name")
						+ "!", page.getContent());
		page = client.getPage(contextPath + "hello?name=Ale&scaron;");
		assertEquals(
				"Please to meet you Ale! My name is "
						+ System.getProperty("os.name") + "!",
				page.getContent());
	}

	@Test
	public void testHi() throws Exception {
		
		WebClient client = new WebClient();
		client.setThrowExceptionOnFailingStatusCode(true);

		TextPage page = client.getPage(contextPath + "hi");
		assertEquals(
				"What's your name? My name is " + System.getProperty("os.name")
						+ "!", page.getContent());
		page = client.getPage(contextPath + "hello?name=Jozef");
		assertEquals(
				"Please to meet you Jozef! My name is "
						+ System.getProperty("os.name") + "!",
				page.getContent());
	}

}

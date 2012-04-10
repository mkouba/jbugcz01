package org.jboss.jbug.cz01.jaxrs;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

@Path("/greeting")
public class GreetingResource {
	
	@Inject
	private GreetingService service;

	@GET
	@Path("/hello")
    @Produces("text/plain")
	public String hello(@QueryParam("name") String name) {
		return service.sayHello(name);
	}
	
	@GET
	@Path("/hi/{name}")
    @Produces("text/plain")
	public String hi(@PathParam("name") String name) {
		return service.sayHi(name);
	}
	
}

package edu.sjsu.cmpe.ADayInTwitter.Root;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import com.yammer.metrics.annotation.Timed;


@Path("/v1")
public class RootResource {

	@GET
	@Timed(name="get-root")
	public Response getRoot(){
		
		System.out.println("root");
		return Response.ok().build();
	}
}

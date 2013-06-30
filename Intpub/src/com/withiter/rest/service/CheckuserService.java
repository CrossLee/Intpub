package com.withiter.rest.service;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Path("/user")
public class CheckuserService {

	private static final Log log = LogFactory.getLog(CheckuserService.class);
	
	@POST
	@Path("/checkUser")
	@Produces(MediaType.TEXT_PLAIN)
	public String helloWorld(@FormParam(value = "mac") String mac) {
		if(log.isDebugEnabled()){
			log.debug("RESTFUL CALL FOR CHECK USER...");
		}
		String isExist = "false";
		
		return isExist;
	}
}
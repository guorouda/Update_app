package com.ron;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

public class ResourceLoader extends Application {

	@Override
	public Set<Class<?>> getClasses() {
		// TODO Auto-generated method stub
		final Set<Class <?>> classes = new HashSet<Class<?>>();
		
		//register root resource
		classes.add(HelloWorldResetResource.class);
		return classes;
	}
	

}

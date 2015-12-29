package com.ron;

import java.io.File;
import java.net.MalformedURLException;

import javax.servlet.ServletException;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

public class Main {
	private static Tomcat tomcat = new Tomcat();
	private static  boolean isRunning = false;

	public static void main(String[] args) throws Exception, LifecycleException{
		Main.start();

	}

	public static void start() throws ServletException, LifecycleException, MalformedURLException {
		if(isRunning == true){
			return;
		}
		
		//Define a folder to hold web application contents.
		String webappDirLocation = "WebContent/";
		
		//Define port number for the web application
		String webPort = System.getenv("PORT");
		if(webPort == null || webPort.isEmpty()){
			webPort = "8080";
		}
		
		//Bind the port to Tomcat server
		tomcat.setPort(Integer.valueOf(webPort));
		
		//Define a web application context.
		Context context = tomcat.addWebapp("", new File(webappDirLocation).getAbsolutePath());
		
		//Define and bind web.xml file location.
		File configFile = new File(webappDirLocation + "WEB-INF/web.xml");
		
		tomcat.start();
		isRunning = false;
		System.out.println("running");
		
		tomcat.getServer().await();
		
	}
	
	public static void stop() throws LifecycleException{
		tomcat.stop();
		isRunning = false;
	}

}

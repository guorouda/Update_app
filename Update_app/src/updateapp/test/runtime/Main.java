package updateapp.test.runtime;

import java.io.File;
import java.util.logging.Logger;
 
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
 
public class Main {
  private final static Logger LOGGER = Logger.getLogger(Main.class.getName());
  private final static String mWorkingDir = System.getProperty("java.io.tmpdir");
  private static Tomcat tomcat = null;
 
  public static void main(String[] args) {
 
    tomcat = new Tomcat();
    tomcat.setPort(8081);
    tomcat.setBaseDir(mWorkingDir);
    tomcat.getHost().setAppBase(mWorkingDir);
    tomcat.getHost().setAutoDeploy(true);
    tomcat.getHost().setDeployOnStartup(true);
 
    try {
      tomcat.start();
    } catch (LifecycleException e) {
      LOGGER.severe("Tomcat could not be started.");
      e.printStackTrace();
    }
    LOGGER.info("Tomcat started on " + tomcat.getHost());
 
    // Alternatively, you can specify a WAR file as last parameter in the following call e.g. "C:\\Users\\admin\\Desktop\\app.war"
    Context appContext = Main.getTomcat().addWebapp(Main.getTomcat().getHost(), "/app", "C:\\Users\\sun\\Desktop\\app\\");
    LOGGER.info("Deployed " + appContext.getBaseName() + " as " + appContext.getBaseName());
 
    tomcat.getServer().await();
  }

	private static Tomcat getTomcat() {
		return tomcat;
	}
}
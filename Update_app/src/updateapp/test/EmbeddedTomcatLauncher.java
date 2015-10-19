package updateapp.test;

import org.apache.catalina.Context;
import org.apache.catalina.Engine;
import org.apache.catalina.Host;
import org.apache.catalina.Lifecycle;
import org.apache.catalina.LifecycleEvent;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.LifecycleListener;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Embedded;
/**
 * 嵌入式Tomcat管理器
 * @author lifeng
 * 2011-11-24 下午03:00:13
 */
public class EmbeddedTomcatLauncher {

	private Embedded tomcat = null;
	private String path;		//EmbeddedTomcat所在路径
	private String projectName;	//所要发布的项目名
	private int port;
	/**
	 * 服务是否正在启用状态中
	 */
	private boolean isRunning;
	
	
	public EmbeddedTomcatLauncher(String path , String projectName,int port) {
		this.path = path;
		this.projectName = projectName;
		this.port = port;
		isRunning = false;
	}
	public EmbeddedTomcatLauncher(String path , String projectName) {
		this.path = path;
		this.projectName = projectName;
		this.port = 8080;
		isRunning = false;
	}

	public static void main(String[] args) {
		EmbeddedTomcatLauncher tomcatLauncher = new EmbeddedTomcatLauncher("D:/temp/tomcat-embed","FileTree");
		tomcatLauncher.startTomcat();
	}

	/**
	 * 启动Tomcat服务
	 * @author lifeng
	 */
	public void startTomcat(){
		try {
			if(!isRunning){
				initEmbedded();
				tomcat.start();
				isRunning = true;
			}
		} catch (LifecycleException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 停止Tomcat服务
	 * @author lifeng
	 */
	public void stopTomcat(){
		try {
			if(isRunning){
				tomcat.stop();
				isRunning = false;
			}
		} catch (LifecycleException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 重启Tomcat服务
	 * @author lifeng
	 */
	public void restartTomcat(){
		try {
			if(isRunning){
				tomcat.stop();
				initEmbedded();
				tomcat.start();
				System.out.println();
			}else {
				initEmbedded();
				tomcat.start();
			}
		} catch (LifecycleException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 初始化Tomcat服务信息
	 * @author lifeng
	 */
	public void initEmbedded() {
		tomcat = new Embedded();
		tomcat.setCatalinaHome(path);
		
		Engine engine = tomcat.createEngine();
		engine.setName("DhccEngine");

		Host host = tomcat.createHost("localhost", path + "\\webapps");
		Context myContext = tomcat.createContext("/"+projectName, path + "/webapps/" + projectName);
		
		host.addChild(myContext);
		engine.addChild(host);
		engine.setDefaultHost("localhost");
		tomcat.addEngine(engine);
		Connector connector = tomcat.createConnector("localhost", port, false);
		tomcat.addConnector(connector);
		tomcat.addLifecycleListener(new LifecycleListener() {
			public void lifecycleEvent(LifecycleEvent le) {
				String lc = le.getType();
				if(lc.equals(Lifecycle.START_EVENT)){
					System.out.println("start!");
					isRunning = true;
				}else if (lc.equals(Lifecycle.STOP_EVENT)){
					System.out.println("stop!");
					isRunning = false;
				}else if (lc.equals(Lifecycle.DESTROY_EVENT)){
					try {
						EmbeddedTomcatLauncher.this.tomcat.stop();
						isRunning = false;
					} catch (LifecycleException e) {
						e.printStackTrace();
					}
				}
			}
		});
		
	}

	
}


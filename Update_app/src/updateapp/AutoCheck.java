package updateapp;

import java.io.IOException;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class AutoCheck implements Job {

	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		 try {
				System.out.println("!!!...");
			if (Integer.parseInt(Updater.getLatestVersion()) > 0) {						
				System.out.println("updating...");
			    update();
			}else{
				
			}
		} catch (NumberFormatException e1) {
//			e1.printStackTrace();
//			infoPane.setText("Version Number error.");
			System.out.println("Version Number error.");
		} catch (IOException e1) {
			e1.printStackTrace();
//			infoPane.setText("Connection error.");
			System.out.println("Connection error.");
		}
	}

    public static void update() {
      String[] run = {"java","-jar","update.jar"};
      try {
          Runtime.getRuntime().exec(run);
      } catch (Exception ex) {
          ex.printStackTrace();
      }
      System.exit(0);
  }
}

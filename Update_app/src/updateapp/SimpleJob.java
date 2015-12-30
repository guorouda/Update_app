package updateapp;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class SimpleJob {
	private static SchedulerFactory sf;
	private static Scheduler sched ; 
	
	static {
		sf = new StdSchedulerFactory();
		try {
			sched = sf.getScheduler();
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public  static  String startSchedule(){
//		SchedulerFactory sf = new StdSchedulerFactory();
		try {
//			Scheduler sched = sf.getScheduler();
			JobDetail job = JobBuilder.newJob(AutoCheck.class)
					 .withIdentity("job1", "group1")
					 .build();
			Trigger trigger = TriggerBuilder.newTrigger()
					 .withIdentity("trigger1", "group1")
					 .withSchedule(CronScheduleBuilder.cronSchedule("0/40 * * * * ?"))
					 .build();
			sched.scheduleJob(job, trigger);
			sched.start();
			return "success!";
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "error";
			
	}
	
	public static void stopSchedule(){
		try {
			sched.shutdown(true);
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args){
		startSchedule();
	}

}

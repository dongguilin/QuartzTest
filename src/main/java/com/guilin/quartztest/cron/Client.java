package com.guilin.quartztest.cron;

import com.guilin.quartztest.helloworld.HelloJob;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.calendar.HolidayCalendar;

import java.util.Date;

/**
 * Created by guilin1 on 15/4/1.
 */
public class Client {

    private static Logger log = Logger.getLogger(Client.class);

    public void run() throws SchedulerException {
        log.info("------- Initializing -------------------");

        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler sched = sf.getScheduler();

        log.info("------- Initialization Complete --------");

        log.info("------- Scheduling Jobs ----------------");

        JobDetail job = JobBuilder.newJob(CronJob.class).withIdentity("job1", "group1").build();

        CronTrigger trigger = (CronTrigger) TriggerBuilder.newTrigger().withIdentity("trigger1", "group1").withSchedule(CronScheduleBuilder.cronSchedule("0/20 * * * * ?")).build();

        Date ft = sched.scheduleJob(job, trigger);
        log.info(job.getKey() + " has been scheduled to run at: " + ft + " and repeat based on expression: " + trigger.getCronExpression());

        job = JobBuilder.newJob(CronJob.class).withIdentity("job2", "group1").build();

        trigger = (CronTrigger) TriggerBuilder.newTrigger().withIdentity("trigger2", "group1").withSchedule(CronScheduleBuilder.cronSchedule("15 0/2 * * * ?")).build();

        ft = sched.scheduleJob(job, trigger);
        log.info(job.getKey() + " has been scheduled to run at: " + ft + " and repeat based on expression: " + trigger.getCronExpression());

        job = JobBuilder.newJob(CronJob.class).withIdentity("job3", "group1").build();

        trigger = (CronTrigger) TriggerBuilder.newTrigger().withIdentity("trigger3", "group1").withSchedule(CronScheduleBuilder.cronSchedule("0 0/2 8-17 * * ?")).build();

        ft = sched.scheduleJob(job, trigger);
        log.info(job.getKey() + " has been scheduled to run at: " + ft + " and repeat based on expression: " + trigger.getCronExpression());

        job = JobBuilder.newJob(CronJob.class).withIdentity("job4", "group1").build();

        trigger = (CronTrigger) TriggerBuilder.newTrigger().withIdentity("trigger4", "group1").withSchedule(CronScheduleBuilder.cronSchedule("0 0/3 17-23 * * ?")).build();

        ft = sched.scheduleJob(job, trigger);
        log.info(job.getKey() + " has been scheduled to run at: " + ft + " and repeat based on expression: " + trigger.getCronExpression());

        job = JobBuilder.newJob(CronJob.class).withIdentity("job5", "group1").build();

        trigger = (CronTrigger) TriggerBuilder.newTrigger().withIdentity("trigger5", "group1").withSchedule(CronScheduleBuilder.cronSchedule("0 0 10am 1,15 * ?")).build();

        ft = sched.scheduleJob(job, trigger);
        log.info(job.getKey() + " has been scheduled to run at: " + ft + " and repeat based on expression: " + trigger.getCronExpression());

        job = JobBuilder.newJob(CronJob.class).withIdentity("job6", "group1").build();

        trigger = (CronTrigger) TriggerBuilder.newTrigger().withIdentity("trigger6", "group1").withSchedule(CronScheduleBuilder.cronSchedule("0,30 * * ? * MON-FRI")).build();

        ft = sched.scheduleJob(job, trigger);
        log.info(job.getKey() + " has been scheduled to run at: " + ft + " and repeat based on expression: " + trigger.getCronExpression());

        job = JobBuilder.newJob(CronJob.class).withIdentity("job7", "group1").build();

        trigger = (CronTrigger) TriggerBuilder.newTrigger().withIdentity("trigger7", "group1").withSchedule(CronScheduleBuilder.cronSchedule("0,30 * * ? * SAT,SUN")).build();

        ft = sched.scheduleJob(job, trigger);
        log.info(job.getKey() + " has been scheduled to run at: " + ft + " and repeat based on expression: " + trigger.getCronExpression());

        log.info("------- Starting Scheduler ----------------");

        sched.start();

        log.info("------- Started Scheduler -----------------");

        log.info("------- Waiting five minutes... ------------");
        try {
            Thread.sleep(300000L);
        } catch (Exception e) {
        }
        log.info("------- Shutting Down ---------------------");

        sched.shutdown(true);

        log.info("------- Shutdown Complete -----------------");

        SchedulerMetaData metaData = sched.getMetaData();
        log.info("Executed " + metaData.getNumberOfJobsExecuted() + " jobs.");
    }

    public static void main(String[] args) throws SchedulerException {
        Client client = new Client();
        client.run();
    }
}

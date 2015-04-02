package com.guilin.quartztest.helloworld;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

/**
 * Created by guilin1 on 15/4/1.
 */
public class Client {

    public static void main(String[] args) {
        SchedulerFactory sf = new StdSchedulerFactory();
        try {
            Scheduler scheduler = sf.getScheduler();

            JobDetail job = JobBuilder.newJob(HelloJob.class).withIdentity("job1", "group1").build();

            Date current = new Date();

            System.out.println(current);

            Date date = new Date(current.getTime() + 1000 * 2);

            Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "group1").startAt(date).build();

            scheduler.scheduleJob(job, trigger);

//            System.out.println("isStarted:" + scheduler.isStarted());//false
//            System.out.println("isInStandbyMode:" + scheduler.isInStandbyMode());//true


            scheduler.start();

            System.out.println("启动scheduler " + current);

            Thread.sleep(1000 * 3);

            //Passing “true” to the shutdown method tells Quartz Scheduler to wait until all jobs have completed running before returning from the method call
            scheduler.shutdown(true);

            System.out.println("关闭scheduler " + new Date());

//            System.out.println("isStarted:" + scheduler.isStarted());//true
//            System.out.println("isInStandbyMode:" + scheduler.isInStandbyMode());//true
        } catch (SchedulerException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

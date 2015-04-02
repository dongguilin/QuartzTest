package com.guilin.quartztest.helloworld;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;

import java.util.Date;

/**
 * Created by guilin1 on 15/4/1.
 */
public class HelloJob implements Job {

    private static Logger _LOG = Logger.getLogger(HelloJob.class);

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobKey key = context.getJobDetail().getKey();
        _LOG.info("开始执行HelloJob " + key + "! - " + new Date());
        try {
            Thread.sleep(1000 * 15);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        _LOG.info("HelloJob执行完毕！");
    }
}

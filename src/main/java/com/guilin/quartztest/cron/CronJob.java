package com.guilin.quartztest.cron;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;

/**
 * Created by guilin1 on 15/4/2.
 */
public class CronJob implements Job {

    private static Logger _LOG = Logger.getLogger(CronJob.class);

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobKey key = context.getJobDetail().getKey();
        _LOG.info("执行" + CronJob.class.toString() + "-" + key);

    }
}

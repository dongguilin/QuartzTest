package com.guilin.quartztest;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;

/**
 * Created by guilin1 on 15/4/2.
 */
public class SimpleJob implements Job {

    private static Logger _LOG = Logger.getLogger(SimpleJob.class);

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        _LOG.info("执行SimpleJob" + new Date());

    }
}

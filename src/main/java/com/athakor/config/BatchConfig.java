package com.athakor.config;

import com.athakor.ETLConstants;
import com.athakor.batch.job.CallLogETLManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * athakor
 */
@Slf4j
@Configuration
@EnableBatchProcessing
@EnableScheduling
public class BatchConfig {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    @Qualifier("callLogETLManager")
    private CallLogETLManager callLogETLManager;

    @Scheduled(cron = "*/50 * * * * *")
    public void start() {
        JobParameters params = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis()).toJobParameters();
        try {
            log.info("Started job {} :: {}", ETLConstants.IMPORT_CALL_LOG_JOB, System.currentTimeMillis());
            JobExecution jobExecution = jobLauncher.run(callLogETLManager.startJob(), params);
            log.debug("Job's Status :: {}", jobExecution.getStatus());
            log.info("Finished job {} :: {}", ETLConstants.IMPORT_CALL_LOG_JOB, System.currentTimeMillis());
        } catch (Exception e) {
            log.error("Exception occurred in job : {} ", ETLConstants.IMPORT_CALL_LOG_JOB);
        }
    }


}


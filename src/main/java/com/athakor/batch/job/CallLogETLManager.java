package com.athakor.batch.job;

import com.athakor.ETLConstants;
import com.athakor.batch.entity.CallLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.job.flow.JobExecutionDecider;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.extensions.excel.RowMapper;
import org.springframework.batch.extensions.excel.poi.PoiItemReader;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

/**
 * athakor
 */
@Slf4j
@Configuration("callLogETLManager")
public class CallLogETLManager extends AETLManager implements JobExecutionDecider {

    @Value("${etl.calling.inbound.path}")
    private Resource inboundPath;

    @Value("${etl.calling.outbound.path}")
    private Resource outboundPath;

    @Value("${etl.calling.fail.path}")
    private Resource failPath;

    @Override
    public FlowExecutionStatus decide(JobExecution jobExecution, StepExecution stepExecution) {
        // Needs to check and implement
        return null;
    }

    @Bean(name = ETLConstants.IMPORT_CALL_LOG_JOB + "-BEAN")
    public Job startJob() {
        return jobBuilderFactory.get(ETLConstants.IMPORT_CALL_LOG_JOB)
                .incrementer(new RunIdIncrementer())
                .start(step())
                .build();
    }

    @Bean
    public Step step() {
        return stepBuilderFactory.get(ETLConstants.IMPORT_CALL_LOG_JOB + "-STEP-1")
                .<CallLog, CallLog>chunk(5)
                .reader(excelCallLogReader())
                .processor(processor())
                .writer(writer())
                .build();
    }

    @Bean
    public ItemReader<CallLog> excelCallLogReader() {
        PoiItemReader<CallLog> reader = new PoiItemReader<>();
        reader.setLinesToSkip(1);
        reader.setResource(inboundPath);
        reader.setRowMapper(rowMapper());
        return reader;
    }

    private RowMapper<CallLog> rowMapper() {
        return new CallLogExcelRowMapper();
    }

    @Bean
    public ItemProcessor<CallLog, CallLog> processor() {
        return new CallLogItemProcessor();
    }

    @Bean
    public ItemWriter<? super CallLog> writer() {
        return new CallLogItemWriter();
    }

}

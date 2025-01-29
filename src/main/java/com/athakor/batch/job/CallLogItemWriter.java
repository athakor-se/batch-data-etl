package com.athakor.batch.job;

import com.athakor.batch.entity.CallLog;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

/**
 * athakor
 */
public class CallLogItemWriter implements ItemWriter<CallLog> {

    @Override
    public void write(List<? extends CallLog> callLogs) {
        // Maybe: Implement transfer logic with outbound, need to check
    }

}

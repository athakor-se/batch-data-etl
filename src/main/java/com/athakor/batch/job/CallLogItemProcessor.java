package com.athakor.batch.job;

import com.athakor.batch.entity.CallLog;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.BeanUtils;

/**
 * athakor
 */
public class CallLogItemProcessor implements ItemProcessor<CallLog, CallLog> {

    @Override
    public CallLog process(CallLog callLogDTO) {
        CallLog callLog = new CallLog();
        BeanUtils.copyProperties(callLogDTO, callLog);
        return callLog;
    }
}

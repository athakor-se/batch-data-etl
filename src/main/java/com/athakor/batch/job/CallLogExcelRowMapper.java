package com.athakor.batch.job;

import com.athakor.batch.entity.CallLog;
import org.springframework.batch.extensions.excel.RowMapper;
import org.springframework.batch.extensions.excel.support.rowset.RowSet;

/**
 * This is mapper class, which is used to map all columns in the current row with a data class.
 * <p>
 * athakor
 */
public class CallLogExcelRowMapper implements RowMapper<CallLog> {

    @Override
    public CallLog mapRow(RowSet rs) {
        String[] currentRow = rs.getCurrentRow();
        int column = 0;
        CallLog callLogDTO = CallLog.builder()
                .clientNumber(currentRow[column++])
                .justCallNumber(currentRow[column++])
                .dateCall(currentRow[column++])
                .timeCall(currentRow[column++])
                .direction(currentRow[column++])
                .durationSec(currentRow[column++])
                .answeredTimeSec(currentRow[column++])
                .teammate(currentRow[column++])
                .recordingURL(currentRow[column++])
                .secondaryRecordingURL(currentRow[column++])
                .notes(currentRow[column++])
                .callStatus(currentRow[column++])
                .missedCallType(currentRow[column++])
                .ratting(currentRow[column++])
                .dispositionCode(currentRow[column])
                .build();
        return callLogDTO;
    }

}

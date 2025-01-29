package com.athakor.batch.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * athakor
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CallLog {

    private String clientNumber;
    private String justCallNumber;
    private String dateCall;
    private String timeCall;
    private String direction;
    private String durationSec;
    private String answeredTimeSec;
    private String teammate;
    private String recordingURL;
    private String secondaryRecordingURL;
    private String notes;
    private String callStatus;
    private String missedCallType;
    private String ratting;
    private String dispositionCode;

}


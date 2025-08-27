package com.niyiment.dataquality.dto;

import lombok.Data;

import java.util.Map;

@Data
public class DatimReport {
    private Integer txNew;
    private Integer txCurr;
    private Integer previousTxCurr;
    private Integer txRtt;
    private Integer txMl;
    private Integer txtDsd;
    private Integer artInitiations;
    private Map<String, Integer> ageDisaggregation;
}

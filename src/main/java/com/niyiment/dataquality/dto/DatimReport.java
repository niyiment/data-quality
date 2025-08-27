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
    private Integer txDsd;
    private Integer htsTst;
    private Integer htsPos;
    private Integer artInitiations;
    private Integer totalPatients;
    private Map<String, Integer> ageDisaggregation;
}

package com.shishir.reports.service;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface ReportEngine {
    void execute(String jsonFilterParams) throws JsonProcessingException;

    void preProcess();
    void process();
    void postProcess();
}

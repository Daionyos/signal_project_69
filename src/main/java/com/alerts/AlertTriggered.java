package com.alerts;

public class AlertTriggered extends Alert{
    public AlertTriggered(String patientId, String condition, long timestamp, int priority){
        super( patientId,  condition,  timestamp);
    }
}

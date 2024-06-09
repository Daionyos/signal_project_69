package com.alerts;

public class AlertECGData extends Alert {
    public AlertECGData(String patientId, String condition, long timestamp, int priority){
        super( patientId,  condition,  timestamp);
    }
}

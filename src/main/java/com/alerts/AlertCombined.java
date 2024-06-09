package com.alerts;

public class AlertCombined extends Alert {
    public AlertCombined(String patientId, String condition, long timestamp, int priority){
        super( patientId,  condition,  timestamp);
    }
}

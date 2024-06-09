package com.alerts;

public class AlertBloodPressure extends Alert {
    public AlertBloodPressure(String patientId, String condition, long timestamp, int priority){
        super( patientId,  condition,  timestamp);
    }
    
}

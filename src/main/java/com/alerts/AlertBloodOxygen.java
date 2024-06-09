package com.alerts;


public class AlertBloodOxygen extends Alert {
    public AlertBloodOxygen(String patientId, String condition, long timestamp, int priority){
        super( patientId,  condition,  timestamp);
    }
    
}

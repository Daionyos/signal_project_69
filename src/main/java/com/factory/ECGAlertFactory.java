package com.factory;


import com.alerts.Alert;
import com.alerts.AlertECGData;

public class ECGAlertFactory extends AlertFactory {
    
    public Alert createAlert(String patientId, String condition, long timestamp, int priority){
        return new AlertECGData(patientId, condition, timestamp, priority);
    }
}
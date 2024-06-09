package com.factory;


import com.alerts.Alert;
import com.alerts.AlertCombined;

public class CombinedAlertFactory extends AlertFactory {
    
    public Alert createAlert(String patientId, String condition, long timestamp, int priority){
        return new AlertCombined(patientId, condition, timestamp, priority);
    }
}
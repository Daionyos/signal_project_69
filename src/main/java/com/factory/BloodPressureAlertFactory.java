package com.factory;

import com.alerts.Alert;
import com.alerts.AlertBloodPressure;

public class BloodPressureAlertFactory extends AlertFactory {
    //bloodpressure alert
    
    public Alert createAlert(String patientId, String condition, long timestamp, int priority){
        return new AlertBloodPressure(patientId, condition, timestamp, priority);
    }
}

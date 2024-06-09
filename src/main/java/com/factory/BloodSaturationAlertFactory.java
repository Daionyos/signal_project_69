package com.factory;


import com.alerts.*;

public class BloodSaturationAlertFactory extends AlertFactory {
    
    public Alert createAlert(String patientId, String condition, long timestamp, int priority){
        return new AlertBloodOxygen(patientId, condition, timestamp, priority);
    }
}

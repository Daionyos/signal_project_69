package com.factory;

import com.alerts.*;

public class BloodSaturationAlertFactory extends AlertFactory {
    // This class extends the AlertFactory class and specializes in Blood Saturation alerts.

    // Method to create a Blood Saturation Alert object.
    public Alert createAlert(String patientId, String condition, long timestamp, int priority){
        // Create and return a new AlertBloodOxygen object.
        return new AlertBloodOxygen(patientId, condition, timestamp, priority);
    }
}

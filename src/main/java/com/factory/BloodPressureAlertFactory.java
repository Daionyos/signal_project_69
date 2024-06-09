package com.factory;

import com.alerts.Alert;
import com.alerts.AlertBloodPressure;

public class BloodPressureAlertFactory extends AlertFactory {
    // This class extends the AlertFactory class and specializes in  Blood Pressure alerts.

    // Method to create a Blood Pressure Alert object with the given parameters.
    public Alert createAlert(String patientId, String condition, long timestamp, int priority){
        // Create and return a new AlertBloodPressure object.
        return new AlertBloodPressure(patientId, condition, timestamp, priority);
    }
}

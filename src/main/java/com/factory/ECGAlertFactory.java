package com.factory;

import com.alerts.Alert;
import com.alerts.AlertECGData;

public class ECGAlertFactory extends AlertFactory {
    // This class extends the AlertFactory class and specializes in ECG data alerts.

    // Method to create an ECG Data Alert object.
    public Alert createAlert(String patientId, String condition, long timestamp, int priority){
        // Create and return a new AlertECGData object.
        return new AlertECGData(patientId, condition, timestamp, priority);
    }
}

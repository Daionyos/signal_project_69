package com.factory;

import com.alerts.Alert;

public class AlertFactory {
    // This class represents a factory for creating Alert objects.
    // Method that creates an Alert object.
    public Alert createAlert(String patientId, String condition, long timestamp){
        // Create and return a new Alert object.
        return new Alert(patientId, condition, timestamp);
    }
}

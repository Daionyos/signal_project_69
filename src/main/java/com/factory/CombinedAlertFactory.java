package com.factory;

import com.alerts.*;

public class CombinedAlertFactory extends AlertFactory {
    // This class extends the AlertFactory class and specializes in Combined alerts.

    // Method to create a Combined Alert object.
    public Alert createAlert(String patientId, String condition, long timestamp, int priority){
        // Create and return a new AlertCombined object.
        return new AlertCombined(patientId, condition, timestamp, priority);
    }
}

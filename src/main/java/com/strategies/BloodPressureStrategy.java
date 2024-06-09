package com.strategies;

import com.alerts.Alert;
import com.data_management.Patient;

public class BloodPressureStrategy implements AlertStrategy {
    @Override
    public Alert checkAlert(Patient patient){
        return null;
    }
    
}

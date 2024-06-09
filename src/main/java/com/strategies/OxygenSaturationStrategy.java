package com.strategies;

import com.alerts.Alert;
import com.data_management.Patient;

public class OxygenSaturationStrategy implements AlertStrategy {
    @Override
    public Alert checkAlert(Patient patient){
        return null;
    }
}

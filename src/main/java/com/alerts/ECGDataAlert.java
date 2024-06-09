package com.alerts;

import java.util.ArrayList;
import java.util.List;

import com.data_management.PatientRecord;

public class ECGDataAlert {
    // Default constructor
    public ECGDataAlert(){}

    /**
     Checks for abnormalities in ECG data.
     @param list of patient records
     @return Alert object if abnormality is detected, null otherwise
     */
    public Alert check(List<PatientRecord> records){
        // Get ECG records from the provided list
        List<PatientRecord> ecg = sorting(records, "ECG");
        // Initialize checking variable to null
        Alert checking = null;
        // TODO: Add logic to analyze ECG data
        // (Implementation of ECG analysis logic is missing)
        return checking;
    }

    /**
     Sorts patient records according to their record type.
     @param records List of patient records
     @param parameter Record type to filter
     @return List of patient records with specified record type
     */
    public List<PatientRecord> sorting(List<PatientRecord> records, String parameter){
        List<PatientRecord> list = new ArrayList<>();
        // Iterate through all records
        for(int i = 0; i < records.size(); i++){
            // Check if the record type matches the specified parameter
            if(records.get(i).getRecordType().equals(parameter)){
                // Add the record to the list if it matches
                list.add(records.get(i));
            }
        }
        return list;
    }

    /**
     Detects abnormal ECG data
     calculated by comparing the average of last 10 ECG data to current ECG value
     @param records List of ECG records
     @return Alert object if abnormality is detected, null otherwise
     */
    public Alert abnormalECG(List<PatientRecord> records){
        for(int i = 11; i < records.size(); i++){
            double sum = 0;
            for(int j = (i - 11); j < i; j++){
                sum += records.get(j).getMeasurementValue();
            }
            // Calculate the average of the last 10 ECG values
            double average = sum / 10;

            // Check if the absolute difference between the current ECG value
            // and the average of the last 10 values is greater than or equal to 0.5
            if(Math.abs(records.get(i).getMeasurementValue() - average) >= 0.5){
                // Return an Alert object indicating abnormal ECG data
                return new Alert(null, null, i);
            }
        }
        // Return null if no abnormality is detected
        return null;
    }
}

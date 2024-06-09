package com.alerts;

import java.util.ArrayList;
import java.util.List;

import com.data_management.PatientRecord;

public class BloodPressureAlert {

    public BloodPressureAlert(){
    }
    /**
      Checks the patient records for any blood pressure alerts.
      @param records list of patientRecords  to check.
      @return An alert if any condition is met, otherwise returns null.
     */
    public Alert check(List <PatientRecord> records){
        List <PatientRecord> bloodRecordsSystolic = sorting(records, "DiastolicPressure");
        List <PatientRecord> bloodRecordsDiastolic = sorting(records, "SystolicPressure");
        Alert checking = trend(bloodRecordsDiastolic);
        if(checking != null){
            return checking;
        }
        checking = trend(bloodRecordsSystolic);
        if(checking != null){
            return checking;
        }
        checking = diastolicCheck(bloodRecordsDiastolic);
        if(checking != null){
            return checking;
        }
        checking = systolicCheck(bloodRecordsSystolic);
        if(checking != null){
            return checking;
        }
        return null;
    }
    /**
     Filters the patientRecords by the given parameter.
     @param records List of PatientRecord objects.
     @param parameter The parameter to filter by.
     @return A list of PatientRecord objects that are filtered.
     */
    public List <PatientRecord> sorting(List <PatientRecord> records, String parameter){
        List <PatientRecord> list = new ArrayList<>();
        for(int i = 0; i < records.size(); i++){
            if(records.get(i).getRecordType().equals(parameter)){
                list.add(records.get(i));
            }
        }
        return list;

    }
    /**
     Checks for a trend in the patient records, returns an alert if a trend is found.
     @param records List of PatientRecord objects to check.
     @return Alert if a trend is found, otherwise null.
     */
    private Alert trend(List <PatientRecord> records){
        Alert trending = null;
        for(int i = 2; i < records.size(); i++){
            boolean increasing = (records.get(i - 2).getMeasurementValue() + 10.0) < records.get(i - 1).getMeasurementValue() && (records.get(i - 1).getMeasurementValue() +10.0) < records.get(i).getMeasurementValue();
            if (increasing) {
                trending  = new Alert(Integer.toString(records.get(i).getPatientId()),"Trend Alert: Blood presure increasing", records.get(i).getTimestamp());
                return trending;
            }
            boolean decreasing = (records.get(i - 2).getMeasurementValue() + 10.0) > records.get(i - 1).getMeasurementValue() && (records.get(i - 1).getMeasurementValue() +10.0) > records.get(i).getMeasurementValue();
            if (decreasing) {
                trending  = new Alert(Integer.toString(records.get(i).getPatientId()),"Trend Alert: Blood presure decreasing", records.get(i).getTimestamp());
                return trending;
            }
        }
        return trending;
    }
    /**
     Checks systolic blood pressure records of each patient against predefined thresholds.
     @param records List of PatientRecord objects to check.
     @return Alert if any systolic pressure is it goes over the limit, otherwise null.
     */
    private Alert systolicCheck(List <PatientRecord> records){
        for(int i = 0; i < records.size(); i++){
            if(records.get(i).getMeasurementValue() < 90.0 || records.get(i).getMeasurementValue() > 180.0){
                return new Alert(Integer.toString(records.get(i).getPatientId()), "Critical Threshold Alert: Systolic Pressure Alert", records.get(i).getTimestamp());
            }
        }
        return null;
    }
    /**
     Checks diastolic blood pressure records of each patient against predefined thresholds.
     @param records List of PatientRecord objects to check.
     @return Alert if any diastolic pressure is it goes over the limit, otherwise null.
     */
    private Alert diastolicCheck(List <PatientRecord> records){
        for(int i = 0; i < records.size(); i++){
            if(records.get(i).getMeasurementValue() < 60.0 || records.get(i).getMeasurementValue() > 120.0){
                return new Alert(Integer.toString(records.get(i).getPatientId()), "Critical Threshold Alert: Dialostic Pressure Alert", records.get(i).getTimestamp());
            }
        }
        return null;
    }
}

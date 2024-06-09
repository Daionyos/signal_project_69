package com.alerts;

import java.util.ArrayList;
import java.util.List;

import com.data_management.PatientRecord;

public class BloodPressureAlert {
    public BloodPressureAlert(){
    }
    public Alert check(List <PatientRecord> records){
        List <PatientRecord> bloodRecordsSystolic = new ArrayList<>();
        List <PatientRecord> bloodRecordsDiastolic = new ArrayList<>();

        for(int i = 0; i < records.size(); i++){
            if(records.get(i).getRecordType().equals("DiastolicPressure")){
                bloodRecordsDiastolic.add(records.get(i));
            }
            else if(records.get(i).getRecordType().equals("SystolicPressure")){
                bloodRecordsSystolic.add(records.get(i));
            }
            else{continue;}
        }
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
    private Alert systolicCheck(List <PatientRecord> records){
        for(int i = 0; i < records.size(); i++){
            if(records.get(i).getMeasurementValue() < 90.0 || records.get(i).getMeasurementValue() > 180.0){
                return new Alert(Integer.toString(records.get(i).getPatientId()), "Critical Threshold Alert: Systolic Pressure Alert", records.get(i).getTimestamp());
            }
        }
        return null;
    }
    private Alert diastolicCheck(List <PatientRecord> records){
        for(int i = 0; i < records.size(); i++){
            if(records.get(i).getMeasurementValue() < 60.0 || records.get(i).getMeasurementValue() > 120.0){
                return new Alert(Integer.toString(records.get(i).getPatientId()), "Critical Threshold Alert: Dialostic Pressure Alert", records.get(i).getTimestamp());
            }
        }
        return null;
    }
}

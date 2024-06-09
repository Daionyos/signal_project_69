package com.alerts;


import java.util.ArrayList;
import java.util.List;

import com.data_management.PatientRecord;

public class CombinedAlert {
    public CombinedAlert(){}
    /**
     Checks for combined alerts, depending on blood pressure and saturation levels of the patient.
     @param records as list of patient records
     @return an Alert object if combined alert condition is met, otherwise returns null
     */
    public Alert check(List <PatientRecord> records){
        // Sort records based on systolic pressure and blood saturation
        List <PatientRecord> bloodPressure = sorting(records,"SystolicPressure");
        List <PatientRecord> saturation = sorting(records, "Saturation");
        Alert checking = combinedCheck(bloodPressure, saturation);
        if(checking != null){return checking;}else{return null;}
        
    }
    /**
     Sorts patient records based on the record type.
     @param records as list of patient records
     @param parameter the Record type to filter
     @return a list of patient records with specified record type
     */
    public List <PatientRecord> sorting(List <PatientRecord> records, String parameter){
        List <PatientRecord> list = new ArrayList<>();
        for(int i = 0; i < records.size(); i++){
            if(records.get(i).getRecordType().equals(parameter) ){
                list.add(records.get(i));
            }
        }
        return list;

    }
    /**
     Performs a combined check for blood pressure and saturation alerts.
     @param sys List of systolic pressure records
     @param sat List of saturation records
     @return Alert object if combined alert condition is met, otherwise returns null
     */
    public Alert combinedCheck(List <PatientRecord> sys, List <PatientRecord> sat){
        List <PatientRecord> sysAlerts = new ArrayList<>();
        List <PatientRecord> satAlerts = new ArrayList<>();

        for(int i = 0; i < sys.size(); i++ ){
            if(sys.get(i).getMeasurementValue() < 90.0){
                sysAlerts.add(sys.get(i));
            }
        }
        for(int i = 0; i < sat.size(); i++ ){
            if(sat.get(i).getMeasurementValue() < 90.0){
                satAlerts.add(sat.get(i));
            }
        }
        for(int i = 0; i < sysAlerts.size(); i++){
            for(int j = 0; j < satAlerts.size(); j++){
                if(Math.abs(sysAlerts.get(i).getTimestamp() - satAlerts.get(j).getTimestamp()) >= 120000){
                    return new Alert(Integer.toString(sysAlerts.get(i).getPatientId()), "Combined Alert: Hypotensive Hypoxemia Alert", sysAlerts.get(i).getTimestamp());
                }
            }
        }
        return null;
    }

    

    
}

package com.alerts;


import java.util.ArrayList;
import java.util.List;

import com.data_management.PatientRecord;

public class CombinedAlert {
    public CombinedAlert(){}

    public Alert check(List <PatientRecord> records){
        List <PatientRecord> bloodPressure = sorting(records,"SystolicPressure");
        List <PatientRecord> saturation = sorting(records, "Saturation");
        Alert checking = combinedCheck(bloodPressure, saturation);
        if(checking != null){return checking;}else{return null;}
        
    }
    public List <PatientRecord> sorting(List <PatientRecord> records, String parameter){
        List <PatientRecord> list = new ArrayList<>();
        for(int i = 0; i < records.size(); i++){
            if(records.get(i).getRecordType().equals(parameter) ){
                list.add(records.get(i));
            }
        }
        return list;

    }
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

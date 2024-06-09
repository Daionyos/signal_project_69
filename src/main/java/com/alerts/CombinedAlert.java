package com.alerts;


import java.util.ArrayList;
import java.util.List;

import com.data_management.PatientRecord;

public class CombinedAlert {
    public CombinedAlert(){}

    public Alert check(List <PatientRecord> records){
        List <PatientRecord> bloodPressure = sorting(records,"SystolicPressure");
        List <PatientRecord> saturation = sorting(records, "Saturation");
        
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
    public combinedCheck(List <PatientRecord> sys, List <PatientRecord> sat){
        
    }

    

    
}

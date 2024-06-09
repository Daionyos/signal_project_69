package com.alerts;

import java.util.ArrayList;
import java.util.List;

import com.data_management.PatientRecord;

public class TriggeredAlert {
    public TriggeredAlert(){}

    public check(List <PatientRecord> records){
        List <PatientRecord> alerts = sorting(records, "Alert");
        Alert checking = triggered(alerts);



    }
    public Alert triggered(List<PatientRecord> records){
        for(int i = 0; i < records.size(); i++){
            if()
        }

    }

    public List <PatientRecord> sorting(List <PatientRecord> records, String parameter){
        List <PatientRecord> list = new ArrayList<>();
        for(int i = 0; i < records.size(); i++){
            if(records.get(i).getRecordType().equals(parameter)){
                list.add(records.get(i));
            }
        }
        return list;

    }
    
}

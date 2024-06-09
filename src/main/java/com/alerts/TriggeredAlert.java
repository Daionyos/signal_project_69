package com.alerts;

import java.util.ArrayList;
import java.util.List;

import com.data_management.PatientRecord;

public class TriggeredAlert {
    public TriggeredAlert(){}

    public Alert check(List <PatientRecord> records){
        List <PatientRecord> alerts = sorting(records, "Alert");
        Alert checking = triggered(alerts);
        return checking;
    }
    public Alert triggered(List<PatientRecord> records){
        for(int i = 0; i < records.size(); i++){
            if(records.get(i).getMeasurementValue() == 0){
                int patient = records.get(i).getPatientId();
                long time = records.get(i).getPatientId();
                boolean solved = false;

                for(int j = i; j < records.size(); j++){
                    if(records.get(j).getMeasurementValue() == 1){
                        solved = true;
                        break;
                    }
                }
                if(!solved){
                    return new Alert(Integer.toString(records.get(i).getPatientId()), "Patient Alert Triggered", time);
                }
            }
        }
        return null;
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

package com.alerts;

import java.util.ArrayList;
import java.util.List;

import com.data_management.PatientRecord;

public class BloodSaturationAlert {
    public BloodSaturationAlert(){}

    public Alert check(List <PatientRecord> records){
        Alert checking = null;
        List <PatientRecord> saturation = sorting(records, "Saturation" );
        checking = lowSaturation(saturation);
        if(checking != null){
            return checking;
        }
        checking = rapidDrop(saturation);
        if(checking != null){
            return checking;
        }
        return checking;
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
    public Alert lowSaturation(List <PatientRecord> records){
        
        for(int i = 0; i < records.size(); i++){
            if(records.get(i).getMeasurementValue() < 92.0){
                return new Alert(Integer.toString(records.get(i).getPatientId()), "Low Saturation Alert", records.get(i).getTimestamp());
            }
        }
        return null;
    }
    public Alert rapidDrop(List <PatientRecord> records){
        for(int i = 0; i < records.size();i++){
            for(int j = i; j > 0; j--){
                if(records.get(i).getTimestamp() - records.get(j).getTimestamp() >600000){break;}
                if(records.get(j).getMeasurementValue() - records.get(i).getMeasurementValue() >= 5){
                    return new Alert(Integer.toString(records.get(i).getPatientId()), "Rapid Drop Alert: Saturation", records.get(i).getTimestamp());
                }
            }
        }

        return null;
    }
}

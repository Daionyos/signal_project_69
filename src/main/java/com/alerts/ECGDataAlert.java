package com.alerts;

import java.util.ArrayList;
import java.util.List;

import com.data_management.PatientRecord;

public class ECGDataAlert {
    public ECGDataAlert(){}
    public Alert check(List<PatientRecord> records){
        List<PatientRecord> ecg = sorting(records, "ECG");
        Alert checking = null;


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
    public Alert abnormalECG(List<PatientRecord> records){

        for(int i = 11; i < records.size(); i++){
            double sum = 0;
            // takes the ten values before the considered iteration
            for(int j = (i - 11); j < i; j++){
                sum += records.get(j).getMeasurementValue();
            }
            double average = sum/10;

            if(Math.abs(records.get(i).getMeasurementValue() - average) >=0.5){
                return new Alert(null, null, i);
            }



        }
        return null;
    }
}

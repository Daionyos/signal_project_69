package com.alerts;

//This class represents an alert related to blood oxygen levels by extending the base Alert class.
public class AlertBloodOxygen extends Alert {
    //Constructor to initialize a blood oxygen alert with patient ID, condition, timestamp, and priority.
    public AlertBloodOxygen(String patientId, String condition, long timestamp, int priority){
        super(patientId, condition, timestamp);
    }
}

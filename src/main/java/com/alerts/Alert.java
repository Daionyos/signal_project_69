package com.alerts;

//Represents an alert entity containing information about a specific condition for a patient at a given timestamp.
public class Alert {
    private String patientId; // The ID of the patient associated with the alert.
    private String condition; // The condition triggering the alert.
    private long timestamp;   // The timestamp indicating when the alert occurred.

    //Constructor to initialize an alert with patient ID, condition, and timestamp.
    public Alert(String patientId, String condition, long timestamp) {
        this.patientId = patientId;
        this.condition = condition;
        this.timestamp = timestamp;
    }

    //Getter method to retrieve the patient ID associated with the alert.
    public String getPatientId() {
        return patientId;
    }

    //Getter method to retrieve the condition triggering the alert.
    public String getCondition() {
        return condition;
    }

    //Getter method to retrieve the timestamp of the alert.
    public long getTimestamp() {
        return timestamp;
    }
}

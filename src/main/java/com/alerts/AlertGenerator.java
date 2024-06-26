package com.alerts;


import java.util.*;

import com.data_management.DataStorage;
import com.data_management.Patient;
import com.data_management.PatientRecord;

/**
 * The {@code AlertGenerator} class is responsible for monitoring patient data
 * and generating alerts when certain predefined conditions are met. This class
 * relies on a {@link DataStorage} instance to access patient data and evaluate
 * it against specific health criteria.
 */
public class AlertGenerator {
    private DataStorage dataStorage;

    /**
     * Constructs an {@code AlertGenerator} with a specified {@code DataStorage}.
     * The {@code DataStorage} is used to retrieve patient data that this class
     * will monitor and evaluate.
     *
     * @param dataStorage the data storage system that provides access to patient
     *                    data
     */
    public AlertGenerator(DataStorage dataStorage) {
        this.dataStorage = dataStorage;
    }

    /**
     * Evaluates the specified patient's data to determine if any alert conditions
     * are met. If a condition is met, an alert is triggered via the
     * {@link #triggerAlert}
     * method. This method should define the specific conditions under which an
     * alert
     * will be triggered.
     *
     * @param patient the patient data to evaluate for alert conditions
     */
    public void evaluateData(Patient patient) {
        List <Alert> alerting = new ArrayList <>();
        // Implementation goes here
        List <PatientRecord> records = dataStorage.getRecords(patient.getPatientId(), 0, System.currentTimeMillis());
        // blood pressure check
        BloodPressureAlert bloodPressure = new BloodPressureAlert();
        alerting.add( bloodPressure.check(records));
        
        //2. Blood Saturation Data Alerts 
        BloodSaturationAlert bloodSaturation = new BloodSaturationAlert();
        alerting.add( bloodSaturation.check(records));
        //3. Combined Alert: Hypotensive Hypoxemia Alert
        CombinedAlert combined = new CombinedAlert();
        alerting.add(combined.check(records));
        //4. ECG 
        ECGDataAlert ecgalerts = new ECGDataAlert();
        alerting.add(ecgalerts.check(records));
        // 5. triggered alerts
        TriggeredAlert trigger = new TriggeredAlert();
        alerting.add(trigger.check(records));
        for(int i = 0 ; i < alerting.size(); i++){
            if(alerting.get(i) != null){
                triggerAlert(alerting.get(i));
            }
        }
        // 
    }

    /**
     * Triggers an alert for the monitoring system. This method can be extended to
     * notify medical staff, log the alert, or perform other actions. The method
     * currently assumes that the alert information is fully formed when passed as
     * an argument.
     *
     * @param alert the alert object containing details about the alert condition
     */
    private void triggerAlert(Alert alert) {
        // Implementation might involve logging the alert or notifying staff
        System.out.println("ALERT: " + alert.getCondition() + ", PATIENT ID: " + alert.getPatientId() + ", at time: " + new Date (alert.getTimestamp()));
    }
}

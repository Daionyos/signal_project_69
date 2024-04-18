package com.cardio_generator.outputs;


/*
* Method that takes in parameters to later be used for outputting data with different strategies.
* @param patientId identifier for patient 
* @param timestamp time stamp in millseconds 
* @param label label of the data 
* @param radomnly generated patient data 
* @author Tom Pepels 🤘
*/
public interface OutputStrategy {
    void output(int patientId, long timestamp, String label, String data);
}

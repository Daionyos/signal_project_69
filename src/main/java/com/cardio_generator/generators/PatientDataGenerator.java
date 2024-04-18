package com.cardio_generator.generators;

import com.cardio_generator.outputs.OutputStrategy;
/*
 * This is an interface class for generating patient data.
 * 
 */
public interface PatientDataGenerator {
/**
 * This is an method is responsible for generating patient data for the patientID and outputs it using the given output strategy.
* @param patientId       The identifier of the patient
* @param outputStrategy  The output strategy used to output the alerts.
*/
    void generate(int patientId, OutputStrategy outputStrategy);
}

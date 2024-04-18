package com.cardio_generator.generators;

import java.util.Random;

import com.cardio_generator.outputs.OutputStrategy;
/**
 * The AlertGenerator class is responsible for generating alerts for users.
 * Alerts can be triggered or resolved based on defined probabilities.
 * @aut
 */
public class AlertGenerator implements PatientDataGenerator {

    public static final Random randomGenerator = new Random();
    //AlertStates -> alertStates, variable names should start with lowercase
    private boolean[] alertStates; // false = resolved, true = pressed
    /**
     * Constructs an AlertGenerator object with the specified number of patients.
     * @param patientCount The number of patients for which alerts need to be generated.
     */
    public AlertGenerator(int patientCount) {
        alertStates = new boolean[patientCount + 1];
    }
    /**
     * Generates alerts patients and outputs them using an output strategy.
     * @param patientId The identifier of the patient
     * @param outputStrategy  The output strategy used to output the alerts.
     */
    @Override
    public void generate(int patientId, OutputStrategy outputStrategy) {
        try {
            if (alertStates[patientId]) {
                // declaring resolveProbability as final because its a fixed number to use in code
                final double resolveProbability = 0.9;
                if (randomGenerator.nextDouble() < resolveProbability) { // 90% chance to resolve
                    alertStates[patientId] = false;
                    // Output the alert
                    outputStrategy.output(patientId, System.currentTimeMillis(), "Alert", "resolved");
                }
            } else {
                //Lambda-> lambda variable names should start with lowercase
                //lambda should be initialized as final if we are using it as a constant
                final double lambda = 0.1; // Average rate (alerts per period), adjust based on desired frequency
                double p = -Math.expm1(-lambda); // Probability of at least one alert in the period
                boolean alertTriggered = randomGenerator.nextDouble() < p;

                if (alertTriggered) {
                    alertStates[patientId] = true;
                    // Output the alert
                    outputStrategy.output(patientId, System.currentTimeMillis(), "Alert", "triggered");
                }
            }
        } catch (Exception e) {
            System.err.println("An error occurred while generating alert data for patient " + patientId);
            e.printStackTrace();
        }
    }
}

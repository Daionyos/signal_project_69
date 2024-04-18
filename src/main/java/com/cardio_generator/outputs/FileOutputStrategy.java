package com.cardio_generator.outputs;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.ConcurrentHashMap;
/*
 * This Class implements the Output Strategy interface.
 * This class is responsible for printing out files of patient information to the selected directory 
 * 
 * intially it takes a directory string name  when an object of the class in constructed,
 * and then when the overriding method output is called it trys to create a directory (named by the basedirectory variable)
 * it then creates a printwriter that then prints all the patient information and data to the file.
 * 
 * @author Tom Pepels 🤘

*/

public class FileOutputStrategy implements OutputStrategy {

    private String baseDirectory; // Changed variable name to start with lowercase

    public final ConcurrentHashMap<String, String> fileMap = new ConcurrentHashMap<>(); // Changed variable name to camelCase
    /*
         * Contructor of class
         * @param baseDirectory  A string representation of the directory
    */
    public FileOutputStrategy(String baseDirectory) {  
        /*
         * When initialzed selects the directory name where the information will get printed out
         * @return A string representation of the directory
        */
        this.baseDirectory = baseDirectory; // removed an emtpy line above 
    }
    /*
         * overriden interface class 
         * @param patientId identifier for patient 
         * @param timestamp time stamp in millseconds 
         * @param label label of the data 
         * @param radomnly generated patient data 
    */
    @Override
    public void output(int patientId, long timestamp, String label, String data) {
        /*
         * Creates and adds files to the directory and then prints out the information to the files
         * @return patient information to the files in the directory
         *  IO exception error in case of error in creating a directory 
         * if an exception occurs during writing, it catches the exception and prints an error message to the standard error stream.
        */
        try {
            // Create the directory
            Files.createDirectories(Paths.get(baseDirectory));
        } catch (IOException e) {
            System.err.println("Error creating base directory: " + e.getMessage());
            return;
        }
        // Set the filePath variable
        String filePath = fileMap.computeIfAbsent(label, k -> Paths.get(baseDirectory, label + ".txt").toString()); // Changed variable name to start with lowercase

        // Write the data to the file
        try (PrintWriter out = new PrintWriter(
                Files.newBufferedWriter(Paths.get(filePath), StandardOpenOption.CREATE, StandardOpenOption.APPEND))) {
            out.printf("Patient ID: %d, Timestamp: %d, Label: %s, Data: %s%n", patientId, timestamp, label, data);
        } catch (Exception e) {
            System.err.println("Error writing to file " + filePath + ": " + e.getMessage());
        }
    }
}
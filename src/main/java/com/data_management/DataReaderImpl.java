package com.data_management;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DataReaderImpl implements DataReader {
    private String filePath;

    /**
     Constructs a dataReader with the specified file path.
     @param filePath the path to the CSV containing the data
     */
    public DataReaderImpl(String filePath) {
        this.filePath = filePath;
    }

    /**
     Reads data from given source and stores it in the data storage.
     @param dataStorage the storage where data will be stored
     @throws IOException if there is an error reading the data
     */
    @Override
    public void readData(DataStorage dataStorage) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length == 4) {
                    int patientId = Integer.parseInt(values[0]);
                    double measurementValue = Double.parseDouble(values[1]);
                    String recordType = values[2];
                    long timestamp = Long.parseLong(values[3]);
                    dataStorage.addPatientData(patientId, measurementValue, recordType, timestamp);
                }
            }
        }
    }
}

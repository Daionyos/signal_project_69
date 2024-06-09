package com.data_management;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;

public class DataReaderImpl implements DataReader {
    private String filePath;

    /**
     Constructs a datareader with the specified file path.
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

    /**
     Loads data since last data into data storage
     @throws IOException if the input throws on read
     */
    @Override
    public void update() throws IOException {
        // Implementation for update method
        // For this example, let's assume it just calls readData again.
        // This should be changed based on actual update logic required.
    }

    /**
     @param data string to format
     @return {patientId, timestamp, label, data}
     */
    @Override
    public String[] format(String data) {
        return data.split(",");
    }

    /**
     Adapts given amount of data
     @param reader reader for data input
     @param dataStorage storage to write to
     @param startLine line to start reading
     @throws IOException if input reader throws on read
     @return last line that has been read + 1
     */
    @Override
    public int decodeData(Reader reader, DataStorage dataStorage, int startLine) throws IOException {
        int charVal;
        StringBuilder dataString = new StringBuilder();
        while ((charVal = reader.read()) != -1) {
            dataString.append((char) charVal);
        }
        reader.close();
        // Changes the string to a format that data can be read
        String[] lines = dataString.toString().split("\n");
        lines = Arrays.copyOfRange(lines, startLine, lines.length);
        if (lines.length == 1 && lines[0].equals("")) {
            return 0;
        }
        for (String line : lines) {
            String[] fields = format(line);
            if (fields.length != 4) {
                throw new IOException("Invalid data format: too many properties");
            }
            try {
                int patientId = Integer.parseInt(fields[0]);
                long timestamp = Long.parseLong(fields[1]);
                String label = fields[2];
                String dataFull = fields[3];
                double data = 0;
                if (dataFull.contains("%")) {
                    data = Double.parseDouble(dataFull.substring(0, dataFull.length() - 1));
                    data /= 100;
                } else {
                    data = Double.parseDouble(dataFull);
                }
                dataStorage.addPatientData(patientId, data, label, timestamp);
            } catch (NumberFormatException e) {
                throw new IOException("Invalid data format: no proper number conversion possible");
            }
        }
        return lines.length;
    }

    /**
     Connect to a WebSocket server to receive real-time data
     @param dataStorage the storage where data will be stored
     @param serverUri the WebSocket server URI
     @throws IOException if there is an error connecting to the WebSocket server
     */
    @Override
    public void connectWebSocket(DataStorage dataStorage, String serverUri) throws IOException {
        // WebSocket client implementation here
        // For example, you can use Java-WebSocket library to connect and receive data
    }
}

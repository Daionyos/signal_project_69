package com.data_management;

import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;

public interface DataReader {
    /**
     Reads data from a given source and stores it.
     @param dataStorage the storage where data will be stored
     @throws IOException if there is an error reading the data
     */
    void readData(DataStorage dataStorage) throws IOException;

    /**
     Loads data since last data into data storage
     @throws IOException if the input throws on read
     */
    void update() throws IOException;

    /**
     @param data string to format
     @return {patientId, timestamp, label, data}
     */
    String[] format(String data);

    /**
     Adapts given amount of data
     @param reader reader for data input
     @param dataStorage storage to write to
     @param startLine line to start reading
     @throws IOException if input reader throws on read
     @return last line that has been read + 1
     */
    default int decodeData(Reader reader, DataStorage dataStorage, int startLine) throws IOException {
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
    void connectWebSocket(DataStorage dataStorage, String serverUri) throws IOException;
}

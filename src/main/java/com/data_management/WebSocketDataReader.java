package com.data_management;

import java.net.URI;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.handshake.ServerHandshake;
import java.io.StringReader;
import java.io.Reader;
import java.io.IOException;

public class WebSocketDataReader extends WebSocketClient implements DataReader {
    private String datacontent = "";
    private int lineNumber = 0;
    private DataStorage dataStorage;
    public WebSocketDataReader(URI serverUri) {
        super(serverUri);
        super.connect();
    }

    public String[] format(String data) {
        return data.split(",");
    }

    @Override
    public void connectWebSocket(DataStorage dataStorage, String serverUri) throws IOException {

    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        System.out.println("New connection! ");
    }

    @Override
    public void onMessage(String message) {
        datacontent += message + "\n";
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        System.out.println("Closed connection!");
    }

    @Override
    public void onError(Exception ex) {
        ex.printStackTrace();
    }

    public void readData(DataStorage dataStorage) throws IOException{
        this.dataStorage = dataStorage;
        Reader reader = new StringReader(datacontent);
        lineNumber = decodeData(reader, dataStorage, 0);
    }

    public void update() throws IOException {
        Reader reader = new StringReader(datacontent);
        lineNumber = decodeData(reader, dataStorage, lineNumber);
    }
}
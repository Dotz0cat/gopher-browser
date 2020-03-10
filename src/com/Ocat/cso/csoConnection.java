package com.Ocat.cso;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;

public class csoConnection extends URLConnection {
    
    private Socket s;
    static int defaultPort = 105;

    public csoConnection(URL url) {
        super(url);
    }

    @Override
    public void connect() throws IOException {
        int port;
        
        if (url.getPort()!=defaultPort&&url.getPort()!=-1) port = url.getPort();
        else port = defaultPort;
        
        s = new Socket(url.getHost(), port);
        connected = true;
    }
    
    @Override
    public InputStream getInputStream() throws IOException {
        if (!connected) {
            connect();
        }
        return s.getInputStream();
    }
    
    @Override
    public OutputStream getOutputStream() throws IOException {
        if (!connected) {
            connect();
        }
        return s.getOutputStream();
    }
    
}

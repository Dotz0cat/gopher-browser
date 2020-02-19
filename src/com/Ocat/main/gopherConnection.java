package com.Ocat.main;

import java.io.*;
import java.net.*;

public class gopherConnection extends URLConnection {
    
    static int defaultPort = 70;
    private Socket s;
    
    protected gopherConnection(URL url) {
        super(url);
    }
    
    @Override
    public void connect() throws IOException {
        //funStuff();
        int port;
        System.out.println(url);
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

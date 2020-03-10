package com.Ocat.cso;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;

public class csoHandler extends URLStreamHandler{

    @Override
    protected URLConnection openConnection(URL url) {
        return new csoConnection(url);
    }
    
}

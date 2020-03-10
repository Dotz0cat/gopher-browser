package com.Ocat.main;

import java.net.*;

public class gopherHandler extends URLStreamHandler {
    
        @Override
        protected URLConnection openConnection(URL url) {
            return new gopherConnection(url);
        }
}

package com.Ocat.main;

import java.net.*;

public class gopherHandler extends URLStreamHandler {
    
        @Override
        protected URLConnection openConnection(URL url) {
            //need to make that
            return new gopherConnection(url);
        }
}

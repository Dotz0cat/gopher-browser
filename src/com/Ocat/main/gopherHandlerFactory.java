package com.Ocat.main;

import java.net.*;

public class gopherHandlerFactory implements URLStreamHandlerFactory {

    @Override
    public URLStreamHandler createURLStreamHandler(String protcol) {
        if ("gopher".equals(protcol)) {
            return new gopherHandler();
        }
        
        return null;
    }
    
}

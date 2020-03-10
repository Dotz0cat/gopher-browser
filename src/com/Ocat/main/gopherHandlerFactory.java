package com.Ocat.main;

import com.Ocat.cso.csoHandler;
import java.net.*;

public class gopherHandlerFactory implements URLStreamHandlerFactory {

    @Override
    public URLStreamHandler createURLStreamHandler(String protcol) {
        if ("gopher".equals(protcol)) {
            return new gopherHandler();
        }
        else if ("cso".equals(protcol)) {
            return new csoHandler();
        }
        
        return null;
    }
    
}

package com.Ocat.graphics;

import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;

public class secondPrintStream extends OutputStream {
    private JTextComponent jText;
    
    public secondPrintStream(JTextComponent text) {
        this.jText = text;
    }
    
    @Override
    public void write(int i) throws IOException {
        char[] a = {(char)i};
        String c = new String(a);
        try {
            jText.getDocument().insertString(jText.getDocument().getLength(), c, null);
        } catch (BadLocationException ex) {
            ex.printStackTrace();
        }
        jText.setCaretPosition(jText.getDocument().getLength());
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bug.detection.file;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.logging.Logger;
import org.openide.util.Exceptions;

/**
 * Generate txt Report from Bugs
 * @author karayel
 */
public class TextFile extends AbstractFile {
    
    private final static Logger log = Logger.getLogger(TextFile.class.getName());

    private PrintWriter writer;

    public TextFile() {
        super(".txt");
        try {
            writer = new PrintWriter(getFile());
            log.warning("Txt file has been created.");
        } catch (FileNotFoundException ex) {
            log.warning(ex.getMessage());
        }
    }
    /**
     * Adding string to file
     * @param text
     * @return 
     */
    public TextFile addText(String text) {
        writer.println(text);
        log.warning("Text has been added to text file");
        return this;
    }
    /**
     * Closing file
     */
    public void closeFile() {
        log.warning("Text file has been closed.");
        writer.close();
    }
}

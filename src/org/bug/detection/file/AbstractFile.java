/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bug.detection.file;

import java.io.File;

/**
 * 
 * @author karayel
 */
public abstract class AbstractFile{

    private File file;

    public AbstractFile() {

        file = new File(System.getProperty("user.home") + "/BugReport" + System.currentTimeMillis());

    }
    
    public AbstractFile(String fileName) {

        file = new File(System.getProperty("user.home") + "/BugReport"+fileName);

    }

    public File getFile() {
        return file;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bug.detection.ui;

import java.util.logging.Logger;
import org.openide.filesystems.FileObject;

/**
 *
 * @author karayel
 */
public class MyCustomFileObject{
    
    private final static Logger log = Logger.getLogger(MyCustomFileObject.class.getName());
    
    private final FileObject fileObject;

    public MyCustomFileObject(FileObject f) {
        this.fileObject = f;
        log.warning(MyCustomFileObject.class.getName()+" has been created.");
    }

    @Override
    public String toString() {
        String result =  fileObject.getName();
        if(fileObject.isData()){
            String extansion = fileObject.getExt();
            result += "."+extansion;
        }
        return result;
    }

    public FileObject getF() {
        return fileObject;
    }
}

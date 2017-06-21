/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bug.detection.core.bug.helper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import org.openide.util.Exceptions;

/**
 *
 * @author karayel
 */
public class FileHelper {
    /**
     * Reading data from the given source file
     * @param source
     * @return 
     */
    public static String readFile(String source){
        String s="";
        try(BufferedReader br = new BufferedReader(new FileReader(source))){
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            s = sb.toString();
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
        return s;
    }
}

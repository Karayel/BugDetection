/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bug.detection.core.bug.factory;

import java.util.Properties;
import org.bug.detection.config.BugProperties;
import org.bug.detection.core.bug.AbstractBug;

/**
 * 
 * @author karayel
 */
public interface AbstractFactory {
    
    Properties bugProperties = BugProperties.getInstance();
    
    public AbstractBug getBug(String source,String bugName);
}

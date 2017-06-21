/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bug.detection.core.bug.factory;

import org.bug.detection.config.GeneralProperties;
import org.bug.detection.core.bug.AbstractBug;
import org.bug.detection.core.bug.pattern.DivZeroBug;
import org.bug.detection.core.bug.pattern.SwitchCaseBug;

/**
 *
 * @author karayel
 */
public class ErrorBugFactory implements AbstractFactory{
    
    /**
     * Finding Error Bug for given source and bug name
     * @param source
     * @param bugName
     * @return 
     */
    @Override
    public AbstractBug getBug(String source,String bugName) {
        if (bugName == null) {
            return null;
        }
        if (bugName.equalsIgnoreCase(GeneralProperties.BUG_DIVZERO_NAME)) {
            return new DivZeroBug(source);
        }else if(bugName.equalsIgnoreCase(GeneralProperties.BUG_SWITCHCASE_NAME)){
            return new SwitchCaseBug(source);
        } 

        return null;
    }
    
}

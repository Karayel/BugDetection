/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bug.detection.core;

import java.util.ArrayList;
import java.util.List;
import org.bug.detection.config.GeneralProperties;
import org.bug.detection.core.bug.AbstractBug;
import org.bug.detection.core.bug.factory.AbstractFactory;
import org.bug.detection.core.bug.factory.ErrorBugFactory;

/**
 *
 * @author karayel
 */
public class ErrorBugFactoryMain implements BugFactoryMain{
    
    /**
     * Finding Error Bugs for given source
     * @param source
     * @return List<AbstractBug>
     */
    @Override
    public List<AbstractBug> findBugs(String source){
        AbstractFactory abstractFactory = new ErrorBugFactory();
        
        AbstractBug bugDivZero = abstractFactory.getBug(source, GeneralProperties.BUG_DIVZERO_NAME);
        bugDivZero.findBug();
        
        AbstractBug bugSwitchCase = abstractFactory.getBug(source, GeneralProperties.BUG_SWITCHCASE_NAME);
        bugSwitchCase.findBug();
        
        List<AbstractBug> combined = new ArrayList<AbstractBug>();
        combined.addAll(bugDivZero.getBdaos());
        combined.addAll(bugSwitchCase.getBdaos());
        
        return combined;
    }
}

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
import org.bug.detection.core.bug.factory.SuggestionBugFactory;

/**
 *
 * @author karayel
 */
public class SuggestionBugFactoryMain implements BugFactoryMain{
    /**
     * Finding Suggestion Bugs for given source
     * @param source
     * @return List<AbstractBug>
     */
    @Override
    public List<AbstractBug> findBugs(String source){
        
        AbstractFactory abstractFactory = new SuggestionBugFactory();
        
        AbstractBug equalsNoHashCode = abstractFactory.getBug(source, GeneralProperties.BUG_EQUALSNOHASHCODE_NAME);
        equalsNoHashCode.findBug();
        
        AbstractBug hashCodeNoEquals = abstractFactory.getBug(source, GeneralProperties.BUG_HASHCODENOEQUALS_NAME);
        hashCodeNoEquals.findBug();
        
        List<AbstractBug> combined = new ArrayList<AbstractBug>();
        combined.addAll(equalsNoHashCode.getBdaos());
        combined.addAll(hashCodeNoEquals.getBdaos());
        
        return combined;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bug.detection.core.bug.factory;

import org.bug.detection.config.GeneralProperties;
import org.bug.detection.core.bug.AbstractBug;
import org.bug.detection.core.bug.pattern.DivZeroBug;
import org.bug.detection.core.bug.pattern.EqualsNoHashcode;
import org.bug.detection.core.bug.pattern.HashcodeNoEquals;

/**
 *
 * @author karayel
 */
public class SuggestionBugFactory implements AbstractFactory{
    /**
     * Finding Suggestion Bug for given source and bug name
     * @param source
     * @param bugName
     * @return 
     */
    @Override
    public AbstractBug getBug(String source,String bugName) {
        if (bugName == null) {
            return null;
        }
        if (bugName.equalsIgnoreCase(GeneralProperties.BUG_EQUALSNOHASHCODE_NAME)) {
            return new EqualsNoHashcode(source);
        }else if (bugName.equalsIgnoreCase(GeneralProperties.BUG_HASHCODENOEQUALS_NAME)) {
            return new HashcodeNoEquals(source);
        }
        return null;
    }
}

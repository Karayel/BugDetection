/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bug.detection.core.bug.factory;

import org.bug.detection.config.GeneralProperties;
import org.bug.detection.core.bug.AbstractBug;
import org.bug.detection.core.bug.pattern.ComparisonBug;
import org.bug.detection.core.bug.pattern.DefaultCaseMissingBug;

/**
 *
 * @author karayel
 */
public class WarningBugFactory implements AbstractFactory {
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
        if (bugName.equalsIgnoreCase(GeneralProperties.BUG_COMPARISON_NAME)) {
            return new ComparisonBug(source);
        } else if(bugName.equalsIgnoreCase(GeneralProperties.BUG_DEFAULTCASE_NAME)){
            return new DefaultCaseMissingBug(source);
        }

        return null;
    }

}

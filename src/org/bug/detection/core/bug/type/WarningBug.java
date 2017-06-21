/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bug.detection.core.bug.type;

import org.bug.detection.config.GeneralProperties;
import org.bug.detection.core.bug.AbstractBug;
import org.bug.detection.core.bug.pattern.BugTypeEnum;

/**
 *
 * @author karayel
 */
public abstract class WarningBug extends AbstractBug{

    public WarningBug() {
        super();
        initilaze();
        
    }
    
    public WarningBug(String source){
        super(source);
        initilaze();
    }
    /**
     * Setting icon and bug type
     */
    private void initilaze(){
        setIcon(GeneralProperties.BUG_YELLOW);
        setType(BugTypeEnum.WARNING);
    }
    
}

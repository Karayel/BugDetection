/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bug.detection.core;

import java.util.ArrayList;
import java.util.List;
import org.bug.detection.core.bug.AbstractBug;

/**
 *
 * @author karayel
 */
public class DetectionMain {
    
    public static List<AbstractBug> detectBug(String path){
        
        BugFactoryMain errorFactoryMain = new ErrorBugFactoryMain();
        List<AbstractBug> errorBugList = errorFactoryMain.findBugs(path);
        
        BugFactoryMain suggestionFactoryMain = new SuggestionBugFactoryMain();
        List<AbstractBug> suggestionBugList = suggestionFactoryMain.findBugs(path);
        
        BugFactoryMain warningBugFactoryMain = new WarningBugFactoryMain();
        List<AbstractBug> warningBugList = warningBugFactoryMain.findBugs(path);
        
        List<AbstractBug> combined = new ArrayList<AbstractBug>();
        combined.addAll(errorBugList);
        combined.addAll(suggestionBugList);
        combined.addAll(warningBugList);
        
        return combined;
    }
}

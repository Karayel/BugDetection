/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bug.detection.core;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import java.util.ArrayList;
import java.util.List;
import org.bug.detection.config.GeneralProperties;
import org.bug.detection.core.bug.AbstractBug;
import org.bug.detection.core.bug.factory.AbstractFactory;
import org.bug.detection.core.bug.factory.WarningBugFactory;
import org.bug.detection.core.bug.helper.FileHelper;

/**
 *
 * @author karayel
 */
public class WarningBugFactoryMain implements BugFactoryMain{
    /**
     * Finding Warning Bugs for given source
     * @param source
     * @return List<AbstractBug>
     */
    @Override
    public List<AbstractBug> findBugs(String source){
        String s = FileHelper.readFile(source);
        CompilationUnit cu = JavaParser.parse(s);
        
        AbstractFactory abstractFactory = new WarningBugFactory();
        
        AbstractBug bugComparison = abstractFactory.getBug(source, GeneralProperties.BUG_COMPARISON_NAME);
        bugComparison.findBug();
        
        AbstractBug defaultCaseMissing = abstractFactory.getBug(source, GeneralProperties.BUG_DEFAULTCASE_NAME);
        defaultCaseMissing.findBug();
        
        List<AbstractBug> combined = new ArrayList<AbstractBug>();
        combined.addAll(bugComparison.getBdaos());
        combined.addAll(defaultCaseMissing.getBdaos());
        
        return combined;
    }
}

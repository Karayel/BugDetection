/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bug.detection.config;

import java.util.Properties;

/**
 * This class contains general properties like bug name, desc etc.
 * @author karayel
 */
public class GeneralProperties {
    
    private static final Properties BUG_PROPERTIES = BugProperties.getInstance();
    private static final Properties IMAGE_PROPERTIES = ImageProperties.getInstance();
    
    public static final String BUG_DEFAULTCASE_NAME = BUG_PROPERTIES.getProperty("bug.defaultcase.name");
    public static final String BUG_DEFAULTCASE_DESC = BUG_PROPERTIES.getProperty("bug.defaultcase.desc");
            
    public static final String BUG_DIVZERO_NAME = BUG_PROPERTIES.getProperty("bug.divzero.name");
    public static final String BUG_DIVZERO_DESC = BUG_PROPERTIES.getProperty("bug.divzero.desc");
    
    public static final String BUG_SWITCHCASE_NAME = BUG_PROPERTIES.getProperty("bug.switchcase.name");
    public static final String BUG_SWITCHCASE_DESC = BUG_PROPERTIES.getProperty("bug.switchcase.desc");
    
    public static final String BUG_COMPARISON_NAME = BUG_PROPERTIES.getProperty("bug.comparison.name");
    public static final String BUG_COMPARISON_DESC = BUG_PROPERTIES.getProperty("bug.comparison.desc");

    public static final String BUG_EQUALSNOHASHCODE_NAME = BUG_PROPERTIES.getProperty("bug.equalsnohascode.name");
    public static final String BUG_EQUALSNOHASHCODE_DESC = BUG_PROPERTIES.getProperty("bug.equalsnohascode.desc");
     
    public static final String BUG_HASHCODENOEQUALS_NAME = BUG_PROPERTIES.getProperty("bug.hashcodenoequals.name");
    public static final String BUG_HASHCODENOEQUALS_DESC = BUG_PROPERTIES.getProperty("bug.hashcodenoequals.desc");         
    
    public static final String BUG_RED = IMAGE_PROPERTIES.getProperty("bug_red");
    public static final String BUG_YELLOW = IMAGE_PROPERTIES.getProperty("bug_yellow");
    public static final String BUG_GREEN = IMAGE_PROPERTIES.getProperty("bug_green");
    
}

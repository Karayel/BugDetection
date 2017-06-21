/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bug.detection.core.bug.helper;

import com.github.javaparser.ast.expr.BinaryExpr;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.bug.detection.config.GeneralProperties;
import org.bug.detection.core.bug.AbstractBug;
import org.bug.detection.core.bug.pattern.BugTypeEnum;

/**
 *
 * @author karayel
 */
public class BugHelper {
    /**
     * Finding binary expression for given operation type
     * @param expr
     * @param o
     * @return 
     */
    public static List<BinaryExpr> findBinaryExprWithOperatorType(List<BinaryExpr> expr, BinaryExpr.Operator o) {
        List<BinaryExpr> list = new ArrayList<BinaryExpr>();
        for (BinaryExpr binaryExpr : expr) {
            if(binaryExpr.getOperator().equals(o))
                list.add(binaryExpr);
        }
        System.out.println(list.size());
        return list;
    }
    
    /**
     * Finding number of bugs for given bug type
     * @param bugs
     * @param bte
     * @return 
     */
    public static HashMap<String,Integer> getBugTypeSize(List<AbstractBug> bugs){
        HashMap<String, Integer> hmap = new HashMap<String, Integer>();
        
        hmap.put("ERROR", 0);
        hmap.put("WARNING", 0);
        hmap.put("SUGGESTION", 0);
        
        for (AbstractBug bug : bugs) {
            java.util.Set<Map.Entry<String, Integer>> set = hmap.entrySet();
            Iterator iterator = set.iterator();
            while (iterator.hasNext()) {
                
                Map.Entry mentry = (Map.Entry) iterator.next();
                if(bug.getType().toString().equals(mentry.getKey().toString())){
                    mentry.setValue(((Integer) mentry.getValue()) + 1);
                }
            }
        }
        return hmap;
    }
    
    public static HashMap<String,Integer> getBugSize(List<AbstractBug> bugs){
        HashMap<String, Integer> hmap = new HashMap<String, Integer>();
        
        hmap.put(GeneralProperties.BUG_COMPARISON_NAME, 0);
        hmap.put(GeneralProperties.BUG_DEFAULTCASE_NAME, 0);
        hmap.put(GeneralProperties.BUG_DIVZERO_NAME, 0);
        hmap.put(GeneralProperties.BUG_EQUALSNOHASHCODE_NAME, 0);
        hmap.put(GeneralProperties.BUG_HASHCODENOEQUALS_NAME, 0);
        hmap.put(GeneralProperties.BUG_SWITCHCASE_NAME, 0);

        for (AbstractBug bug : bugs) {
            java.util.Set<Map.Entry<String, Integer>> set = hmap.entrySet();
            Iterator iterator = set.iterator();
            while (iterator.hasNext()) {
                
                Map.Entry mentry = (Map.Entry) iterator.next();
                if(bug.getName().equals(mentry.getKey().toString())){
                    mentry.setValue(((Integer) mentry.getValue()) + 1);
                }
            }
        }
        
        return hmap;
    }
  
    
    
}

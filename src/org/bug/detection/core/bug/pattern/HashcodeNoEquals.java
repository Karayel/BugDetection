/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bug.detection.core.bug.pattern;

import com.github.javaparser.Position;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.symbolsolver.javaparser.Navigator;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import org.bug.detection.config.GeneralProperties;
import org.bug.detection.core.bug.AbstractBug;
import org.bug.detection.core.bug.type.SuggestionBug;

/**
 * Hash Code No Equals 
 * @author karayel
 */
public class HashcodeNoEquals extends SuggestionBug{
    
    private final static Logger log = Logger.getLogger(HashcodeNoEquals.class.getName());
    
    private List<MethodDeclaration> methods = new ArrayList<MethodDeclaration>();

    public HashcodeNoEquals() {
        setName(GeneralProperties.BUG_HASHCODENOEQUALS_NAME);
        setDesc(GeneralProperties.BUG_HASHCODENOEQUALS_DESC);
        log.warning(HashcodeNoEquals.class.getName()+" has been created.");
    }

    public HashcodeNoEquals(String source) {
        super(source);
        methods = Navigator.findAllNodesOfGivenClass(getCu(), MethodDeclaration.class);
    }

    @Override
    public void findBug() {
        if (checkMethodName("hashCode")) {
            MethodDeclaration md = findMethodDeclarationObj("hashCode");
            if (!checkMethodName("equals")) {
                Optional<Position> p = md.getBegin();
                AbstractBug bdao = new HashcodeNoEquals()
                            .setLine(p.get().line)
                            .setColumn(p.get().column)
                            .setClassName(this.getClassName());
                getBdaos().add(bdao);
                log.warning(bdao.toString());
            }
        }
    }
    
    private boolean checkMethodName(String name) {
         for (MethodDeclaration method : methods) {
            if (method.getNameAsString().equals(name)) {
                return true;
            }
        }
        return false;
    }

    private MethodDeclaration findMethodDeclarationObj(String name) {
        for (MethodDeclaration method : methods) {
            if (method.getNameAsString().equals(name)) {
                return method;
            }
        }
        return null;
    }
}

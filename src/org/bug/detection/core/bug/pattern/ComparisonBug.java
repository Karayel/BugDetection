/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bug.detection.core.bug.pattern;

import org.bug.detection.core.bug.type.WarningBug;
import com.github.javaparser.Position;
import com.github.javaparser.ast.expr.BinaryExpr;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.symbolsolver.javaparser.Navigator;
import com.github.javaparser.symbolsolver.javaparsermodel.JavaParserFacade;
import com.github.javaparser.symbolsolver.model.typesystem.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import org.bug.detection.config.GeneralProperties;
import org.bug.detection.core.bug.AbstractBug;
import org.bug.detection.core.bug.helper.BugHelper;

/**
 * Comparison using reference equality instead of value equality
 * @author karayel
 */
public class ComparisonBug extends WarningBug {
    
    private final static Logger log = Logger.getLogger(ComparisonBug.class.getName());
    
    private List<BinaryExpr> expr = new ArrayList<>();
    
    public ComparisonBug() {
        setName(GeneralProperties.BUG_COMPARISON_NAME);
        setDesc(GeneralProperties.BUG_COMPARISON_DESC);
        log.warning(ComparisonBug.class.getName()+ "has been created.");
    }
    
    public ComparisonBug(String source) {
        super(source);
        List<BinaryExpr> assignExpr = Navigator.findAllNodesOfGivenClass(getCu(), BinaryExpr.class);
        
        expr = BugHelper.findBinaryExprWithOperatorType(assignExpr, BinaryExpr.Operator.EQUALS);
    }

    public List<BinaryExpr> getExpr() {
        return expr;
    }

    @Override
    public void findBug() {
        boolean isPrimitive;
        for (BinaryExpr binaryExpr : expr) {
            try {
                Type t = JavaParserFacade.get(getTypeSolver()).getType(binaryExpr.getLeft());
                System.out.println(t);
                isPrimitive = JavaParserFacade.get(getTypeSolver()).getType(binaryExpr.getLeft()).isPrimitive();
                if (!isPrimitive) {
                    Expression e = binaryExpr.getLeft();
                    Optional<Position> p = e.getBegin();
                    AbstractBug bdao = new ComparisonBug()
                            .setLine(p.get().line - 1)
                            .setColumn(p.get().column)
                            .setClassName(this.getClassName());
                    getBdaos().add(bdao);
                    log.warning(bdao.toString());
                }

            } catch (Exception ex) {
                log.warning(ex.getMessage());
            }
        }
    }

}

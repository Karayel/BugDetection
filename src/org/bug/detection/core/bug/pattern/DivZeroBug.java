/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bug.detection.core.bug.pattern;

import org.bug.detection.core.bug.type.ErrorBug;
import com.github.javaparser.Position;
import com.github.javaparser.ast.expr.BinaryExpr;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.IntegerLiteralExpr;
import com.github.javaparser.symbolsolver.javaparser.Navigator;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import org.bug.detection.config.GeneralProperties;
import org.bug.detection.core.bug.AbstractBug;
import org.bug.detection.core.bug.helper.BugHelper;

/**
 * Division by integer literal zero
 * @author karayel
 */
public class DivZeroBug extends ErrorBug {
    
    private final static Logger log = Logger.getLogger(DivZeroBug.class.getName());

    private List<BinaryExpr> expr = new ArrayList<>();

    public DivZeroBug() {
        setName(GeneralProperties.BUG_DIVZERO_NAME);
        setDesc(GeneralProperties.BUG_DIVZERO_DESC);
        log.warning(DivZeroBug.class.getName()+" has been created.");
        
    }
    
    public DivZeroBug(String source) {
        super(source);
        List<BinaryExpr> assignExpr = Navigator.findAllNodesOfGivenClass(getCu(), BinaryExpr.class);

        expr = BugHelper.findBinaryExprWithOperatorType(assignExpr, BinaryExpr.Operator.DIVIDE);
    }

    public List<BinaryExpr> getExpr() {
        return expr;
    }

    @Override
    public void findBug() {
        for (BinaryExpr binaryExpr : expr) {
            Expression expression = binaryExpr.getRight();
            if (expression instanceof IntegerLiteralExpr) {
                if (((IntegerLiteralExpr) expression).getValue().equals("0")) {
                    Optional<Position> p = ((IntegerLiteralExpr) expression).getBegin();
                    
                    AbstractBug bdao = new DivZeroBug()
                            .setLine(p.get().line - 1 )
                            .setColumn(p.get().column)
                            .setClassName(this.getClassName());
                    getBdaos().add(bdao);
                    log.warning(bdao.toString());
                }
            }
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bug.detection.core.bug.pattern;

import com.github.javaparser.Position;
import com.github.javaparser.ast.stmt.SwitchEntryStmt;
import com.github.javaparser.symbolsolver.javaparser.Navigator;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import org.bug.detection.config.GeneralProperties;
import org.bug.detection.core.bug.AbstractBug;
import org.bug.detection.core.bug.type.WarningBug;

/**
 * This bug is comming from forgetting default case.
 *
 * @author karayel
 */
public class DefaultCaseMissingBug extends WarningBug {
    
    private final static Logger log = Logger.getLogger(DefaultCaseMissingBug.class.getName());

    private List<SwitchEntryStmt> expr = new ArrayList<>();

    public DefaultCaseMissingBug() {
        setName(GeneralProperties.BUG_DEFAULTCASE_NAME);
        setDesc(GeneralProperties.BUG_DEFAULTCASE_DESC);
        log.warning(DefaultCaseMissingBug.class.getName()+" has been created.");
    }

    public DefaultCaseMissingBug(String source) {
        super(source);
        expr = Navigator.findAllNodesOfGivenClass(getCu(), SwitchEntryStmt.class);
    }

    @Override
    public void findBug() {
        if (expr.size() > 0) {
            boolean b = checkDefaultStatement(expr);
            if (!b) {
                int size = expr.size();
                SwitchEntryStmt stmt = expr.get(size - 1);
                Optional<Position> p = stmt.getBegin();
                AbstractBug bdao = new DefaultCaseMissingBug()
                        .setLine(p.get().line - 1)
                        .setColumn(p.get().column)
                        .setClassName(this.getClassName());
                getBdaos().add(bdao);
                log.warning(bdao.toString());
            }
        }
    }

    /**
     * Return tree if the statement is breakstmt
     *
     * @param list
     * @return
     */
    private boolean checkDefaultStatement(List<SwitchEntryStmt> expr) {
        for (SwitchEntryStmt switchEntryStmt : expr) {
            if (switchEntryStmt.getLabel() == null) {
                return true;
            }
        }
        return false;
    }

}

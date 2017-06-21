/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bug.detection.core.bug.pattern;

import com.github.javaparser.Position;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.stmt.BreakStmt;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.stmt.SwitchEntryStmt;
import com.github.javaparser.symbolsolver.javaparser.Navigator;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import org.bug.detection.config.GeneralProperties;
import org.bug.detection.core.bug.AbstractBug;
import org.bug.detection.core.bug.type.ErrorBug;

/**
 * This method contains a switch statement where one case branch will fall through to the next case.
 * @author karayel
 */

// http://stackoverflow.com/questions/8563970/switch-without-break
public class SwitchCaseBug extends ErrorBug {
    
    private final static Logger log = Logger.getLogger(SwitchCaseBug.class.getName());

    private List<SwitchEntryStmt> expr = new ArrayList<>();

    public SwitchCaseBug() {
        setName(GeneralProperties.BUG_SWITCHCASE_NAME);
        setDesc(GeneralProperties.BUG_SWITCHCASE_DESC);
        log.warning(SwitchCaseBug.class.getName()+" has been created.");
    }

    public SwitchCaseBug(String source) {
        super(source);
        expr = Navigator.findAllNodesOfGivenClass(getCu(), SwitchEntryStmt.class);
    }

    @Override
    public void findBug() {
        for (SwitchEntryStmt switchEntryStmt : expr) {
            if (switchEntryStmt.getStatements().size() > 0) {
                NodeList<Statement> statementList = switchEntryStmt.getStatements();
                if (!checkStatementList(statementList)) {
                    Statement stmt = statementList.get(statementList.size() - 1);
                    Optional<Position> p = stmt.getBegin();
                    AbstractBug bdao = new SwitchCaseBug()
                            .setLine(p.get().line - 1)
                            .setColumn(p.get().column)
                            .setClassName(this.getClassName());
                    getBdaos().add(bdao);
                    log.warning(bdao.toString());
                }
            }
        }
    }
    /**
     * Return tree if the statement is breakstmt
     * @param list
     * @return 
     */
    private boolean checkStatementList(NodeList<Statement> list) {
        for (Statement statement : list) {
            if (statement instanceof BreakStmt) {
                return true;
            }
        }
        return false;
    }

}

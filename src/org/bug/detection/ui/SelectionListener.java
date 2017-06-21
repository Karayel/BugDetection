/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bug.detection.ui;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import org.bug.detection.core.bug.AbstractBug;
import org.openide.cookies.LineCookie;
import org.openide.cookies.OpenCookie;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.loaders.DataObject;
import org.openide.loaders.DataObjectNotFoundException;
import org.openide.text.Line;

/**
 *
 * @author karayel
 */
public class SelectionListener implements TreeSelectionListener {
    
    private final static Logger log = Logger.getLogger(SelectionListener.class.getName());

        @Override
        public void valueChanged(TreeSelectionEvent se) {
            JTree tree = (JTree) se.getSource();
            DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree
                    .getLastSelectedPathComponent();
            Object o = selectedNode.getUserObject();
            
            if (o instanceof MyCustomFileObject) {
                MyCustomFileObject customFileObject = (MyCustomFileObject) o;
                if (!customFileObject.getF().isFolder()) {
                    File file = new File(customFileObject.getF().getPath());
                    try {
                        DataObject.find(FileUtil.toFileObject(file)).
                                getLookup().lookup(OpenCookie.class).open();
                        log.warning("Class has been opened.");
                    } catch (DataObjectNotFoundException ex) {
                        log.warning(ex.getMessage());
                    }
                }
            }
            if(o instanceof AbstractBug){
                try {
                    AbstractBug customFileObject = (AbstractBug) o;
                    FileObject fo = FileUtil.createData(new File(customFileObject.getClassName()));
                    LineCookie lc = DataObject.find(fo).getLookup().lookup(LineCookie.class);
                    int lineNumber=customFileObject.getLine();
                    int colNumber=customFileObject.getColumn();
                    Line line = lc.getLineSet().getOriginal(lineNumber);
                    line.show(Line.ShowOpenType.OPEN, Line.ShowVisibilityType.FRONT, colNumber);
                    log.warning("Class that has the bug has been opened.");
                } catch (DataObjectNotFoundException ex) {
                    log.warning(ex.getMessage());
                } catch (IOException ex) {
                    log.warning(ex.getMessage());
                }
            }
        }
    }

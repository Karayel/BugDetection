/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bug.detection.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import org.bug.detection.core.DetectionMain;
import org.bug.detection.core.bug.AbstractBug;
import org.bug.detection.core.bug.Bug;
import org.bug.detection.core.bug.type.ErrorBug;
import org.bug.detection.core.bug.type.SuggestionBug;
import org.bug.detection.core.bug.type.WarningBug;
import org.netbeans.api.java.project.JavaProjectConstants;
import org.netbeans.api.project.Project;
import org.netbeans.api.project.ProjectUtils;
import org.netbeans.api.project.SourceGroup;
import org.netbeans.api.project.Sources;
import org.openide.filesystems.FileObject;

/**
 *
 * @author karayel
 */
public class GenerateTreeModel {
    
    private final static Logger log = Logger.getLogger(GenerateTreeModel.class.getName());
    
    private List<AbstractBug> bugs;
    private DefaultMutableTreeNode warning;
    private DefaultMutableTreeNode error;
    private DefaultMutableTreeNode suggestion;

    public GenerateTreeModel() {
        bugs = new ArrayList<AbstractBug>();
    }

    public List<AbstractBug> getBugs() {
        return bugs;
    }
    /**
     * Creating tree for project folder option
     * @param project
     * @return 
     */   
    public javax.swing.tree.TreeModel findSourceTreeModel(Project project) {
        String projectName = project.getProjectDirectory().getName();
        DefaultMutableTreeNode parent = new DefaultMutableTreeNode(projectName, true);
        Sources s = ProjectUtils.getSources(project);
        SourceGroup[] sg = s.getSourceGroups(JavaProjectConstants.SOURCES_TYPE_JAVA);
        for (SourceGroup sourceGroup : sg) {
            addSourceToTree(sourceGroup.getRootFolder().getChildren(), parent);
        }
        javax.swing.tree.TreeModel tm = new DefaultTreeModel(parent);
        log.warning("Tree has been generated.");
        return tm;
    }
    /**
     * Creating tree for bug type option
     * @param project
     * @return 
     */
    public  javax.swing.tree.TreeModel findSourceTreeModelForType(Project project) {
        String projectName = project.getProjectDirectory().getName();
        DefaultMutableTreeNode parent = new DefaultMutableTreeNode(projectName, true);
        Sources s = ProjectUtils.getSources(project);
        SourceGroup[] sg = s.getSourceGroups(JavaProjectConstants.SOURCES_TYPE_JAVA);

        warning = new DefaultMutableTreeNode("WARNING", true);
        error = new DefaultMutableTreeNode("ERROR", true);
        suggestion = new DefaultMutableTreeNode("SUGGESTION", true);

        parent.add(warning);
        parent.add(error);
        parent.add(suggestion);

        for (SourceGroup sourceGroup : sg) {
            addSourceToTreeForType(sourceGroup.getRootFolder().getChildren());

        }

        javax.swing.tree.TreeModel tm = new DefaultTreeModel(parent);
        log.warning("Tree has been generated.");
        return tm;
    }
    /**
     * It helps to finding bugs with project folder option. 
     * It creates the tree by project folder.
     * @param f
     * @param node 
     */
    private void addSourceToTree(FileObject[] f, DefaultMutableTreeNode node) {
        for (FileObject fileObject : f) {
            MyCustomFileObject customFileObject = new MyCustomFileObject(fileObject);
            if (fileObject.isFolder()) {
                DefaultMutableTreeNode folderNode = new DefaultMutableTreeNode(customFileObject, true);
                node.add(folderNode);
                log.warning("Source tree element has been generated and rerun with itself.");
                addSourceToTree(fileObject.getChildren(), folderNode);
            }
            if (fileObject.isData()) {
                DefaultMutableTreeNode className = new DefaultMutableTreeNode(customFileObject, true);
                node.add(className);
                List<AbstractBug> abstractBugs = DetectionMain.detectBug(customFileObject.getF().getPath());
                bugs.addAll(abstractBugs);
                log.warning("Bugs added to file.");
                addBugToClass(className, abstractBugs);
            }
        }
    }
    /**
     * It helps to finding bugs with bug type option. 
     * It creates the tree by bug type.
     * @param f 
     */
    private void addSourceToTreeForType(FileObject[] f) {

        for (FileObject fileObject : f) {
            MyCustomFileObject customFileObject = new MyCustomFileObject(fileObject);
            if (fileObject.isFolder()) {
                addSourceToTreeForType(fileObject.getChildren());
            }
            if (fileObject.isData()) {
                List<AbstractBug> bdaos = DetectionMain.detectBug(customFileObject.getF().getPath());
                bugs.addAll(bdaos);
                for (AbstractBug bdao : bdaos) {
                    DefaultMutableTreeNode className = new DefaultMutableTreeNode(bdao, true);
                    if (bdao instanceof ErrorBug) {
                        log.warning("Error bugs added "+className);
                        error.add(className);
                    } else if (bdao instanceof SuggestionBug) {
                        log.warning("Suggestion bugs added "+className);
                        suggestion.add(className);
                    } else if (bdao instanceof WarningBug) {
                        log.warning("Warning bugs added "+className);
                        warning.add(className);
                    }
                }
            }
        }
    }
    /**
     * Adding bug to given class
     * @param node
     * @param list 
     */
    private void addBugToClass(DefaultMutableTreeNode node, List<AbstractBug> list){
        for (Bug bug : list) {
            DefaultMutableTreeNode dmtn = new DefaultMutableTreeNode(bug, true);
            node.add(dmtn);
        }
    }
}

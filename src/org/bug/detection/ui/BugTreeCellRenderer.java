/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bug.detection.ui;

import org.bug.detection.config.ImageProperties;
import java.awt.Component;
import java.net.URL;
import java.util.Properties;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import org.bug.detection.core.bug.AbstractBug;

/**
 * Custom DefaultTreeCellRenderer
 * It contains label with icon.
 * @author karayel
 */
public class BugTreeCellRenderer extends DefaultTreeCellRenderer {
    
    private final static Logger log = Logger.getLogger(BugTreeCellRenderer.class.getName());

    private JLabel label;
    Properties config = ImageProperties.getInstance();

    BugTreeCellRenderer() {
        label = new JLabel();
    }
    /**
     * Get tree cell renderer for given value
     * @param tree
     * @param value
     * @param selected
     * @param expanded
     * @param leaf
     * @param row
     * @param hasFocus
     * @return 
     */
    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded,
            boolean leaf, int row, boolean hasFocus) {
        Object o = ((DefaultMutableTreeNode) value).getUserObject();
        if(o instanceof String){
            log.warning(o.toString() +"has been added to project.");
            setLabelIcon(config.getProperty("project"));
        }
        else if (o instanceof AbstractBug) {
            AbstractBug abstractBug = (AbstractBug) o;
            setLabelIcon(abstractBug.getIcon());
        } else if (o instanceof MyCustomFileObject) {
            MyCustomFileObject bugDAO = (MyCustomFileObject) o;
            if (bugDAO.getF().isData()) {
                log.warning(bugDAO.getF().getName() +" class has been added to project.");
                setLabelIcon(config.getProperty("class"));
            }
            if (bugDAO.getF().isFolder()) {
                log.warning(bugDAO.getF().getName() +" package has been added to project.");
                setLabelIcon(config.getProperty("package"));
            }
        }
        label.setText(""+value);
        return label;
    }
    
    public void setLabelIcon(String icon) {
        ImageIcon imageIcon = getImageIcon(icon);
        if (imageIcon != null) {
            label.setIcon(imageIcon);
        }
    }
    
    public ImageIcon getImageIcon(String icon){
        URL imageUrl = getClass().getClassLoader().getResource(icon);
        if(imageUrl != null){
            return new ImageIcon(imageUrl);
        }
        return null;
    }
}

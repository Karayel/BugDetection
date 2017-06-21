/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bug.detection.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle.Messages;

@ActionID(
        category = "Bugtracking",
        id = "org.bug.detection.BugDetection"
)
@ActionRegistration(
        iconBase = "org/bug/detection/ui/bug_red.png",
        displayName = "#CTL_BugDetection"
)
@ActionReference(path = "Toolbars/Run", position = 0)
@Messages("CTL_BugDetection=Detect Bug")
public final class BugDetection implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO implement action body
        final BugDetectionFrame f = new BugDetectionFrame();
        f.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                f.dispose(); // use dispose method 
            }
        }
        );
        f.setVisible(true);
    }
}

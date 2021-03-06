/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bug.detection.ui;

import javax.swing.tree.TreeModel;
import org.bug.detection.core.bug.AbstractBug;
import org.netbeans.api.project.Project;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.windows.TopComponent;
import org.openide.util.NbBundle.Messages;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import java.awt.Desktop;
import java.awt.HeadlessException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.swing.JOptionPane;
import org.bug.detection.config.EmailProperties;
import org.bug.detection.config.GeneralProperties;
import org.bug.detection.core.bug.helper.BugHelper;
import org.bug.detection.core.bug.pattern.BugTypeEnum;
import org.bug.detection.file.PdfFile;
import org.bug.detection.file.TextFile;
import org.openide.util.Exceptions;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(
        dtd = "-//org.bug.detection//BugDetection//EN",
        autostore = false
)
@TopComponent.Description(
        preferredID = "BugDetectionTopComponent",
        iconBase = "org/bug/detection/bug_red.png",
        persistenceType = TopComponent.PERSISTENCE_ALWAYS
)
@TopComponent.Registration(mode = "leftSlidingSide", openAtStartup = false)
@ActionID(category = "Window", id = "org.bug.detection.BugDetectionTopComponent")
@ActionReference(path = "Menu/Window", position = 888)
@TopComponent.OpenActionRegistration(
        displayName = "#CTL_BugDetectionAction",
        preferredID = "BugDetectionTopComponent"
)
@Messages({
    "CTL_BugDetectionAction=BugDetection",
    "CTL_BugDetectionTopComponent=BugDetection Window",
    "HINT_BugDetectionTopComponent=This is a BugDetection window"
})
public final class BugDetectionTopComponent extends TopComponent {

    private final static Logger log = Logger.getLogger(BugDetectionTopComponent.class.getName());

    private GenerateTreeModel tm;

    private Properties emailProperties = EmailProperties.getInstance();

    public BugDetectionTopComponent() {
        initComponents();
        setName(Bundle.CTL_BugDetectionTopComponent());
        setToolTipText(Bundle.HINT_BugDetectionTopComponent());

        log.warning("Bug Detection Top Component initialized.");
    }

    public BugDetectionTopComponent(Project p, int id) {
        initComponents();
        setName(Bundle.CTL_BugDetectionTopComponent());
        setToolTipText(Bundle.HINT_BugDetectionTopComponent());

        log.warning("Bug Detection Top Component initialized.");

        tm = new GenerateTreeModel();
        TreeModel treeModel = null;

        log.warning("Tree generated.");

        if (id == 0) {
            treeModel = tm.findSourceTreeModel(p);

        } else if (id == 1) {
            treeModel = tm.findSourceTreeModelForType(p);
        }

        jTree1.setModel(treeModel);

        log.warning("Tree model has been setted.");

        jTree1.addTreeSelectionListener(new SelectionListener());
        jTree1.setCellRenderer(new BugTreeCellRenderer());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();

        org.openide.awt.Mnemonics.setLocalizedText(jButton1, org.openide.util.NbBundle.getMessage(BugDetectionTopComponent.class, "BugDetectionTopComponent.jButton1.text")); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jButton2, org.openide.util.NbBundle.getMessage(BugDetectionTopComponent.class, "BugDetectionTopComponent.jButton2.text")); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jButton2.setText("Send E-mail");

        jScrollPane2.setViewportView(jTree1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(392, 392, 392))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addGap(29, 29, 29)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 392, Short.MAX_VALUE)))
        );

        jButton1.setText("Report Bugs");
        jButton1.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(BugDetectionTopComponent.class, "BugDetectionTopComponent.jButton1.AccessibleContext.accessibleName")); // NOI18N
        jButton1.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(BugDetectionTopComponent.class, "BugDetectionTopComponent.jButton1.AccessibleContext.accessibleDescription")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    /**
     * Generating Report Action
     *
     * @param evt
     */
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        String title = "Warning Creating Report";
        String message = "No bugs were found to create the file.";

        boolean bool = showMessage(title, message);

        if (bool) {
            log.warning("During the file creation process something went wrong.");
            return;
        }

        createTextReport();
        log.warning("Txt file has been created.");
        createPDFReport();
        log.warning("PDF file has been created.");

        message = "Txt and PDF have been created.";

        JOptionPane.showMessageDialog(this, message, title, JOptionPane.WARNING_MESSAGE);

        String fileName = System.getProperty("user.home") + "/BugReport.pdf";

        if (!Desktop.isDesktopSupported()) {
            message = "Your desktop is not supported to open file.";
            JOptionPane.showMessageDialog(this, message, title, JOptionPane.WARNING_MESSAGE);
            return;
        }

        Desktop desktop = Desktop.getDesktop();
        File file = new File(fileName);

        if (file.exists()) {
            try {
                desktop.open(file);
            } catch (IOException ex) {
                message = "File is not created";
                JOptionPane.showMessageDialog(this, message, title, JOptionPane.WARNING_MESSAGE);
                Exceptions.printStackTrace(ex);
            }
        }

    }//GEN-LAST:event_jButton1ActionPerformed
    /**
     * Sending Report via E-mail
     *
     * @param evt
     */
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        File f = new File(System.getProperty("user.home") + "/BugReport.pdf");

        String title = "Sending E-mail";
        String warningMessage = "No file created for send.";

        boolean bool = showMessage(title, warningMessage);

        if (bool) {
            log.warning("During the sending e-mail process something went wrong.");
            return;
        }

        if (!f.exists() && !f.isDirectory()) {
            log.warning("PDF file does not exist.");
            createPDFReport();
            log.warning("PDF file has been created.");
        }

        // SMTP info
        String host = emailProperties.getProperty("host");
        String port = emailProperties.getProperty("port");;
        String mailFrom = emailProperties.getProperty("mailFrom");
        String password = emailProperties.getProperty("password");

        // message info
        String mailTo = emailProperties.getProperty("mailTo");
        String subject = emailProperties.getProperty("subject");
        String message = emailProperties.getProperty("message");

        // attachments
        String[] attachFiles = new String[1];

        attachFiles[0] = System.getProperty("user.home") + "/BugReport.pdf";

        try {
            SendEmail.sendEmailWithAttachments(host, port, mailFrom, password, mailTo,
                    subject, message, attachFiles);
            log.warning("E-mail has been sent.");

            message = "E-mail has been sent.";

            JOptionPane.showMessageDialog(this, message, title, JOptionPane.WARNING_MESSAGE);
        } catch (MessagingException | HeadlessException ex) {
            log.warning("Could not send email.");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTree jTree1;
    // End of variables declaration//GEN-END:variables
    @Override
    public void componentOpened() {
        // TODO add custom code on component opening
    }

    @Override
    public void componentClosed() {
        // TODO add custom code on component closing
    }

    void writeProperties(java.util.Properties p) {
        // better to version settings since initial version as advocated at
        // http://wiki.apidesign.org/wiki/PropertyFiles
        p.setProperty("version", "1.0");
        // TODO store your settings
    }

    void readProperties(java.util.Properties p) {
        String version = p.getProperty("version");
        // TODO read your settings according to their version
    }

    /**
     * Return number of bugs for given bug type
     *
     * @param bugs
     * @param bte
     * @return
     */
    private int getTypeSize(java.util.List<AbstractBug> bugs, BugTypeEnum bte) {
        int i = 0;
        for (AbstractBug bdao : bugs) {
            if (bdao.getType().equals(bte)) {
                i++;
            }
        }
        return i;
    }

    /**
     * Creating text report with bugs
     */
    private void createTextReport() {
        TextFile textFile = new TextFile();
        for (AbstractBug bug : tm.getBugs()) {
            textFile.addText(bug.toString());
        }
        textFile.closeFile();
    }

    /**
     * Creating pdf report with bugs
     */
    private void createPDFReport() {
        java.util.List<AbstractBug> bugList = tm.getBugs();

        PdfFile file = new PdfFile();

        file.addParagraphWithAlign("BUG DESCRIPTION", Element.ALIGN_CENTER, 16);

        HashMap<String, String> hmap = bugHashMap();

        java.util.Set<Map.Entry<String, String>> set = hmap.entrySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry) iterator.next();

            String s = mentry.getKey() + ": " + mentry.getValue();
            file.addParagraph(s);
        }

        file.addParagraph("");
        file.addParagraph("");
        file.addParagraph("");

        file.addParagraphWithAlign("BUG TYPE", Element.ALIGN_CENTER, 16);
        file.addParagraph("");
        
        PdfPTable table = getBugTable();
        
        file.addTable(table);

        file.addParagraphWithAlign("BUG REPORT", Element.ALIGN_CENTER, 16);
        file.addParagraphWithAlign("* Bug Name - Source File - Line , Column", Element.ALIGN_LEFT, 12);

        for (int i = 0; i < bugList.size(); i++) {
            file.addParagraph((i + 1) + " " + bugList.get(i).customToString());
        }

        HashMap<String, Integer> bugMap = BugHelper.getBugSize(bugList);

        Image img = getChartImage(bugMap, "BUG PIE CHART");

        file.addImage(img);
        log.warning("PIE chart added to file");

        HashMap<String, Integer> typeMap = BugHelper.getBugTypeSize(bugList);
        
        img = getChartImage(typeMap, "BUG TYPE PIE CHART");

        file.addImage(img);
        log.warning("PIE chart added to file");

        file.build();
    }

    private boolean showMessage(String title, String message) {
        if (tm.getBugs().isEmpty()) {

            JOptionPane.showMessageDialog(this, message, title, JOptionPane.WARNING_MESSAGE);

            return true;
        }
        return false;
    }

    private HashMap<String, String> bugHashMap() {
        HashMap<String, String> hmap = new HashMap<String, String>();

        /*Adding elements to HashMap*/
        hmap.put(GeneralProperties.BUG_COMPARISON_NAME, GeneralProperties.BUG_COMPARISON_DESC);
        hmap.put(GeneralProperties.BUG_DEFAULTCASE_NAME, GeneralProperties.BUG_DEFAULTCASE_DESC);
        hmap.put(GeneralProperties.BUG_DIVZERO_NAME, GeneralProperties.BUG_DIVZERO_DESC);
        hmap.put(GeneralProperties.BUG_EQUALSNOHASHCODE_NAME, GeneralProperties.BUG_EQUALSNOHASHCODE_DESC);
        hmap.put(GeneralProperties.BUG_HASHCODENOEQUALS_NAME, GeneralProperties.BUG_HASHCODENOEQUALS_DESC);
        hmap.put(GeneralProperties.BUG_SWITCHCASE_NAME, GeneralProperties.BUG_SWITCHCASE_DESC);

        return hmap;
    }

    private Image getChartImage(HashMap<String, Integer> hmap,String chartName) {
        PieChart pieChart = new PieChart();

        java.util.Set<Map.Entry<String, Integer>> setTypeMap = hmap.entrySet();
        Iterator iteratorTypeMap = setTypeMap.iterator();
        while (iteratorTypeMap.hasNext()) {

            Map.Entry mentry = (Map.Entry) iteratorTypeMap.next();
            pieChart.addData(mentry.getKey().toString(), Integer.parseInt(mentry.getValue().toString()));
        }

        pieChart.build(chartName);

        Image img = pieChart.createImage(480, 480);
        log.warning("PIE chart has been created.");
        return img;
    }

    private PdfPTable getBugTable() {
       Font bold = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);

        PdfPTable table = new PdfPTable(3);

        PdfPCell errorCell = new PdfPCell(new Phrase("Error", bold));
        PdfPCell suggestionCell = new PdfPCell(new Phrase("Suggestion", bold));
        PdfPCell warningCell = new PdfPCell(new Phrase("Warning", bold));

        table.addCell(errorCell);
        table.addCell(warningCell);
        table.addCell(suggestionCell);
        table.addCell(GeneralProperties.BUG_SWITCHCASE_NAME);
        table.addCell(GeneralProperties.BUG_COMPARISON_NAME);
        table.addCell(GeneralProperties.BUG_EQUALSNOHASHCODE_NAME);
        table.addCell(GeneralProperties.BUG_DIVZERO_NAME);
        table.addCell(GeneralProperties.BUG_DEFAULTCASE_NAME);
        table.addCell(GeneralProperties.BUG_HASHCODENOEQUALS_NAME);
        
        return table;
    }
    
}

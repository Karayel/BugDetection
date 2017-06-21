/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bug.detection.file;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.logging.Logger;
import org.openide.util.Exceptions;

/**
 *  Generate PDF Report from Bugs
 * @author karayel
 */
public class PdfFile extends AbstractFile {
    
    private final static Logger log = Logger.getLogger(PdfFile.class.getName());

    Document doc;

    public PdfFile() {
        super(".pdf");
        try {
            doc = new Document();
            PdfWriter.getInstance(doc, new FileOutputStream(getFile()));
            doc.open();
            log.warning("Pdf file has been created.");
        } catch (DocumentException | FileNotFoundException ex) {
            log.warning(ex.getMessage());
        }
    }
    /**
     * Adding paragraph to pdf file
     * @param paragraph
     * @return 
     */
    public PdfFile addParagraph(String paragraph) {
        try {
            Paragraph p = new Paragraph();
            p.add(paragraph);
            doc.add(p);
            log.warning("Paragraph has been added to file.");
        } catch (DocumentException ex) {
            Exceptions.printStackTrace(ex);
            addParagraph(paragraph);
            log.warning(ex.getMessage());
        }
        return this;
    }
    /**
     * Adding paragraph with given align
     * @param paragraph
     * @param alignID
     * @return 
     */
    public PdfFile addParagraphWithAlign(String paragraph, int alignID,int size) {
        try {
            Font bold = new Font(Font.FontFamily.TIMES_ROMAN, size,Font.BOLD);
            Paragraph p = new Paragraph(paragraph,bold);
            p.setAlignment(alignID);
            doc.add(p);
            log.warning("Paragraph has been added to file.");
        } catch (DocumentException ex) {
            Exceptions.printStackTrace(ex);
            addParagraphWithAlign(paragraph, alignID,size);
            log.warning(ex.getMessage());
        }

        return this;
    }
    /**
     * Adding image to PDF
     * @param img
     * @return 
     */
    public PdfFile addImage(Image img) {
        try {
            doc.add(img);
            log.warning("Image has been added to file.");
        } catch (DocumentException ex) {
            Exceptions.printStackTrace(ex);
            addImage(img);
            log.warning(ex.getMessage());
        }
        return this;
    }
    /**
     * Closing PDF 
     */
    public void build() {
        log.warning("PDF file has been closed.");
        doc.close();
    }

    public void addTable(PdfPTable table) {
        try {
            doc.add(table);
        } catch (DocumentException ex) {
            Exceptions.printStackTrace(ex);
        }
    }

}

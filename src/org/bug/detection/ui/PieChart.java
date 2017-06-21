/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bug.detection.ui;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

/**
 *  This class generate report's chart as an image.
 * @author karayel
 */
public class PieChart {
    
    private final static Logger log = Logger.getLogger(PieChart.class.getName());

    private DefaultPieDataset dataset;
    private JFreeChart chart;

    public PieChart() {
        dataset = new DefaultPieDataset();
        log.warning(PieChart.class.getName()+" has been created.");
    }
    /**
     * Adding data to chart
     * @param key
     * @param value
     * @return 
     */
    public PieChart addData(Comparable key, int value) {
        if (value > 0) {
            dataset.setValue(key, value);
        }
        log.warning("Data has been added to chart.");
        return this;
    }
    /**
     * Creating chart
     * @param chartName
     * @return 
     */
    public JFreeChart build(String chartName) {
        chart = ChartFactory.createPieChart(
                chartName, // chart title
                dataset, // data
                true, // include legend
                true,
                false);
        log.warning("Chart has been generated.");
        return chart;
    }
    /**
     * Generating image from chart for given width and height values
     * @param width
     * @param height
     * @return 
     */
    public Image createImage(int width, int height) {
        Image img = null;
        try {
            BufferedImage bi = chart.createBufferedImage(width, height);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bi, "jpg", baos);
            baos.flush();
            byte[] imageInByte = baos.toByteArray();
            img = Image.getInstance(imageInByte);
            log.warning("Image has been added to chart.");
        } catch (IOException | BadElementException ex) {
            log.warning(ex.getMessage());
        }
        return img;
    }
}

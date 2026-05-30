package Vistas;

import Modelos.AnaliticasDAO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

// JFreeChart Core e Interfaces de Estilo
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.RingPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

// iTextPDF Core
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.PageSize;

public class FrmAnaliticas extends javax.swing.JInternalFrame {

    private final AnaliticasDAO dao;
    private JFreeChart chartVentas;
    private JFreeChart chartCategorias;
    private JFreeChart chartTopProductos;
    private JFreeChart chartProporcionStock;
    private boolean isUpdating = false; // Bloqueo para evitar colisiones de hilos

    public FrmAnaliticas() {
        initComponents();
        getContentPane().setBackground(Color.decode("#F0F4F8"));
        lblTitulo.setText("Anal\u00EDticas de Rendimiento");
        dao = new AnaliticasDAO();



    }




    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        btnGenerarPDF = new javax.swing.JButton();
        panelGraficoVentas = new javax.swing.JPanel();
        panelGraficoCat = new javax.swing.JPanel();
        panelGraficoTopProd = new javax.swing.JPanel();
        panelGraficoStockStatus = new javax.swing.JPanel();

        setBackground(new java.awt.Color(240, 244, 248));
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Analíticas y Reportes");
        setPreferredSize(new java.awt.Dimension(1100, 710));
        getContentPane().setLayout(null);

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(45, 74, 138));
        lblTitulo.setText("Analíticas de Rendimiento");
        getContentPane().add(lblTitulo);
        lblTitulo.setBounds(25, 20, 400, 30);

        btnGenerarPDF.setBackground(new java.awt.Color(45, 74, 138));
        btnGenerarPDF.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnGenerarPDF.setForeground(new java.awt.Color(255, 255, 255));
        btnGenerarPDF.setText("Generar Reporte PDF");
        btnGenerarPDF.setBorderPainted(false);
        btnGenerarPDF.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnGenerarPDF.setFocusPainted(false);
        btnGenerarPDF.addActionListener(this::btnGenerarPDFActionPerformed);
        getContentPane().add(btnGenerarPDF);
        btnGenerarPDF.setBounds(830, 15, 230, 40);

        panelGraficoVentas.setBackground(new java.awt.Color(255, 255, 255));
        panelGraficoVentas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(197, 216, 245)));
        panelGraficoVentas.setLayout(new java.awt.BorderLayout());
        getContentPane().add(panelGraficoVentas);
        panelGraficoVentas.setBounds(25, 70, 510, 270);

        panelGraficoCat.setBackground(new java.awt.Color(255, 255, 255));
        panelGraficoCat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(197, 216, 245)));
        panelGraficoCat.setLayout(new java.awt.BorderLayout());
        getContentPane().add(panelGraficoCat);
        panelGraficoCat.setBounds(550, 70, 510, 270);

        panelGraficoTopProd.setBackground(new java.awt.Color(255, 255, 255));
        panelGraficoTopProd.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(197, 216, 245)));
        panelGraficoTopProd.setLayout(new java.awt.BorderLayout());
        getContentPane().add(panelGraficoTopProd);
        panelGraficoTopProd.setBounds(25, 360, 510, 270);

        panelGraficoStockStatus.setBackground(new java.awt.Color(255, 255, 255));
        panelGraficoStockStatus.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(197, 216, 245)));
        panelGraficoStockStatus.setLayout(new java.awt.BorderLayout());
        getContentPane().add(panelGraficoStockStatus);
        panelGraficoStockStatus.setBounds(550, 360, 510, 270);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGenerarPDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarPDFActionPerformed

    }//GEN-LAST:event_btnGenerarPDFActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnGenerarPDF;
    private javax.swing.JLabel lblTitulo;
    public javax.swing.JPanel panelGraficoCat;
    public javax.swing.JPanel panelGraficoStockStatus;
    public javax.swing.JPanel panelGraficoTopProd;
    public javax.swing.JPanel panelGraficoVentas;
    // End of variables declaration//GEN-END:variables
}
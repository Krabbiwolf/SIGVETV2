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

        btnGenerarPDF.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btnGenerarPDF.setBackground(Color.decode("#3A5AAA")); }
            public void mouseExited(MouseEvent e)  { btnGenerarPDF.setBackground(Color.decode("#2D4A8A")); }
        });

        // ESTA ES LA MAGIA DEL TIEMPO REAL: Se actualiza cada vez que enfocas la ventana
        this.addInternalFrameListener(new InternalFrameAdapter() {
            @Override
            public void internalFrameActivated(InternalFrameEvent e) {
                cargarTodosLosGraficos();
            }
        });

        cargarTodosLosGraficos();
    }

    private void cargarTodosLosGraficos() {
        if (isUpdating) return; // Si ya está actualizando, que no se sature
        isUpdating = true;

        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() {
                // ============================================================
                // GRÁFICO 1: Ventas
                // ============================================================
                DefaultCategoryDataset dsVentas = new DefaultCategoryDataset();
                for (Map.Entry<String, Double> entry : dao.getVentasUltimos7Dias().entrySet()) {
                    dsVentas.addValue(entry.getValue(), "Ingresos ($)", entry.getKey());
                }
                chartVentas = ChartFactory.createBarChart("Historial de Facturaci\u00F3n (7 D\u00EDas)", "Fecha", "Monto ($)", dsVentas, PlotOrientation.VERTICAL, false, true, false);
                chartVentas.setBackgroundPaint(Color.WHITE);
                CategoryPlot plotVentas = (CategoryPlot) chartVentas.getPlot();
                plotVentas.setBackgroundPaint(Color.decode("#F8F9FA"));
                BarRenderer rendererVentas = (BarRenderer) plotVentas.getRenderer();
                rendererVentas.setSeriesPaint(0, Color.decode("#2D4A8A")); 

                // ============================================================
                // GRÁFICO 2: Categorías (AHORA MUESTRA LAS 4, INCLUSO CON 0)
                // ============================================================
                DefaultPieDataset dsCat = new DefaultPieDataset();
                for (Map.Entry<String, Integer> entry : dao.getProductosPorCategoria().entrySet()) {
                    // Quitamos la restricción: Queremos que muestre todo
                    dsCat.setValue(entry.getKey(), entry.getValue());
                }
                chartCategorias = ChartFactory.createRingChart("Distribuci\u00F3n de Cat\u00E1logo", dsCat, true, true, false);
                chartCategorias.setBackgroundPaint(Color.WHITE);
                RingPlot plotCat = (RingPlot) chartCategorias.getPlot();
                plotCat.setBackgroundPaint(Color.WHITE);
                plotCat.setOutlineVisible(false);
                plotCat.setSectionDepth(0.30); 
                plotCat.setLabelGenerator(new org.jfree.chart.labels.StandardPieSectionLabelGenerator("{0}: {1} ({2})"));
                plotCat.setLabelBackgroundPaint(Color.WHITE);
                plotCat.setLabelOutlinePaint(null);
                plotCat.setLabelShadowPaint(null);
                
                int indexCat = 0;
                for (Object key : dsCat.getKeys()) {
                    float hue = (indexCat * 0.618033988749895f) % 1.0f;
                    Color colorUnicoYDistinto = Color.getHSBColor(hue, 0.75f, 0.80f);
                    plotCat.setSectionPaint((Comparable) key, colorUnicoYDistinto);
                    indexCat++;
                }

                // ============================================================
                // GRÁFICO 3: Top 5 Productos
                // ============================================================
                DefaultCategoryDataset dsTop = new DefaultCategoryDataset();
                for (Map.Entry<String, Double> entry : dao.getTopProductosMasVendidos().entrySet()) {
                    dsTop.addValue(entry.getValue(), "Unidades", entry.getKey());
                }
                chartTopProductos = ChartFactory.createBarChart("Top 5 Productos M\u00E1s Vendidos", "Producto", "Unidades", dsTop, PlotOrientation.HORIZONTAL, false, true, false);
                chartTopProductos.setBackgroundPaint(Color.WHITE);
                CategoryPlot plotTop = (CategoryPlot) chartTopProductos.getPlot();
                plotTop.setBackgroundPaint(Color.decode("#F8F9FA"));
                BarRenderer rendererTop = (BarRenderer) plotTop.getRenderer();
                rendererTop.setSeriesPaint(0, Color.decode("#17A2B8")); 

                // ============================================================
                // GRÁFICO 4: Estado Stock (ROJO Y VERDE FIJOS)
                // ============================================================
                DefaultPieDataset dsStock = new DefaultPieDataset();
                for (Map.Entry<String, Integer> entry : dao.getProporcionStock().entrySet()) {
                    dsStock.setValue(entry.getKey(), entry.getValue()); // Añade ambos siempre
                }
                chartProporcionStock = ChartFactory.createRingChart("Estado de Alerta de Lotes", dsStock, true, true, false);
                chartProporcionStock.setBackgroundPaint(Color.WHITE);
                RingPlot plotStock = (RingPlot) chartProporcionStock.getPlot();
                plotStock.setBackgroundPaint(Color.WHITE);
                plotStock.setOutlineVisible(false);
                plotStock.setSectionDepth(0.30);
                plotStock.setLabelGenerator(new org.jfree.chart.labels.StandardPieSectionLabelGenerator("{0}: {1} ({2})"));
                plotStock.setLabelBackgroundPaint(Color.WHITE);
                plotStock.setLabelOutlinePaint(null);
                plotStock.setLabelShadowPaint(null);
                
                for (Object key : dsStock.getKeys()) {
                    String textoLlave = key.toString();
                    if (textoLlave.contains("<=5") || textoLlave.toLowerCase().contains("cr")) {
                        plotStock.setSectionPaint((Comparable) key, Color.decode("#E63946")); 
                    } else {
                        plotStock.setSectionPaint((Comparable) key, Color.decode("#28A745")); 
                    }
                }

                return null;
            }

            @Override
            protected void done() {
                panelGraficoVentas.setLayout(new BorderLayout());
                panelGraficoVentas.removeAll();
                panelGraficoVentas.add(new ChartPanel(chartVentas), BorderLayout.CENTER);
                panelGraficoVentas.validate();

                panelGraficoCat.setLayout(new BorderLayout());
                panelGraficoCat.removeAll();
                panelGraficoCat.add(new ChartPanel(chartCategorias), BorderLayout.CENTER);
                panelGraficoCat.validate();

                panelGraficoTopProd.setLayout(new BorderLayout());
                panelGraficoTopProd.removeAll();
                panelGraficoTopProd.add(new ChartPanel(chartTopProductos), BorderLayout.CENTER);
                panelGraficoTopProd.validate();

                panelGraficoStockStatus.setLayout(new BorderLayout());
                panelGraficoStockStatus.removeAll();
                panelGraficoStockStatus.add(new ChartPanel(chartProporcionStock), BorderLayout.CENTER);
                panelGraficoStockStatus.validate();
                
                isUpdating = false; // Liberamos el candado
            }
        }.execute();
    }

    private void generarReportePDF() {
        if (chartVentas == null || chartCategorias == null || chartTopProductos == null || chartProporcionStock == null) {
            JOptionPane.showMessageDialog(this, "A\u00FAn se est\u00E1n recopilando los datos m\u00E9tricos. Espere...");
            return;
        }

        try {
            String dest = System.getProperty("user.home") + "/Desktop/Reporte_Analiticas_SIGVET.pdf";
            Document document = new Document(PageSize.A4);
            PdfWriter.getInstance(document, new FileOutputStream(dest));
            document.open();

            com.itextpdf.text.Font fontTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 22, new com.itextpdf.text.BaseColor(45, 74, 138));
            Paragraph titulo = new Paragraph("Reporte de Rendimiento SIGVET ERP", fontTitulo);
            titulo.setAlignment(Element.ALIGN_CENTER);
            document.add(titulo);
            
            String fechaActual = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
            com.itextpdf.text.Font fontFecha = FontFactory.getFont(FontFactory.HELVETICA_OBLIQUE, 11, com.itextpdf.text.BaseColor.GRAY);
            Paragraph subtitulo = new Paragraph("Emitido de forma segura el: " + fechaActual, fontFecha);
            subtitulo.setAlignment(Element.ALIGN_CENTER);
            document.add(subtitulo);
            document.add(new Paragraph("\n\n"));

            agregarGraficoAlPDF(document, chartVentas);
            document.add(new Paragraph("\n"));
            agregarGraficoAlPDF(document, chartCategorias);
            document.newPage(); 
            agregarGraficoAlPDF(document, chartTopProductos);
            document.add(new Paragraph("\n"));
            agregarGraficoAlPDF(document, chartProporcionStock);

            document.close();
            JOptionPane.showMessageDialog(this, "\u00A1Reporte PDF gerencial guardado con \u00E9xito en tu Escritorio!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al escribir documento: " + e.getMessage());
        }
    }

    private void agregarGraficoAlPDF(Document doc, JFreeChart chart) throws Exception {
        BufferedImage img = chart.createBufferedImage(480, 280);
        java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
        javax.imageio.ImageIO.write(img, "png", baos);
        
        com.itextpdf.text.Image pdfImg = com.itextpdf.text.Image.getInstance(baos.toByteArray());
        pdfImg.setAlignment(Element.ALIGN_CENTER);
        doc.add(pdfImg);
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
        getContentPane().add(lblTitulo);
        lblTitulo.setBounds(25, 20, 400, 30);

        btnGenerarPDF.setBackground(new java.awt.Color(45, 74, 138));
        btnGenerarPDF.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnGenerarPDF.setForeground(new java.awt.Color(255, 255, 255));
        btnGenerarPDF.setText("Generar Reporte PDF");
        btnGenerarPDF.setBorderPainted(false);
        btnGenerarPDF.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGenerarPDF.setFocusPainted(false);
        btnGenerarPDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarPDFActionPerformed(evt);
            }
        });
        getContentPane().add(btnGenerarPDF);
        btnGenerarPDF.setBounds(830, 15, 230, 40);

        panelGraficoVentas.setBackground(new java.awt.Color(255, 255, 255));
        panelGraficoVentas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(197, 216, 245)));
        getContentPane().add(panelGraficoVentas);
        panelGraficoVentas.setBounds(25, 70, 510, 270);

        panelGraficoCat.setBackground(new java.awt.Color(255, 255, 255));
        panelGraficoCat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(197, 216, 245)));
        getContentPane().add(panelGraficoCat);
        panelGraficoCat.setBounds(550, 70, 510, 270);

        panelGraficoTopProd.setBackground(new java.awt.Color(255, 255, 255));
        panelGraficoTopProd.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(197, 216, 245)));
        getContentPane().add(panelGraficoTopProd);
        panelGraficoTopProd.setBounds(25, 360, 510, 270);

        panelGraficoStockStatus.setBackground(new java.awt.Color(255, 255, 255));
        panelGraficoStockStatus.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(197, 216, 245)));
        getContentPane().add(panelGraficoStockStatus);
        panelGraficoStockStatus.setBounds(550, 360, 510, 270);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGenerarPDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarPDFActionPerformed
        generarReportePDF();
    }//GEN-LAST:event_btnGenerarPDFActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGenerarPDF;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel panelGraficoCat;
    private javax.swing.JPanel panelGraficoStockStatus;
    private javax.swing.JPanel panelGraficoTopProd;
    private javax.swing.JPanel panelGraficoVentas;
    // End of variables declaration//GEN-END:variables
}
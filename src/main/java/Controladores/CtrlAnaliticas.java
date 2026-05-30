package Controladores;

import Modelos.AnaliticasDAO;
import Vistas.FrmAnaliticas;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

// JFreeChart
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.RingPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

// iTextPDF
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.PageSize;

public class CtrlAnaliticas implements ActionListener {

    private final FrmAnaliticas vista;
    private final AnaliticasDAO dao;
    
    // Variables para guardar los gráficos generados
    private JFreeChart chartVentas;
    private JFreeChart chartCategorias;
    private JFreeChart chartTopProductos;
    private JFreeChart chartProporcionStock;
    
    // Bandera para no saturar con clics múltiples o enfoques rápidos
    private boolean isUpdating = false;

    public CtrlAnaliticas(FrmAnaliticas vista) {
        this.vista = vista;
        this.dao = new AnaliticasDAO();

        asignarEventos();
        
        // Cargar inmediatamente al abrir el módulo
        cargarTodosLosGraficosAsync();
    }

    private void asignarEventos() {
        // Evento click del botón PDF
        vista.btnGenerarPDF.addActionListener(this);

        // Efecto hover (Light Blue Theme)
        vista.btnGenerarPDF.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) { vista.btnGenerarPDF.setBackground(Color.decode("#3A5AAA")); }
            @Override
            public void mouseExited(MouseEvent e)  { vista.btnGenerarPDF.setBackground(Color.decode("#2D4A8A")); }
        });

        // Recargar datos cada vez que la ventana toma el foco (tiempo real)
        vista.addInternalFrameListener(new InternalFrameAdapter() {
            @Override
            public void internalFrameActivated(InternalFrameEvent e) {
                cargarTodosLosGraficosAsync();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnGenerarPDF) {
            generarReportePDF();
        }
    }

    // =========================================================
    // LÓGICA ASÍNCRONA (AQUÍ ESTÁ LA VELOCIDAD)
    // =========================================================
    private void cargarTodosLosGraficosAsync() {
        if (isUpdating) return; 
        isUpdating = true;

        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                // 1. VENTAS
                DefaultCategoryDataset dsVentas = new DefaultCategoryDataset();
                for (Map.Entry<String, Double> entry : dao.getVentasUltimos7Dias().entrySet()) {
                    dsVentas.addValue(entry.getValue(), "Ingresos ($)", entry.getKey());
                }
                chartVentas = ChartFactory.createBarChart("Historial de Facturacion (7 Dias)", "Fecha", "Monto ($)", dsVentas, PlotOrientation.VERTICAL, false, true, false);
                aplicarEstiloBarra(chartVentas, "#2D4A8A");

                // 2. CATEGORÍAS
                DefaultPieDataset dsCat = new DefaultPieDataset();
                for (Map.Entry<String, Integer> entry : dao.getProductosPorCategoria().entrySet()) {
                    dsCat.setValue(entry.getKey(), entry.getValue());
                }
                chartCategorias = ChartFactory.createRingChart("Distribucion de Catalogo", dsCat, true, true, false);
                aplicarEstiloAnillo(chartCategorias, dsCat);

                // 3. TOP PRODUCTOS
                DefaultCategoryDataset dsTop = new DefaultCategoryDataset();
                for (Map.Entry<String, Double> entry : dao.getTopProductosMasVendidos().entrySet()) {
                    dsTop.addValue(entry.getValue(), "Unidades", entry.getKey());
                }
                chartTopProductos = ChartFactory.createBarChart("Top 5 Productos Mas Vendidos", "Producto", "Unidades", dsTop, PlotOrientation.HORIZONTAL, false, true, false);
                aplicarEstiloBarra(chartTopProductos, "#17A2B8");

                // 4. STOCK
                DefaultPieDataset dsStock = new DefaultPieDataset();
                for (Map.Entry<String, Integer> entry : dao.getProporcionStock().entrySet()) {
                    dsStock.setValue(entry.getKey(), entry.getValue());
                }
                chartProporcionStock = ChartFactory.createRingChart("Estado de Alerta de Lotes", dsStock, true, true, false);
                aplicarEstiloStock(chartProporcionStock, dsStock);

                return null;
            }

            @Override
            protected void done() {
                try {
                    get(); // Verificar si hubo errores en la BD

                    // Pintar en la Vista (ESTO SE HACE EN EL EDT)
                    actualizarPanelGrafico(vista.panelGraficoVentas, chartVentas);
                    actualizarPanelGrafico(vista.panelGraficoCat, chartCategorias);
                    actualizarPanelGrafico(vista.panelGraficoTopProd, chartTopProductos);
                    actualizarPanelGrafico(vista.panelGraficoStockStatus, chartProporcionStock);

                } catch (Exception ex) {
                    System.out.println("Error cargando gráficos: " + ex.getMessage());
                } finally {
                    isUpdating = false; 
                }
            }
        }.execute();
    }

    // =========================================================
    // MÉTODOS DE DISEÑO DE GRÁFICOS (Limpieza visual)
    // =========================================================
    private void aplicarEstiloBarra(JFreeChart chart, String colorHex) {
        chart.setBackgroundPaint(Color.WHITE);
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setBackgroundPaint(Color.decode("#F8F9FA"));
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setSeriesPaint(0, Color.decode(colorHex)); 
    }

    private void aplicarEstiloAnillo(JFreeChart chart, DefaultPieDataset dataset) {
        chart.setBackgroundPaint(Color.WHITE);
        RingPlot plot = (RingPlot) chart.getPlot();
        plot.setBackgroundPaint(Color.WHITE);
        plot.setOutlineVisible(false);
        plot.setSectionDepth(0.30); 
        plot.setLabelGenerator(new org.jfree.chart.labels.StandardPieSectionLabelGenerator("{0}: {1} ({2})"));
        plot.setLabelBackgroundPaint(Color.WHITE);
        plot.setLabelOutlinePaint(null);
        plot.setLabelShadowPaint(null);
        
        int indexCat = 0;
        for (Object key : dataset.getKeys()) {
            float hue = (indexCat * 0.618033988749895f) % 1.0f;
            Color colorUnico = Color.getHSBColor(hue, 0.75f, 0.80f);
            plot.setSectionPaint((Comparable) key, colorUnico);
            indexCat++;
        }
    }

    private void aplicarEstiloStock(JFreeChart chart, DefaultPieDataset dataset) {
        chart.setBackgroundPaint(Color.WHITE);
        RingPlot plot = (RingPlot) chart.getPlot();
        plot.setBackgroundPaint(Color.WHITE);
        plot.setOutlineVisible(false);
        plot.setSectionDepth(0.30);
        plot.setLabelGenerator(new org.jfree.chart.labels.StandardPieSectionLabelGenerator("{0}: {1} ({2})"));
        plot.setLabelBackgroundPaint(Color.WHITE);
        plot.setLabelOutlinePaint(null);
        plot.setLabelShadowPaint(null);
        
        for (Object key : dataset.getKeys()) {
            String textoLlave = key.toString();
            if (textoLlave.contains("<=5") || textoLlave.toLowerCase().contains("cr")) {
                plot.setSectionPaint((Comparable) key, Color.decode("#E63946")); // Rojo
            } else {
                plot.setSectionPaint((Comparable) key, Color.decode("#28A745")); // Verde
            }
        }
    }

    private void actualizarPanelGrafico(javax.swing.JPanel panel, JFreeChart chart) {
        panel.setLayout(new BorderLayout());
        panel.removeAll();
        panel.add(new ChartPanel(chart), BorderLayout.CENTER);
        panel.validate();
        panel.repaint();
    }

    // =========================================================
    // GENERAR PDF
    // =========================================================
    private void generarReportePDF() {
        if (chartVentas == null || chartCategorias == null || chartTopProductos == null || chartProporcionStock == null) {
            JOptionPane.showMessageDialog(vista, "Aun se estan recopilando los datos metricos. Espere un momento...");
            return;
        }

        try {
            String dest = System.getProperty("user.home") + File.separator + "Desktop" + File.separator + "Reporte_Analiticas_SIGVET.pdf";
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
            JOptionPane.showMessageDialog(vista, "Reporte PDF gerencial guardado con exito en tu Escritorio!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(vista, "Error al escribir documento: " + e.getMessage());
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
}
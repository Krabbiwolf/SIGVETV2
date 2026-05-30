package Controladores.CtrlPuntoVenta;

import Modelos.ConsultarFacturasDAO;
import Modelos.SesionUsuario;
import Vistas.FrmFacturaConsultar;

// Librerías para iText (PDF)
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

// Librerías de Java
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

public class CtrlConsultarFactura implements ActionListener {

    private FrmFacturaConsultar vista;
    private ConsultarFacturasDAO dao;

    public CtrlConsultarFactura(FrmFacturaConsultar vista, ConsultarFacturasDAO dao) {
        this.vista = vista;
        this.dao = dao;
        
        // Asignar los eventos a los 5 botones
        this.vista.btnFiltrar.addActionListener(this);
        this.vista.btnLimpiar.addActionListener(this);
        this.vista.btnExportar.addActionListener(this);
        this.vista.btnAnularFactura.addActionListener(this);
        this.vista.btnImprimir.addActionListener(this);
        
        // Cargar la tabla inicialmente sin filtros (todas las facturas)
        cargarTabla(null, null);
        
        if (!SesionUsuario.tienePermiso("EXPORTAR_VENTAS")) {
            vista.btnExportar.setVisible(false);
        }
    }

    // =========================================================================
    // CARGA ASÍNCRONA DE LA TABLA (SWING WORKER)
    // =========================================================================
    private void cargarTabla(Date inicio, Date fin) {
        // 1. Bloquear botones de filtrado y mostrar mensaje de carga en la tabla
        vista.btnFiltrar.setEnabled(false);
        vista.btnLimpiar.setEnabled(false);
        
        DefaultTableModel modeloCargando = new DefaultTableModel(
            new Object[][]{{"Cargando facturas, por favor espere..."}}, 
            new String[]{"Estado del Sistema"}
        );
        vista.tblConsultaFacturas.setModel(modeloCargando);

        // 2. Definir el proceso en un hilo secundario
        SwingWorker<DefaultTableModel, Void> worker = new SwingWorker<DefaultTableModel, Void>() {
            @Override
            protected DefaultTableModel doInBackground() throws Exception {
                // Esta consulta se procesa en segundo plano
                return dao.listarFacturas(inicio, fin);
            }

            @Override
            protected void done() {
                try {
                    // Recuperar el modelo de datos e inyectarlo a la tabla de forma segura
                    DefaultTableModel modelo = get();
                    vista.tblConsultaFacturas.setModel(modelo);
                } catch (Exception ex) {
                    System.out.println("Error en la carga asíncrona de facturas: " + ex.getMessage());
                    JOptionPane.showMessageDialog(vista, "Error al cargar el listado de facturas.");
                } finally {
                    // 3. Rehabilitar el uso de los botones al terminar
                    vista.btnFiltrar.setEnabled(true);
                    vista.btnLimpiar.setEnabled(true);
                }
            }
        };

        // 3. Ejecutar el hilo
        worker.execute();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        // ======================= 1. FILTRAR POR FECHAS =======================
        if (e.getSource() == vista.btnFiltrar) {
            Date inicio = (Date) vista.spFechaInicio.getValue();
            Date fin = (Date) vista.spFechaFin.getValue();
            
            if (inicio.after(fin)) {
                JOptionPane.showMessageDialog(vista, "La fecha de inicio no puede ser mayor a la fecha de fin.");
                return;
            }
            cargarTabla(inicio, fin);
        }

        // ======================= 2. LIMPIAR FILTRO =======================
        if (e.getSource() == vista.btnLimpiar) {
            vista.spFechaInicio.setValue(new Date()); // Restablece a hoy
            vista.spFechaFin.setValue(new Date());    // Restablece a hoy
            cargarTabla(null, null); // Carga todo de nuevo sin bloquear
        }

        // ======================= 3. EXPORTAR A EXCEL =======================
        if (e.getSource() == vista.btnExportar) {
            // Validar que no esté el modelo de carga ni la tabla vacía antes de exportar
            if (vista.tblConsultaFacturas.getRowCount() == 0 || vista.tblConsultaFacturas.getColumnCount() == 1) {
                JOptionPane.showMessageDialog(vista, "No hay datos en la tabla para exportar.");
                return;
            }
            exportarExcel();
        }

        // ======================= 4. ANULAR FACTURA =======================
        if (e.getSource() == vista.btnAnularFactura) {
            int fila = vista.tblConsultaFacturas.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(vista, "Seleccione una factura de la tabla para anular.");
                return;
            }
            
            String estado = vista.tblConsultaFacturas.getValueAt(fila, 3).toString();
            if (estado.equalsIgnoreCase("ANULADA")) {
                JOptionPane.showMessageDialog(vista, "Esta factura ya se encuentra anulada.");
                return;
            }

            int idVenta = Integer.parseInt(vista.tblConsultaFacturas.getValueAt(fila, 0).toString());
            int confirm = JOptionPane.showConfirmDialog(vista, "¿Está seguro de anular la venta #" + idVenta + "?", "Confirmar", JOptionPane.YES_NO_OPTION);
            
            if (confirm == JOptionPane.YES_OPTION) {
                if (dao.anularFactura(idVenta)) {
                    JOptionPane.showMessageDialog(vista, "Factura anulada exitosamente.");
                    vista.btnFiltrar.doClick(); // Simula clic para refrescar asíncronamente manteniendo el filtro actual
                } else {
                    JOptionPane.showMessageDialog(vista, "Error al anular la factura.");
                }
            }
        }

        // ======================= 5. IMPRIMIR PDF =======================
        if (e.getSource() == vista.btnImprimir) {
            int fila = vista.tblConsultaFacturas.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(vista, "Seleccione una factura para imprimir.");
                return;
            }

            int idVenta = Integer.parseInt(vista.tblConsultaFacturas.getValueAt(fila, 0).toString());
            generarPDF(idVenta);
        }
    }

    // =========================================================================
    // EXPORTAR A EXCEL (Formato CSV compatible con Excel)
    // =========================================================================
    private void exportarExcel() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Guardar exportación");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Archivo CSV (*.csv)", "csv"));
        
        int userSelection = fileChooser.showSaveDialog(vista);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File archivoToSave = fileChooser.getSelectedFile();
            String ruta = archivoToSave.getAbsolutePath();
            if (!ruta.toLowerCase().endsWith(".csv")) {
                ruta += ".csv";
            }

            // Usamos OutputStreamWriter con UTF-8 para que las tildes y ñ se guarden bien
            try (java.io.OutputStreamWriter fw = new java.io.OutputStreamWriter(new java.io.FileOutputStream(ruta), java.nio.charset.StandardCharsets.UTF_8)) {
                
                // Escribir el BOM para que Excel detecte la codificación correctamente
                fw.write("\ufeff");

                // Escribir cabeceras separadas por PUNTO Y COMA (;)
                for (int i = 0; i < vista.tblConsultaFacturas.getColumnCount(); i++) {
                    fw.write(vista.tblConsultaFacturas.getColumnName(i));
                    if (i < vista.tblConsultaFacturas.getColumnCount() - 1) {
                        fw.write(";");
                    }
                }
                fw.write("\n");

                // Escribir datos
                for (int i = 0; i < vista.tblConsultaFacturas.getRowCount(); i++) {
                    for (int j = 0; j < vista.tblConsultaFacturas.getColumnCount(); j++) {
                        Object valor = vista.tblConsultaFacturas.getValueAt(i, j);
                        String texto = (valor != null ? valor.toString() : "");
                        
                        // Si el texto tiene un punto y coma, comillas dobles o saltos de línea, lo encerramos entre comillas
                        if (texto.contains(";") || texto.contains("\"") || texto.contains("\n") || texto.contains("\r")) {
                            texto = "\"" + texto.replace("\"", "\"\"") + "\"";
                        }
                        
                        fw.write(texto);
                        
                        // Separador de columnas: PUNTO Y COMA (;)
                        if (j < vista.tblConsultaFacturas.getColumnCount() - 1) {
                            fw.write(";");
                        }
                    }
                    fw.write("\n");
                }
                JOptionPane.showMessageDialog(vista, "Exportación exitosa.");
                Desktop.getDesktop().open(new File(ruta));
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(vista, "Error al exportar: " + ex.getMessage());
            }
        }
    }

    // =========================================================================
    // LÓGICA DE CREACIÓN DEL PDF CON iTEXT
    // =========================================================================
    private void generarPDF(int idVenta) {
        Map<String, String> cabecera = dao.obtenerCabeceraFactura(idVenta);
        List<Object[]> detalles = dao.obtenerDetallesFactura(idVenta);

        if (cabecera.isEmpty() || detalles.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "No se encontraron datos para generar el PDF.");
            return;
        }

        Document documento = new Document();
        try {
            String rutaArchivo = System.getProperty("user.home") + "/Desktop/Factura_" + cabecera.get("comprobante") + ".pdf";
            PdfWriter.getInstance(documento, new FileOutputStream(rutaArchivo));
            documento.open();

            Font fontTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, BaseColor.BLUE.darker());
            Paragraph titulo = new Paragraph("SIVET", fontTitulo);
            titulo.setAlignment(Element.ALIGN_CENTER);
            documento.add(titulo);

            Font fontSub = FontFactory.getFont(FontFactory.HELVETICA, 10, BaseColor.DARK_GRAY);
            Paragraph subtitulo = new Paragraph("Santa Ana, El Salvador\nComprobante de Venta", fontSub);
            subtitulo.setAlignment(Element.ALIGN_CENTER);
            subtitulo.setSpacingAfter(20);
            documento.add(subtitulo);

            Font fontNormal = FontFactory.getFont(FontFactory.HELVETICA, 11, BaseColor.BLACK);
            documento.add(new Paragraph("N° Comprobante: " + cabecera.get("comprobante"), fontNormal));
            documento.add(new Paragraph("Fecha: " + cabecera.get("fecha"), fontNormal));
            documento.add(new Paragraph("Cliente: " + cabecera.get("cliente") + " (DUI: " + cabecera.get("dui") + ")", fontNormal));
            documento.add(new Paragraph("Cajero: " + cabecera.get("cajero"), fontNormal));
            documento.add(Chunk.NEWLINE);

            PdfPTable tabla = new PdfPTable(5);
            tabla.setWidthPercentage(100);
            tabla.setWidths(new float[]{10f, 40f, 15f, 15f, 20f});

            String[] headers = {"Cant.", "Descripción", "P. Unit", "IVA", "Subtotal"};
            Font fontHeader = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 11, BaseColor.WHITE);
            for (String header : headers) {
                PdfPCell celda = new PdfPCell(new Phrase(header, fontHeader));
                celda.setBackgroundColor(BaseColor.BLUE.darker());
                celda.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabla.addCell(celda);
            }

            double subtotalGlobal = 0.0;
            double ivaGlobal = 0.0;

            for (Object[] row : detalles) {
                int cantidad = (int) row[0];
                String nombre = (String) row[1];
                double precioUnitario = (double) row[2]; 
                
                double subtotalFila = cantidad * precioUnitario;
                double ivaFila = subtotalFila * 0.13; 
                double totalFila = subtotalFila + ivaFila;

                subtotalGlobal += subtotalFila;
                ivaGlobal += ivaFila;

                tabla.addCell(String.valueOf(cantidad));
                tabla.addCell(nombre);
                tabla.addCell(String.format("$%.2f", precioUnitario));
                tabla.addCell(String.format("$%.2f", ivaFila));
                tabla.addCell(String.format("$%.2f", totalFila));
            }
            documento.add(tabla);

            double totalFinal = subtotalGlobal + ivaGlobal;
            Paragraph totales = new Paragraph();
            totales.setAlignment(Element.ALIGN_RIGHT);
            totales.setSpacingBefore(10);
            totales.add(new Phrase(String.format("Subtotal (Sin IVA): $%.2f\n", subtotalGlobal), fontNormal));
            totales.add(new Phrase(String.format("IVA (13%%): $%.2f\n", ivaGlobal), fontNormal));
            
            Font fontTotal = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14, BaseColor.RED.darker());
            totales.add(new Phrase(String.format("TOTAL A PAGAR: $%.2f", totalFinal), fontTotal));
            documento.add(totales);

            documento.close();
            JOptionPane.showMessageDialog(vista, "Factura guardada en el Escritorio.");

            File file = new File(rutaArchivo);
            if (file.exists() && Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(file);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Error al generar PDF: " + ex.getMessage());
        }
    }
}
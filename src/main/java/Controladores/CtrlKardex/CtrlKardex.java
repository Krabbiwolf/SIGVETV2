/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores.CtrlKardex;

import Modelos.Kardex;
import Modelos.KardexDAO;
import Vistas.FrmKardex;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class CtrlKardex {

    private final FrmKardex vista;
    private final KardexDAO dao;

    public CtrlKardex(FrmKardex vista) {
        this.vista = vista;
        this.dao = new KardexDAO();

        configurarTabla();
        agregarEventos();
        cargarTabla();
    }

    private void configurarTabla() {
        vista.tblKardex.setModel(crearModeloTabla());
    }

    private DefaultTableModel crearModeloTabla() {
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };

        modelo.addColumn("Fecha/Hora");
        modelo.addColumn("Tipo Movimiento");
        modelo.addColumn("Producto");
        modelo.addColumn("Código Barras");
        modelo.addColumn("Lote");
        modelo.addColumn("Cantidad");
        modelo.addColumn("Usuario");
        modelo.addColumn("Respaldo/Motivo");

        return modelo;
    }

    private void agregarEventos() {
        vista.btnFiltrar.addActionListener(e -> cargarTabla());
        vista.btnLimpiar.addActionListener(e -> limpiarFiltros());
        vista.btnExportar.addActionListener(e -> exportarCSV());
    }

    private void cargarTabla() {
        Date fechaInicio = vista.getFechaInicio();
        Date fechaFin = vista.getFechaFin();
        String tipoMovimiento = vista.cboTipoMovimiento.getSelectedItem().toString();
        String busqueda = vista.txtBuscar.getText().trim();

        DefaultTableModel modelo = crearModeloTabla();

        ArrayList<Kardex> lista = dao.listarKardex(fechaInicio, fechaFin, tipoMovimiento, busqueda);

        for (Kardex k : lista) {
            Object[] fila = new Object[]{
                k.getFechaHora(),
                k.getTipoMovimiento(),
                k.getProducto(),
                k.getCodigoBarras(),
                k.getLote(),
                k.getCantidad(),
                k.getUsuario(),
                k.getRespaldoMotivo()
            };

            modelo.addRow(fila);
        }

        vista.tblKardex.setModel(modelo);
    }

    private void limpiarFiltros() {
    vista.cboTipoMovimiento.setSelectedIndex(0);
    vista.txtBuscar.setText("");

    java.util.Calendar calendarInicio = java.util.Calendar.getInstance();
    calendarInicio.add(java.util.Calendar.MONTH, -1);
    vista.spFechaInicio.setValue(calendarInicio.getTime());

    java.util.Calendar calendarFin = java.util.Calendar.getInstance();
    calendarFin.add(java.util.Calendar.DAY_OF_MONTH, 1);
    vista.spFechaFin.setValue(calendarFin.getTime());

    cargarTabla();
}

    private void exportarCSV() {
        try {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Guardar Kardex");

            int seleccion = fileChooser.showSaveDialog(vista);

            if (seleccion != JFileChooser.APPROVE_OPTION) {
                return;
            }

            String ruta = fileChooser.getSelectedFile().getAbsolutePath();

            if (!ruta.toLowerCase().endsWith(".csv")) {
                ruta += ".csv";
            }

            FileWriter writer = new FileWriter(ruta);

            DefaultTableModel modelo = (DefaultTableModel) vista.tblKardex.getModel();

            for (int i = 0; i < modelo.getColumnCount(); i++) {
                writer.write(modelo.getColumnName(i));

                if (i < modelo.getColumnCount() - 1) {
                    writer.write(",");
                }
            }

            writer.write("\n");

            for (int fila = 0; fila < modelo.getRowCount(); fila++) {
                for (int columna = 0; columna < modelo.getColumnCount(); columna++) {
                    Object valor = modelo.getValueAt(fila, columna);

                    String texto = valor == null ? "" : valor.toString();
                    texto = texto.replace(",", " ");

                    writer.write(texto);

                    if (columna < modelo.getColumnCount() - 1) {
                        writer.write(",");
                    }
                }

                writer.write("\n");
            }

            writer.close();

            JOptionPane.showMessageDialog(vista, "Kardex exportado correctamente.");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(vista, "Error al exportar: " + e.getMessage());
        }
    }
}

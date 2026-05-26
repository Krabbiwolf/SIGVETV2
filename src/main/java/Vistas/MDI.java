package Vistas;

import Controladores.CtrlCompra.*;
import Controladores.CtrlFactura.*;
import Controladores.CtrlProveedor.*;
import Controladores.CtrlRoles.*;
import Controladores.CtrlUsuarios.*;
import Controladores.ctrlProductos.*;
import Controladores.CtrlGestionarClientes;
import Modelos.*;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class MDI extends javax.swing.JFrame {

    private String nombreUsuario = "Admin";
    private String rolUsuario = "Administrador";

    public MDI() {
        configurarEstilo();
        initComponents();
        this.setExtendedState(MAXIMIZED_BOTH);
    }

    public MDI(String nombre, String rol) {
        this.nombreUsuario = nombre;
        this.rolUsuario = rol;
        configurarEstilo();
        initComponents();
        this.setExtendedState(MAXIMIZED_BOTH);
    }

    private void configurarEstilo() {
        // Forzamos colores oscuros a nivel global para todas las ventanas internas que abramos
        UIManager.put("InternalFrame.activeTitleBackground", Color.decode("#181D2E"));
        UIManager.put("InternalFrame.inactiveTitleBackground", Color.decode("#111520"));
        UIManager.put("InternalFrame.activeTitleForeground", Color.decode("#F0F2FF"));
        UIManager.put("InternalFrame.inactiveTitleForeground", Color.decode("#9BA3C4"));
        UIManager.put("InternalFrame.borderColor", Color.decode("#2A3050"));
    }

    // Método universal para abrir ventanas y centrarlas en el DesktopPane
    private void abrirVentana(JInternalFrame ventana) {
        desktopPane.add(ventana);
        Dimension desktopSize = desktopPane.getSize();
        Dimension jInternalFrameSize = ventana.getSize();
        ventana.setLocation((desktopSize.width - jInternalFrameSize.width) / 2,
                (desktopSize.height - jInternalFrameSize.height) / 2);
        ventana.setVisible(true);
        try {
            ventana.setSelected(true);
        } catch (Exception e) {
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        desktopPane = new javax.swing.JDesktopPane();
        menuBar = new javax.swing.JMenuBar();
        menuArchivo = new javax.swing.JMenu();
        menuItemSalir = new javax.swing.JMenuItem();
        menuProveedores = new javax.swing.JMenu();
        itemGestionarProveedores = new javax.swing.JMenuItem();
        itemGestionarClientes = new javax.swing.JMenuItem();
        menuProductos = new javax.swing.JMenu();
        itemGestionarProductos = new javax.swing.JMenuItem();
        itemGestionarCategorias = new javax.swing.JMenuItem();
        itemAjusteInventario = new javax.swing.JMenuItem();
        itemKardex = new javax.swing.JMenuItem();
        menuUsuarios = new javax.swing.JMenu();
        itemNuevoUsuario = new javax.swing.JMenuItem();
        itemGestionarUsuarios = new javax.swing.JMenuItem();
        itemRoles = new javax.swing.JMenuItem();
        menuCompras = new javax.swing.JMenu();
        itemNuevaCompra = new javax.swing.JMenuItem();
        menuFacturacion = new javax.swing.JMenu();
        itemPuntoVenta = new javax.swing.JMenuItem();
        itemConsultarFactura = new javax.swing.JMenuItem();
        menuAnaliticas = new javax.swing.JMenu();
        itemDashboard = new javax.swing.JMenuItem();
        itemAnaliticas = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SIGVET - Sistema ERP");

        desktopPane.setBackground(new java.awt.Color(10, 12, 16));
        getContentPane().add(desktopPane, java.awt.BorderLayout.CENTER);

        menuArchivo.setText("Archivo");

        menuItemSalir.setText("Cerrar Sesión y Salir");
        menuItemSalir.addActionListener(this::menuItemSalirActionPerformed);
        menuArchivo.add(menuItemSalir);

        menuBar.add(menuArchivo);

        menuProveedores.setText("Terceros");

        itemGestionarProveedores.setText("Gestionar Proveedores");
        itemGestionarProveedores.addActionListener(this::itemGestionarProveedoresActionPerformed);
        menuProveedores.add(itemGestionarProveedores);

        itemGestionarClientes.setText("Gestionar Clientes");
        itemGestionarClientes.addActionListener(this::itemGestionarClientesActionPerformed);
        menuProveedores.add(itemGestionarClientes);

        menuBar.add(menuProveedores);

        menuProductos.setText("Productos");

        itemGestionarProductos.setText("Gestionar Productos");
        itemGestionarProductos.addActionListener(this::itemGestionarProductosActionPerformed);
        menuProductos.add(itemGestionarProductos);

        itemGestionarCategorias.setText("Gestionar Categorias");
        itemGestionarCategorias.addActionListener(this::itemGestionarCategoriasActionPerformed);
        menuProductos.add(itemGestionarCategorias);

        itemAjusteInventario.setText("Ajuste de Inventario");
        itemAjusteInventario.addActionListener(this::itemAjusteInventarioActionPerformed);
        menuProductos.add(itemAjusteInventario);

        itemKardex.setText("Kardex de Movimientos");
        itemKardex.addActionListener(this::itemKardexActionPerformed);
        menuProductos.add(itemKardex);

        menuBar.add(menuProductos);

        menuUsuarios.setText("Usuarios y Permisos");

        itemNuevoUsuario.setText("Nuevo Usuario");
        itemNuevoUsuario.addActionListener(this::itemNuevoUsuarioActionPerformed);
        menuUsuarios.add(itemNuevoUsuario);

        itemGestionarUsuarios.setText("Gestionar Usuarios");
        itemGestionarUsuarios.addActionListener(this::itemGestionarUsuariosActionPerformed);
        menuUsuarios.add(itemGestionarUsuarios);

        itemRoles.setText("Roles y Permisos");
        itemRoles.addActionListener(this::itemRolesActionPerformed);
        menuUsuarios.add(itemRoles);

        menuBar.add(menuUsuarios);

        menuCompras.setText("Compras");

        itemNuevaCompra.setText("Registrar Compra");
        itemNuevaCompra.addActionListener(this::itemNuevaCompraActionPerformed);
        menuCompras.add(itemNuevaCompra);

        menuBar.add(menuCompras);

        menuFacturacion.setText("Facturación y Ventas");

        itemPuntoVenta.setText("Punto de Venta");
        itemPuntoVenta.addActionListener(this::itemPuntoVentaActionPerformed);
        menuFacturacion.add(itemPuntoVenta);

        itemConsultarFactura.setText("Consultar Facturas");
        itemConsultarFactura.addActionListener(this::itemConsultarFacturaActionPerformed);
        menuFacturacion.add(itemConsultarFactura);

        menuBar.add(menuFacturacion);

        menuAnaliticas.setText("Dashboard y Reportes");

        itemDashboard.setText("Dashboard General");
        itemDashboard.addActionListener(this::itemDashboardActionPerformed);
        menuAnaliticas.add(itemDashboard);

        itemAnaliticas.setText("Analíticas");
        itemAnaliticas.addActionListener(this::itemAnaliticasActionPerformed);
        menuAnaliticas.add(itemAnaliticas);

        menuBar.add(menuAnaliticas);

        setJMenuBar(menuBar);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void itemGestionarProveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemGestionarProveedoresActionPerformed
        FrmGestionarProveedores form = new FrmGestionarProveedores();
        CtrlGestionarProveedores ctrl = new CtrlGestionarProveedores(new Proveedor(), form, new ProveedorDAO());
        ctrl.cargarTabla();
        abrirVentana(form);
    }//GEN-LAST:event_itemGestionarProveedoresActionPerformed

    private void itemGestionarProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemGestionarProductosActionPerformed
        FrmGestionarProductos vista = new FrmGestionarProductos();
        new GestionProductosController(vista);
        abrirVentana(vista);
    }//GEN-LAST:event_itemGestionarProductosActionPerformed

    private void itemAjusteInventarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemAjusteInventarioActionPerformed
        FrmAjusteInventario vista = new FrmAjusteInventario();
        new AjusteInventarioController(vista);
        abrirVentana(vista);
    }//GEN-LAST:event_itemAjusteInventarioActionPerformed

    private void itemKardexActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemKardexActionPerformed
    FrmKardex vista = new FrmKardex();
    abrirVentana(vista);
    }//GEN-LAST:event_itemKardexActionPerformed

    private void itemNuevoUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemNuevoUsuarioActionPerformed
        FrmNuevoUsuario vista = new FrmNuevoUsuario();
        new CtrlNuevoUsuario(new Usuario(), vista, new UsuarioDAO());
        abrirVentana(vista);
    }//GEN-LAST:event_itemNuevoUsuarioActionPerformed

    private void itemGestionarUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemGestionarUsuariosActionPerformed
        FrmGestionarUsuarios vista = new FrmGestionarUsuarios();
        CtrlGestionarUsuarios ctrl = new CtrlGestionarUsuarios(new Usuario(), vista, new UsuarioDAO());
        ctrl.cargarTabla();
        abrirVentana(vista);
    }//GEN-LAST:event_itemGestionarUsuariosActionPerformed

    private void itemRolesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemRolesActionPerformed
        FrmRolesPermisos vista = new FrmRolesPermisos();
        FrmNuevoUsuario vistaUsuario = new FrmNuevoUsuario();           
        new CtrlGestionarRoles(new Roles(), vista, vistaUsuario, new RolesDAO());
        abrirVentana(vista);
    }//GEN-LAST:event_itemRolesActionPerformed

    private void itemNuevaCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemNuevaCompraActionPerformed
        FrmRegistrarNuevaCompra vista = new FrmRegistrarNuevaCompra();
        new CtrlNuevaCompra(new Compra(), vista, new CompraDAO());
        abrirVentana(vista);
    }//GEN-LAST:event_itemNuevaCompraActionPerformed

    private void itemPuntoVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemPuntoVentaActionPerformed
        FrmFacturaPuntoVenta vista = new FrmFacturaPuntoVenta();
        abrirVentana(vista);
    }//GEN-LAST:event_itemPuntoVentaActionPerformed

    private void itemConsultarFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemConsultarFacturaActionPerformed
        FrmFacturaConsultar vista = new FrmFacturaConsultar();
        abrirVentana(vista);
    }//GEN-LAST:event_itemConsultarFacturaActionPerformed

    private void itemDashboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemDashboardActionPerformed
        FrmDashboard vista = new FrmDashboard();
        abrirVentana(vista);
    }//GEN-LAST:event_itemDashboardActionPerformed

    private void itemAnaliticasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemAnaliticasActionPerformed
        FrmAnaliticas vista = new FrmAnaliticas();
        abrirVentana(vista);
    }//GEN-LAST:event_itemAnaliticasActionPerformed

    private void itemGestionarClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemGestionarClientesActionPerformed
        GestionarClientes vista = new GestionarClientes();
        abrirVentana(vista);
    }//GEN-LAST:event_itemGestionarClientesActionPerformed

    private void menuItemSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemSalirActionPerformed
        int confirm = JOptionPane.showConfirmDialog(this, "¿Cerrar sesión y salir?", "SIGVET", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) { 
            Modelos.SesionUsuario.cerrarSesion(); 
            this.dispose(); 
            new FrmLogin().setVisible(true); 
        }
    }//GEN-LAST:event_menuItemSalirActionPerformed

    private void itemGestionarCategoriasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemGestionarCategoriasActionPerformed
        // TODO add your handling code here:
        GestionarCategorias vista = new GestionarCategorias();
        abrirVentana(vista);
    }//GEN-LAST:event_itemGestionarCategoriasActionPerformed

    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(MDI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MDI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane desktopPane;
    private javax.swing.JMenuItem itemAjusteInventario;
    private javax.swing.JMenuItem itemAnaliticas;
    private javax.swing.JMenuItem itemConsultarFactura;
    private javax.swing.JMenuItem itemDashboard;
    private javax.swing.JMenuItem itemGestionarCategorias;
    private javax.swing.JMenuItem itemGestionarClientes;
    private javax.swing.JMenuItem itemGestionarProductos;
    private javax.swing.JMenuItem itemGestionarProveedores;
    private javax.swing.JMenuItem itemGestionarUsuarios;
    private javax.swing.JMenuItem itemKardex;
    private javax.swing.JMenuItem itemNuevaCompra;
    private javax.swing.JMenuItem itemNuevoUsuario;
    private javax.swing.JMenuItem itemPuntoVenta;
    private javax.swing.JMenuItem itemRoles;
    private javax.swing.JMenu menuAnaliticas;
    private javax.swing.JMenu menuArchivo;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu menuCompras;
    private javax.swing.JMenu menuFacturacion;
    private javax.swing.JMenuItem menuItemSalir;
    private javax.swing.JMenu menuProductos;
    private javax.swing.JMenu menuProveedores;
    private javax.swing.JMenu menuUsuarios;
    // End of variables declaration//GEN-END:variables
}
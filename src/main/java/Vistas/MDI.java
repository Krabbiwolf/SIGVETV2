package Vistas;

import Controladores.CtrlCompra.CtrlConsultaCompras;
import Controladores.CtrlCompra.CtrlPuntoCompra;
import Controladores.CtrlProveedor.*;
import Controladores.CtrlRoles.*;
import Controladores.CtrlUsuarios.*;
import Controladores.ctrlProductos.*;
import Controladores.CtrlGestionarClientes;
import Controladores.CtrlPuntoVenta.CtrlPuntoVenta;
import Controladores.CtrlPuntoVenta.CtrlConsultarFactura;
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
        
        menuUsuarios.setVisible(SesionUsuario.tienePermiso("LECTURA_USUARIOS"));
        menuProductos.setVisible(SesionUsuario.tienePermiso("LECTURA_PRODUCTOS"));
        menuFacturacion.setVisible(SesionUsuario.tienePermiso("LECTURA_VENTAS"));
        menuCompras.setVisible(SesionUsuario.tienePermiso("LECTURA_COMPRAS"));
        menuAnaliticas.setVisible(SesionUsuario.tienePermiso("LECTURA_REPORTES"));
        menuProveedores.setVisible(SesionUsuario.tienePermiso("LECTURA_TERCEROS"));
        jMenu3.setVisible(SesionUsuario.tienePermiso("LECTURA_LOTES"));
        
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
        // Inyectamos el tema Azul Corporativo (Light) a nivel global para las ventanas internas
        UIManager.put("InternalFrame.activeTitleBackground", Color.decode("#2D4A8A"));
        UIManager.put("InternalFrame.inactiveTitleBackground", Color.decode("#DCE6F2"));
        UIManager.put("InternalFrame.activeTitleForeground", Color.WHITE);
        UIManager.put("InternalFrame.inactiveTitleForeground", Color.decode("#2D4A8A"));
        UIManager.put("InternalFrame.borderColor", Color.decode("#C5D8F5"));
        UIManager.put("DesktopPane.background", Color.decode("#F0F4F8")); // Fondo limpio
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

        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
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
        itemRoles = new javax.swing.JMenuItem();
        menuCompras = new javax.swing.JMenu();
        itemNuevaCompra = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        menuFacturacion = new javax.swing.JMenu();
        itemPuntoVenta = new javax.swing.JMenuItem();
        itemConsultarFactura = new javax.swing.JMenuItem();
        menuAnaliticas = new javax.swing.JMenu();
        itemDashboard = new javax.swing.JMenuItem();
        itemAnaliticas = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        menuConfiguracion = new javax.swing.JMenu();
        itemConfiguracionSistema = new javax.swing.JMenuItem();

        jMenuItem1.setText("jMenuItem1");

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SIGVET - Sistema ERP");

        desktopPane.setBackground(new java.awt.Color(240, 244, 248));
        getContentPane().add(desktopPane, java.awt.BorderLayout.CENTER);

        menuArchivo.setText("Secion");

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

        itemRoles.setText("Roles y Permisos");
        itemRoles.addActionListener(this::itemRolesActionPerformed);
        menuUsuarios.add(itemRoles);

        menuBar.add(menuUsuarios);

        menuCompras.setText("Compras");

        itemNuevaCompra.setText("Registrar Compra");
        itemNuevaCompra.addActionListener(this::itemNuevaCompraActionPerformed);
        menuCompras.add(itemNuevaCompra);

        jMenuItem6.setText("Consultar Compras");
        jMenuItem6.addActionListener(this::jMenuItem6ActionPerformed);
        menuCompras.add(jMenuItem6);

        menuBar.add(menuCompras);

        menuFacturacion.setText("Facturación y Ventas");

        itemPuntoVenta.setText("Punto de Venta");
        itemPuntoVenta.addActionListener(this::itemPuntoVentaActionPerformed);
        menuFacturacion.add(itemPuntoVenta);

        itemConsultarFactura.setText("Consultar Ventas");
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

        jMenu3.setText("Maestros-Detalle");

        jMenuItem3.setText("Clientes-Ventas");
        jMenuItem3.addActionListener(this::jMenuItem3ActionPerformed);
        jMenu3.add(jMenuItem3);

        jMenuItem5.setText("Proveedores-Compras");
        jMenuItem5.addActionListener(this::jMenuItem5ActionPerformed);
        jMenu3.add(jMenuItem5);

        jMenuItem4.setText("Productos-Lotes");
        jMenuItem4.addActionListener(this::jMenuItem4ActionPerformed);
        jMenu3.add(jMenuItem4);

        jMenuItem2.setText("Categorias-Productos");
        jMenuItem2.addActionListener(this::jMenuItem2ActionPerformed);
        jMenu3.add(jMenuItem2);

        menuBar.add(jMenu3);

        menuConfiguracion.setText("Configuracion");
        menuConfiguracion.addActionListener(this::menuConfiguracionActionPerformed);

        itemConfiguracionSistema.setText("Configuracion del Sistema");
        itemConfiguracionSistema.addActionListener(this::itemConfiguracionSistemaActionPerformed);
        menuConfiguracion.add(itemConfiguracionSistema);

        menuBar.add(menuConfiguracion);

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
        CtrlNuevoUsuario ctrl = new CtrlNuevoUsuario(new Usuario(), vista, new UsuarioDAO());
        ctrl.cargarTabla();
        abrirVentana(vista);
    }//GEN-LAST:event_itemNuevoUsuarioActionPerformed

    private void itemGestionarUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemGestionarUsuariosActionPerformed
        
    }//GEN-LAST:event_itemGestionarUsuariosActionPerformed

    private void itemRolesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemRolesActionPerformed
        FrmRolesPermisos vista = new FrmRolesPermisos();
        FrmNuevoUsuario vistaUsuario = new FrmNuevoUsuario();
        new CtrlGestionarRoles(new Roles(), vista, vistaUsuario, new RolesDAO());
        abrirVentana(vista);
    }//GEN-LAST:event_itemRolesActionPerformed

    private void itemNuevaCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemNuevaCompraActionPerformed
        FrmPuntoDeCompra vista = new FrmPuntoDeCompra();
        new CtrlPuntoCompra(vista, new CompraDAO());
        abrirVentana(vista);
    }//GEN-LAST:event_itemNuevaCompraActionPerformed

    private void itemPuntoVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemPuntoVentaActionPerformed
        FrmPuntoDeVenta vista = new FrmPuntoDeVenta();
        new CtrlPuntoVenta(vista, new VentaDAO());
        abrirVentana(vista);
    }//GEN-LAST:event_itemPuntoVentaActionPerformed

    private void itemConsultarFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemConsultarFacturaActionPerformed
        FrmFacturaConsultar vista = new FrmFacturaConsultar();
        ConsultarFacturasDAO dao = new ConsultarFacturasDAO();
        CtrlConsultarFactura controlador = new CtrlConsultarFactura(vista, dao);
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
        FrmGestionarClientes vista = new FrmGestionarClientes(); // Cambiado al nuevo nombre de la clase
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
        FrmGestionarCategorias vista = new FrmGestionarCategorias(); // Cambiado al nuevo nombre de la clase
        abrirVentana(vista);
    }//GEN-LAST:event_itemGestionarCategoriasActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        FrmMDCategoriasProductos vista = new FrmMDCategoriasProductos();
        abrirVentana(vista);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        FrmMDClientesFacturas vista = new FrmMDClientesFacturas();
        abrirVentana(vista);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
        FrmMDProductosLotes vista = new FrmMDProductosLotes();
        abrirVentana(vista);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        // TODO add your handling code here:
        FrmMDProveedoresCompras vista = new FrmMDProveedoresCompras();
        abrirVentana(vista);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        // TODO add your handling code here:
        FrmConsultarCompras vista = new FrmConsultarCompras();
        ConsultaComprasDAO dao = new ConsultaComprasDAO();
        CtrlConsultaCompras controlador = new CtrlConsultaCompras(vista, dao);
        abrirVentana(vista);
    }//GEN-LAST:event_jMenuItem6ActionPerformed
    private void menuConfiguracionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuConfiguracionActionPerformed
        // TODO add your handling code here:
        FrmConfiguracion vista = new FrmConfiguracion();
        abrirVentana(vista);
    }//GEN-LAST:event_menuConfiguracionActionPerformed

    private void itemConfiguracionSistemaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemConfiguracionSistemaActionPerformed
        // TODO add your handling code here:
        FrmConfiguracion vista = new FrmConfiguracion();
        abrirVentana(vista);
    }//GEN-LAST:event_itemConfiguracionSistemaActionPerformed

    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(MDI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane desktopPane;
    private javax.swing.JMenuItem itemAjusteInventario;
    private javax.swing.JMenuItem itemAnaliticas;
    private javax.swing.JMenuItem itemConfiguracionSistema;
    private javax.swing.JMenuItem itemConsultarFactura;
    private javax.swing.JMenuItem itemDashboard;
    private javax.swing.JMenuItem itemGestionarCategorias;
    private javax.swing.JMenuItem itemGestionarClientes;
    private javax.swing.JMenuItem itemGestionarProductos;
    private javax.swing.JMenuItem itemGestionarProveedores;
    private javax.swing.JMenuItem itemKardex;
    private javax.swing.JMenuItem itemNuevaCompra;
    private javax.swing.JMenuItem itemNuevoUsuario;
    private javax.swing.JMenuItem itemPuntoVenta;
    private javax.swing.JMenuItem itemRoles;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenu menuAnaliticas;
    private javax.swing.JMenu menuArchivo;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu menuCompras;
    private javax.swing.JMenu menuConfiguracion;
    private javax.swing.JMenu menuFacturacion;
    private javax.swing.JMenuItem menuItemSalir;
    private javax.swing.JMenu menuProductos;
    private javax.swing.JMenu menuProveedores;
    private javax.swing.JMenu menuUsuarios;
    // End of variables declaration//GEN-END:variables
}

package Vistas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JDesktopPane;
import javax.swing.BorderFactory;

import Controladores.ctrlProductos.NuevoProductoController;
import Controladores.ctrlProductos.GestionProductosController;
import Controladores.ctrlProductos.AjusteInventarioController;
import Modelos.Roles;
import Modelos.Usuario;
import com.formdev.flatlaf.FlatDarkLaf;
import javax.swing.UIManager;

/**
 *
 * @author ASUS
 */
public class MDI extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(MDI.class.getName());
    public static JDesktopPane jDesktopPane_MDI;

    public MDI() {
        // Configuraciones globales Premium para FlatLaf ANTES de inicializar componentes
        try {
            UIManager.put("MenuBar.background", Color.decode("#111520"));
            UIManager.put("MenuBar.borderColor", Color.decode("#2A3050"));
            UIManager.put("Menu.selectionBackground", Color.decode("#6C63FF"));
            UIManager.put("Menu.selectionForeground", Color.WHITE);
            UIManager.put("MenuItem.selectionBackground", Color.decode("#6C63FF"));
            UIManager.put("MenuItem.background", Color.decode("#111520"));
            UIManager.put("MenuItem.foreground", Color.decode("#9BA3C4"));
            UIManager.put("PopupMenu.background", Color.decode("#111520"));
            UIManager.put("PopupMenu.border", BorderFactory.createLineBorder(Color.decode("#2A3050")));
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception ex) {
            System.err.println("Fallo al inicializar FlatLaf");
        }
        
        initComponents();
        
        this.setSize(new Dimension(1200, 700));
        this.setExtendedState(this.MAXIMIZED_BOTH);
        this.setLocationRelativeTo(null);
        this.setTitle("SIGVET / NexStore ERP");

        this.setLayout(null);
        jDesktopPane_MDI = new JDesktopPane();
        
        // --- APLICAR ESTILO VISUAL AL DESKTOP PANE ---
        jDesktopPane_MDI.setBackground(Color.decode("#0A0C10"));

        int ancho = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
        int alto = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
        this.jDesktopPane_MDI.setBounds(0, 0, ancho, (alto));
        this.add(jDesktopPane_MDI);
        
        aplicarEstiloMenuPrincipal();
    }

    private void aplicarEstiloMenuPrincipal() {
        // Estilizar la barra de menú para que coincida con el navbar de la plantilla
        jMenuBar1.setBackground(Color.decode("#111520"));
        jMenuBar1.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.decode("#2A3050"))); 
        jMenuBar1.setOpaque(true);

        // Recorrer los menús para estilizarlos (compactos y limpios)
        for (int i = 0; i < jMenuBar1.getMenuCount(); i++) {
            javax.swing.JMenu menu = jMenuBar1.getMenu(i);
            if (menu != null) {
                menu.setForeground(Color.decode("#F0F2FF"));
                menu.setFont(new Font("Segoe UI", Font.BOLD, 13));
                menu.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

                for (java.awt.Component item : menu.getMenuComponents()) {
                    if (item instanceof javax.swing.JMenuItem) {
                        javax.swing.JMenuItem menuItem = (javax.swing.JMenuItem) item;
                        menuItem.setBackground(Color.decode("#181D2E"));
                        menuItem.setForeground(Color.decode("#9BA3C4"));
                        menuItem.setFont(new Font("Segoe UI", Font.PLAIN, 12));
                        menuItem.setBorder(BorderFactory.createEmptyBorder(6, 10, 6, 10));
                    }
                }
            }
        }
        
        // Forzar diseño minimalista en las subventanas (InternalFrames)
        UIManager.put("InternalFrame.activeTitleBackground", Color.decode("#161B2E"));
        UIManager.put("InternalFrame.inactiveTitleBackground", Color.decode("#111520"));
        UIManager.put("InternalFrame.border", BorderFactory.createLineBorder(Color.decode("#2A3050"), 1));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu_proveedores = new javax.swing.JMenu();
        jMenuItem_nuevo_proveedor = new javax.swing.JMenuItem();
        jMenuItem_gestionar_proveedores = new javax.swing.JMenuItem();
        jMenu_producto = new javax.swing.JMenu();
        jMenuItem_nuevo_producto = new javax.swing.JMenuItem();
        jMenuItem_gestionar_producto = new javax.swing.JMenuItem();
        jMenuItem_ajuste_inventario = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        nuevaCompra = new javax.swing.JMenuItem();
        jMenu_Factura = new javax.swing.JMenu();
        jMenuItem_punto_venta = new javax.swing.JMenuItem();
        jMenuItem_consultar_factura = new javax.swing.JMenuItem();

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu_proveedores.setText("Proveedores");

        jMenuItem_nuevo_proveedor.setText("Nuevo Proveedor");
        jMenuItem_nuevo_proveedor.addActionListener(this::jMenuItem_nuevo_proveedorActionPerformed);
        jMenu_proveedores.add(jMenuItem_nuevo_proveedor);

        jMenuItem_gestionar_proveedores.setText("Gestionar Proveedores");
        jMenuItem_gestionar_proveedores.addActionListener(this::jMenuItem_gestionar_proveedoresActionPerformed);
        jMenu_proveedores.add(jMenuItem_gestionar_proveedores);

        jMenuBar1.add(jMenu_proveedores);

        jMenu_producto.setText("Producto");

        jMenuItem_nuevo_producto.setText("Nuevo Producto");
        jMenuItem_nuevo_producto.addActionListener(this::jMenuItem_nuevo_productoActionPerformed);
        jMenu_producto.add(jMenuItem_nuevo_producto);

        jMenuItem_gestionar_producto.setText("Gestion Productos");
        jMenuItem_gestionar_producto.addActionListener(this::jMenuItem_gestionar_productoActionPerformed);
        jMenu_producto.add(jMenuItem_gestionar_producto);

        jMenuItem_ajuste_inventario.setText("Ajuste de Inventario");
        jMenuItem_ajuste_inventario.addActionListener(this::jMenuItem_ajuste_inventarioActionPerformed);
        jMenu_producto.add(jMenuItem_ajuste_inventario);

        jMenuBar1.add(jMenu_producto);

        jMenu2.setText("Usuarios");

        jMenuItem2.setText("Nuevo Usuario");
        jMenuItem2.addActionListener(this::jMenuItem2ActionPerformed);
        jMenu2.add(jMenuItem2);

        jMenuItem3.setText("Gestionar Usuarios");
        jMenuItem3.addActionListener(this::jMenuItem3ActionPerformed);
        jMenu2.add(jMenuItem3);

        jMenuItem4.setText("Roles y Permisos");
        jMenuItem4.addActionListener(this::jMenuItem4ActionPerformed);
        jMenu2.add(jMenuItem4);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Compras");

        nuevaCompra.setText("Nueva Compra");
        nuevaCompra.addActionListener(this::nuevaCompraActionPerformed);
        jMenu3.add(nuevaCompra);

        jMenuBar1.add(jMenu3);

        jMenu_Factura.setText("Factura");

        jMenuItem_punto_venta.setText("Punto Venta");
        jMenuItem_punto_venta.addActionListener(this::jMenuItem_punto_ventaActionPerformed);
        jMenu_Factura.add(jMenuItem_punto_venta);

        jMenuItem_consultar_factura.setText("Consultar Factura");
        jMenuItem_consultar_factura.addActionListener(this::jMenuItem_consultar_facturaActionPerformed);
        jMenu_Factura.add(jMenuItem_consultar_factura);

        jMenuBar1.add(jMenu_Factura);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>                        

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {                                           
        Vistas.FrmRolesPermisos roles = new Vistas.FrmRolesPermisos();
        FrmNuevoUsuario usuarios = new FrmNuevoUsuario();
        Modelos.Roles rol = new Modelos.Roles();
        Modelos.RolesDAO dao = new Modelos.RolesDAO();
        Modelos.UsuarioDAO daoUsuario = new Modelos.UsuarioDAO();
        Usuario usuario = new Usuario();
        Controladores.CtrlRoles.CtrlGestionarRoles controlador = new Controladores.CtrlRoles.CtrlGestionarRoles(rol, roles, usuarios, dao);
        Controladores.CtrlUsuarios.CtrlNuevoUsuario controladorUsuario = new Controladores.CtrlUsuarios.CtrlNuevoUsuario(usuario, usuarios, daoUsuario);
        jDesktopPane_MDI.add(usuarios);
        centrarInternalFrame(usuarios);
        usuarios.setVisible(true);
    }                                          

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {                                           
        Modelos.Usuario usuario = new Modelos.Usuario();
        FrmGestionarUsuarios gestionarUsuarios = new FrmGestionarUsuarios();
        Modelos.UsuarioDAO usuarioDAO = new Modelos.UsuarioDAO();
        Controladores.CtrlUsuarios.CtrlGestionarUsuarios ctrl = new Controladores.CtrlUsuarios.CtrlGestionarUsuarios(usuario, gestionarUsuarios, usuarioDAO);
        
        ctrl.cargarTabla();
        jDesktopPane_MDI.add(gestionarUsuarios);
        centrarInternalFrame(gestionarUsuarios);
        gestionarUsuarios.setVisible(true);
    }                                          

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {                                           
        FrmRolesPermisos roles = new FrmRolesPermisos();
        jDesktopPane_MDI.add(roles);
        centrarInternalFrame(roles);
        roles.setVisible(true);
    }                                          

    private void nuevaCompraActionPerformed(java.awt.event.ActionEvent evt) {                                            
        FrmRegistrarNuevaCompra nuevaCompra = new FrmRegistrarNuevaCompra();
        Modelos.Compra compra = new Modelos.Compra();
        Modelos.CompraDAO compraDAO = new Modelos.CompraDAO();
        Controladores.CtrlCompra.CtrlNuevaCompra ctrlCompra = new Controladores.CtrlCompra.CtrlNuevaCompra(compra, nuevaCompra, compraDAO);
        jDesktopPane_MDI.add(nuevaCompra);
        centrarInternalFrame(nuevaCompra);
        nuevaCompra.setVisible(true);
    }                                           

    private void jMenuItem_punto_ventaActionPerformed(java.awt.event.ActionEvent evt) {                                                      
        FrmFacturaPuntoVenta puntoVenta = new FrmFacturaPuntoVenta();
        jDesktopPane_MDI.add(puntoVenta);
        Controladores.CtrlFactura.CtrlPuntoVenta pV = new Controladores.CtrlFactura.CtrlPuntoVenta();
        centrarInternalFrame(puntoVenta);
        puntoVenta.setVisible(true);
    }                                                     

    private void jMenuItem_consultar_facturaActionPerformed(java.awt.event.ActionEvent evt) {                                                            
        FrmFacturaConsultar consultar = new FrmFacturaConsultar();
        jDesktopPane_MDI.add(consultar);
        Controladores.CtrlFactura.CtrlConsultarFactura Con = new Controladores.CtrlFactura.CtrlConsultarFactura();
        centrarInternalFrame(consultar);
        consultar.setVisible(true);
    }                                                           

    private void jMenuItem_nuevo_proveedorActionPerformed(java.awt.event.ActionEvent evt) {                                                          
        Vistas.FrmNuevoProveedor form = new Vistas.FrmNuevoProveedor();
        Modelos.Proveedor proveedor = new Modelos.Proveedor();
        Modelos.ProveedorDAO dao = new Modelos.ProveedorDAO();
        Controladores.CtrlProveedor.CtrlNuevoProveedor ctrl = new Controladores.CtrlProveedor.CtrlNuevoProveedor(
                proveedor, form, dao);

        jDesktopPane_MDI.add(form);
        centrarInternalFrame(form);
        form.setVisible(true);

    }                                                         

    private void jMenuItem_gestionar_proveedoresActionPerformed(java.awt.event.ActionEvent evt) {                                                                
        Vistas.FrmGestionarProveedores form = new Vistas.FrmGestionarProveedores();
        Modelos.Proveedor proveedor = new Modelos.Proveedor();
        Modelos.ProveedorDAO dao = new Modelos.ProveedorDAO();
        Controladores.CtrlProveedor.CtrlGestionarProveedores ctrl = new Controladores.CtrlProveedor.CtrlGestionarProveedores(
                proveedor, form, dao);

        ctrl.cargarTabla();
        jDesktopPane_MDI.add(form);
        centrarInternalFrame(form);
        form.setVisible(true);
    }                                                               

    private void jMenuItem_nuevo_productoActionPerformed(java.awt.event.ActionEvent evt) {                                                         
        FrmNuevoProducto vista = new FrmNuevoProducto();
        jDesktopPane_MDI.add(vista);
        NuevoProductoController controlador = new NuevoProductoController(vista);
        centrarInternalFrame(vista);
        vista.setVisible(true);
    }                                                        

    private void jMenuItem_gestionar_productoActionPerformed(java.awt.event.ActionEvent evt) {                                                             
        FrmGestionarProductos vista = new FrmGestionarProductos();
        jDesktopPane_MDI.add(vista);
        GestionProductosController controlador = new GestionProductosController(vista);
        centrarInternalFrame(vista);
        vista.setVisible(true);
    }                                                            

    private void jMenuItem_ajuste_inventarioActionPerformed(java.awt.event.ActionEvent evt) {                                                            
        FrmAjusteInventario vista = new FrmAjusteInventario();
        jDesktopPane_MDI.add(vista);
        AjusteInventarioController controlador = new AjusteInventarioController(vista);
        centrarInternalFrame(vista);
        vista.setVisible(true);
    }                                                           

    // Helper para que las ventanas se abran en el centro de la pantalla
    private void centrarInternalFrame(javax.swing.JInternalFrame f) {
        Dimension desktopSize = jDesktopPane_MDI.getSize();
        Dimension jInternalFrameSize = f.getSize();
        f.setLocation((desktopSize.width - jInternalFrameSize.width) / 2,
                (desktopSize.height - jInternalFrameSize.height) / 2);
    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* INICIA EN EL LOGIN POR SEGURIDAD */
        java.awt.EventQueue.invokeLater(() -> new FrmLogin().setVisible(true));
    }

    // Variables declaration - do not modify                     
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem_ajuste_inventario;
    private javax.swing.JMenuItem jMenuItem_consultar_factura;
    private javax.swing.JMenuItem jMenuItem_gestionar_producto;
    private javax.swing.JMenuItem jMenuItem_gestionar_proveedores;
    private javax.swing.JMenuItem jMenuItem_nuevo_producto;
    private javax.swing.JMenuItem jMenuItem_nuevo_proveedor;
    private javax.swing.JMenuItem jMenuItem_punto_venta;
    private javax.swing.JMenu jMenu_Factura;
    private javax.swing.JMenu jMenu_producto;
    private javax.swing.JMenu jMenu_proveedores;
    private javax.swing.JMenuItem nuevaCompra;
    // End of variables declaration                   
}
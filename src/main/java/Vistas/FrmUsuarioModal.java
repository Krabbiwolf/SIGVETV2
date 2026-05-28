package Vistas;

public class FrmUsuarioModal extends javax.swing.JInternalFrame {

    public FrmUsuarioModal() {
        initComponents();
        getContentPane().setBackground(new java.awt.Color(240, 244, 248));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cardUsuarioModal = new javax.swing.JPanel();
        lblTituloEmpleado = new javax.swing.JLabel();
        lblNombresEmpleado = new javax.swing.JLabel();
        txtNombresEmpleado = new javax.swing.JTextField();
        lblApellidosEmpleado = new javax.swing.JLabel();
        txtApellidosEmpleado = new javax.swing.JTextField();
        lblDUIEmpleado = new javax.swing.JLabel();
        txtDUIEmpleado = new javax.swing.JTextField();
        lblTelefonoEmpleado = new javax.swing.JLabel();
        txtTelefonoEmpleado = new javax.swing.JTextField();
        lblUsuarioEmpleado = new javax.swing.JLabel();
        txtUsuarioEmpleado = new javax.swing.JTextField();
        lblPasswordEmpleado = new javax.swing.JLabel();
        txtRolEmpleado = new javax.swing.JTextField();
        lblRolEmpleado = new javax.swing.JLabel();
        txtEstadoEmpleado = new javax.swing.JTextField();
        lblEstadoEmpleado = new javax.swing.JLabel();
        lblImagenPreviewEmpleado = new javax.swing.JLabel();
        txtPasswordEmpleado = new javax.swing.JPasswordField();

        setClosable(true);

        cardUsuarioModal.setBackground(new java.awt.Color(240, 244, 248));

        lblTituloEmpleado.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        lblTituloEmpleado.setForeground(new java.awt.Color(45, 74, 138));
        lblTituloEmpleado.setText("Empleado");

        lblNombresEmpleado.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblNombresEmpleado.setForeground(new java.awt.Color(45, 74, 138));
        lblNombresEmpleado.setText("NOMBRES");

        txtNombresEmpleado.setEditable(false);
        txtNombresEmpleado.setBackground(new java.awt.Color(255, 255, 255));
        txtNombresEmpleado.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtNombresEmpleado.setForeground(new java.awt.Color(51, 51, 51));
        txtNombresEmpleado.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(197, 216, 245)), javax.swing.BorderFactory.createEmptyBorder(6, 12, 6, 12)));

        lblApellidosEmpleado.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblApellidosEmpleado.setForeground(new java.awt.Color(45, 74, 138));
        lblApellidosEmpleado.setText("APELLIDOS");

        txtApellidosEmpleado.setEditable(false);
        txtApellidosEmpleado.setBackground(new java.awt.Color(255, 255, 255));
        txtApellidosEmpleado.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtApellidosEmpleado.setForeground(new java.awt.Color(51, 51, 51));
        txtApellidosEmpleado.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(197, 216, 245)), javax.swing.BorderFactory.createEmptyBorder(6, 12, 6, 12)));

        lblDUIEmpleado.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblDUIEmpleado.setForeground(new java.awt.Color(45, 74, 138));
        lblDUIEmpleado.setText("DUI");

        txtDUIEmpleado.setEditable(false);
        txtDUIEmpleado.setBackground(new java.awt.Color(255, 255, 255));
        txtDUIEmpleado.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtDUIEmpleado.setForeground(new java.awt.Color(51, 51, 51));
        txtDUIEmpleado.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(197, 216, 245)), javax.swing.BorderFactory.createEmptyBorder(6, 12, 6, 12)));

        lblTelefonoEmpleado.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblTelefonoEmpleado.setForeground(new java.awt.Color(45, 74, 138));
        lblTelefonoEmpleado.setText("TELÉFONO");

        txtTelefonoEmpleado.setEditable(false);
        txtTelefonoEmpleado.setBackground(new java.awt.Color(255, 255, 255));
        txtTelefonoEmpleado.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtTelefonoEmpleado.setForeground(new java.awt.Color(51, 51, 51));
        txtTelefonoEmpleado.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(197, 216, 245)), javax.swing.BorderFactory.createEmptyBorder(6, 12, 6, 12)));

        lblUsuarioEmpleado.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblUsuarioEmpleado.setForeground(new java.awt.Color(45, 74, 138));
        lblUsuarioEmpleado.setText("USERNAME");

        txtUsuarioEmpleado.setEditable(false);
        txtUsuarioEmpleado.setBackground(new java.awt.Color(255, 255, 255));
        txtUsuarioEmpleado.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtUsuarioEmpleado.setForeground(new java.awt.Color(51, 51, 51));
        txtUsuarioEmpleado.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(197, 216, 245)), javax.swing.BorderFactory.createEmptyBorder(6, 12, 6, 12)));

        lblPasswordEmpleado.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblPasswordEmpleado.setForeground(new java.awt.Color(45, 74, 138));
        lblPasswordEmpleado.setText("CONTRASEÑA");

        txtRolEmpleado.setEditable(false);
        txtRolEmpleado.setBackground(new java.awt.Color(255, 255, 255));
        txtRolEmpleado.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtRolEmpleado.setForeground(new java.awt.Color(51, 51, 51));
        txtRolEmpleado.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(197, 216, 245)), javax.swing.BorderFactory.createEmptyBorder(6, 12, 6, 12)));

        lblRolEmpleado.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblRolEmpleado.setForeground(new java.awt.Color(45, 74, 138));
        lblRolEmpleado.setText("ROL");

        txtEstadoEmpleado.setEditable(false);
        txtEstadoEmpleado.setBackground(new java.awt.Color(255, 255, 255));
        txtEstadoEmpleado.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtEstadoEmpleado.setForeground(new java.awt.Color(51, 51, 51));
        txtEstadoEmpleado.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(197, 216, 245)), javax.swing.BorderFactory.createEmptyBorder(6, 12, 6, 12)));

        lblEstadoEmpleado.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblEstadoEmpleado.setForeground(new java.awt.Color(45, 74, 138));
        lblEstadoEmpleado.setText("ESTADO");

        lblImagenPreviewEmpleado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblImagenPreviewEmpleado.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(197, 216, 245)));

        txtPasswordEmpleado.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtPasswordEmpleado.setForeground(new java.awt.Color(51, 51, 51));
        txtPasswordEmpleado.setText("**********");
        txtPasswordEmpleado.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(197, 216, 245)), javax.swing.BorderFactory.createEmptyBorder(6, 12, 6, 12)));
        txtPasswordEmpleado.setPreferredSize(new java.awt.Dimension(64, 32));

        javax.swing.GroupLayout cardUsuarioModalLayout = new javax.swing.GroupLayout(cardUsuarioModal);
        cardUsuarioModal.setLayout(cardUsuarioModalLayout);
        cardUsuarioModalLayout.setHorizontalGroup(
            cardUsuarioModalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardUsuarioModalLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(cardUsuarioModalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(cardUsuarioModalLayout.createSequentialGroup()
                        .addComponent(lblTituloEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(cardUsuarioModalLayout.createSequentialGroup()
                        .addGroup(cardUsuarioModalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNombresEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(cardUsuarioModalLayout.createSequentialGroup()
                                .addGroup(cardUsuarioModalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtNombresEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblApellidosEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtApellidosEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(cardUsuarioModalLayout.createSequentialGroup()
                                        .addComponent(lblUsuarioEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(20, 20, 20)
                                        .addComponent(lblPasswordEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(cardUsuarioModalLayout.createSequentialGroup()
                                        .addComponent(txtUsuarioEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtPasswordEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(cardUsuarioModalLayout.createSequentialGroup()
                                        .addGap(2, 2, 2)
                                        .addGroup(cardUsuarioModalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblDUIEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtDUIEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(cardUsuarioModalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblTelefonoEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtTelefonoEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(cardUsuarioModalLayout.createSequentialGroup()
                                        .addComponent(lblRolEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(20, 20, 20)
                                        .addComponent(lblEstadoEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(cardUsuarioModalLayout.createSequentialGroup()
                                        .addComponent(txtRolEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(20, 20, 20)
                                        .addComponent(txtEstadoEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblImagenPreviewEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(17, Short.MAX_VALUE))))
        );
        cardUsuarioModalLayout.setVerticalGroup(
            cardUsuarioModalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardUsuarioModalLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(lblTituloEmpleado)
                .addGap(18, 18, 18)
                .addComponent(lblNombresEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addGroup(cardUsuarioModalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(cardUsuarioModalLayout.createSequentialGroup()
                        .addComponent(txtNombresEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(lblApellidosEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(txtApellidosEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(cardUsuarioModalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(cardUsuarioModalLayout.createSequentialGroup()
                                .addComponent(lblTelefonoEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(txtTelefonoEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addGroup(cardUsuarioModalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblUsuarioEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblPasswordEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(cardUsuarioModalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtUsuarioEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtPasswordEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(cardUsuarioModalLayout.createSequentialGroup()
                                .addComponent(lblDUIEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(txtDUIEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(cardUsuarioModalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblRolEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblEstadoEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(cardUsuarioModalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtRolEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEstadoEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(lblImagenPreviewEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(cardUsuarioModal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(cardUsuarioModal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel cardUsuarioModal;
    private javax.swing.JLabel lblApellidosEmpleado;
    private javax.swing.JLabel lblDUIEmpleado;
    private javax.swing.JLabel lblEstadoEmpleado;
    public javax.swing.JLabel lblImagenPreviewEmpleado;
    private javax.swing.JLabel lblNombresEmpleado;
    private javax.swing.JLabel lblPasswordEmpleado;
    private javax.swing.JLabel lblRolEmpleado;
    private javax.swing.JLabel lblTelefonoEmpleado;
    private javax.swing.JLabel lblTituloEmpleado;
    private javax.swing.JLabel lblUsuarioEmpleado;
    public javax.swing.JTextField txtApellidosEmpleado;
    public javax.swing.JTextField txtDUIEmpleado;
    public javax.swing.JTextField txtEstadoEmpleado;
    public javax.swing.JTextField txtNombresEmpleado;
    private javax.swing.JPasswordField txtPasswordEmpleado;
    public javax.swing.JTextField txtRolEmpleado;
    public javax.swing.JTextField txtTelefonoEmpleado;
    public javax.swing.JTextField txtUsuarioEmpleado;
    // End of variables declaration//GEN-END:variables
}
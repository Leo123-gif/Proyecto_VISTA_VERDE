/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.condominio_vistaverde;

import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;
import java.time.Month;

public class CasasMorosas extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(CasasMorosas.class.getName());

    /**
     * Creates new form CASAS_MOROSAS
     */
    public CasasMorosas() {
        initComponents();
                this.setSize(900, 681);
    this.setLocationRelativeTo(null);
    this.setResizable(false);
    cargarCasasMorosas();
    cargarMesActual();
    tblMorosos.setDefaultEditor(Object.class, null);
    }
    
    private void cargarMesActual() {

    LocalDate fecha = LocalDate.now();

    String mes = fecha.getMonth()
            .getDisplayName(TextStyle.FULL, new Locale("es", "ES"));

    int año = fecha.getYear();

    lblMes.setText(
        mes.substring(0,1).toUpperCase()
        + mes.substring(1)
        + " " + año
    );
}
    
private void cargarCasasMorosas() {

    DefaultTableModel modelo =
            (DefaultTableModel) tblMorosos.getModel();

    modelo.setRowCount(0);

    int totalMorosos = 0;

    try {

        // =========================================
        // FECHA ACTUAL
        // =========================================

        LocalDate fecha = LocalDate.now();

        String mesActual =
                fecha.getMonth()
                        .getDisplayName(
                                TextStyle.FULL,
                                new Locale("es", "ES")
                        );

        mesActual =
                mesActual.substring(0, 1).toUpperCase()
                + mesActual.substring(1);

        int añoActual = fecha.getYear();

        // =========================================
        // RECORRER LAS 30 CASAS
        // =========================================

        for (int numeroCasa = 1;
                numeroCasa <= 30;
                numeroCasa++) {

            // =====================================
            // OBTENER PROPIETARIO COMO OBJETO
            // =====================================

            Propietario propietario =
                    BDXML.obtenerPropietario(
                            numeroCasa
                    );

            // =====================================
            // SI NO TIENE PROPIETARIO
            // NO MOSTRAR
            // =====================================

            if (propietario == null) {
                continue;
            }

            // =====================================
            // VALIDAR SI YA PAGÓ
            // =====================================

            boolean pagoRealizado =
                    BDXML.existePago(
                            numeroCasa,
                            mesActual,
                            añoActual
                    );

            // =====================================
            // SI NO PAGÓ → ES MOROSO
            // =====================================

            if (!pagoRealizado) {

                modelo.addRow(new Object[]{

                    "CASA " + numeroCasa,

                    propietario.getNombre(),

                    propietario.getTelefono(),

                    mesActual + " " + añoActual
                });

                totalMorosos++;
            }
        }

        // =========================================
        // TOTAL MOROSOS
        // =========================================

        lblTotalMorosos.setText(
                "Total casas morosas: "
                + totalMorosos
        );

    } catch (Exception e) {

        javax.swing.JOptionPane.showMessageDialog(
                this,
                "Error cargando casas morosas:\n"
                + e.getMessage()
        );

    }
}
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton2 = new javax.swing.JButton();
        lblTitulo = new javax.swing.JLabel();
        lblDescripcion = new javax.swing.JLabel();
        scrollMorosos = new javax.swing.JScrollPane();
        tblMorosos = new javax.swing.JTable();
        lblMesActual = new javax.swing.JLabel();
        lblMes = new javax.swing.JLabel();
        lblTotalMorosos = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Casas Morosas");

        jButton2.setText("Volver a Menú");
        jButton2.addActionListener(this::jButton2ActionPerformed);

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("CASAS MOROSAS");
        lblTitulo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        lblDescripcion.setText("Casas que no han pagado el mes actual");

        tblMorosos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "No. de Casa", "Propietario", "Teléfono", "Mes Pendiente"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scrollMorosos.setViewportView(tblMorosos);

        lblMesActual.setText("Mes actual:");

        lblMes.setText("Mayo 2026");

        lblTotalMorosos.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblTotalMorosos.setText("Total casas morosas: 0");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(52, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(15, 15, 15)
                                        .addComponent(lblDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(lblTitulo))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblMesActual, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblMes, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(scrollMorosos, javax.swing.GroupLayout.PREFERRED_SIZE, 793, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(56, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTotalMorosos)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblMesActual)
                        .addGap(18, 18, 18)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDescripcion)
                    .addComponent(lblMes))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scrollMorosos, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblTotalMorosos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(97, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        MenuPrincipal menu = new MenuPrincipal ();
        menu.setVisible(true);
        dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
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
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new CasasMorosas().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel lblDescripcion;
    private javax.swing.JLabel lblMes;
    private javax.swing.JLabel lblMesActual;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblTotalMorosos;
    private javax.swing.JScrollPane scrollMorosos;
    private javax.swing.JTable tblMorosos;
    // End of variables declaration//GEN-END:variables
}

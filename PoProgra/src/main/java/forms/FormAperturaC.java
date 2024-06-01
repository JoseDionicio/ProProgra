/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package forms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author nebra
 */

public class FormAperturaC extends JFrame {
    private JTextField txtDPI;
    private JComboBox<String> comboBoxTipoCuenta;

    public FormAperturaC() {
        initComponents();
    }
    
    private void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Formulario de Apertura de Cuenta");

        // Crear componentes
        JLabel lblDPI = new JLabel("DPI:");
        txtDPI = new JTextField(10);
        JButton btnBuscar = new JButton("Buscar");
        JLabel lblTipoCuenta = new JLabel("Tipo de Cuenta:");
        String[] tiposCuenta = {"CAhorro", "CMonetaria", "CClub"};
        comboBoxTipoCuenta = new JComboBox<>(tiposCuenta);
        JButton btnGuardar = new JButton("Guardar");
        JButton btnAtras = new JButton("Atrás");

        // Configurar el diseño
        setLayout(new GridLayout(4, 2));
        add(lblDPI);
        add(txtDPI);
        add(btnBuscar);
        add(new JLabel()); // Espacio vacío
        add(lblTipoCuenta);
        add(comboBoxTipoCuenta);
        add(new JLabel()); // Espacio vacío
        add(btnGuardar);
        add(btnGuardar);
        add(btnAtras);

        // Acción del botón Buscar
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarCliente();
            }
        });

        // Acción del botón Guardar
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarDatos();
            }
        });
        
        // Acción del botón Atrás
        btnAtras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Abre la pestaña del formulario "FormMenu"
                FormMenu formMenu = new FormMenu();
                formMenu.setVisible(true);
                dispose(); // Cierra la ventana actual (FormAperturaC)
            }
        });

        pack();
        setLocationRelativeTo(null); // Centrar la ventana
        
        
    }
    
    private void buscarCliente() {
        String DPI = txtDPI.getText();
        String consulta = "SELECT * FROM conclientes WHERE DPI = ?";
        try (Connection conexion = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bcprogramadores", "root", "");
             PreparedStatement ps = conexion.prepareStatement(consulta)) {
            ps.setString(1, DPI);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String TipCuenta = rs.getString("TipCuenta");
                comboBoxTipoCuenta.setSelectedItem(TipCuenta);
            } else {
                JOptionPane.showMessageDialog(this, "Cliente no encontrado. Intenta de nuevo.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
     private void guardarDatos() {
        String DPI = txtDPI.getText();
        String TipCuenta = comboBoxTipoCuenta.getSelectedItem().toString();
        String consultaActualizacion = "UPDATE conclientes SET TipCuenta = ? WHERE DPI = ?";
        try (Connection conexion = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bcprogramadores", "root", "");
             PreparedStatement ps = conexion.prepareStatement(consultaActualizacion)) {
            ps.setString(1, TipCuenta);
            ps.setString(2, DPI);
            int filasActualizadas = ps.executeUpdate();
            if (filasActualizadas > 0) {
                JOptionPane.showMessageDialog(this, "Datos actualizados correctamente.");
            } else {
                JOptionPane.showMessageDialog(this, "Error al actualizar los datos.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        
    }
     public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FormAperturaC frame = new FormAperturaC();
            frame.setVisible(true);
        });
    }
}
     
   


        
  
     
/*
   
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
*/
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables


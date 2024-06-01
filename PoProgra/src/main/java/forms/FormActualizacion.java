/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package forms;
//IMPORTACIONES QUE SE AUTILIZARAN EN EL PROYECTO 
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FormActualizacion extends JFrame {
    private JTextField dpiField, nombreField, apellidoField, direccionField, telefonoField,
            correoField, ocupacionField, ingresoField;

    public FormActualizacion() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Formulario de Actualización de Datos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(11, 2));

        JLabel etiquetaDPI = new JLabel("Número de DPI del cliente:");
        JLabel etiquetaNombre = new JLabel("Nombre del cliente:");
        JLabel etiquetaApellido = new JLabel("Apellido del cliente:");
        JLabel etiquetaDireccion = new JLabel("Dirección del cliente:");
        JLabel etiquetaTelefono = new JLabel("Telefono del cliente:");
        JLabel etiquetaCorreo = new JLabel("Correo del cliente:");
        JLabel etiquetaOcupacion = new JLabel("Ocupación del cliente:");
        JLabel etiquetaIngreso = new JLabel("Ingreso del cliente:");
        
        dpiField = new JTextField(20);
        nombreField = new JTextField(20);
        apellidoField = new JTextField(20);
        direccionField = new JTextField(20);
        telefonoField = new JTextField(20);
        correoField = new JTextField(20);
        ocupacionField = new JTextField(20);
        ingresoField = new JTextField(20);

        // Agrega las demás etiquetas y campos de texto aquí

        JButton botonBuscar = new JButton("Buscar");
        botonBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarClientePorDPI();
            }
        });

        JButton botonGuardar = new JButton("Guardar/Actualizar");
        botonGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarDatosEnBD();
            }
        });
        
        JButton btnAtras = new JButton("Atrás");
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

        add(etiquetaDPI);
        add(dpiField);
        add(etiquetaNombre);
        add(nombreField);
        add(etiquetaApellido);
        add(apellidoField);
        add(etiquetaDireccion);
        add(direccionField);
        add(etiquetaTelefono);
        add(telefonoField);
        add(etiquetaCorreo);
        add(correoField);
        add(etiquetaOcupacion);
        add(ocupacionField);
        add(etiquetaIngreso);
        add(ingresoField);
        // Agrega las demás etiquetas y campos de texto aquí
        add(botonBuscar);
        add(botonGuardar);
        add(btnAtras);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void buscarClientePorDPI() {
        String dpi = dpiField.getText();
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bcprogramadores", "root", "");
            String sql = "SELECT * FROM conclientes WHERE DPI = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, dpi);
            ResultSet rs = stmt.executeQuery();
            
            if(rs.next()) {
            // Si se encuentra el cliente, muestra los datos en los campos de texto
            nombreField.setText(rs.getString("NomCliente"));
            apellidoField.setText(rs.getString("ApellCliente"));
            direccionField.setText(rs.getString("DireCliente"));
            telefonoField.setText(rs.getString("N0Telefono"));
            correoField.setText(rs.getString("Correo"));
            ocupacionField.setText(rs.getString("OcupCliente"));
            ingresoField.setText(rs.getString("IngreMensual"));

            }else{
                JOptionPane.showMessageDialog(this, "El cliente no existe. Intenta de nuevo.");
            }
            
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al buscar el cliente.");
        }

    }

    private void guardarDatosEnBD() {
        // Obtén los valores de los campos de texto
        String dpi = dpiField.getText();
        String nombre = nombreField.getText();
        String apellido = apellidoField.getText();
        String direccion = direccionField.getText();
        String telefono = telefonoField.getText();
        String correo = correoField.getText();
        String ocupacion = ocupacionField.getText();
        String ingreso = ingresoField.getText();
        // ... (obtén los demás campos)

        // Realiza la actualización en la base de datos
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bcprogramadores", "root", "");
            String sql = "UPDATE conclientes SET DireCliente = ?, N0Telefono = ?, Correo = ?, OcupCliente = ?, IngreMensual = ? WHERE DPI = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, direccion);
            stmt.setString(2, telefono);
            stmt.setString(3, correo);
            stmt.setString(4, ocupacion);
            stmt.setString(5, ingreso);
            stmt.setString(6, dpi);
            stmt.executeUpdate();
            stmt.close();
            conn.close();
            JOptionPane.showMessageDialog(this, "Datos actualizados correctamente.");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al actualizar los datos.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FormActualizacion());
    }
}

    

    /*
    @SuppressWarnings("unchecked")
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


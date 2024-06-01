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
import java.sql.SQLException;

public class FormRegistro extends JFrame {
    private JTextField dpiField, nombreField, apellidoField, direccionField, telefonoField,
            correoField, ocupacionField, ingresoField;

    public FormRegistro() {
        initComponents();
    }

    private void initComponents() {
        // Configuración del JFrame principal
        setTitle("Formulario de Cliente");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(11, 2)); // GridLayout con 11 filas y 2 columnas

        // Etiquetas
        JLabel etiquetaDPI = new JLabel("Número de DPI del cliente:");
        JLabel etiquetaNombre = new JLabel("Nombre del cliente:");
        JLabel etiquetaApellido = new JLabel("Apellido del cliente:");
        JLabel etiquetaDireccion = new JLabel("Dirección del cliente:");
        JLabel etiquetaTelefono = new JLabel("Telefono del cliente:");
        JLabel etiquetaCorreo = new JLabel("Correo del cliente:");
        JLabel etiquetaOcupacion = new JLabel("Ocupación del cliente:");
        JLabel etiquetaIngreso = new JLabel("Ingreso del cliente:");
        // Campos de texto
        dpiField = new JTextField(20);
        nombreField = new JTextField(20);
        apellidoField = new JTextField(20);
        direccionField = new JTextField(20);
        telefonoField = new JTextField(20);
        correoField = new JTextField(20);
        ocupacionField = new JTextField(20);
        ingresoField = new JTextField(20);
        // Agrega los demás campos de texto aquí

        // Botones
        JButton botonGuardar = new JButton("Guardar");
        botonGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarDatosEnBD();
            }
        });

        JButton botonLimpiar = new JButton("Limpiar");
        botonLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
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
        // Agrega los componentes al JFrame
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
        add(botonGuardar);
        add(botonLimpiar);
        add(btnAtras);

        pack(); // Ajusta el tamaño del JFrame automáticamente
        setLocationRelativeTo(null); // Centra la ventana en la pantalla
        setVisible(true);
    }

    private void guardarDatosEnBD() {
        // Obtén los valores de los campos de texto
        String DPI = dpiField.getText();
        String NomCliente = nombreField.getText();
        String ApellCliente = apellidoField.getText();
        String DireCliente = direccionField.getText();
        String N0Telefono = telefonoField.getText();
        String Correo = correoField.getText();
        String OcupCliente = ocupacionField.getText();
        String IngreMensual = ingresoField.getText();
        
        // ... (obtén los demás campos)

        // Realiza la inserción en la base de datos
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bcprogramadores", "root", "");
            String sql = "INSERT INTO conclientes (DPI, NomCliente, ApellCliente, DireCliente, N0Telefono, Correo, OcupCliente, IngreMensual) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, DPI);
            stmt.setString(2, NomCliente);
            stmt.setString(3, ApellCliente);
            stmt.setString(4, DireCliente);
            stmt.setString(5, N0Telefono);
            stmt.setString(6, Correo);
            stmt.setString(7, OcupCliente);
            stmt.setString(8, IngreMensual);
            
            // ... (configura los demás parámetros)
            stmt.executeUpdate();
            stmt.close();
            conn.close();
            JOptionPane.showMessageDialog(this, "Datos guardados correctamente.");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al guardar los datos.");
        }
    }

    private void limpiarCampos() {
        // Establece los campos de texto en blanco
        dpiField.setText("");
        nombreField.setText("");
        apellidoField.setText("");
        direccionField.setText("");
        telefonoField.setText("");
        correoField.setText("");
        ocupacionField.setText("");
        ingresoField.setText("");
        // ... (limpia los demás campos)
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FormRegistro());
    }
}

    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
   /* @SuppressWarnings("unchecked")
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


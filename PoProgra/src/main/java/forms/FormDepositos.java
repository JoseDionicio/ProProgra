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

public class FormDepositos extends JFrame {
    private JTextField txtDpi;
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtTipoCuenta;
    private JTextField txtCantidad;
    private JLabel lblSaldoActual;

    // Configura la conexión a la base de datos (ajusta los valores según tu entorno)
    private static final String DB_URL = "jdbc:mysql://localhost:3306/bcprogramadores";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "";

    public FormDepositos() {
        setTitle("Formulario de Depósitos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 300);

        // Crear componentes
        txtDpi = new JTextField(10);
        txtNombre = new JTextField(20);
        txtApellido = new JTextField(20);
        txtTipoCuenta = new JTextField(10);
        txtCantidad = new JTextField(10);
        lblSaldoActual = new JLabel();

        JButton btnBuscar = new JButton("Buscar Cliente");
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarCliente();
            }
        });

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarDeposito();
            }
        });

        // Agregar componentes al panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2));
        panel.add(new JLabel("DPI del Cliente:"));
        panel.add(txtDpi);
        panel.add(btnBuscar);
        panel.add(new JLabel("Nombre del Cliente:"));
        panel.add(txtNombre);
        panel.add(new JLabel()); // Espacio vacío
        panel.add(new JLabel("Apellido del Cliente:"));
        panel.add(txtApellido);
        panel.add(new JLabel()); // Espacio vacío
        panel.add(new JLabel("Tipo de Cuenta:"));
        panel.add(txtTipoCuenta);
        panel.add(new JLabel()); // Espacio vacío
        panel.add(new JLabel("Cantidad a Depositar:"));
        panel.add(txtCantidad);
        panel.add(new JLabel()); // Espacio vacío
        panel.add(btnGuardar);
        panel.add(new JLabel("Saldo Actual:"));
        panel.add(lblSaldoActual);

        add(panel);
    }

    private void buscarCliente() {
        String dpi = txtDpi.getText();
        try {
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            String sql = "SELECT NomCliente, ApellCliente, TipCuenta, SaldCuenta FROM conclientes WHERE DPI = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, dpi);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                txtNombre.setText(rs.getString("NomCliente"));
                txtApellido.setText(rs.getString("ApellCliente"));
                txtTipoCuenta.setText(rs.getString("TipCuenta"));
                double saldoActual = rs.getDouble("SaldCuenta");
                lblSaldoActual.setText(String.valueOf(saldoActual));
            } else {
                JOptionPane.showMessageDialog(this, "Cliente no encontrado");
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void guardarDeposito() {
        String DPI = txtDpi.getText();
        double SaldCuenta = Double.parseDouble(txtCantidad.getText());
        try {
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            String sql = "UPDATE conclientes SET SaldCuenta = SaldCuenta + ? WHERE DPI = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setDouble(1, SaldCuenta);
            stmt.setString(2, DPI);
            stmt.executeUpdate();

            lblSaldoActual.setText(String.valueOf(Double.parseDouble(lblSaldoActual.getText()) + SaldCuenta));

            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FormDepositos form = new FormDepositos();
            form.setVisible(true);
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
*/
    

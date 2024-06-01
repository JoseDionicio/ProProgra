/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

import forms.FormMenu;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author nebra
 */
public class IngresoUsers {
    //CLASE PARA VALIDAR EL LOGIN 
    
    // METODO PARA VALIDAR USUARIO
    
    public void validaUsuario(JTextField usuario, JPasswordField contrasenia){
        
        //HACER UN TRY PARA LAS EXCEPCIONES
        try {
            //LA VALIDACIÓN 
            ResultSet rs=null;
            PreparedStatement ps= null;
            
            //IR LA CLASE DE LA CONEXION PARA LA CONSULTA
            clases.BDConection objetConexion = new clases.BDConection();
            
            String consulta="select * from Usuarios where Usuarios.ingresoUsuarios=(?) and Usuarios.ingresoContrasenia=(?);";
            ps=objetConexion.estableceConexion().prepareStatement(consulta);
            
            //TRANSFORMAR PASSWORD EN CADENA
            String contra = String.valueOf(contrasenia.getPassword());
            
            //INCORPORAR LOS VALORES DE LOS PARAMETROS
            ps.setString(1, usuario.getText());
            ps.setString(2,contra);
            
            rs = ps.executeQuery();
            
            //VER SI REALMENTE VA A DAR UN RESULTADO POSITIVO
            if (rs.next()) {
                JOptionPane.showConfirmDialog(null, "EL USUARIO ES CORRECTO");
                //QUE NOS ENVIE AL MENU PRINCIPAL
                forms.FormMenu objetoMenu = new FormMenu();
                objetoMenu.setVisible(true);
            }
            //SI NO LLEGA A PASAR EL USUARIO ES INCORRECTO Y NO ABRIR NADA
            else{
                JOptionPane.showConfirmDialog(null, "EL USUARIO ES INCORRECTO, VUELVA A INTENTAR");
            }

        } catch (Exception e) {
            //MENSAJE QUE SE MOSTRARA POR ALGUN ERROR 
            JOptionPane.showConfirmDialog(null, "ERROR: "+e.toString());

        }
    }
}

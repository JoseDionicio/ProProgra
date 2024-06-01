/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;
//IMPORTACIONES PARA LA CONEXION A LA BASE DE DATOS
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;


/**
 *
 * @author nebra
 */
public class BDConection {
    Connection conectar;
    
    //PASO 2 CREAR LAS VARIABLES PARA ALMACENAR LAS CREDENCIALES 
    String usuario="root";
    String contrasenia="";
    String bd="bcprogramadores";
    String ip="127.0.0.1";
    String puerto="3306";
    
    //PASO 3 CRAR UNA CADENA DE CONEXIÓN 
    String cadena = "jdbc:mysql://"+ip+":"+puerto+"/"+bd;
    //LO DE ARRIBA ES LA CONCATENACIÓN DE TODA LA CADENA DE CONEXIÓN
    
    //PASO 4 CREAR UN METODO PARA CONECTAR LO QUE SE VA A REUTILIZAR PARA LA VALIDACIÓN
    public Connection estableceConexion(){
        
       // SE REALIZA EL TRY PARA LAS EXCEPCIONES
        try {
            //REALIZAR LA CONEXIÓN CON LA CADENA DE CONEXIÓN
            Class.forName("com.mysql.jdbc.Driver");
            conectar = DriverManager.getConnection(cadena,usuario,contrasenia);
            //MENSAJE PARA VER SI SE LLEGA A CONECTAR 
            //JOptionPane.showMessageDialog(null, "SE CONECTO CORRECTAMENTE");

            
        } catch (Exception e) {                    
            //MENSAJE PARA CUANDO HAYA ERRORES 
            JOptionPane.showMessageDialog(null, "PROBLEMAS EN LA CONEXION POR FAVOR REVISAR"+ e.toString());
        }
        //RETORNO DE LA VARIABLE TIPO CONEXIÓN
        return conectar;
    }
}

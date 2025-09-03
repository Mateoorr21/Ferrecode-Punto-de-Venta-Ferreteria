import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;
import java.sql.*;
import javax.swing.*;

public class Conexion {
	protected static Connection conn = null;
	protected static String ruta = "jdbc:ucanaccess://base/BDDS_Ferreteria.accdb";
	
	//Declaramos el Id, Nombre Completo, Usuario y Permiso del usuario que inicia sesión
    public static int IdUsuario = 0;
    public static String NombreCompleto = null;
    public static String Usuario = null;
    public static String TipoUsuario = null;
    public static String PasswordBD = null;
    public static boolean TienePermiso = false;
    public static int ErroresInicioSesion = 0;
    public static Login loginVentana; //Formulario tipo login para poder volver a el al cerrarsesion
    public static Inicio inicioVentana; //Formulario tipo inicio para regresar al inicio del sistema
	
	public static void abrirConexion(){
		 try {
			 Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			 conn = DriverManager.getConnection(ruta);
		 }
		 catch(Exception e) {
			 JOptionPane.showMessageDialog(null, "Ocurrió el error: "+ e);
		 }
	}
	
	public static void cerrarConexion(){
		if(conn != null) {
			try {
				conn.commit(); //Se actualiza base de datos
				conn.close();
                conn = null;
            } catch (Exception e) {
            	JOptionPane.showMessageDialog(null, "Ocurrió el error: "+ e);
            }
		}
	}
	
	//FUNCION PARA REINICIAR VARIABLES
    public static void ReiniciarVariables()
    {
        //Reinciamos el valor de las Variables usadas
        Ventas_funcion.total_venta = 0;
    }
    
	public static void iniciarSesion(String username, String password, JTextField txtUsuario, JPasswordField txtPassword, Login ventanaInicioSesion) {
	    PreparedStatement sentencia = null;
	    ResultSet rs = null;

	    try {
	        Conexion.abrirConexion();

	        String sql = "SELECT Id, Nombre_Completo, Usuario, Password, Tipo FROM Usuarios_Operativos WHERE Usuario = ?";
	        sentencia = Conexion.conn.prepareStatement(sql);
	        sentencia.setString(1, username);
	        rs = sentencia.executeQuery();

	        if (rs.next()) {
	            // Datos de la base de datos
	            IdUsuario = rs.getInt("Id");
	            NombreCompleto = rs.getString("Nombre_Completo");
	            Usuario = rs.getString("Usuario");
	            PasswordBD = rs.getString("Password");
	            TipoUsuario = rs.getString("Tipo");

	            if (username.equals(Usuario) && password.equals(PasswordBD)) {
	                // Limpiar campos
	                txtUsuario.setText("");
	                txtPassword.setText("");

	                // Verificación de permiso
	                TienePermiso = TipoUsuario.equalsIgnoreCase("Admin") || TipoUsuario.equalsIgnoreCase("Propietario");

	                // Ocultar ventana actual y abrir siguiente formulario
	                ventanaInicioSesion.setVisible(false);
	                Principal principal = new Principal();
					principal.setLocationRelativeTo(null);
					principal.setVisible(true);

	                //Puedes reiniciar variables globales si las usas
	                ReiniciarVariables();

	            } else {
	                mostrarError();
	            }

	        } else {
	            mostrarError();
	        }

	        Conexion.cerrarConexion();

	    } catch (SQLException e) {
	        JOptionPane.showMessageDialog(null, "Error en la base de datos: " + e.getMessage(), "Error SQL", JOptionPane.ERROR_MESSAGE);
	    }
	}

    private static void mostrarError() {
        JOptionPane.showMessageDialog(null,
                "Usuario y/o Contraseña incorrectos.",
                "Error. No existe la cuenta.",
                JOptionPane.ERROR_MESSAGE);

        ErroresInicioSesion++;
        if (ErroresInicioSesion == 3) {
        	cerrarConexion();
            System.exit(0); // Cierra la app después de 3 intentos fallidos
        }
    }
    
    public static void cerrarSesion(JFrame frame) {
    	// Mostrar mensaje de despedida
        JOptionPane.showMessageDialog(null,
            "Gracias por usar nuestro sistema. ¡Vuelva Pronto!",
            "CERRANDO SESIÓN",
            JOptionPane.INFORMATION_MESSAGE);

        // Cerrar la ventana principal y abrir inicio
        frame.dispose();
        inicioVentana = new Inicio();
        inicioVentana.setLocationRelativeTo(null);
        inicioVentana.setVisible(true);
    }
	
}

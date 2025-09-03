import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Usuario extends Conexion{
	public static boolean SeAutoModifico = false; //Booleano para ver si el usuario se modifico a si mismo
	public static void cargarUsuarios(JTable tablaUsuarios) {
		DefaultTableModel modelo = (DefaultTableModel) tablaUsuarios.getModel();
	    modelo.setRowCount(0); // Limpiar la tabla

	    abrirConexion(); // Método para abrir conexión
	    PreparedStatement sentencia = null;
	    ResultSet resultado = null;

	    int contador = 0;

	    try {

	        String sql = "SELECT Id, Nombre_Completo, Usuario, Password, Tipo FROM Usuarios_Operativos";

	        if (Conexion.TipoUsuario.equals("Admin")) {
	            sql += " WHERE StrComp(Usuario, ?, 0) = 0 OR Tipo = 'Cajero'";
	        }
	        //Ordenar por rol
	        sql += " ORDER BY CASE Tipo  WHEN 'Propietario' THEN 1  WHEN 'Admin' THEN 2 WHEN 'Cajero' THEN 3 END";
	        sentencia = conn.prepareStatement(sql);
	        
	        if (Conexion.TipoUsuario.equals("Admin")) {
	            sentencia.setString(1, Conexion.Usuario);
	        }
	        resultado = sentencia.executeQuery();

	        while (resultado.next()) {
	            Object[] fila = {
	                resultado.getInt("Id"),
	                resultado.getString("Nombre_Completo"),
	                resultado.getString("Usuario"),
	                "******",
	                resultado.getString("Tipo")
	            };
	            modelo.addRow(fila);
	            contador++;
	        }

	    } catch (SQLException e) {
	        JOptionPane.showMessageDialog(null, "Error al consultar todos los usuarios: " + e.getMessage());
	    }

	    cerrarConexion(); // Cerrar conexión

	    // Actualizar la etiqueta con el número de usuarios encontrados
	    //lblUsuariosEncontrados.setText("Usuarios Encontrados: " + contador);
	}
	
	public static void consultarUsuario(JTextField txtBuscar, JTable tablaUsuarios) {
	    String buscar = txtBuscar.getText().trim();

	    if (buscar.isEmpty()) {
	        JOptionPane.showMessageDialog(null,
	            "Ingrese un nombre o usuario para buscar.",
	            "CAMPO DE BÚSQUEDA VACÍO",
	            JOptionPane.WARNING_MESSAGE);
	        return;
	    }

	    DefaultTableModel modelo = (DefaultTableModel) tablaUsuarios.getModel();
	    modelo.setRowCount(0); // Limpiar tabla

	    abrirConexion(); // Método de tu clase de conexión
	    PreparedStatement sentencia = null;
	    ResultSet resultado = null;

	    int contador = 0; // Contador de usuarios encontrados

	    try {
	        String sql = "SELECT Id, Nombre_Completo, Usuario, Password, Tipo FROM Usuarios_Operativos " +
	                     "WHERE (Nombre_Completo LIKE ? OR Usuario LIKE ?)";

	        if (Conexion.TipoUsuario.equals("Admin")) {
	            sql += " AND (StrComp(Usuario, ?, 0) = 0 OR Tipo = 'Cajero')";
	        }
	        //Ordenar por rol
	        sql += " ORDER BY CASE Tipo  WHEN 'Propietario' THEN 1  WHEN 'Admin' THEN 2 WHEN 'Cajero' THEN 3 END";
	        sentencia = conn.prepareStatement(sql);

	        sentencia = conn.prepareStatement(sql);
	        sentencia.setString(1, "%" + buscar + "%");
	        sentencia.setString(2, "%" + buscar + "%");

	        if (Conexion.TipoUsuario.equals("Admin")) {
	            sentencia.setString(3, Conexion.Usuario);
	        }
	        resultado = sentencia.executeQuery();

	        while (resultado.next()) {
	            Object[] fila = {
	                resultado.getInt("Id"),
	                resultado.getString("Nombre_Completo"),
	                resultado.getString("Usuario"),
	                "*******",
	                resultado.getString("Tipo")
	            };
	            modelo.addRow(fila);
	            contador++;
	        }

	    } catch (SQLException e) {
	        JOptionPane.showMessageDialog(null, "Error al consultar usuarios: " + e.getMessage());
	    }

	    cerrarConexion(); // Método de tu clase de conexión

	    // Actualizar etiqueta con la cantidad de usuarios encontrados
	    //lblUsuariosEncontrados.setText("Usuarios Encontrados: " + contador);
	    //String tipo = cmbTipo.getSelectedItem() != null ? cmbTipo.getSelectedItem().toString() : "";
	}
	

	
	public static boolean darDeAltaUsuario(JTextField txtNombre, JTextField txtUsuario, JTextField txtContrasena, String tipoUsuario) {
	    String nombre = txtNombre.getText().trim();
	    String usuario = txtUsuario.getText().trim();
	    String contrasena = txtContrasena.getText().trim();

	    // Validación de campos vacíos
	    if (nombre.isEmpty() || usuario.isEmpty() || contrasena.isEmpty()) {
	    	JOptionPane.showMessageDialog(null, "Error. Ingrese información del usuario a añadir.",
					"ERROR. CAMPOS VACÍOS", JOptionPane.ERROR_MESSAGE);
	    	return false;
	    }

	    abrirConexion(); // Método de conexión a la base de datos
	    PreparedStatement sentencia = null;
	    ResultSet resultado = null;

	    try {
	        // Verificar si el usuario ya existe (comparación sensible a mayúsculas/minúsculas)
	        String sqlVerificar = "SELECT Usuario FROM Usuarios_Operativos WHERE StrComp(Usuario, ?, 0) = 0";
	        sentencia = conn.prepareStatement(sqlVerificar);
	        sentencia.setString(1, usuario);
	        resultado = sentencia.executeQuery();

	        if (resultado.next()) {
	        	JOptionPane.showMessageDialog(null, "Error. El nombre de usuario ya existe. Pruebe con otro nombre de usuario",
						"ERROR. USUARIO YA EXISTENTE", JOptionPane.ERROR_MESSAGE);
	        	cerrarConexion();
	            return false;
	        }

	        // Insertar nuevo usuario
	        String sqlInsertar = "INSERT INTO Usuarios_Operativos (Nombre_Completo, Usuario, Password, Tipo) VALUES (?, ?, ?, ?)";
	        sentencia = conn.prepareStatement(sqlInsertar);
	        sentencia.setString(1, nombre);
	        sentencia.setString(2, usuario);
	        sentencia.setString(3, contrasena);
	        sentencia.setString(4, tipoUsuario);
	        sentencia.executeUpdate();

	        JOptionPane.showMessageDialog(null,
	            "Usuario registrado exitosamente.",
	            "ALTA EXITOSA",
	            JOptionPane.INFORMATION_MESSAGE);

	    } catch (SQLException e) {
	        JOptionPane.showMessageDialog(null,
	            "Error al registrar usuario: " + e.getMessage(),
	            "ERROR",
	            JOptionPane.ERROR_MESSAGE);
	    }

	    cerrarConexion();
	    return true;
	}
	
	
	public static void darDeBaja(int id, String nombre, String tipo, JTable TablaUsuarios) {
	    // Validar que no se intente eliminar al usuario logueado
	    if (id == Conexion.IdUsuario) {
	        JOptionPane.showMessageDialog(null,
	            "No puedes eliminar tu propio usuario mientras estás logueado.",
	            "Operación no permitida", JOptionPane.WARNING_MESSAGE);
	        return;
	    }
	    // Confirmar eliminación
	    int confirmacion = JOptionPane.showConfirmDialog(null,
	        "¿Estás seguro que deseas eliminar al "+ tipo + " " + nombre + "?",
	        "Confirmar eliminación",
	        JOptionPane.YES_NO_OPTION,
	        JOptionPane.QUESTION_MESSAGE);
	    if (confirmacion != JOptionPane.YES_OPTION) {
	        return; // Cancelado por el usuario
	    }
	    abrirConexion();
	    PreparedStatement sentencia = null;
	    try {
	        sentencia = conn.prepareStatement("DELETE FROM Usuarios_Operativos WHERE id = ?");
	        sentencia.setInt(1, id);
	        sentencia.executeUpdate();
	        JOptionPane.showMessageDialog(null,
	            "Se eliminó con éxito al  " + tipo+ " " + nombre + " de la lista de usuarios");
	        int filaSeleccionada = TablaUsuarios.getSelectedRow();
	        DefaultTableModel modelo = (DefaultTableModel) TablaUsuarios.getModel();
	        modelo.removeRow(filaSeleccionada);
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    cerrarConexion();
	}
	
	public static void editar(JTextField txtNombre, JTextField txtUsuario, JTextField txtClave, JComboBox<String> cmbTipo, JTable tablaUsuarios) {
	    
	    PreparedStatement sentencia = null;
	    ResultSet resultado = null;

	    String nombre = txtNombre.getText().trim();
	    String usuario = txtUsuario.getText().trim();
	    String clave = txtClave.getText().trim();
	    String tipo = cmbTipo.getSelectedItem() != null ? cmbTipo.getSelectedItem().toString() : "";
	    
	    int filaSeleccionada = tablaUsuarios.getSelectedRow();
	    if (filaSeleccionada == -1) {
	        JOptionPane.showMessageDialog(null, "Seleccione un usuario para editar.", "Error", JOptionPane.ERROR_MESSAGE);
	        return;
	    }

	    int id = Integer.parseInt(tablaUsuarios.getValueAt(filaSeleccionada, 0).toString());

	    // Validación de campos vacíos (excepto clave)
	    if (nombre.isEmpty() || usuario.isEmpty() || tipo.isEmpty()) {
	        JOptionPane.showMessageDialog(null, "Por favor complete todos los campos (excepto contraseña si no desea cambiarla).", "Campos incompletos", JOptionPane.WARNING_MESSAGE);
	        return;
	    }
	    
	    // Validación de campos vacíos (excepto clave)
	    if (tipo.equals("Propietario") && Conexion.TipoUsuario.equals("Propietario") && id != Conexion.IdUsuario) {
	        JOptionPane.showMessageDialog(null, "No se puede editar el permiso a Propietario.", "Error. No se modifica a permiso Propietario", JOptionPane.WARNING_MESSAGE);
	        
	        // Obtener el tipo original del usuario desde la tabla (columna 4, ajusta si es necesario)
	        String tipoOriginal = tablaUsuarios.getValueAt(filaSeleccionada, 4).toString();
	        
	        // Restaurar selección al tipo anterior
	        cmbTipo.setSelectedItem(tipoOriginal);
	        return;
	    }
	    
	    // Confirmar modificacion
	    int confirmacion = JOptionPane.showConfirmDialog(null,
	        "¿Desea editar la información del Usuario Operativo?",
	        "Confirmar Edición",
	        JOptionPane.YES_NO_OPTION,
	        JOptionPane.QUESTION_MESSAGE);
	    if (confirmacion != JOptionPane.YES_OPTION) {
	        return; // Cancelado por el usuario
	    }
	    
	    abrirConexion();
	    try {
	        // Validar que el nuevo nombre de usuario no esté ya en uso por otro usuario
	        sentencia = conn.prepareStatement("SELECT Id FROM Usuarios_Operativos WHERE StrComp(Usuario, ?, 0) = 0 AND Id != ?");
	        sentencia.setString(1, usuario);
	        sentencia.setInt(2, id);
	        resultado = sentencia.executeQuery();
	        if (resultado.next()) {
	            JOptionPane.showMessageDialog(null, "El nombre de usuario ya está en uso.", "Error", JOptionPane.ERROR_MESSAGE);
	            cerrarConexion();
	            return;
	        }

	        // Actualizar datos básicos
	        sentencia = conn.prepareStatement("UPDATE Usuarios_Operativos SET Nombre_Completo = ?, Usuario = ?, Tipo = ? WHERE Id = ?");
	        sentencia.setString(1, nombre);
	        sentencia.setString(2, usuario);
	        sentencia.setString(3, tipo);
	        sentencia.setInt(4, id);
	        sentencia.executeUpdate();

	        // Si se proporciona una nueva contraseña
	        if (!clave.isEmpty()) {
	            sentencia = conn.prepareStatement("UPDATE Usuarios_Operativos SET Password = ? WHERE Id = ?");
	            sentencia.setString(1, clave);
	            sentencia.setInt(2, id);
	            sentencia.executeUpdate();
	        }

	        // Actualizar la fila en la tabla
	        tablaUsuarios.setValueAt(nombre, filaSeleccionada, 1);
	        tablaUsuarios.setValueAt(usuario, filaSeleccionada, 2);
	        tablaUsuarios.setValueAt(tipo, filaSeleccionada, 4);
	        tablaUsuarios.clearSelection();
	        
	        if(Conexion.IdUsuario == id) {
	        	SeAutoModifico = true;
	        	Conexion.Usuario = usuario;
	        	Conexion.NombreCompleto = nombre;
	        }
	        
	        JOptionPane.showMessageDialog(null, "Se editó con éxito el usuario seleccionado.", "Edición de Usuario Exitosa",JOptionPane.INFORMATION_MESSAGE);
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    cerrarConexion();
	}

}

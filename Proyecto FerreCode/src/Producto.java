import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class Producto extends Conexion{ 
	public static BufferedImage imagenTemporal = null;
	public static String nombreImagenTemporal = null;
	public static boolean SeModificoImagen = false;
	
	// Función que registra un producto nuevo tomando los datos directamente desde los JTextField del formulario.
	// También guarda una imagen temporal si está definida, con el ID generado como nombre.
	public static boolean registrarProducto(JTextField txtNombre, JTextArea txtDesc, JTextField txtMarca, 
			JTextField txtPrecio, JTextField txtCantidad) {
	    // Obtener y limpiar los valores ingresados
	    String nombre = txtNombre.getText().trim();
	    String descripcion = txtDesc.getText().trim();
	    String marca = txtMarca.getText().trim();
	    String textoPrecio = txtPrecio.getText().trim();
	    String textoCantidad = txtCantidad.getText().trim();
	    
	    // Verificar campos vacíos, incluyendo imagen
	    if (nombre.isEmpty() || descripcion.isEmpty() || marca.isEmpty() || 
	        textoPrecio.isEmpty() || textoCantidad.isEmpty() ||
	        imagenTemporal == null || nombreImagenTemporal == null) {
	        JOptionPane.showMessageDialog(null, "Por favor completa todos los campos y carga una imagen.", "Error. Campos incompletos", JOptionPane.WARNING_MESSAGE);
	        return false;
	    }

	    double precio = 0.0;
	    int cantidad = 0;

	    // Validar precio y cantidad sean  mayores a cero
	        precio = Double.parseDouble(textoPrecio);
	        cantidad = Integer.parseInt(textoCantidad);
	        if (precio <= 0 || cantidad <= 0) {
	            JOptionPane.showMessageDialog(null, "Precio y cantidad deben ser mayores a cero.", "Valores inválidos", JOptionPane.WARNING_MESSAGE);
	            return false;
	        }

	    abrirConexion();
	    PreparedStatement sentencia = null;
	    ResultSet rs = null;
	    int idGenerado = -1;

	    try {
	        // Verificar si ya existe un producto con el mismo nombre y marca
	        sentencia = conn.prepareStatement("SELECT COUNT(*) FROM Productos WHERE Nombre = ? AND Marca = ?");
	        sentencia.setString(1, nombre);
	        sentencia.setString(2, marca);
	        rs = sentencia.executeQuery();
	        if (rs.next() && rs.getInt(1) > 0) {
	            JOptionPane.showMessageDialog(null, "Ya existe un producto " + nombre + " de la marca " 
	        + marca +" en la Ferretería.", "Error. Producto Duplicado", JOptionPane.ERROR_MESSAGE);
	            cerrarConexion();
	            return false;
	        }
	        rs.close();
	        sentencia.close();

	        // Insertar el nuevo producto
	        sentencia = conn.prepareStatement(
	            "INSERT INTO productos (Nombre, Descripcion, Marca, Precio, Cantidad_En_Stock) VALUES (?, ?, ?, ?, ?)",
	            Statement.RETURN_GENERATED_KEYS //Para retornar el id del usuario recien ingresado
	        );
	        sentencia.setString(1, nombre);
	        sentencia.setString(2, descripcion);
	        sentencia.setString(3, marca);
	        sentencia.setDouble(4, precio);
	        sentencia.setInt(5, cantidad); 
	        sentencia.executeUpdate();

	        rs = sentencia.getGeneratedKeys();
	        if (rs.next()) idGenerado = rs.getInt(1);

	        JOptionPane.showMessageDialog(null, "Se agregó con éxito el producto: " + nombre 
	            + "\n de la marca " + marca, "Alta de Producto Exitosa", JOptionPane.INFORMATION_MESSAGE);

	        // Guardar imagen si todo fue exitoso
	        if (imagenTemporal != null && idGenerado != -1) {
	            File directorio = new File("Imagenes");
	            if (!directorio.exists()) {
	                directorio.mkdir();
	            }
	            File archivoDestino = new File("Imagenes/" + idGenerado + ".png");
	            ImageIO.write(imagenTemporal, "png", archivoDestino);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(null, "Error al insertar producto en la base de datos.", "Error SQL", JOptionPane.ERROR_MESSAGE);
	    } catch (IOException e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(null, "Error al guardar la imagen del producto.", "Error de imagen", JOptionPane.ERROR_MESSAGE);
	    } 
	        
	    cerrarConexion();
	    return true;  
	}
	
	public static void darDeBaja(JTable TablaProductos) {
	    // Validar que haya una fila seleccionada
	    int filaSeleccionada = TablaProductos.getSelectedRow();
	    if (filaSeleccionada == -1) {
	        JOptionPane.showMessageDialog(null, "Por favor selecciona un producto de la tabla.", "Sin selección", JOptionPane.WARNING_MESSAGE);
	        return;
	    }
	    // Obtener ID, nombre y marca del producto seleccionado (columnas 0, 1 y 3)
	    int id = Integer.parseInt(TablaProductos.getValueAt(filaSeleccionada, 0).toString());
	    String nombre = TablaProductos.getValueAt(filaSeleccionada, 1).toString();
	    String marca = TablaProductos.getValueAt(filaSeleccionada, 3).toString();

	    abrirConexion();
	    PreparedStatement sentencia = null;
	    
	    // Confirmación
		int confirmacion = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea eliminar el producto seleccionado?",
				"Confirmación de Eliminación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (confirmacion != JOptionPane.YES_OPTION) return;

		
	    try {
	        // Eliminar producto de la base de datos
	        sentencia = conn.prepareStatement("DELETE FROM productos WHERE id = ?");
	        sentencia.setInt(1, id);
	        sentencia.executeUpdate();
	        
	        // Eliminar la imagen del producto si existe
	        File imagen = new File("Imagenes/" + id + ".png");
	        if(imagen.exists()) imagen.delete();
	        
	        // Mensaje de éxito
	        JOptionPane.showMessageDialog(null, "Se eliminó con éxito el producto: " + nombre + 
	            " de la marca " + marca + " de la ferretería.", "Producto eliminado", JOptionPane.INFORMATION_MESSAGE);

	        // Eliminar la fila del JTable
	        DefaultTableModel modelo = (DefaultTableModel) TablaProductos.getModel();
	        modelo.removeRow(filaSeleccionada);

	    } catch (SQLException e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(null, "Error al eliminar el producto de la base de datos.", "Error SQL", JOptionPane.ERROR_MESSAGE);
	    } 
	    cerrarConexion();
	}
	
	public static boolean ActualizarProducto(JTable tablaProductos, JTextField txtNombre, JTextField txtDescripcion,
            JTextField txtMarca, JTextField txtPrecio, JLabel picImagenProducto) {

			int filaSeleccionada = tablaProductos.getSelectedRow();
			if (filaSeleccionada == -1) {
				JOptionPane.showMessageDialog(null, "Error. Seleccione un producto a modificar", "ERROR. NO SE SELECCIONÓ PRODUCTO",
						JOptionPane.ERROR_MESSAGE);
				return false;
			}
			
			int IdSeleccionado = Integer.parseInt(tablaProductos.getValueAt(filaSeleccionada, 0).toString());

			// Obtener campos desde las cajas de texto
			String nombre = txtNombre.getText().trim();
			String descripcion = txtDescripcion.getText().trim();
			String marca = txtMarca.getText().trim();
			String precioTexto = txtPrecio.getText().trim();
			
			//Checamos que los campos no esten vacios y que se haya puesto una imagen
			if (nombre.isEmpty() || descripcion.isEmpty() || marca.isEmpty() || precioTexto.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Error. Ingrese información a modificar y cargue imagen si desea cambiarla.", "Error. Alguno de los campos está vacío.",
						JOptionPane.ERROR_MESSAGE);
				return false;
			}

			float precio; //Obtenemos cantidad y precio ingresados
			precio = Float.parseFloat(precioTexto);
			
			//No se puede ingresar precio como 0
			if (precio == 0) {
				JOptionPane.showMessageDialog(null, "Error. No se puede ingresar 0 como valor de Precio",
						"Error. 0 no es válido.", JOptionPane.ERROR_MESSAGE);
				return false;
			}

			abrirConexion(); //Conexion

			try {
				// Verificar duplicado
				String buscar = "SELECT Nombre, Marca FROM Productos WHERE Nombre = ? AND Marca = ? AND Id <> ?";
				PreparedStatement buscarDuplicado = conn.prepareStatement(buscar);
				buscarDuplicado.setString(1, nombre);
				buscarDuplicado.setString(2, marca);
				buscarDuplicado.setInt(3, IdSeleccionado);
				ResultSet rs = buscarDuplicado.executeQuery();
				if (rs.next()) {
					JOptionPane.showMessageDialog(null, "El producto " + nombre + " de la marca " + marca + " ya se encuentra en la ferretería.",
							"Advertencia. Producto Existente", JOptionPane.INFORMATION_MESSAGE);
					cerrarConexion();
					return false;
				}

				// Confirmación
				int confirmacion = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea modificar la información del producto seleccionado?",
						"Confirmación de Actualización", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

				if (confirmacion != JOptionPane.YES_OPTION) {
					cerrarConexion();
					return false;
				}
					

				// Actualizar en BD
				String actualizar = "UPDATE Productos SET Nombre = ?, Descripcion = ?, Marca = ?, Precio = ? WHERE Id = ?";
				PreparedStatement stmtActualizar = conn.prepareStatement(actualizar);
				stmtActualizar.setString(1, nombre);
				stmtActualizar.setString(2, descripcion);
				stmtActualizar.setString(3, marca);
				stmtActualizar.setFloat(4, precio);
				stmtActualizar.setInt(5, IdSeleccionado);
				stmtActualizar.executeUpdate();

				// Eliminar imagen anterior y copiar la nueva solo si se modifico
				if (SeModificoImagen) {
					String rutaImagenDestino = "Imagenes/" + IdSeleccionado + ".png";
					try {
						//Sobre escribimmos sobre la imagen previa y copiamos la nueva imagen en la carpeta de Imagenes
						ImageIO.write(imagenTemporal, "png", new File(rutaImagenDestino));
					} catch (IOException e) {
						JOptionPane.showMessageDialog(null, "Error al copiar la nueva imagen del producto.", "ERROR DE IMAGEN", JOptionPane.ERROR_MESSAGE);
						e.printStackTrace();
					}
				}

				// Actualizar datos en el JTable visualmente
				DefaultTableModel modelo = (DefaultTableModel) tablaProductos.getModel();
				modelo.setValueAt(nombre, filaSeleccionada, 1);
				modelo.setValueAt(descripcion, filaSeleccionada, 2);
				modelo.setValueAt(marca, filaSeleccionada, 3);
				modelo.setValueAt("$" + Ventas_funcion.df.format(precio), filaSeleccionada, 4);
				tablaProductos.clearSelection();

				// Mensaje final
				JOptionPane.showMessageDialog(null, "Datos del producto actualizados correctamente.",
						"Actualización de Producto Exitosa", JOptionPane.INFORMATION_MESSAGE);
				
				//Booleano se modifico imagen a falso
				SeModificoImagen = false;
				imagenTemporal = null;
				tablaProductos.clearSelection(); //Limpiamos seleccion para que se limpien las cajas de texto
				return true;

			} catch (SQLException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Error al actualizar el producto.", "ERROR SQL", JOptionPane.ERROR_MESSAGE);
				return false;
			} finally {
				cerrarConexion();
			}
	}
	
	
	public static void actualizarInventario(int id, int cant, String nombre, JTable tablaProductos) {
		abrirConexion();
		PreparedStatement sentencia = null;
		try {
			sentencia = conn.prepareStatement("UPDATE productos SET Cantidad_En_Stock = ? WHERE id = ?");
			sentencia.setInt(1, cant);
			sentencia.setInt(2, id);
			sentencia.executeUpdate();
			JOptionPane.showMessageDialog(null, "Se actualizó con exito el inventario del producto: " + nombre);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		cerrarConexion();
		
		//Actualizamos tabla y limpiamos selección
		int filaseleccionada = tablaProductos.getSelectedRow();
		tablaProductos.setValueAt(cant, filaseleccionada, 4);
		tablaProductos.clearSelection();
 	}
	
	public static void cargarProductos(JTable tablaProductos) {
		Conexion.abrirConexion();
		PreparedStatement sentencia = null;
		ResultSet Rs = null;
		try {
			sentencia = Conexion.conn.prepareStatement("SELECT * FROM productos");
			Rs = sentencia.executeQuery();
			
			// Obtener el modelo de la tabla
	        DefaultTableModel modelo = (DefaultTableModel) tablaProductos.getModel();
	        
	        // Limpiar la tabla antes de cargar nuevos datos
	        modelo.setRowCount(0);
	        
            // Agregar filas
            while (Rs.next()) {
            	Object[] fila = new Object[6];
                fila[0] = Rs.getInt("Id");
                fila[1] = Rs.getString("Nombre");
                fila[2] = Rs.getString("Descripcion");
                fila[3] = Rs.getString("Marca");
                fila[4] = "$" + Ventas_funcion.df.format(Rs.getDouble("Precio"));
                fila[5] = Rs.getInt("Cantidad_en_Stock");
                modelo.addRow(fila);
            }        
            Conexion.cerrarConexion();
			
		}catch(SQLException e){}
	}
	
	public static void consultarProductos(String texto, JTable tablaProductos) {
		Conexion.abrirConexion();
		PreparedStatement sentencia = null;
		ResultSet Rs = null;
		
		try {
			sentencia = Conexion.conn.prepareStatement("SELECT * FROM productos WHERE nombre LIKE ?");
			sentencia.setString(1,"%" + texto + "%"); //Primer parametro
			Rs = sentencia.executeQuery();
			
			// Obtener el modelo de la tabla
	        DefaultTableModel modelo = (DefaultTableModel) tablaProductos.getModel();
	        
	        // Limpiar la tabla antes de cargar nuevos datos
	        modelo.setRowCount(0);
            // Agregar filas
            while (Rs.next()) {
            	Object[] fila = new Object[6];
                fila[0] = Rs.getInt("Id");
                fila[1] = Rs.getString("Nombre");
                fila[2] = Rs.getString("Descripcion");
                fila[3] = Rs.getString("Marca");
                fila[4] = "$" + Ventas_funcion.df.format(Rs.getDouble("Precio"));
                fila[5] = Rs.getInt("Cantidad_en_Stock");
                modelo.addRow(fila);
            }
            Rs.close();
            sentencia.close();
            Conexion.cerrarConexion();
			
		}catch(SQLException e){}
	}
}
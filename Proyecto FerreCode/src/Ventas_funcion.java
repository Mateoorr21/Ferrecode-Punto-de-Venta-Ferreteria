import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Ventas_funcion {
	
	public static DecimalFormat df = new DecimalFormat("#.##"); //Formato con dos decimales
	public static int CantidadEnStockSeleccionado = 0;
    public static int CantidadIngresada = 0;
    public static int CantidadEnCarrito = 0;
    public static int FilaSeleccionada = 0;
    public static boolean existe_en_carrito = false;
	public static String Tipo_Actualizacion = null;
	public static float total_venta = 0;
	public static boolean hayVentas = false;
	public static boolean seCompletoOperacion = false;
	
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
            	Object[] fila = new Object[5];
                fila[0] = Rs.getInt("Id");
                fila[1] = Rs.getString("Nombre");
                fila[2] = Rs.getString("Marca");
                fila[3] = "$" + df.format(Rs.getDouble("Precio"));
                fila[4] = Rs.getInt("Cantidad_en_Stock");
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
            	Object[] fila = new Object[5];
                fila[0] = Rs.getInt("Id");
                fila[1] = Rs.getString("Nombre");
                fila[2] = Rs.getString("Marca");
                fila[3] = "$" + df.format(Rs.getDouble("Precio"));
                fila[4] = Rs.getInt("Cantidad_en_Stock");
                modelo.addRow(fila);
            }
            Rs.close();
            sentencia.close();
            Conexion.cerrarConexion();
			
		}catch(SQLException e){}
	}
	
	
	public static void agregarAlCarrito(JTable tablaProductos, JTable tablaCarrito, JTextField txtCantidad, 
			JTextField txtPorPagar, JLabel lblCantidadRegistrosCarrito, JTextField txtProductos) {
	    FilaSeleccionada = tablaProductos.getSelectedRow();
	    existe_en_carrito = false;
	    
	    //Si la caja de cantidad esta vacía solicitamos que se ingrese
	    if(txtCantidad.getText().trim().isEmpty()) {
	    	JOptionPane.showMessageDialog(null, "Por favor completa el campo de Cantidad para agregar al Carrito.", "Error. Campo incompleto", JOptionPane.WARNING_MESSAGE);
	        return;
	    }
	    
	    // Validación: no se ha seleccionado producto
	    if (FilaSeleccionada == -1) {
	        JOptionPane.showMessageDialog(null, "Seleccione un producto para agregar al carrito.",
	                "ERROR. NO SE SELECCIONÓ PRODUCTO", JOptionPane.ERROR_MESSAGE);
	        return;
	    }

	    CantidadIngresada = Integer.parseInt(txtCantidad.getText());
	    if (CantidadIngresada <= 0) {
	        JOptionPane.showMessageDialog(null, "Cantidad inválida. Ingrese un número mayor a cero.",
	                "ERROR. CANTIDAD INVÁLIDA", JOptionPane.ERROR_MESSAGE);
	        return;
	    }

	    DefaultTableModel modeloProductos = (DefaultTableModel) tablaProductos.getModel();
	    DefaultTableModel modeloCarrito = (DefaultTableModel) tablaCarrito.getModel();

	    // Obtener datos del producto seleccionado
	    String id = modeloProductos.getValueAt(FilaSeleccionada, 0).toString();
	    String nombre = modeloProductos.getValueAt(FilaSeleccionada, 1).toString();
	    String marca = modeloProductos.getValueAt(FilaSeleccionada, 2).toString();
	    String precioStr = modeloProductos.getValueAt(FilaSeleccionada, 3).toString().replace("$", "").replace(",", "");
	    float precio = Float.parseFloat(precioStr);
	    
	    // Cálculos
	    float totalSinIVA = CantidadIngresada * precio;
	    float totalConIVA = totalSinIVA * 1.16f;
	 
	    // Validar si ya está en el carrito
	    for (int i = 0; i < modeloCarrito.getRowCount(); i++) {
	    	
	    	//Si el producto ya esta en el carrito
	        if (modeloCarrito.getValueAt(i, 0).toString().equals(id)) {
	        	
	        	Tipo_Actualizacion = "Agregar_Mismo_Producto"; //Definimos tipo de actualizacion
	        	
	            // Obtener total del producto en tabla (con IVA)
		        float totalConIVA_Anterior = Float.parseFloat(modeloCarrito.getValueAt(i, 6).toString());
	            
	            //El nuevo total es igual a quitarle lo del producto y añadirle lo nuevo
	            total_venta -= totalConIVA_Anterior;
	            total_venta += totalConIVA;
	            
	        	//Si el producto ya esta en el carrito
		    	existe_en_carrito = true;
	            
		    	// Actualizar fila
		    	//CORRECION ERROR
	            modeloCarrito.setValueAt(CantidadIngresada, i, 4);
	            modeloCarrito.setValueAt(String.format("%.2f", totalSinIVA), i, 5);
	            modeloCarrito.setValueAt(String.format("%.2f", totalConIVA), i, 6);
	            break;
	        }
	    }
	    
	    if(!existe_en_carrito) {
	    	// Agregar producto al carrito si no esta en el
		    modeloCarrito.addRow(new Object[] {
		            id,
		            nombre,
		            marca,
		            String.format("%.2f", precio),
		            CantidadIngresada,
		            String.format("%.2f", totalSinIVA),
		            String.format("%.2f", totalConIVA)
		    });
		    
		    total_venta +=  totalConIVA; //Si es nuevo solo añadimos
	    }
	    
	    txtPorPagar.setText(String.format("$%.2f", total_venta)); //Actualizamos total

	    // Actualizar cantidad de productos en el carrito
	    lblCantidadRegistrosCarrito.setText("Productos en Carrito: " + modeloCarrito.getRowCount());

	    // Limpiar selección y cantidad
	    txtCantidad.setText((String) txtCantidad.getClientProperty("placeholder")); // Restaurar texto original
	    txtProductos.setText("");
	    tablaProductos.clearSelection();
	}
	
	public static void vaciarCarrito(JTable tablaCarrito, JTextField txtPorPagar, JLabel lblCantidadRegistrosCarrito) {
	    // Si el carrito está vacío
	    if (tablaCarrito.getRowCount() == 0) {
	        JOptionPane.showMessageDialog(null,
	            "Agregue productos al carrito.",
	            "ERROR. CARRITO ESTÁ VACÍO",
	            JOptionPane.ERROR_MESSAGE);
	        return;
	    }

	    // Confirmación de vaciado
	    int confirmar = JOptionPane.showConfirmDialog(null,
	        "¿Está seguro que desea vaciar el carrito de productos?",
	        "CONFIRMACIÓN DE VACIADO",
	        JOptionPane.YES_NO_OPTION,
	        JOptionPane.QUESTION_MESSAGE);

	    if (confirmar != JOptionPane.YES_OPTION) {
	        return;
	    }

	    // Vaciar el carrito
	    DefaultTableModel modelo = (DefaultTableModel) tablaCarrito.getModel();
	    modelo.setRowCount(0); // Limpiar tabla

	    txtPorPagar.setText((String) txtPorPagar.getClientProperty("placeholder")); // Restaurar texto original
	    lblCantidadRegistrosCarrito.setText((String) lblCantidadRegistrosCarrito.getClientProperty("placeholder")); // Restaurar etiqueta

	    // Reiniciar variables globales
	    CantidadEnCarrito = 0;
	    total_venta = 0;
	}
	
	public static void eliminarProductoCarrito(JTable tablaCarrito, JTextField txtPorPagar, JLabel lblCantidadRegistrosCarrito) {
		 DefaultTableModel modeloCarrito = (DefaultTableModel) tablaCarrito.getModel();
		 
		 	// Obtener filas seleccionadas
		    int[] filasSeleccionadas = tablaCarrito.getSelectedRows();
		    if (filasSeleccionadas.length == 0) {
		        JOptionPane.showMessageDialog(null, "Seleccione uno o más productos para eliminar del carrito.",
		                "ERROR. NO SE SELECCIONÓ DEL CARRITO", JOptionPane.ERROR_MESSAGE);
		        return;
		    }

		    // Confirmar con el usuario
		    int confirmacion = JOptionPane.showConfirmDialog(null,
		            "¿Está seguro que desea eliminar los productos seleccionados del carrito?",
		            "CONFIRMAR ELIMINACIÓN", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

		    if (confirmacion != JOptionPane.YES_OPTION) return;

		    // Eliminar productos desde la última fila seleccionada hacia la primera para evitar errores de índice
		    for (int i = filasSeleccionadas.length - 1; i >= 0; i--) {
		        int fila = filasSeleccionadas[i];

		        float productoPaga = Float.parseFloat(tablaCarrito.getValueAt(fila, 6).toString()); // Columna Total IVA
		        total_venta -= productoPaga;

		        modeloCarrito.removeRow(fila);
		    }

		    // Actualizar total a pagar y etiqueta
		    txtPorPagar.setText(String.format("$%.2f", total_venta));
		 // Actualizar cantidad de productos en el carrito
		    lblCantidadRegistrosCarrito.setText("Productos en Carrito: " + modeloCarrito.getRowCount());
	}
	
	public static void validarCantidadConStock(JTextField txtCantidad, JTable tablaProductos) {
	    String valorOriginal = (String) txtCantidad.getClientProperty("placeholder"); // Texto original
	    
	    int cantidadIngresada;

	    // Si la caja está en su valor original
	    if (txtCantidad.getText().equals(valorOriginal)) {
	        cantidadIngresada = 0;
	    }

	    // Si no hay producto seleccionado
	    if (tablaProductos.getSelectedRow() == -1) {
	        txtCantidad.setText(valorOriginal);
	        JOptionPane.showMessageDialog(null, "Seleccione un registro antes de ingresar la cantidad de productos a vender.",
	                "ERROR. NO SE HA SELECCIONADO PRODUCTO PARA VENDER", JOptionPane.ERROR_MESSAGE);
	        return;
	    }
	    
	    cantidadIngresada = Integer.parseInt(txtCantidad.getText()); //Convertimos a entero
	    
	    // Obtener stock del producto seleccionado
	    int fila = tablaProductos.getSelectedRow();
	    int cantidadEnStockSeleccionado = Integer.parseInt(tablaProductos.getValueAt(fila, 4).toString());


	    if (cantidadIngresada > cantidadEnStockSeleccionado) {
	        JOptionPane.showMessageDialog(null, "La Cantidad a Vender no debe ser mayor \nque la cantidad de ejemplares en stock: "
	                + cantidadEnStockSeleccionado + ".", "ERROR. CANTIDAD A VENDER SOBREPASA STOCK", JOptionPane.ERROR_MESSAGE);
	        txtCantidad.setText(valorOriginal);
	    }
	}
	
	public static void validarCantidadConStockTablaCarrito(int FilaEditar, Object ValorId, 
			Object valorCantidad, int Anterior, JTable tablaCarrito, JTextField txtPorPagar) {    
	    int cantidadIngresada = Integer.parseInt(valorCantidad.toString());
	    int idProducto = Integer.parseInt(ValorId.toString());
	    int cantidadEnStockSeleccionado = 0;
	    
	    if (cantidadIngresada <= 0) {
	        JOptionPane.showMessageDialog(null, "Cantidad inválida. Ingrese un número mayor a cero.",
	                "ERROR. CANTIDAD INVÁLIDA", JOptionPane.ERROR_MESSAGE);
	        tablaCarrito.setValueAt(Anterior, FilaEditar, 4);
	        return;
	    }
	    
	    Conexion.abrirConexion();
		PreparedStatement sentencia = null;
		ResultSet Rs = null;
		try {
			sentencia = Conexion.conn.prepareStatement("SELECT Cantidad_en_Stock FROM productos WHERE Id = ?");
			sentencia.setInt(1, idProducto); //Parametro de Id
			Rs = sentencia.executeQuery();	
			if (Rs.next()) cantidadEnStockSeleccionado = Rs.getInt("Cantidad_en_Stock"); //Obtenemos cantidad
            Conexion.cerrarConexion();
		}catch(SQLException e){}
		
	    if (cantidadIngresada > cantidadEnStockSeleccionado) {
	        JOptionPane.showMessageDialog(null, "La Cantidad a Vender no debe ser mayor \nque la cantidad de ejemplares en stock: "
	                + cantidadEnStockSeleccionado + ".", "ERROR. CANTIDAD A VENDER SOBREPASA STOCK", JOptionPane.ERROR_MESSAGE);
	        
	     // Restaurar valor anterior en tabla (columna 4 = "Cantidad")
	        tablaCarrito.setValueAt(Anterior, FilaEditar, 4);
	        return;
	    }
	    
	    // Obtener total del producto en tabla (con IVA)
        float totalConIVA_Anterior = Float.parseFloat(tablaCarrito.getValueAt(FilaEditar, 6).toString().toString());
        
	    //JOptionPane.showMessageDialog(null, "se edita fila "+ FilaEditar + " a " + Cantidadin,"DEBUG", JOptionPane.ERROR_MESSAGE);
	    tablaCarrito.setValueAt(cantidadIngresada, FilaEditar, 4);
	    float Total = Float.parseFloat(tablaCarrito.getValueAt(FilaEditar, 3).toString())*cantidadIngresada;
	    float TotalIVA = Total*1.16f;
	    tablaCarrito.setValueAt(Total, FilaEditar, 5);
	    tablaCarrito.setValueAt(String.format("%.2f", TotalIVA), FilaEditar, 6);
	  
        
        //El nuevo total es igual a quitarle lo del producto y añadirle lo nuevo
        total_venta -= totalConIVA_Anterior;
        total_venta += TotalIVA;
        
        txtPorPagar.setText(String.format("$%.2f", total_venta)); //Actualizamos total
	}
	
	public static void limpiarPorRegex(JTextField txt, String regex) {
		txt.setText(txt.getText().replaceAll("[^" + regex + "]", ""));
	}
	
	public static boolean hacerCobro(JTextField txtDineroRecibo) {
	    seCompletoOperacion = false; // Booleano empieza en falso

	    // Si la caja de Dinero Recibo está vacía, mensaje de error
	    if (txtDineroRecibo.getText().trim().isEmpty()) {
	        JOptionPane.showMessageDialog(null,
	                "Error. Ingrese dinero que se recibe.",
	                "ERROR. CAMPO RECIBO DINERO VACÍO",
	                JOptionPane.ERROR_MESSAGE);
	        return false;
	    }

	    float reciboDinero = 0;
	    reciboDinero = Math.round(Float.parseFloat(txtDineroRecibo.getText()) * 100) / 100.0f; // Redondeo a 2 decimales

	    // Si el dinero es menor a la cantidad que se tiene que pagar
	    if (reciboDinero < total_venta) {
	        JOptionPane.showMessageDialog(null,
	                "Error. Dinero recibido no es suficiente.",
	                "ERROR. DINERO INSUFICIENTE",
	                JOptionPane.ERROR_MESSAGE);
	        return false;
	    }

	    // Si el cambio es mayor o igual a 1000
	    if ((reciboDinero - total_venta) >= 1000) {
	        JOptionPane.showMessageDialog(null,
	                "El cambio es mayor a lo posible. Verifique el monto ingresado. El cambio no puede llegar a 1000 pesos.",
	                "ERROR. CAMBIO EXCEDIÓ LO PERMITIDO",
	                JOptionPane.WARNING_MESSAGE);
	        return false;
	    }

	    // Mostramos el cambio
	    float cambio = reciboDinero - total_venta;
	    JOptionPane.showMessageDialog(null,
	            "Cambio: $" + String.format("%.2f", cambio),
	            "CAMBIO A ENTREGAR",
	            JOptionPane.INFORMATION_MESSAGE);

	    return true;
	}
	
	
	public static boolean venderProductos(Ventas_visual ventas, JTable tablaCarrito, JTextField txtPorPagar, JLabel lblCantidadCarrito) {
	    if (tablaCarrito.getRowCount() == 0) {
	        JOptionPane.showMessageDialog(null, "Agregue productos al carrito para poder hacer venta.",
	                "ERROR. CARRITO ESTÁ VACÍO", JOptionPane.ERROR_MESSAGE);
	        return false;
	    }

	    // Mostrar el diálogo de cobro
	    Cobro_visual cobro = new Cobro_visual(ventas);
	    cobro.setVisible(true);
	    cobro.setLocationRelativeTo(null);
	    
	    // Si el usuario canceló o no se completó el pago
	    if (!seCompletoOperacion) {
	        return false;
	    }
	     
	    //Bloque try-catch para hacer la venta
	    try {
	    	DefaultTableModel modelo = (DefaultTableModel) tablaCarrito.getModel();
	        PreparedStatement insertVenta = null;
	        PreparedStatement actualizarStock = null;
	        Conexion.abrirConexion();


	        // Inserción en la tabla de ventas
	        String sqlInsert = "INSERT INTO Ventas (Id_Usuario, Usuario, Tipo, Id_Producto, Nombre_Producto, Precio_Producto, Cantidad_Producto, Total, Total_IVA, Fecha_Hora) " +
	              "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	        insertVenta = Conexion.conn.prepareStatement(sqlInsert);

	            // Actualización del stock
	            String sqlUpdate = "UPDATE Productos SET Cantidad_en_Stock = Cantidad_en_Stock - ? WHERE Id = ?";
	            actualizarStock = Conexion.conn.prepareStatement(sqlUpdate);
	            
	            for (int i = 0; i < tablaCarrito.getRowCount(); i++) {
	                int idProducto = Integer.parseInt(tablaCarrito.getValueAt(i, 0).toString());
	                String nombreProducto = tablaCarrito.getValueAt(i, 1).toString();
	                double precio = Double.parseDouble(tablaCarrito.getValueAt(i, 3).toString());
	                int cantidad = Integer.parseInt(tablaCarrito.getValueAt(i, 4).toString());
	                double total = Double.parseDouble(tablaCarrito.getValueAt(i, 5).toString());
	                double totalIVA = total * 1.16f;
	                
	                // Insertar venta
	                insertVenta.setInt(1, 5);
	                insertVenta.setString(2, "Mateo");
	                insertVenta.setString(3, "Prueba");
	                insertVenta.setInt(4, idProducto);
	                insertVenta.setString(5, nombreProducto);
	                insertVenta.setDouble(6, precio);
	                insertVenta.setInt(7, cantidad);
	                insertVenta.setDouble(8, total);
	                insertVenta.setDouble(9, totalIVA);
	                insertVenta.setTimestamp(10, new Timestamp(System.currentTimeMillis()));
	                insertVenta.executeUpdate(); // ejecución inmediata
	                


	                // Actualizar stock
	                actualizarStock.setInt(1, cantidad);
	                actualizarStock.setInt(2, idProducto);
	                actualizarStock.executeUpdate(); // ejecución inmediata        
	            }

	            // Limpiar tabla del carrito
	            modelo.setRowCount(0);

	            txtPorPagar.setText((String) txtPorPagar.getClientProperty("placeholder")); // Restaurar texto original
	    	    lblCantidadCarrito.setText((String) lblCantidadCarrito.getClientProperty("placeholder")); // Restaurar etiqueta
	    	    
	            JOptionPane.showMessageDialog(null, "La venta ha sido completada.",
	                    "VENTA EXITOSA DE PRODUCTOS", JOptionPane.INFORMATION_MESSAGE);
	            
	            Conexion.cerrarConexion(); //Cerramos conexion
	            insertVenta.close(); //Cerramos objeto
	            actualizarStock.close(); //Cerramos objeto
	            
	            hayVentas = true;
	            return true;

	        } catch (SQLException ex) {return false;}

	}
	
}

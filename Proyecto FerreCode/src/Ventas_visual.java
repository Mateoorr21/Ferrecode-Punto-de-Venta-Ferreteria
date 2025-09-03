import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class Ventas_visual extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPanel = new JPanel(new GridBagLayout());
	GridBagConstraints gbc2 = new GridBagConstraints();
	GridBagConstraints gbc = new GridBagConstraints();
	private JLabel lblIconoUsuarios;
	private JTextField txtbuscar;

	private JTable tablaProductos;
	private JTable tablaCarrito;
	private JTextField Txt_Producto;
	private JTextField Txt_Cantidad;
	private JTextField Txt_Total;
	private Object Anterior; //Para guardar la cantidad de vendidos de un producto seleccionado en carrito
	private boolean actualizando = false; //Flag para que al usar SetValue no se vuelva a validar
	private boolean actualizandoTipo = false; //Flag para que al agregar el mismo producto se complete actualizacion
	private boolean edicionManual = true; //Para evaluar si se edito el campo cantidad de la tabla productos
	//Comienza en true para la primera vez que se edite manualmente el campo

	public Ventas_visual(JFrame anterior) {
		Ventas_funcion.Tipo_Actualizacion = ""; //No hay tipo de actualizacion todavia
		Ventas_funcion.total_venta = 0; //Reiniciamos venta
		setBounds(anterior.getBounds());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Sistema Ferretería FerreCode");
		ImageIcon IconoFrameLogo = new ImageIcon(getClass().getResource("Imagenes_Sistema/IconoInicio.png"));
		setIconImage(IconoFrameLogo.getImage()); //Icono de barra de titulo
		gbc2.fill = GridBagConstraints.HORIZONTAL;
		gbc2.weightx = 1.0;
		gbc2.weighty = 0;
		gbc2.insets = new Insets(5, 5, 5, 5);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		ImageIcon timgProductos = new ImageIcon(getClass().getResource("Imagenes_Sistema/iconoProductos.png"));
		Image imgProductosr = timgProductos.getImage().getScaledInstance(250, 70, Image.SCALE_SMOOTH);
		ImageIcon imgProductos = new ImageIcon(imgProductosr);

		// Imagen opaca (para cuando se presiona)
		ImageIcon timgProductosOpaco = new ImageIcon(getClass().getResource("Imagenes_Sistema/iconoProductosO.png"));
		Image imgProductosOpacor = timgProductosOpaco.getImage().getScaledInstance(250, 70, Image.SCALE_SMOOTH);
		ImageIcon imgProductosOpaco = new ImageIcon(imgProductosOpacor);
		
		ImageIcon timgUsuarios = new ImageIcon(getClass().getResource("Imagenes_Sistema/iconoUsuarios.png"));
		Image imgUsuariosr = timgUsuarios.getImage().getScaledInstance(250, 70, Image.SCALE_SMOOTH);
		ImageIcon imgUsuarios = new ImageIcon(imgUsuariosr);

		// Imagen opaca (para cuando se presiona)
		ImageIcon timgUsuariosOpaco = new ImageIcon(getClass().getResource("Imagenes_Sistema/iconoUsuariosO.png"));
		Image imgUsuariosOpacor = timgUsuariosOpaco.getImage().getScaledInstance(250, 70, Image.SCALE_SMOOTH);
		ImageIcon imgUsuariosOpaco = new ImageIcon(imgUsuariosOpacor);
		
		ImageIcon timgInventario = new ImageIcon(getClass().getResource("Imagenes_Sistema/iconoInventario.png"));
		Image imgInventarior = timgInventario.getImage().getScaledInstance(250, 70, Image.SCALE_SMOOTH);
		ImageIcon imgInventario = new ImageIcon(imgInventarior);

		// Imagen opaca (para cuando se presiona)
		ImageIcon timgInventarioOpaco = new ImageIcon(getClass().getResource("Imagenes_Sistema/iconoInventarioO.png"));
		Image imgInventarioOpacor = timgInventarioOpaco.getImage().getScaledInstance(250, 70, Image.SCALE_SMOOTH);
		ImageIcon imgInventarioOpaco = new ImageIcon(imgInventarioOpacor);
		
//		ImageIcon timgVentas = new ImageIcon(getClass().getResource("Imagenes_Sistema/iconoVender.png"));
//		Image imgVentasr = timgVentas.getImage().getScaledInstance(250, 70, Image.SCALE_SMOOTH);
//		ImageIcon imgVentas = new ImageIcon(imgVentasr);

		// Imagen opaca (para cuando se presiona)
		ImageIcon timgVentasOpaco = new ImageIcon(getClass().getResource("Imagenes_Sistema/iconoVenderO.png"));
		Image imgVentasOpacor = timgVentasOpaco.getImage().getScaledInstance(250, 70, Image.SCALE_SMOOTH);
		ImageIcon imgVentasOpaco = new ImageIcon(imgVentasOpacor);
		
		ImageIcon timgCerrar = new ImageIcon(getClass().getResource("Imagenes_Sistema/iconoCerrar.png"));
		Image imgCerrarr = timgCerrar.getImage().getScaledInstance(250, 70, Image.SCALE_SMOOTH);
		ImageIcon imgCerrar = new ImageIcon(imgCerrarr);

		// Imagen opaca (para cuando se presiona)
		ImageIcon timgCerrarOpaco = new ImageIcon(getClass().getResource("Imagenes_Sistema/iconoCerrarO.png"));
		Image imgCerrarOpacor = timgCerrarOpaco.getImage().getScaledInstance(250, 70, Image.SCALE_SMOOTH);
		ImageIcon imgCerrarOpaco = new ImageIcon(imgCerrarOpacor);		
//		************************aqui pones la ruta d ela imagen de usuarios************
		ImageIcon timgIconoUsuario = new ImageIcon(getClass().getResource("Imagenes_Sistema/Usuario2.png"));
		Image imgIconoUsuarioEscalado = timgIconoUsuario.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
		ImageIcon imgIconoUsuario = new ImageIcon(imgIconoUsuarioEscalado);
		
		JPanel titulo = new JPanel(new GridBagLayout());
		titulo.setBackground(new Color(2, 139, 183));
		gbc = (GridBagConstraints) gbc2.clone();
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.weighty = 0;
		gbc.insets = new Insets(0,0,0,0);
		gbc.fill = GridBagConstraints.BOTH;
		contentPanel.add(titulo, gbc);
		
		JPanel info = new JPanel(new GridBagLayout());
		info.setBackground(new Color(28, 90, 121));
		gbc = (GridBagConstraints) gbc2.clone();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weighty = 0;
		gbc.weightx = 0;
		gbc.insets = new Insets(0,0,0,0);
		gbc.fill = GridBagConstraints.BOTH;
		contentPanel.add(info, gbc);
		
		JPanel contenido = new JPanel(new GridBagLayout());
		contenido.setBackground(new Color(241, 241, 233));
		gbc = (GridBagConstraints) gbc2.clone();
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.weighty = 3.0;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(0,0,0,0);
		contentPanel.add(contenido, gbc);
		
		JPanel navbar =  new JPanel(new GridBagLayout());
		navbar.setBackground(new Color(50, 51, 76));
		gbc = (GridBagConstraints) gbc2.clone();
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weightx = 0;
		gbc.weighty = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(0,0,0,0);
		contentPanel.add(navbar, gbc);
		
		ImageIcon imgtitulo = new ImageIcon(getClass().getResource("Imagenes_Sistema/lbltitulo.png"));
		Image imgtitulor = imgtitulo.getImage().getScaledInstance(750, 90, Image.SCALE_SMOOTH);
		imgtitulo = new ImageIcon(imgtitulor);
		JLabel lblTituloMenu = new JLabel(imgtitulo);
		lblTituloMenu.setForeground(new Color(255, 255, 255));
		lblTituloMenu.setFont(new Font("Dubai Light", Font.BOLD, 50));
		gbc = (GridBagConstraints) gbc2.clone();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(0,0,0,0);
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.NONE;
		titulo.add(lblTituloMenu, gbc);		
		
		ImageIcon lblxi = new ImageIcon(getClass().getResource("Imagenes_Sistema/boton X Blanco.png"));
		Image lblxir= lblxi.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
		lblxi = new ImageIcon(lblxir);
		JButton btncerrar = new JButton(lblxi);
		btncerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Conexion.cerrarSesion(Ventas_visual.this);
			}
		});
		gbc = (GridBagConstraints) gbc2.clone();
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.weightx = 0;
		btncerrar.setBounds(427, 0, 48, 48);
		btncerrar.setOpaque(false);
		btncerrar.setContentAreaFilled(false);
		btncerrar.setBorderPainted(false);
		btncerrar.setFocusPainted(false);
		btncerrar.setMargin(new Insets(0, 0, 0, 20));
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.NONE;
		titulo.add(btncerrar, gbc);	
		
		gbc = (GridBagConstraints) gbc2.clone();
		lblIconoUsuarios = new JLabel(imgIconoUsuario);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 0;
		gbc.weighty = 0;
		gbc.gridheight = 3;
		gbc.fill = GridBagConstraints.NONE;
		info.add(lblIconoUsuarios, gbc);
		
		JLabel lblnombreUsuario = new JLabel(Conexion.Usuario);
		lblnombreUsuario.setForeground(new Color(255, 255, 255));
		lblnombreUsuario.setFont(new Font("Dubai Light", Font.BOLD, 18));
		gbc = (GridBagConstraints) gbc2.clone();
		gbc.gridx = 1;
		gbc.gridy = 0;
		info.add(lblnombreUsuario, gbc);	
		
		JLabel lblnombrePersona = new JLabel(Conexion.NombreCompleto);
		lblnombrePersona.setForeground(new Color(255, 255, 255));
		lblnombrePersona.setFont(new Font("Dubai Light", Font.PLAIN, 15));
		gbc = (GridBagConstraints) gbc2.clone();
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.insets = new Insets(0,5,0,0);
		info.add(lblnombrePersona, gbc);	
		
		JLabel lblnombreTipo = new JLabel("(" + Conexion.TipoUsuario + ")");
		lblnombreTipo.setForeground(new Color(255, 255, 255));
		lblnombreTipo.setFont(new Font("Dubai Light", Font.ITALIC, 15));
		gbc = (GridBagConstraints) gbc2.clone();
		gbc.gridx = 1;
		gbc.gridy = 2;
		info.add(lblnombreTipo, gbc);
		
		JButton BtnProductos_Menu = new JButton(imgProductos);
		BtnProductos_Menu.setBackground(new Color(50, 51, 76));
		BtnProductos_Menu.setFont(new Font("Tahoma", Font.PLAIN, 1));
		gbc = (GridBagConstraints) gbc2.clone();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 0;
		gbc.weighty = 0;
		gbc.insets = new Insets(0,0,0,0);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		navbar.add(BtnProductos_Menu, gbc);
		if(Conexion.TienePermiso == false) BtnProductos_Menu.setEnabled(false);
		
		JButton BtnVender_Menu = new JButton(imgVentasOpaco);
		BtnVender_Menu.setBackground(new Color(50, 51, 76));
		BtnVender_Menu.setFont(new Font("Tahoma", Font.PLAIN, 20));
		gbc = (GridBagConstraints) gbc2.clone();
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weighty = 0;
		gbc.weightx = 0;
		gbc.insets = new Insets(0,0,0,0);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		navbar.add(BtnVender_Menu, gbc);
		
		JButton BtnInventario_Menu = new JButton(imgInventario);
		BtnInventario_Menu.setBackground(new Color(50, 51, 76));
		BtnInventario_Menu.setFont(new Font("Tahoma", Font.PLAIN, 20));
		gbc = (GridBagConstraints) gbc2.clone();
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.weighty = 0;
		gbc.weightx = 0;
		gbc.insets = new Insets(0,0,0,0);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		navbar.add(BtnInventario_Menu, gbc);
		
		JButton BtnUsuarios_Menu = new JButton(imgUsuarios);
		BtnUsuarios_Menu.setBackground(new Color(50, 51, 76));
		BtnUsuarios_Menu.setFont(new Font("Tahoma", Font.PLAIN, 20));
		gbc = (GridBagConstraints) gbc2.clone();
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.weighty = 0;
		gbc.insets = new Insets(0,0,0,0);
		navbar.add(BtnUsuarios_Menu, gbc);
		if(Conexion.TienePermiso == false) BtnUsuarios_Menu.setEnabled(false);
		
		JButton Cerrar_Sesion = new JButton(imgCerrar);
		Cerrar_Sesion.setBackground(new Color(50, 51, 76));
		Cerrar_Sesion.setFont(new Font("Tahoma", Font.PLAIN, 20));
		gbc = (GridBagConstraints) gbc2.clone();
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.weighty = 1;
		gbc.insets = new Insets(0,0,0,0);
		gbc.anchor = GridBagConstraints.SOUTH;
		navbar.add(Cerrar_Sesion, gbc);
//		
		for (Component comp : navbar.getComponents()) {
		    if (comp instanceof JButton boton) {
		        boton.setOpaque(false);
		        boton.setContentAreaFilled(false);
		        boton.setBorderPainted(false);
		        boton.setFocusPainted(false);
		        boton.setMargin(new Insets(0, 0, 0, 0));
		    }
		}
		//apartado para imagenes dinamicas al pasar el mouse*******************
		BtnProductos_Menu.addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	if(Conexion.TienePermiso == false) return;
		        BtnProductos_Menu.setIcon(imgProductosOpaco); // imagen opaca o resaltada
		    }

		    @Override
		    public void mouseExited(java.awt.event.MouseEvent evt) {
		        BtnProductos_Menu.setIcon(imgProductos); // imagen normal
		    }
		});
		BtnInventario_Menu.addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	BtnInventario_Menu.setIcon(imgInventarioOpaco); // imagen opaca o resaltada
		    }

		    @Override
		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	BtnInventario_Menu.setIcon(imgInventario); // imagen normal
		    }
		});
		BtnUsuarios_Menu.addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	if(Conexion.TienePermiso == false) return;
		    	BtnUsuarios_Menu.setIcon(imgUsuariosOpaco); // imagen opaca o resaltada
		    }

		    @Override
		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	BtnUsuarios_Menu.setIcon(imgUsuarios); // imagen normal
		    }
		});
		Cerrar_Sesion.addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	Cerrar_Sesion.setIcon(imgCerrarOpaco); // imagen opaca o resaltada
		    }

		    @Override
		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	Cerrar_Sesion.setIcon(imgCerrar); // imagen normal
		    }
		});
		
		BtnProductos_Menu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Productos productos = new Productos(Ventas_visual.this);
				productos.setVisible(true);
				dispose();
			}
		});
		BtnUsuarios_Menu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				usuarios usuarios = new usuarios(Ventas_visual.this);
				usuarios.setVisible(true);
				dispose();
			}
		});
		
		BtnInventario_Menu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Inventario inventario = new Inventario(Ventas_visual.this);
				inventario.setVisible(true);
				dispose();
			}
		});
		
		Cerrar_Sesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Conexion.cerrarSesion(Ventas_visual.this); //Cerramos sesion
			}
		});
		
		JLabel lblTitulo = new JLabel("Vender Productos");
		lblTitulo.setFont(new Font("Dubai Medium", Font.BOLD, 35)); // Fuente moderna y grande
		lblTitulo.setForeground(new Color(0, 0, 0)); // Azul atractivo
		gbc = (GridBagConstraints) gbc2.clone();
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridwidth = 4;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.CENTER;
		contenido.add(lblTitulo, gbc);
		
		JLabel lblProductosEncontrados = new JLabel("Productos Encontrados: 0");
		lblProductosEncontrados.setFont(new Font("Dubai Light", Font.BOLD, 16));
		gbc = (GridBagConstraints) gbc2.clone();
		gbc.gridx = 0;
		gbc.gridy = 9;
		contenido.add(lblProductosEncontrados, gbc);
		
		JLabel lblMontoAPagar = new JLabel("Monto a Pagar IVA (MXN)");
		lblMontoAPagar.setFont(new Font("Dubai", Font.PLAIN, 18));
		gbc = (GridBagConstraints) gbc2.clone();
		gbc.gridx = 2;
		gbc.gridy = 4;
		gbc.gridwidth = 3;
		contenido.add(lblMontoAPagar, gbc);
		
		JLabel lblNombreProducto = new JLabel("Producto a Vender");
		lblNombreProducto.setFont(new Font("Dubai", Font.PLAIN, 18));
		gbc = (GridBagConstraints) gbc2.clone();
		gbc.gridx = 2;
		gbc.gridy = 6;
		gbc.gridwidth = 3;
		contenido.add(lblNombreProducto, gbc);
		
		JLabel lblCantidadAVender = new JLabel("Cantidad a Vender:");
		lblCantidadAVender.setFont(new Font("Dubai", Font.PLAIN, 18));
		gbc = (GridBagConstraints) gbc2.clone();
		gbc.gridx = 2;
		gbc.gridy = 8;
		gbc.gridwidth = 3;
		contenido.add(lblCantidadAVender, gbc);

		JLabel lblProductosEnCarrito = new JLabel("Productos en Carrito: 0");
		lblProductosEnCarrito.putClientProperty("placeholder", "Productos en Carrito: 0");
		lblProductosEnCarrito.setFont(new Font("Dubai Light", Font.BOLD, 16));
		gbc = (GridBagConstraints) gbc2.clone();
		gbc.gridx = 5;
		gbc.gridy = 4;
		contenido.add(lblProductosEnCarrito, gbc);

		txtbuscar = new JFormattedTextField();
		txtbuscar.setToolTipText("Buscar Producto");
		txtbuscar.setFont(new Font("Dubai Light", Font.PLAIN, 20));
		gbc = (GridBagConstraints) gbc2.clone();
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		contenido.add(txtbuscar, gbc);
		txtbuscar.addKeyListener(new KeyAdapter() {
		    @Override
		    public void keyReleased(KeyEvent e) {
		    	 int code = e.getKeyCode();

		         // Permitir teclas especiales
		         if (code == KeyEvent.VK_ENTER ||
		             code == KeyEvent.VK_BACK_SPACE ||
		             code == KeyEvent.VK_TAB ||
		             code == KeyEvent.VK_LEFT ||
		             code == KeyEvent.VK_RIGHT) {
		             return;
		         }
		         
		         //Solo permitimos esos caracteres especiales
		         Ventas_funcion.limpiarPorRegex(txtbuscar, "a-zA-ZáéíóúÁÉÍÓÚñÑ0-9 .\"\\-/#°%+");
		    }
		});
//		txtbuscar.setColumns(10);
		
		Txt_Total = new JTextField();
		Txt_Total.setFont(new Font("Dubai Light", Font.PLAIN, 16));
		Txt_Total.setText("$0.00");
		Txt_Total.setEditable(false);
//		Txt_Total.setColumns(10);
		gbc = (GridBagConstraints) gbc2.clone();
		gbc.gridx = 2;
		gbc.gridy = 5;
		gbc.gridwidth = 3;
		Txt_Total.putClientProperty("placeholder", "$0.00");
		contenido.add(Txt_Total, gbc);
		
		Txt_Producto = new JTextField();
		Txt_Producto.setFont(new Font("Dubai Light", Font.PLAIN, 16));
		Txt_Producto.setEditable(false);
		gbc = (GridBagConstraints) gbc2.clone();
		gbc.gridx = 2;
		gbc.gridy = 7;
		gbc.gridwidth = 3;
		contenido.add(Txt_Producto, gbc);
//		Txt_Producto.setColumns(10);
		
		Txt_Cantidad = new JTextField();
		Txt_Cantidad.setText("0");
		Txt_Cantidad.setFont(new Font("Dubai Light", Font.PLAIN, 18));
//		Txt_Cantidad.setColumns(10);
		gbc = (GridBagConstraints) gbc2.clone();
		gbc.gridx = 2;
		gbc.gridy = 9;
		Txt_Cantidad.putClientProperty("placeholder", "0");
		contenido.add(Txt_Cantidad, gbc);
		
		Txt_Cantidad.addKeyListener(new KeyAdapter() {
		    @Override
		    public void keyReleased(KeyEvent e) {
		    	int code = e.getKeyCode();
		    	// Permitir teclas especiales
		         if (code == KeyEvent.VK_BACK_SPACE ||
		             code == KeyEvent.VK_TAB ||
		             code == KeyEvent.VK_LEFT ||
		             code == KeyEvent.VK_RIGHT) {
		        	 Ventas_funcion.validarCantidadConStock(Txt_Cantidad, tablaProductos);
		         }
		         
		         else {
		        	 Ventas_funcion.limpiarPorRegex(Txt_Cantidad, "0-9"); 
		        	 Ventas_funcion.validarCantidadConStock(Txt_Cantidad, tablaProductos);
		         }
		    }
		});
		
		JButton Btn_Consultar_Productos = new JButton("Consultar");
		Btn_Consultar_Productos.setForeground(new Color(255, 255, 255));
		Btn_Consultar_Productos.setBackground(new Color(0, 63, 125));
		Btn_Consultar_Productos.setFont(new Font("Dubai Light", Font.PLAIN, 20));
		gbc = (GridBagConstraints) gbc2.clone();
		gbc.gridx = 0;
		gbc.gridy = 2;
		contenido.add(Btn_Consultar_Productos, gbc);

		JButton Btn_ConsultarTodos_Productos = new JButton("Consultar Todos");
		Btn_ConsultarTodos_Productos.setForeground(new Color(255, 255, 255));
		Btn_ConsultarTodos_Productos.setBackground(new Color(0, 63, 125));
		Btn_ConsultarTodos_Productos.setFont(new Font("Dubai Light", Font.PLAIN, 20));
		gbc = (GridBagConstraints) gbc2.clone();
		gbc.gridx = 1;
		gbc.gridy = 2;
		contenido.add(Btn_ConsultarTodos_Productos, gbc);
		
		JButton Btn_Vender = new JButton("Realizar Venta");
		Btn_Vender.setBackground(new Color(28, 90, 121));
		Btn_Vender.setForeground(new Color(255, 255, 255));
		Btn_Vender.setFont(new Font("Dubai Light", Font.PLAIN, 45));
		gbc = (GridBagConstraints) gbc2.clone();
		gbc.gridx = 2;
		gbc.gridy = 1;
		gbc.gridwidth = 4;
		gbc.gridheight = 2;
		contenido.add(Btn_Vender, gbc);
		
		JButton Btn_Mas = new JButton("+");
		Btn_Mas.setForeground(new Color(255, 255, 255));
		Btn_Mas.setBackground(new Color(0, 149, 74));
		Btn_Mas.setFont(new Font("Tahome", Font.BOLD, 16));
		gbc = (GridBagConstraints) gbc2.clone();
		gbc.gridx = 3;
		gbc.gridy = 9;
		contenido.add(Btn_Mas, gbc);
		
		JButton Btn_Menos = new JButton("-");
		Btn_Menos.setForeground(new Color(255, 255, 255));
		Btn_Menos.setBackground(new Color(174, 0, 0));
		Btn_Menos.setFont(new Font("Tahoma", Font.BOLD, 16));
		gbc = (GridBagConstraints) gbc2.clone();
		gbc.gridx = 4;
		gbc.gridy = 9;
		contenido.add(Btn_Menos, gbc);
		
		JButton Btn_Agregar_Carrito = new JButton("Agregar a Carrito");
		Btn_Agregar_Carrito.setForeground(new Color(255, 255, 255));
		Btn_Agregar_Carrito.setBackground(new Color(0, 63, 125));
		Btn_Agregar_Carrito.setFont(new Font("Dubai Light", Font.PLAIN, 18));
		gbc = (GridBagConstraints) gbc2.clone();
		gbc.gridx = 5;
		gbc.gridy = 5;
		contenido.add(Btn_Agregar_Carrito, gbc );
		
		JButton Btn_Eliminar_Carrito = new JButton("Eliminar de Carrito");
		Btn_Eliminar_Carrito.setForeground(new Color(255, 255, 255));
		Btn_Eliminar_Carrito.setBackground(new Color(0, 63, 125));
		Btn_Eliminar_Carrito.setFont(new Font("Dubai Light", Font.PLAIN, 18));
		gbc = (GridBagConstraints) gbc2.clone();
		gbc.gridx = 5;
		gbc.gridy = 6;
		contenido.add(Btn_Eliminar_Carrito, gbc);
		
		JButton Btn_Vaciar_Carrito = new JButton("Vaciar Carrito");
		Btn_Vaciar_Carrito.setBackground(new Color(0, 63, 125));
		Btn_Vaciar_Carrito.setForeground(new Color(255, 255, 255));
		Btn_Vaciar_Carrito.setFont(new Font("Dubai Light", Font.PLAIN, 18));
		gbc = (GridBagConstraints) gbc2.clone();
		gbc.gridx = 5;
		gbc.gridy = 7;
		contenido.add(Btn_Vaciar_Carrito, gbc);
		
		JScrollPane scrollPane = new JScrollPane();
		gbc = (GridBagConstraints) gbc2.clone();
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridheight = 6;
		gbc.gridwidth = 2;
		gbc.weighty = 1;
		gbc.fill = GridBagConstraints.BOTH;
		contenido.add(scrollPane, gbc);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		gbc = (GridBagConstraints) gbc2.clone();
		gbc.gridx = 2;
		gbc.gridy = 3;
		gbc.gridwidth = 5;
		gbc.weighty = 1;
		gbc.fill = GridBagConstraints.BOTH;
		contenido.add(scrollPane_1, gbc);
		
		DefaultTableModel modeloProductos = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;

			@Override
		    public boolean isCellEditable(int row, int column) {
		        return false; // Ninguna celda editable
		    }
		};
		modeloProductos.addColumn("No. Inv");
		modeloProductos.addColumn("Nombre");
		modeloProductos.addColumn("Marca");
		modeloProductos.addColumn("Precio");
		modeloProductos.addColumn("Cantidad");
		
		
		tablaProductos = new JTable(modeloProductos);
		tablaProductos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //Solo elegir uno
		tablaProductos.getColumnModel().getColumn(0).setMaxWidth(0); 
		tablaProductos.getColumnModel().getColumn(0).setMinWidth(0); 
		tablaProductos.getColumnModel().getColumn(0).setPreferredWidth(0); //Columna de No Inv oculta
		tablaProductos.getColumnModel().getColumn(1).setPreferredWidth(140); //Ancho para nombre
		tablaProductos.getColumnModel().getColumn(2).setPreferredWidth(60); 
		tablaProductos.getColumnModel().getColumn(3).setPreferredWidth(20); 
		tablaProductos.getColumnModel().getColumn(4).setPreferredWidth(30); 
		tablaProductos.getTableHeader().setFont(new Font("Dubai", Font.BOLD, 14));
		tablaProductos.setFont(new Font("Dubai", Font.PLAIN, 14));
		tablaProductos.setRowHeight(22);
		scrollPane.setViewportView(tablaProductos);
		Ventas_funcion.cargarProductos(tablaProductos);
		lblProductosEncontrados.setText("Productos Encontrados: " + tablaProductos.getRowCount());
		
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
			private static final long serialVersionUID = 1L;
			@Override
		    public Component getTableCellRendererComponent(JTable table, Object value,
		            boolean isSelected, boolean hasFocus, int row, int column) {
		        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);		        
		        if (isSelected) {
		            c.setBackground(new Color(173, 216, 230)); // color cuando está seleccionado (puedes ajustar)
		        } else {
		            c.setBackground(new Color(230, 245, 255)); // azul muy claro
		        }		        
		        return c;
		    }
		};

		// Aplicar el renderer a todas las columnas
		for (int i = 0; i < tablaProductos.getColumnCount(); i++) {
		    tablaProductos.getColumnModel().getColumn(i).setCellRenderer(renderer);
		}
		
		// Agregar listener para que actualice el Txt_Producto con el nombre
		tablaProductos.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
		    public void valueChanged(ListSelectionEvent e) {
		        // Evitar que se dispare dos veces por cada clic
		        if (!e.getValueIsAdjusting()) {
		            int filaSeleccionada = tablaProductos.getSelectedRow();
		            if (filaSeleccionada != -1) {
		                String nombreProducto = tablaProductos.getValueAt(filaSeleccionada, 1).toString(); // Columna 1 es el nombre
		                Txt_Producto.setText(nombreProducto);
		            }
		            else {
		            	lblProductosEncontrados.setText("Productos Encontrados: " + tablaProductos.getRowCount());
		            }
		            Txt_Cantidad.setText("0");
		        }
		    }
		});

		
		JTextField campoNumerico = new JTextField();

		campoNumerico.addKeyListener(new KeyAdapter() {
		    @Override
		    public void keyReleased(KeyEvent e) {
		        int code = e.getKeyCode();
		        // Permitir teclas especiales
		        if (code == KeyEvent.VK_ENTER ||
		        	code == KeyEvent.VK_BACK_SPACE ||
		            code == KeyEvent.VK_TAB ||
		            code == KeyEvent.VK_LEFT ||
		            code == KeyEvent.VK_RIGHT) {
		            return;
		        } else {
		            Ventas_funcion.limpiarPorRegex(campoNumerico, "0-9");
		        }
		    }
		});

		DefaultCellEditor editorNumerico = new DefaultCellEditor(campoNumerico);
		
		
		//Creación del modelo para manipular la tabla de carrito
		DefaultTableModel modeloCarrito = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;
			@Override
		    public boolean isCellEditable(int row, int column) {
				 // Solo la columna 4 (Cantidad) será editable
		        return column == 4;
		    }
		};
				
		//Crear las columnas de la Tabla de Carrito
		modeloCarrito.addColumn("No. Inv");
		modeloCarrito.addColumn("Nombre");
		modeloCarrito.addColumn("Marca");
		modeloCarrito.addColumn("Precio");
		modeloCarrito.addColumn("Cantidad");
		modeloCarrito.addColumn("Total");
		modeloCarrito.addColumn("Total IVA");
		
		tablaCarrito = new JTable(modeloCarrito);
		tablaCarrito.getColumnModel().getColumn(0).setMaxWidth(0); 
		tablaCarrito.getColumnModel().getColumn(0).setMinWidth(0); 
		tablaCarrito.getColumnModel().getColumn(0).setPreferredWidth(0); //Columna de No Inv oculta
		tablaCarrito.getColumnModel().getColumn(1).setPreferredWidth(130); //Ancho mayor para nombre
		tablaCarrito.getColumnModel().getColumn(2).setPreferredWidth(50); 
		tablaCarrito.getColumnModel().getColumn(3).setPreferredWidth(30); 
		tablaCarrito.getColumnModel().getColumn(4).setPreferredWidth(30); 
		tablaCarrito.getColumnModel().getColumn(5).setPreferredWidth(40); 
		tablaCarrito.getColumnModel().getColumn(6).setPreferredWidth(40); 
		
		// Asignar el editor personalizado a la columna 4 ("Cantidad")
		tablaCarrito.getColumnModel().getColumn(4).setCellEditor(editorNumerico);
		
		scrollPane_1.setViewportView(tablaCarrito);
		tablaCarrito.getTableHeader().setFont(new Font("Dubai", Font.BOLD, 14));
		tablaCarrito.setFont(new Font("Dubai", Font.PLAIN, 14));
		tablaCarrito.setRowHeight(22);
		
		DefaultTableCellRenderer renderer2 = new DefaultTableCellRenderer() {
		    private static final long serialVersionUID = 1L;
		    @Override
		    public Component getTableCellRendererComponent(JTable table, Object value,
		            boolean isSelected, boolean hasFocus, int row, int column) {
		        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);            
		        if (isSelected) {
		            // Morado más notorio (lavanda medio)
		        	 c.setBackground(new Color(230, 210, 255)); // puedes ajustar a tu gusto
		        } else {
		            // Lila muy claro
		            c.setBackground(new Color(245, 240, 255)); // muy suave, casi blanco con toque lila
		        }
		        return c;
		    }
		};
		
		for (int i = 0; i < tablaCarrito.getColumnCount(); i++) {
			tablaCarrito.getColumnModel().getColumn(i).setCellRenderer(renderer2);
		}
		
		DefaultCellEditor editor = (DefaultCellEditor) tablaCarrito.getDefaultEditor(Object.class);
		editor.addCellEditorListener(new CellEditorListener() {
		    @Override
		    public void editingStopped(ChangeEvent e) {
		        edicionManual = true;
		    }

		    @Override
		    public void editingCanceled(ChangeEvent e) {
		        edicionManual = false;
		    }
		});
		
		// Guardar el valor antes de editar
		tablaCarrito.addMouseListener(new MouseAdapter() {
		    public void mousePressed(MouseEvent e) {
		        int fila = tablaCarrito.rowAtPoint(e.getPoint());
		        int columna = tablaCarrito.columnAtPoint(e.getPoint());

		        if (columna == 4) { // Solo si es la columna editable de "Cantidad"
		            Anterior = tablaCarrito.getValueAt(fila, columna);
		        }
		    }
		});
		
		actualizando = false;
		
		tablaCarrito.getModel().addTableModelListener(new TableModelListener() {
		    @Override
		    public void tableChanged(TableModelEvent e) {
		        // Solo actuar cuando se edita una celda (no insertar ni borrar filas)
		        if (e.getType() == TableModelEvent.UPDATE) {

		        	if(actualizando || actualizandoTipo) return; //Si se esta actualizando ("regresando") no volvemos a validar
		        	
		        	//Si la actualizacion de la tabla es agregar el mismo producto no necesitamos validar
			        if("Agregar_Mismo_Producto".equals(Ventas_funcion.Tipo_Actualizacion)) {
			        	actualizandoTipo = true; 
			        	//Se pone en true para que si se dispara de nuevo evento se ignore
			            // No hacemos nada, solo ignoramos esta actualización provocada automáticamente
			            // Restablecemos después de un pequeño delay usando invokeLater
			            SwingUtilities.invokeLater(() -> {
			                Ventas_funcion.Tipo_Actualizacion = null;
			                actualizandoTipo = false;
			            });
			        }
			        	
			        else if (edicionManual) { //Si se edita manualmente y es la primera vez
			        	int fila = e.getFirstRow();
				        int CantidadAnterior = Integer.parseInt(Anterior.toString()); //Obtenemos cantidad anterior
				        Object valorCantidad = tablaCarrito.getValueAt(fila, 4); // Columna 4 = "Cantidad"
				        Object valorId = tablaCarrito.getValueAt(fila, 0); // Columna 0 = "Id del Producto"
				        actualizando = true;
				        Ventas_funcion.validarCantidadConStockTablaCarrito(fila, valorId, valorCantidad, 
				        		CantidadAnterior, tablaCarrito, Txt_Total); //Llamamos validación
				        SwingUtilities.invokeLater(() -> {
				        	actualizando = false;
					        Ventas_funcion.Tipo_Actualizacion = null; //Reseteamos el tipo de actualizacion
			            });
			        }           
		       }
		   }
		});
		
		Btn_Vaciar_Carrito.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Ventas_funcion.vaciarCarrito(tablaCarrito, Txt_Total, lblProductosEnCarrito);
			}
		});
		
		Btn_Agregar_Carrito.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Ventas_funcion.agregarAlCarrito(tablaProductos, tablaCarrito, Txt_Cantidad, Txt_Total, lblProductosEnCarrito, Txt_Producto);

			}
		});
		
		Btn_Eliminar_Carrito.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Ventas_funcion.eliminarProductoCarrito(tablaCarrito, Txt_Total, lblProductosEnCarrito);
			}
		});
		
		Btn_Vender.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Pasamos la ventana como parametro
				//Llamamos a la funcion para vender
				boolean completo = Ventas_funcion.venderProductos(Ventas_visual.this, tablaCarrito, Txt_Total, lblProductosEnCarrito); 
				if (completo) {
					Ventas_funcion.cargarProductos(tablaProductos);
					lblProductosEncontrados.setText("Productos Encontrados: " + tablaProductos.getRowCount());
					tablaProductos.clearSelection(); // Limpiar selección
					txtbuscar.setText("");        // Limpiar campo de texto			
				}
			}
		});
		
		
		Btn_Consultar_Productos.addActionListener(e -> {
			Ventas_funcion.consultarProductos(txtbuscar.getText().trim(), tablaProductos);
			lblProductosEncontrados.setText("Productos Encontrados: " + tablaProductos.getRowCount());
			tablaProductos.clearSelection(); // Limpiar selección
			Txt_Producto.setText("");        // Limpiar campo de texto
		});
		

		Btn_Mas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String texto = Txt_Cantidad.getText().trim();
		        int cantidad = 0;
		        if (!texto.isEmpty()) { //Si el texto no esta vacio tenemos una cantidad, si lo esta es 0
		            cantidad = Integer.parseInt(texto);
		        }
		        cantidad++;
		        Txt_Cantidad.setText(String.valueOf(cantidad));
		        Ventas_funcion.validarCantidadConStock(Txt_Cantidad, tablaProductos);
			}
		});
		
		Btn_Menos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String texto = Txt_Cantidad.getText().trim();
		        int cantidad = 0;
		        if (!texto.isEmpty()) {
		            cantidad = Integer.parseInt(texto);
		        }
		        if (cantidad > 0) { //Si es mayor a 0 restamos 1
		            cantidad--;
		        }
		        Txt_Cantidad.setText(String.valueOf(cantidad));
		        Ventas_funcion.validarCantidadConStock(Txt_Cantidad, tablaProductos);    
			}
		});
		
		Btn_ConsultarTodos_Productos.addActionListener(e -> {
			Ventas_funcion.cargarProductos(tablaProductos);
			lblProductosEncontrados.setText("Productos Encontrados: " + tablaProductos.getRowCount());
			 tablaProductos.clearSelection(); // Limpiar selección
			 Txt_Producto.setText("");        // Limpiar campo de texto	
		});
		Ventas_visual.this.setMinimumSize(new Dimension(1250, 790));
	}
}
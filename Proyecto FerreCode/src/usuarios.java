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

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class usuarios extends JFrame {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel(new GridBagLayout());
	GridBagConstraints gbc = new GridBagConstraints();
	GridBagConstraints gbc2 = new GridBagConstraints();
	private JTextField txtbuscar;
	private JTextField txtclave;
	private JTextField txtnombre;
	private JTextField txtusuario;;
	private JComboBox<String> cmbtipo;

	private JTable tablaUsuarios;
	private int filaSeleccionada = -1;
	private JLabel lblIconoUsuarios;
	
	public usuarios(JFrame anterior) {
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
		
//		ImageIcon timgUsuarios = new ImageIcon(getClass().getResource("Imagenes_Sistema/iconoUsuarios.png"));
//		Image imgUsuariosr = timgUsuarios.getImage().getScaledInstance(250, 70, Image.SCALE_SMOOTH);
//		ImageIcon imgUsuarios = new ImageIcon(imgUsuariosr);

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
		
		ImageIcon timgVentas = new ImageIcon(getClass().getResource("Imagenes_Sistema/iconoVender.png"));
		Image imgVentasr = timgVentas.getImage().getScaledInstance(250, 70, Image.SCALE_SMOOTH);
		ImageIcon imgVentas = new ImageIcon(imgVentasr);

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
//		*********aqui pones la ruta d ela imagen de usuarios*****
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
				Conexion.cerrarSesion(usuarios.this);
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
		
		JButton BtnVender_Menu = new JButton(imgVentas);
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
		
		JButton BtnUsuarios_Menu = new JButton(imgUsuariosOpaco);
		BtnUsuarios_Menu.setBackground(new Color(50, 51, 76));
		BtnUsuarios_Menu.setFont(new Font("Tahoma", Font.PLAIN, 20));
		gbc = (GridBagConstraints) gbc2.clone();
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.weighty = 0;
		gbc.insets = new Insets(0,0,0,0);
		navbar.add(BtnUsuarios_Menu, gbc);
		
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
		//apartado para imagenes dinamicas al pasar el mouse***
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
		BtnVender_Menu.addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	BtnVender_Menu.setIcon(imgVentasOpaco); // imagen opaca o resaltada
		    }

		    @Override
		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	BtnVender_Menu.setIcon(imgVentas); // imagen normal
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
		// eventos para losbotones ***
		BtnProductos_Menu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Productos productos = new Productos(usuarios.this);
				productos.setVisible(true);
				dispose();
			}
		});
		
		BtnVender_Menu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Ventas_visual ventas = new Ventas_visual(usuarios.this);
				ventas.setVisible(true);
				dispose();
			}
		});
		
		BtnInventario_Menu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Inventario inventario = new Inventario(usuarios.this);
				inventario.setVisible(true);
				dispose();
			}
		});
		
		Cerrar_Sesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Conexion.cerrarSesion(usuarios.this); //Cerramos sesion
			}
		});
		
		//parte de usuarios*****
		
		JLabel lbltitulo = new JLabel("Gestión de Usuarios");
		lbltitulo.setFont(new Font("Dubai Medium", Font.BOLD, 35));
		gbc = (GridBagConstraints) gbc2.clone();
		gbc.gridx =1;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.CENTER;
		contenido.add(lbltitulo, gbc);
		
		JLabel lblnombre = new JLabel("<html>Nombre Completo:<html>");
		lblnombre.setFont(new Font("Dubai Light", Font.BOLD, 20));
		gbc = (GridBagConstraints) gbc2.clone();
		gbc.gridx = 0;
		gbc.gridy = 1;
		contenido.add(lblnombre, gbc);
		
		JLabel lblusuario = new JLabel("<html>Usuario:<html>");
		lblusuario.setFont(new Font("Dubai Light", Font.BOLD, 20));
		gbc = (GridBagConstraints) gbc2.clone();
		gbc.gridx =0;
		gbc.gridy = 3;
		contenido.add(lblusuario, gbc);
		
		JLabel lblclave = new JLabel("<html>Contraseña:<html>");
		lblclave.setFont(new Font("Dubai Light", Font.BOLD, 20));
		gbc = (GridBagConstraints) gbc2.clone();
		gbc.gridx =0;
		gbc.gridy = 5;
		contenido.add(lblclave, gbc);

		JLabel lblNewLabel_4 = new JLabel("<html>Tipo de Usuario:<html>");
		lblNewLabel_4.setFont(new Font("Dubai Light", Font.BOLD, 20));
		gbc = (GridBagConstraints) gbc2.clone();
		gbc.gridx =0;
		gbc.gridy = 7;
		contenido.add(lblNewLabel_4, gbc);
		
		JLabel lblencontrados = new JLabel("Usuarios Encontrados: 0");
		lblencontrados.setFont(new Font("Dubai Light", Font.BOLD, 20));
		gbc = (GridBagConstraints) gbc2.clone();
		gbc.gridx = 2;
		gbc.gridy = 11;
		gbc.gridwidth = 2;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.EAST;
		contenido.add(lblencontrados, gbc);
		
		txtbuscar = new JTextField();
		txtbuscar.setToolTipText("Buscar Usuario");
		txtbuscar.setFont(new Font("Dubai Light", Font.PLAIN, 20));
		gbc = (GridBagConstraints) gbc2.clone();
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		contenido.add(txtbuscar, gbc);
		txtbuscar.setColumns(10);
		
		txtnombre = new JTextField();
		txtnombre.setFont(new Font("Dubai Light", Font.PLAIN, 20));
		gbc = (GridBagConstraints) gbc2.clone();
		gbc.gridx = 0;
		gbc.gridy = 2;
		contenido.add(txtnombre, gbc);
		txtnombre.setColumns(10);
		txtnombre.addKeyListener(new KeyAdapter() {
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
		        
		        limpiarPorRegex(txtnombre, "a-zA-ZáéíóúÁÉÍÓÚñÑüÜ\\s");
		    }
		});
		
		txtusuario = new JTextField();
		txtusuario.setFont(new Font("Dubai Light", Font.PLAIN, 20));
		gbc = (GridBagConstraints) gbc2.clone();
		gbc.gridx = 0;
		gbc.gridy = 4;
		contenido.add(txtusuario, gbc);
		txtusuario.setColumns(10);
		txtusuario.addKeyListener(new KeyAdapter() {
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

		        // Permitir letras, números, guion bajo y punto
		        limpiarPorRegex(txtusuario, "a-zA-Z0-9_\\.");
		    }
		});
		
		txtclave = new JTextField();
		txtclave.setFont(new Font("Dubai Light", Font.PLAIN, 20));
		gbc = (GridBagConstraints) gbc2.clone();
		gbc.gridx = 0;
		gbc.gridy = 6;
		contenido.add(txtclave, gbc);
		txtclave.setColumns(10);
		txtclave.addKeyListener(new KeyAdapter() {
		    @Override
		    public void keyReleased(KeyEvent e) {
		        int code = e.getKeyCode();

		        // Permitir teclas de navegación y edición
		        if (code == KeyEvent.VK_ENTER ||
		            code == KeyEvent.VK_BACK_SPACE ||
		            code == KeyEvent.VK_TAB ||
		            code == KeyEvent.VK_LEFT ||
		            code == KeyEvent.VK_RIGHT) {
		            return;
		        }

		        // Permitir letras, dígitos y símbolos seguros comunes
		        limpiarPorRegex(txtclave, "a-zA-Z0-9@#\\$%&\\*!\\-_\\.,;");
		    }
		});
		
		cmbtipo = new JComboBox<>();
		cmbtipo.setFont(new Font("Dubai Light", Font.PLAIN, 20));
		if(Conexion.TipoUsuario.equals("Admin")) {
			cmbtipo.setModel(new DefaultComboBoxModel<String>(new String[] {"Admin", "Cajero"}));
			cmbtipo.setSelectedIndex(-1);
		}
		else {
			cmbtipo.setModel(new DefaultComboBoxModel<String>(new String[] {"Propietario", "Admin", "Cajero"}));
			cmbtipo.setSelectedIndex(-1);
		}
		gbc = (GridBagConstraints) gbc2.clone();
		gbc.gridx = 0;
		gbc.gridy = 8;
		contenido.add(cmbtipo, gbc);
		cmbtipo.setEnabled(false);

		JScrollPane scrollPane = new JScrollPane();
		gbc = (GridBagConstraints) gbc2.clone();
		gbc.gridwidth = 3;
		gbc.gridheight = 8;
		gbc.weighty = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 1;
		gbc.gridy = 3;
		contenido.add(scrollPane, gbc);
		
		DefaultTableModel modeloUsuarios = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;

			@Override
		    public boolean isCellEditable(int row, int column) {
		        return false; // Ninguna celda editable
		    }
		};
		modeloUsuarios.addColumn("No. Id");
		modeloUsuarios.addColumn("Nombre Completo");
		modeloUsuarios.addColumn("Usuario");
		modeloUsuarios.addColumn("Password");
		modeloUsuarios.addColumn("Rol");
		
		tablaUsuarios = new JTable(modeloUsuarios);
		// Establecer ancho 0 a la columna "No. Id"
		tablaUsuarios.getColumnModel().getColumn(0).setMinWidth(0);
		tablaUsuarios.getColumnModel().getColumn(0).setMaxWidth(0);
		tablaUsuarios.getColumnModel().getColumn(0).setPreferredWidth(0);
		tablaUsuarios.getColumnModel().getColumn(1).setPreferredWidth(120);
		tablaUsuarios.getColumnModel().getColumn(2).setPreferredWidth(80);
		tablaUsuarios.getColumnModel().getColumn(3).setPreferredWidth(30);
		tablaUsuarios.getColumnModel().getColumn(4).setPreferredWidth(20);
		tablaUsuarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //Solo elegir uno
		tablaUsuarios.getTableHeader().setFont(new Font("Dubai", Font.PLAIN, 18));
		tablaUsuarios.setFont(new Font("Dubai", Font.PLAIN, 18));
		tablaUsuarios.setRowHeight(22);
		
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
		    private static final long serialVersionUID = 1L;
		    @Override
		    public Component getTableCellRendererComponent(JTable table, Object value,
		            boolean isSelected, boolean hasFocus, int row, int column) {     
		        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);          
		        if (isSelected) {
		            c.setBackground(new Color(144, 238, 144)); // verde más notorio (LightGreen)
		        } else {
		            c.setBackground(new Color(240, 255, 240)); // verde muy clarito (Honeydew)
		        }       
		        return c;
		    }
		};
		
		// Aplicar el renderer a todas las columnas
		for (int i = 0; i < tablaUsuarios.getColumnCount(); i++) {
			tablaUsuarios.getColumnModel().getColumn(i).setCellRenderer(renderer);
		}
		
		
		scrollPane.setViewportView(tablaUsuarios);
		Usuario.cargarUsuarios(tablaUsuarios);
		lblencontrados.setText("Usuarios Encontrados: " + tablaUsuarios.getRowCount());

		JButton Btn_Alta_Productos = new JButton("Agregar Usuario");
		Btn_Alta_Productos.setForeground(new Color(255, 255, 255));
		Btn_Alta_Productos.setBackground(new Color(0, 149, 74));
		Btn_Alta_Productos.setFont(new Font("Dubai Light", Font.PLAIN, 24));
		gbc = (GridBagConstraints) gbc2.clone();
		gbc.gridx = 1;
		gbc.gridy = 2;
		contenido.add(Btn_Alta_Productos, gbc);

		JButton Btn_Editar_Productos = new JButton("Editar Usuario");
		Btn_Editar_Productos.setForeground(new Color(255, 255, 255));
		Btn_Editar_Productos.setBackground(new Color(208, 208, 0));
		Btn_Editar_Productos.setFont(new Font("Dubai Light", Font.PLAIN, 24));
		gbc = (GridBagConstraints) gbc2.clone();
		gbc.gridx = 0;
		gbc.gridy = 9;
		gbc.anchor = GridBagConstraints.NORTH;
		contenido.add(Btn_Editar_Productos, gbc);

		JButton Btn_Baja_Productos = new JButton("Eliminar Usuario");
		Btn_Baja_Productos.setForeground(new Color(255, 255, 255));
		Btn_Baja_Productos.setBackground(new Color(174, 0, 0));
		Btn_Baja_Productos.setFont(new Font("Dubai Light", Font.PLAIN, 24));
		gbc = (GridBagConstraints) gbc2.clone();
		gbc.gridx = 2;
		gbc.gridy = 2;
		contenido.add(Btn_Baja_Productos, gbc);

		JButton Btn_Consultar_Productos = new JButton("Consultar");
		Btn_Consultar_Productos.setForeground(new Color(255, 255, 255));
		Btn_Consultar_Productos.setBackground(new Color(0, 63, 125));
		Btn_Consultar_Productos.setFont(new Font("Dubai Light", Font.PLAIN, 24));
		gbc = (GridBagConstraints) gbc2.clone();
		gbc.gridx = 3;
		gbc.gridy = 1;
		contenido.add(Btn_Consultar_Productos, gbc);

		JButton Btn_ConsultarTodos_Productos = new JButton("Consultar Todos");
		Btn_ConsultarTodos_Productos.setForeground(new Color(255, 255, 255));
		Btn_ConsultarTodos_Productos.setBackground(new Color(0, 63, 125));
		Btn_ConsultarTodos_Productos.setFont(new Font("Dubai Light", Font.PLAIN, 24));
		gbc = (GridBagConstraints) gbc2.clone();
		gbc.gridx = 3;
		gbc.gridy = 2;
		contenido.add(Btn_ConsultarTodos_Productos, gbc);


		Btn_Alta_Productos.addActionListener(e -> {
			usuarios_agregar agregar = new usuarios_agregar(this, tablaUsuarios, lblencontrados);
			agregar.setVisible(true);			
		});

		Btn_Editar_Productos.addActionListener(e -> {
			Usuario.editar(txtnombre, txtusuario, txtclave, cmbtipo, tablaUsuarios);
			if(Usuario.SeAutoModifico) {
				lblnombreUsuario.setText(Conexion.Usuario);			
				lblnombrePersona.setText(Conexion.NombreCompleto);
			}
		});
		
		Btn_Baja_Productos.addActionListener(e -> {		
			int filaSeleccionada = tablaUsuarios.getSelectedRow(); // ← Asegura obtenerla en este punto

		    if (filaSeleccionada == -1) {
		        JOptionPane.showMessageDialog(null, "Seleccione un usuario para darlo de baja.", "Error", JOptionPane.ERROR_MESSAGE);
		        return;
		    }

		    int id = Integer.parseInt(tablaUsuarios.getValueAt(filaSeleccionada, 0).toString());
		    String nombre = tablaUsuarios.getValueAt(filaSeleccionada, 1).toString();
		    String tipo = tablaUsuarios.getValueAt(filaSeleccionada, 4).toString();

		    Usuario.darDeBaja(id, nombre, tipo, tablaUsuarios);
		    lblencontrados.setText("Usuarios Encontrados: " + tablaUsuarios.getRowCount());
		});
		
		Btn_Consultar_Productos.addActionListener(e -> {
			Usuario.consultarUsuario(txtbuscar, tablaUsuarios); //Funcion para consultar
			lblencontrados.setText("Usuarios Encontrados: " + tablaUsuarios.getRowCount());
		});
		
		tablaUsuarios.getSelectionModel().addListSelectionListener(e -> {
		    // Ignorar eventos adicionales que no sean definitivos
		    if (!e.getValueIsAdjusting()) {
		        filaSeleccionada = tablaUsuarios.getSelectedRow();
		        if (filaSeleccionada != -1) {
		            txtnombre.setText(tablaUsuarios.getValueAt(filaSeleccionada, 1).toString());
		            txtusuario.setText(tablaUsuarios.getValueAt(filaSeleccionada, 2).toString());
		            cmbtipo.setSelectedItem(tablaUsuarios.getValueAt(filaSeleccionada, 4).toString());
		            if(Conexion.TipoUsuario.equals("Propietario") && !tablaUsuarios.getValueAt(filaSeleccionada, 4).toString().equals("Propietario")) {
		            	cmbtipo.setEnabled(true);
		            } else cmbtipo.setEnabled(false);
		        }
		    	 else {
		    	filaSeleccionada = -1;
	            txtnombre.setText("");
	            txtusuario.setText("");
	            cmbtipo.setEnabled(false);
	            cmbtipo.setSelectedIndex(-1); // Deselecciona cualquier opción
	        	lblencontrados.setText("Usuarios Encontrados: " + tablaUsuarios.getRowCount());
		    	}
		    }
		});
		
		Btn_ConsultarTodos_Productos.addActionListener(e -> {
			Usuario.cargarUsuarios(tablaUsuarios);
			lblencontrados.setText("Usuarios Encontrados: " + tablaUsuarios.getRowCount());
		});
		usuarios.this.setMinimumSize(new Dimension(1250, 790));
	}
	private void limpiarPorRegex(JTextField txt, String regex) {
		txt.setText(txt.getText().replaceAll("[^" + regex + "]", ""));
	}
}
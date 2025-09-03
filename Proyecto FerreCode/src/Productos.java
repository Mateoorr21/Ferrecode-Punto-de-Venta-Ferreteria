import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.JTable;
import java.awt.Color;
import java.awt.Component;



/* NOTA: Aun falta implementar la funcion de agregar inventario, no se si realizarlo como una ventana adicional o como un menu expandible, lo dejare pendiente por ahora
 * o si gustan realizarlo alguno de ustedes, el programa por ahora ya tiene un 65% completado la parte de Productos, falta la conexion con la base de datos de la
 * ventana principal y las secundarias, man;ana hare la de usuarios si es que esta bien el codigo y el dise;o
 * -Dagoberto        */

public class Productos extends JFrame {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel(new GridBagLayout());
	GridBagConstraints gbc = new GridBagConstraints();
	GridBagConstraints gbc2 = new GridBagConstraints();
	private JTextField txtbuscar;
	private JTextField txtnombre;
	private JTextField txtdesc;
	private JTextField txtmarca;
	private JTextField txtprecio;
	private JLabel lblImagen;
	private JLabel lblIconoUsuarios;
	private JTable tablaProductos;
	private int filaSeleccionada = -1;

	public Productos(JFrame anterior) {
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
		
//		ImageIcon timgProductos = new ImageIcon(getClass().getResource("Imagenes_Sistema/iconoProductos.png"));
//		Image imgProductosr = timgProductos.getImage().getScaledInstance(250, 70, Image.SCALE_SMOOTH);
//		ImageIcon imgProductos = new ImageIcon(imgProductosr);

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
				Conexion.cerrarSesion(Productos.this);
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
		
		JButton BtnProductos_Menu = new JButton(imgProductosOpaco);
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
		
		BtnVender_Menu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Ventas_visual ventas = new Ventas_visual(Productos.this);
				ventas.setVisible(true);
				dispose();
			}
		});
		
		BtnUsuarios_Menu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				usuarios usuarios = new usuarios(Productos.this);
				usuarios.setVisible(true);
				dispose();
			}
		});
		
		BtnInventario_Menu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Inventario inventario = new Inventario(Productos.this);
				inventario.setVisible(true);
				dispose();
			}
		});
		
		Cerrar_Sesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Conexion.cerrarSesion(Productos.this); //Cerramos sesion
			}
		});
		
		
		
		
		
		
		
		//*****************************parte productos ******************************8
		
		gbc = (GridBagConstraints) gbc2.clone();
		JLabel lbltitulo = new JLabel("Gestión de Productos");
		lbltitulo.setFont(new Font("Dubai", Font.BOLD, 35));
		gbc.gridx =1;
		gbc.gridy = 0;
		gbc.gridwidth = 3;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.CENTER;
		contenido.add(lbltitulo, gbc);
		
		gbc = (GridBagConstraints) gbc2.clone();
		JLabel lblnombre = new JLabel("Nombre del Producto:");
		lblnombre.setFont(new Font("Dubai Light", Font.BOLD, 20));
		gbc.gridx =0;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		gbc.weightx = 0;
		gbc.insets = new Insets(0, 10, 0, 0);
		contenido.add(lblnombre, gbc);
		
		gbc = (GridBagConstraints) gbc2.clone();
		JLabel lbldesc = new JLabel("Descripción del Producto:");
		lbldesc.setFont(new Font("Dubai Light", Font.BOLD, 20));
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 2;
		gbc.weightx = 0;
		gbc.insets = new Insets(0, 10, 0, 0);
		contenido.add(lbldesc, gbc);

		gbc = (GridBagConstraints) gbc2.clone();
		JLabel lblmarca = new JLabel("Marca del Producto:");
		lblmarca.setFont(new Font("Dubai Light", Font.BOLD, 20));
		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.gridwidth = 2;
		gbc.weightx = 0;
		gbc.insets = new Insets(0, 10, 0, 0);
		contenido.add(lblmarca, gbc);

		gbc = (GridBagConstraints) gbc2.clone();
		JLabel lblprecio = new JLabel("Precio ($):");
		lblprecio.setFont(new Font("Dubai Light", Font.BOLD, 20));
		gbc.gridx = 0;
		gbc.gridy = 7;
		gbc.gridwidth = 2;
		gbc.weightx = 0;
		gbc.insets = new Insets(0, 10, 0, 0);
		contenido.add(lblprecio, gbc );
		
		JLabel lblencontrados = new JLabel("Productos Encontrados: 0");
		lblencontrados.setFont(new Font("Dubai Light", Font.BOLD, 20));
		gbc = (GridBagConstraints) gbc2.clone();
		gbc.gridx = 3;
		gbc.gridy = 14;
		gbc.gridwidth = 3;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.EAST;
		contenido.add(lblencontrados, gbc);
		
		gbc = (GridBagConstraints) gbc2.clone();
		txtbuscar = new JFormattedTextField();
		txtbuscar.setToolTipText("Buscar Producto");
		txtbuscar.setFont(new Font("Dubai Light", Font.PLAIN, 20));
		gbc.gridx = 2;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		gbc.gridheight = 2;
		contenido.add(txtbuscar, gbc);
		gbc.gridwidth = 1;
		txtbuscar.setColumns(10);
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
		    	limpiarPorRegex(txtbuscar, "a-zA-ZáéíóúÁÉÍÓÚñÑ0-9 .\"\\-/#°%+");
		    }
		});
		
		gbc = (GridBagConstraints) gbc2.clone();
		txtnombre = new JFormattedTextField();
		txtnombre.setFont(new Font("Dubai Light", Font.PLAIN, 20));
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 2;
		gbc.weightx = 0;
		gbc.insets = new Insets(0, 5, 0, 0);
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
		         
		         //Solo permitimos esos caracteres especiales
		    	limpiarPorRegex(txtnombre, "a-zA-ZáéíóúÁÉÍÓÚñÑ0-9 .\"\\-/#°%+");
		    }
		});

		gbc = (GridBagConstraints) gbc2.clone();
		txtdesc = new JFormattedTextField();
		txtdesc.setFont(new Font("Dubai Light", Font.PLAIN, 20));
		txtdesc.setColumns(10);
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.gridwidth = 2;
		gbc.weightx = 0;
		gbc.insets = new Insets(0, 5, 0, 0);
		contenido.add(txtdesc, gbc);
		txtdesc.addKeyListener(new KeyAdapter() {
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
		        // Permitir letras con/sin acento, dígitos, coma y punto
		        limpiarPorRegex(txtdesc, "a-zA-ZáéíóúÁÉÍÓÚñÑ0-9 .,");
		    }
		});

		gbc = (GridBagConstraints) gbc2.clone();
		txtmarca = new JFormattedTextField();
		txtmarca.setFont(new Font("Dubai Light", Font.PLAIN, 20));
		txtmarca.setColumns(10);
		gbc.gridx = 0;
		gbc.gridy = 6;
		gbc.weightx = 0;
		gbc.gridwidth = 2;
		gbc.insets = new Insets(0, 5, 0, 0);
		contenido.add(txtmarca, gbc);
		txtmarca.addKeyListener(new KeyAdapter() {
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
		        
		     // Permitir solo letras, dígitos, comilla simple y &
		        limpiarPorRegex(txtmarca, "a-zA-Z0-9 '&");
		    }
		});
		
		gbc = (GridBagConstraints) gbc2.clone();
		txtprecio = new JFormattedTextField();
		txtprecio.setFont(new Font("Dubai Light", Font.PLAIN, 20));
		txtprecio.setColumns(10);
		gbc.gridx = 0;
		gbc.gridy = 8;
		gbc.gridwidth = 2;
		gbc.weightx = 0;
		gbc.insets = new Insets(0, 5, 0, 0);
		contenido.add(txtprecio, gbc);
		txtprecio.addKeyListener(new KeyAdapter() {
		    @Override
		    public void keyReleased(KeyEvent e) {
		    	int code = e.getKeyCode();
		        // Permitir teclas especiales
		        if (code == KeyEvent.VK_BACK_SPACE ||
		            code == KeyEvent.VK_TAB ||
		            code == KeyEvent.VK_LEFT ||
		            code == KeyEvent.VK_RIGHT) {
		            return;
		        }
		        
		        limpiarPorRegex(txtprecio, "0-9.");
		        String nuevoTexto = txtprecio.getText();
		        int puntoIndex = nuevoTexto.indexOf(".");
		        if (puntoIndex != -1) {
		            nuevoTexto = nuevoTexto.substring(0, puntoIndex + 1) +
		                         nuevoTexto.substring(puntoIndex + 1).replace(".", "");
		        }
		        nuevoTexto = nuevoTexto.replaceFirst("(\\.\\d{2}).*", "$1");
		        txtprecio.setText(nuevoTexto);
		    }
		});

		gbc = (GridBagConstraints) gbc2.clone();
		JButton Btn_Alta_Productos = new JButton("Alta");
		Btn_Alta_Productos.setForeground(new Color(255, 255, 255));
		Btn_Alta_Productos.setBackground(new Color(0, 149, 74));
		Btn_Alta_Productos.setFont(new Font("Dubai Light", Font.PLAIN, 24));
		gbc.gridx = 2;
		gbc.gridy = 3;
		gbc.gridheight = 2;
		contenido.add(Btn_Alta_Productos, gbc);
		
		gbc = (GridBagConstraints) gbc2.clone();
		lblImagen = new JLabel();
		lblImagen.setFont(new Font("Dubai Light", Font.PLAIN, 18));
		gbc.gridx = 0;
		gbc.gridy = 10;
		gbc.gridheight = 1; 
		gbc.gridwidth = 2;
		gbc.fill = GridBagConstraints.NONE;
		lblImagen.setPreferredSize(new Dimension(200, 150));
		contenido.add(lblImagen, gbc);

		gbc = (GridBagConstraints) gbc2.clone();
		JButton Btn_Editar_Productos = new JButton("Editar");
		Btn_Editar_Productos.setForeground(new Color(255, 255, 255));
		Btn_Editar_Productos.setBackground(new Color(208, 208, 0));
		Btn_Editar_Productos.setFont(new Font("Dubai Light", Font.PLAIN, 24));
		gbc.gridx = 0;
		gbc.gridy = 9;
		gbc.weightx = 0;
		gbc.insets = new Insets(5,5,0,0);
		contenido.add(Btn_Editar_Productos, gbc);
		
		gbc = (GridBagConstraints) gbc2.clone();
		JButton Btn_Cambiar_Img = new JButton("Cambiar Imagen");
		Btn_Cambiar_Img.setForeground(new Color(255, 255, 255));
		Btn_Cambiar_Img.setBackground(new Color(208, 208, 0));
		Btn_Cambiar_Img.setFont(new Font("Dubai Light", Font.PLAIN, 24));
		gbc.gridx = 1;
		gbc.gridy = 9;
		gbc.weightx = 0;
		gbc.insets = new Insets(5,5,0,0);
		contenido.add(Btn_Cambiar_Img, gbc);

		gbc = (GridBagConstraints) gbc2.clone();
		JButton Btn_Baja_Productos = new JButton("Baja");
		Btn_Baja_Productos.setForeground(new Color(255, 255, 255));
		Btn_Baja_Productos.setBackground(new Color(174, 0, 0));
		Btn_Baja_Productos.setFont(new Font("Dubai Light", Font.PLAIN, 24));
		gbc.gridx = 3;
		gbc.gridy = 3;
		gbc.gridheight = 2;
		contenido.add(Btn_Baja_Productos, gbc);

		gbc = (GridBagConstraints) gbc2.clone();
		JButton Btn_Consultar_Productos = new JButton("Consultar");
		Btn_Consultar_Productos.setForeground(new Color(255, 255, 255));
		Btn_Consultar_Productos.setBackground(new Color(0, 63, 125));
		Btn_Consultar_Productos.setFont(new Font("Dubai Light", Font.PLAIN, 24));
		gbc.gridx = 5;
		gbc.gridy = 1;
		gbc.gridheight = 2;
		contenido.add(Btn_Consultar_Productos, gbc);

		gbc = (GridBagConstraints) gbc2.clone();
		JButton Btn_ConsultarTodos_Productos = new JButton("Consultar Todos");
		Btn_ConsultarTodos_Productos.setForeground(new Color(255, 255, 255));
		Btn_ConsultarTodos_Productos.setBackground(new Color(0, 63, 125));
		Btn_ConsultarTodos_Productos.setFont(new Font("Dubai Light", Font.PLAIN, 24));
		gbc.gridx = 4;
		gbc.gridy = 3;
		gbc.gridwidth = 2; 
		gbc.gridheight = 2;
		contenido.add(Btn_ConsultarTodos_Productos, gbc);

		
		gbc = (GridBagConstraints) gbc2.clone();
		JScrollPane scrollPane = new JScrollPane();
		gbc.gridx = 2;
		gbc.gridy = 5;
		gbc.gridwidth = 4; // ocupará 4 columnas
		gbc.gridheight = 7; // ocupará 10 filas
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weighty = 1.0;
		contenido.add(scrollPane, gbc);
		DefaultTableModel modeloProductos = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;

			@Override
		    public boolean isCellEditable(int row, int column) {
		        return false; // Ninguna celda editable
		    }
		};
		modeloProductos.addColumn("No. Inv");
		modeloProductos.addColumn("Nombre");
		modeloProductos.addColumn("Descripción");
		modeloProductos.addColumn("Marca");
		modeloProductos.addColumn("Precio");
		modeloProductos.addColumn("Cantidad");
		tablaProductos = new JTable(modeloProductos);
		tablaProductos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //Solo elegir uno
		
		// Ocultarmos la columna "No. Inv" (columna 0)
		tablaProductos.getColumnModel().getColumn(0).setMinWidth(0);
		tablaProductos.getColumnModel().getColumn(0).setMaxWidth(0);
		tablaProductos.getColumnModel().getColumn(0).setWidth(0);
		tablaProductos.getColumnModel().getColumn(2).setPreferredWidth(200);  
		tablaProductos.getColumnModel().getColumn(3).setPreferredWidth(40);  
		tablaProductos.getColumnModel().getColumn(4).setPreferredWidth(10);  // Precio
		tablaProductos.getColumnModel().getColumn(5).setPreferredWidth(10); // Cantidad Stock
		scrollPane.setViewportView(tablaProductos);
		tablaProductos.getTableHeader().setFont(new Font("Dubai", Font.BOLD, 14));
		tablaProductos.setFont(new Font("Dubai", Font.PLAIN, 14));
		tablaProductos.setRowHeight(22);
		Producto.cargarProductos(tablaProductos);
		lblencontrados.setText("Productos Encontrados: " + tablaProductos.getRowCount());
		
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
		
		Btn_Cambiar_Img.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
			    fileChooser.setDialogTitle("Selecciona una imagen");
			    fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Imágenes", "jpg", "jpeg", "png", "gif", "bmp"));

			    int seleccion = fileChooser.showOpenDialog(null);
			    if (seleccion == JFileChooser.APPROVE_OPTION) {
			        File archivo = fileChooser.getSelectedFile();
			        try {
			            BufferedImage imagen = ImageIO.read(archivo);
			            if (imagen != null) {
			                Producto.imagenTemporal = imagen;
			                Producto.nombreImagenTemporal = archivo.getName().replaceFirst("[.][^.]+$", ""); // Sin extensión
			                
			                Dimension size = lblImagen.getPreferredSize();
			            	int anchoDisponible = size.width;
			            	int altoDisponible = size.height;
			            	
			                // Mostrar la imagen en un JLabel
			            	lblImagen.setIcon(null);
			                lblImagen.setIcon((Icon) new ImageIcon(imagen.getScaledInstance(anchoDisponible, altoDisponible, Image.SCALE_SMOOTH)));
			                Producto.SeModificoImagen = true;
			            } else {
			                JOptionPane.showMessageDialog(null, "Archivo inválido.");
			            }
			        } catch (IOException ex) {
			            ex.printStackTrace();
			        }
			    }
			}
		});
		
		Btn_Alta_Productos.addActionListener(e -> {
			productos_agregar agregar = new productos_agregar(this, tablaProductos, lblencontrados);
			agregar.setVisible(true);			
		});

		Btn_Editar_Productos.addActionListener(e -> {
			Producto.ActualizarProducto(tablaProductos, txtnombre, txtdesc, txtmarca, txtprecio, lblImagen);	
		});
		
		Btn_Baja_Productos.addActionListener(e -> {
			Producto.darDeBaja(tablaProductos); //dar de baja un producto
			lblencontrados.setText("Productos Encontrados: " + tablaProductos.getRowCount());
		});
		
		Btn_Consultar_Productos.addActionListener(e -> {
			Producto.consultarProductos(txtbuscar.getText().trim(), tablaProductos);
			lblencontrados.setText("Productos Encontrados: " + tablaProductos.getRowCount());
		});
		
		tablaProductos.getSelectionModel().addListSelectionListener(e -> {
		    // Ignorar eventos adicionales que no sean definitivos
		    if (!e.getValueIsAdjusting()) {
		    	Producto.SeModificoImagen = false; //El booleano falso cada que se elige algo
		        filaSeleccionada = tablaProductos.getSelectedRow();
		        if (filaSeleccionada != -1) {
		            txtnombre.setText(tablaProductos.getValueAt(filaSeleccionada, 1).toString());
		            txtdesc.setText(tablaProductos.getValueAt(filaSeleccionada, 2).toString());
		            txtmarca.setText(tablaProductos.getValueAt(filaSeleccionada, 3).toString());
		            txtprecio.setText(tablaProductos.getValueAt(filaSeleccionada, 4).toString().replace("$", ""));
		            String ruta = "Imagenes/" + tablaProductos.getValueAt(filaSeleccionada, 0).toString() + ".png"; // "../" sube desde src a la raíz del proyecto
		            File archivo = new File(ruta);
		            if (archivo.exists()) {
		            	Dimension size = lblImagen.getPreferredSize();
		            	int anchoDisponible = size.width;
		            	int altoDisponible = size.height;

		            	ImageIcon icon = new ImageIcon(ruta);
		            	Image img = icon.getImage();
		            	Image imagenEscalada = img.getScaledInstance(anchoDisponible, altoDisponible, Image.SCALE_SMOOTH);
		            	lblImagen.setIcon(new ImageIcon(imagenEscalada));
		            } else {
		                System.out.println("La imagen no existe: " + ruta);
		                lblImagen.setIcon(null);
		            }
		        }
		        else {
		        	lblencontrados.setText("Productos Encontrados: " + tablaProductos.getRowCount());
		        	txtnombre.setText("");
		        	txtdesc.setText("");
		        	txtmarca.setText("");
		        	txtprecio.setText("");
		        	lblImagen.setIcon(null);
		        }
		    }
		});
		
		Btn_ConsultarTodos_Productos.addActionListener(e -> {
			Producto.cargarProductos(tablaProductos);
			lblencontrados.setText("Productos Encontrados: " + tablaProductos.getRowCount());
		});
		
		Productos.this.setMinimumSize(new Dimension(1250, 790));
	}
	
	private void limpiarPorRegex(JTextField txt, String regex) {
		txt.setText(txt.getText().replaceAll("[^" + regex + "]", ""));
	}
}
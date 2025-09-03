import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
public class Principal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPanel = new JPanel(new GridBagLayout());
	GridBagConstraints gbc2 = new GridBagConstraints();
	GridBagConstraints gbc = new GridBagConstraints();
	private JLabel lblIconoUsuarios;

	public Principal() {
		setTitle("Sistema Ferretería FerreCode");
		ImageIcon IconoFrameLogo = new ImageIcon(getClass().getResource("Imagenes_Sistema/IconoInicio.png"));
		setIconImage(IconoFrameLogo.getImage()); //Icono de barra de titulo
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(960, 600);
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
		contentPanel.add(info, gbc);///Proyecto_Programacion_IV/src/Imagenes_Sistema/Instrucciones.png
		
		pntimg contenido = new pntimg("Imagenes_Sistema/Instrucciones.png");
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
				Conexion.cerrarSesion(Principal.this);
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
		
		BtnProductos_Menu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Productos productos = new Productos(Principal.this);
				productos.setVisible(true);
				dispose();
			}
		});
		
		BtnVender_Menu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Ventas_visual ventas = new Ventas_visual(Principal.this);
				ventas.setVisible(true);
				dispose();
			}
		});
		
		BtnUsuarios_Menu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				usuarios usuarios = new usuarios(Principal.this);
				usuarios.setVisible(true);
				dispose();
			}
		});
		
		BtnInventario_Menu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Inventario inventario = new Inventario(Principal.this);
				inventario.setVisible(true);
				dispose();
			}
		});
		
		Cerrar_Sesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Conexion.cerrarSesion(Principal.this); //Cerramos sesion
			}
		});

		Principal.this.setMinimumSize(new Dimension(1250, 790));
	}
}

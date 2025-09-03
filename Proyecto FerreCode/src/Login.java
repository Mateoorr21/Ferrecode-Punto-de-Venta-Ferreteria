import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.SystemColor;
import java.awt.Image;
import javax.swing.Icon;

import javax.swing.AbstractAction;



public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField Txt_Usuario;
	private JPasswordField Txt_Password;
	private Boolean mostrarPassword = false; //Password Oculta por default

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
					frame.setTitle("Ingreso al Sistema FerreCode");
					ImageIcon IconoFrameLogin = new ImageIcon(getClass().getResource("Imagenes_Sistema/LoginIcono.png"));
					frame.setIconImage(IconoFrameLogin.getImage());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public Login() {
		setTitle("Ingreso al Sistema FerreCode");
		ImageIcon IconoFrameLogin = new ImageIcon(getClass().getResource("Imagenes_Sistema/LoginIcono.png"));
		setIconImage(IconoFrameLogin.getImage());
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Desactiva el botón cerrar
		setSize(376, 519);
		contentPane = new JPanel();
		contentPane.setForeground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel login = new JPanel();
		login.setBounds(0, 0, 380, 480);
		
		contentPane.add(login);
		login.setLayout(null);
		
		ImageIcon textoLogin = new ImageIcon(getClass().getResource("Imagenes_Sistema/TextoLogin.png"));
		Image textoLoginEscalado = textoLogin.getImage().getScaledInstance(194, 92, Image.SCALE_SMOOTH);
		JLabel LblLogin = new JLabel(new ImageIcon(textoLoginEscalado));
		LblLogin.setForeground(SystemColor.activeCaption);
		LblLogin.setBounds(82, 42, 194, 92);
		login.add(LblLogin);
		
		Txt_Usuario = new JTextField();
		Txt_Usuario.setFont(new Font("Dubai Light", Font.PLAIN, 19));
		Txt_Usuario.setBounds(116, 181, 158, 32);
		Txt_Usuario.setOpaque(false);  // ← Fondo transparente
		Txt_Usuario.setBorder(null);   // ← Sin bordes
		Txt_Usuario.setBackground(new Color(0, 0, 0, 0)); // ← Color transparente
		Txt_Usuario.setColumns(10);
		login.add(Txt_Usuario);
		
		Txt_Password = new JPasswordField();
		Txt_Password.setFont(new Font("Dubai Light", Font.PLAIN, 19));
		Txt_Password.setVisible(true);
		Txt_Password.setColumns(10);
		Txt_Password.setBounds(116, 260, 124, 32);
		Txt_Password.setOpaque(false);  // ← Fondo transparente
		Txt_Password.setBorder(null);   // ← Sin bordes
		Txt_Password.setBackground(new Color(0, 0, 0, 0)); // ← Color transparente
		login.add(Txt_Password);
		
		ImageIcon ImgUsuario = new ImageIcon(getClass().getResource("Imagenes_Sistema/Usuario.png"));
		Image ImgUsuarioEscalado = ImgUsuario.getImage().getScaledInstance(26, 26, Image.SCALE_SMOOTH);
		JLabel LblImgUsuario = new JLabel(new ImageIcon(ImgUsuarioEscalado));
		LblImgUsuario.setForeground(SystemColor.activeCaption);
		LblImgUsuario.setBounds(86, 183, 26, 26);
		login.add(LblImgUsuario);
		
		ImageIcon ImgPassword = new ImageIcon(getClass().getResource("Imagenes_Sistema/Password1.png"));
		Image ImgPasswordEscalado = ImgPassword.getImage().getScaledInstance(26, 26, Image.SCALE_SMOOTH);
		JLabel LblImgPassword = new JLabel(new ImageIcon(ImgPasswordEscalado));
		LblImgPassword.setForeground(SystemColor.activeCaption);
		LblImgPassword.setBounds(86, 262, 26, 26);
		login.add(LblImgPassword);
		
		ImageIcon ImgPassV = new ImageIcon(getClass().getResource("Imagenes_Sistema/Mostrar Password.png")); //Contraseña visible
		Image ImgPassVEscalado = ImgPassV.getImage().getScaledInstance(26, 26, Image.SCALE_SMOOTH);
		
		ImageIcon ImgPassOculto = new ImageIcon(getClass().getResource("Imagenes_Sistema/Esconder Password.png")); //Contraseña oculta
		Image ImgPassOcultoEscalado = ImgPassOculto.getImage().getScaledInstance(26, 26, Image.SCALE_SMOOTH);
		JButton BtnMostrarPass = new JButton();
		
		//Si se tiene el foco y se presiona enter se ejecuta el evento de click del boton
		BtnMostrarPass.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke("ENTER"), "pressed");
		BtnMostrarPass.getActionMap().put("pressed", new AbstractAction() {
			private static final long serialVersionUID = 1L;

			@Override
		    public void actionPerformed(ActionEvent e) {
		        BtnMostrarPass.doClick(); // Ejecuta el botón como si se presionara
		    }
		});
		
		
		BtnMostrarPass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarPassword = !mostrarPassword; //Actualizamos mostrar u ocultar contraseña
				if(mostrarPassword) {
					BtnMostrarPass.setIcon(new ImageIcon(ImgPassVEscalado)); //Icono mostrar
					Txt_Password.setEchoChar((char) 0); //Mostramos contraseña ingresada
				}
				else {
					BtnMostrarPass.setIcon(new ImageIcon(ImgPassOcultoEscalado)); //Icono oculto
					Txt_Password.setEchoChar('•'); //Contraseña oculta
				}
			}
		});
		BtnMostrarPass.setIcon(new ImageIcon(ImgPassOcultoEscalado)); //Icono de boton es la imagen escalada oculta)
		BtnMostrarPass.setForeground(SystemColor.activeCaption);
		BtnMostrarPass.setBounds(242, 262, 26, 26);
		BtnMostrarPass.setOpaque(false);
		BtnMostrarPass.setContentAreaFilled(false);
		BtnMostrarPass.setBorderPainted(false); // Opcional: elimina el borde
		login.add(BtnMostrarPass);
		
		ImageIcon cajaDatos = new ImageIcon(getClass().getResource("Imagenes_Sistema/CajaDatos.png"));
		Image cajaDatosEscalada = cajaDatos.getImage().getScaledInstance(194, 36, Image.SCALE_SMOOTH);
		JLabel LblCaja1 = new JLabel(new ImageIcon(cajaDatosEscalada));
		LblCaja1.setForeground(SystemColor.activeCaption);
		LblCaja1.setFont(new Font("Century Gothic", Font.BOLD, 32));
		LblCaja1.setBounds(82, 258, 194, 36);
		login.add(LblCaja1);
		
		JLabel LblCaja2 = new JLabel(new ImageIcon(cajaDatosEscalada));
		LblCaja2.setForeground(SystemColor.activeCaption);
		LblCaja2.setFont(new Font("Century Gothic", Font.BOLD, 32));
		LblCaja2.setBounds(82, 179, 194, 36);
		login.add(LblCaja2);
		
		JLabel lblusuario = new JLabel("Usuario");
		lblusuario.setForeground(new Color(0, 0, 128));
		lblusuario.setFont(new Font("Dubai Light", Font.PLAIN, 20));
		lblusuario.setBounds(92, 145, 108, 32);
		login.add(lblusuario);
		
		
		JLabel lblContrase = new JLabel("Contraseña");
		lblContrase.setForeground(new Color(0, 0, 128));
		lblContrase.setFont(new Font("Dubai Light", Font.PLAIN, 20));
		lblContrase.setBounds(92, 223, 108, 32);
		login.add(lblContrase);
		
		JButton btniniciar_sesion = new JButton("Iniciar Sesión");
		
		//Se cambia el color del boton al obtener y perder el foco
		btniniciar_sesion.addFocusListener(new FocusAdapter() {
		    @Override
		    public void focusGained(FocusEvent e) {
		        btniniciar_sesion.setBackground(new Color(0, 200, 80)); // Verde Claro
		    }

		    @Override
		    public void focusLost(FocusEvent e) {
		        btniniciar_sesion.setBackground(new Color(34, 139, 34)); // Verde Fuerte
		    }
		});
		
		//Si se tiene el foco y se presiona enter se ejecuta el evento de click del boton
		btniniciar_sesion.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke("ENTER"), "pressed");
		btniniciar_sesion.getActionMap().put("pressed", new AbstractAction() {
			private static final long serialVersionUID = 1L;

			@Override
		    public void actionPerformed(ActionEvent e) {
		        btniniciar_sesion.doClick(); // Ejecuta el botón como si se presionara
		    }
		});
		
		btniniciar_sesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Llamamos al iniciarsesion
				char[] passwordChars = Txt_Password.getPassword();
				String password = new String(passwordChars);
				Conexion.iniciarSesion(Txt_Usuario.getText(), password, Txt_Usuario, Txt_Password, Login.this);
			}
		});
		btniniciar_sesion.setFont(new Font("Dubai Light", Font.PLAIN, 22));
		btniniciar_sesion.setBounds(82, 320, 194, 47);
		
		btniniciar_sesion.setBackground(new Color(0, 153, 51)); // Verde fuerte
		btniniciar_sesion.setForeground(Color.WHITE); // Texto blanco
		btniniciar_sesion.setFocusPainted(false); 
		login.add(btniniciar_sesion);
		
		JButton BtnSalir = new JButton("Salir");
		
		//Se cambia el color del boton al obtener y perder el foco
		BtnSalir.addFocusListener(new FocusAdapter() {
		    @Override
		    public void focusGained(FocusEvent e) {
		        BtnSalir.setBackground(new Color(200, 50, 50)); // Rojo Claro
		    }

		    @Override
		    public void focusLost(FocusEvent e) {
		        BtnSalir.setBackground(new Color(153, 0, 0)); // Rojo fuerte
		    }
		});
		
		//Si se tiene el foco y se presiona enter se ejecuta el evento de click del boton
		BtnSalir.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke("ENTER"), "pressed");
		BtnSalir.getActionMap().put("pressed", new AbstractAction() {
			private static final long serialVersionUID = 1L;

			@Override
		    public void actionPerformed(ActionEvent e) {
		        BtnSalir.doClick(); // Ejecuta el botón como si se presionara
		    }
		});
		
		BtnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Regresamos a inicio
				Inicio inicio = new Inicio();
				inicio.setVisible(true);
				inicio.setLocationRelativeTo(null);
				dispose();
			}
		});
		BtnSalir.setFont(new Font("Dubai Light", Font.PLAIN, 22));
		BtnSalir.setBounds(82, 388, 194, 47);
		
		BtnSalir.setBackground(new Color(153, 0, 0)); // Rojo oscuro
		BtnSalir.setForeground(Color.WHITE); // Texto blanco
		BtnSalir.setFocusPainted(false);  //Sin borde la obtenerfocus
		login.add(BtnSalir);
		
		ImageIcon img = new ImageIcon(getClass().getResource("Imagenes_Sistema/FondoLogin5.jpg"));
		JLabel lblimg = new JLabel(img);
		lblimg.setBounds(0, 0, 359, 480);
		login.add(lblimg);
		
		
		
	}
}

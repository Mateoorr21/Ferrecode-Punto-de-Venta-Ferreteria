import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;

public class Inicio extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inicio frame = new Inicio();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Inicio() {
		setTitle("Bienvenido a la Ferretería FerreCode");
		ImageIcon IconoFrameInicio = new ImageIcon(getClass().getResource("Imagenes_Sistema/IconoInicio.png"));
		setIconImage(IconoFrameInicio.getImage()); //Icono de barra de titulo
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
		
		ImageIcon textoInicio = new ImageIcon(getClass().getResource("Imagenes_Sistema/TextoInicio.png"));
		Image textoInicioEscalado = textoInicio.getImage().getScaledInstance(298, 92, Image.SCALE_SMOOTH);
		JLabel LblInicio = new JLabel(new ImageIcon(textoInicioEscalado));
		LblInicio.setForeground(SystemColor.activeCaption);
		LblInicio.setBounds(37, 11, 298, 92);
		login.add(LblInicio);
		
		ImageIcon LogoImg = new ImageIcon(getClass().getResource("Imagenes_Sistema/LogoFerreCode.png"));
		Image LogoImgEscalado = LogoImg.getImage().getScaledInstance(300,300, Image.SCALE_SMOOTH);
		JLabel LblLogo = new JLabel(new ImageIcon(LogoImgEscalado)); //Logo de ferreteria
		LblLogo.setForeground(SystemColor.activeCaption);
		LblLogo.setBounds(10, 115, 338, 300);
		login.add(LblLogo);
		
		JButton BtnIngresar = new JButton("I N G R E S A R");
				
		//Si se tiene el foco y se presiona enter se ejecuta el evento de click del boton
		BtnIngresar.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke("ENTER"), "pressed");
		BtnIngresar.getActionMap().put("pressed", new AbstractAction() {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				 BtnIngresar.doClick(); // Ejecuta el botón como si se presionara
			}
		});
				
		BtnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login login = new Login();
				login.setLocationRelativeTo(null);
				login.setVisible(true);
				dispose();
				
			}
		});
		BtnIngresar.setFont(new Font("Dubai Light", Font.PLAIN, 22));
		BtnIngresar.setBounds(0, 426, 361, 54);
		
		BtnIngresar.setBackground(new Color(101, 67, 33)); // Café oscuro
		BtnIngresar.setForeground(Color.WHITE); // Letras blancas
		BtnIngresar.setFocusPainted(false); // Quita el borde de enfoque al hacer clic
		BtnIngresar.setBorderPainted(false); // Opcional: sin borde
		
		login.add(BtnIngresar);
		ImageIcon img = new ImageIcon(getClass().getResource("Imagenes_Sistema/FondoInicio10_600x600.png"));
		JLabel lblimg = new JLabel(img);
		lblimg.setBounds(0, 0, 359, 480);
		login.add(lblimg);
	}

}

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import java.awt.Color;

public class usuarios_agregar extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtnombre;
	private JTextField txtusuario;
	private JTextField txtclave;
	
	public usuarios_agregar(usuarios usuarios, JTable tabla, JLabel Usuarios) {
		super(usuarios, "Agregar Usuario", true); // true = modal
		setTitle("Agregar Usuario");
		ImageIcon IconoAgregarProducto = new ImageIcon(getClass().getResource("Imagenes_Sistema/IconoAgregarUsuario.png"));
		setIconImage(IconoAgregarProducto.getImage()); //Icono de barra de titulo
		setBounds(100, 100, 280, 354);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 265, 317);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(usuarios);
        getContentPane().setLayout(null);
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		contentPanel.setBackground(new Color(241, 241, 233));
		
		JLabel lblNewLabel = new JLabel("Nombre Completo");
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblNewLabel.setBounds(22, 11, 172, 23);
		contentPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre Usuario");
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblNewLabel_1.setBounds(22, 65, 153, 23);
		contentPanel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Contraseña");
		lblNewLabel_2.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblNewLabel_2.setBounds(22, 123, 172, 23);
		contentPanel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_4 = new JLabel("Tipo");
		lblNewLabel_4.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblNewLabel_4.setBounds(22, 178, 79, 23);
		contentPanel.add(lblNewLabel_4);
		
		txtnombre = new JFormattedTextField();
		txtnombre.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtnombre.setColumns(10);
		txtnombre.setBounds(22, 35, 222, 20);
		contentPanel.add(txtnombre);
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
		
		txtusuario = new JFormattedTextField();
		txtusuario.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtusuario.setColumns(10);
		txtusuario.setBounds(22, 91, 222, 20);
		contentPanel.add(txtusuario);
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
		
		txtclave = new JFormattedTextField();
		txtclave.setColumns(10);
		txtclave.setBounds(22, 148, 222, 20);
		contentPanel.add(txtclave);
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
		
		
		JComboBox<String> cmbtipo = new JComboBox<String>();
		//Modelo de combobox de acuerdo con el tipo de usuario que inicio sesión
		if(Conexion.TipoUsuario.equals("Admin")) cmbtipo.setModel(new DefaultComboBoxModel<String>(new String[] {"Cajero"}));
		else cmbtipo.setModel(new DefaultComboBoxModel<String>(new String[] {"Cajero", "Admin"}));
		cmbtipo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cmbtipo.setBounds(22, 205, 222, 25);
		contentPanel.add(cmbtipo);
		
		JButton Btn_Agregar_Productos = new JButton("Agregar");
		Btn_Agregar_Productos.setForeground(new Color(255, 255, 255));
		Btn_Agregar_Productos.setBackground(new Color(28, 90, 121));
		Btn_Agregar_Productos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean alta = Usuario.darDeAltaUsuario(txtnombre, txtusuario, txtclave, cmbtipo.getSelectedItem().toString());
				if(alta) {
					dispose();
					Usuario.cargarUsuarios(tabla);
					Usuarios.setText("Usuarios Encontrados: " + tabla.getRowCount());
				}
			}
		});
		Btn_Agregar_Productos.setFont(new Font("Segoe UI", Font.BOLD, 18));
		Btn_Agregar_Productos.setBounds(22, 240, 222, 54);
		contentPanel.add(Btn_Agregar_Productos);
	}
	private void limpiarPorRegex(JTextField txt, String regex) {
		txt.setText(txt.getText().replaceAll("[^" + regex + "]", ""));
	}
}

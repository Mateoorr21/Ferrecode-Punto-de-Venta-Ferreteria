import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import java.awt.Color;

public class productos_agregar extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtnombre;
	private JTextArea txtdesc;
	private JTextField txtmarca;
	private JTextField txtprecio;
	private JTextField txtcant;
	/**
	 * Create the dialog.
	 */
	public productos_agregar(JFrame productos, JTable tabla, JLabel Productos) {
		super(productos, "Agregar Producto", true); // true = modal
		setTitle("Agregar Producto");
		setType(Type.POPUP);
		ImageIcon IconoAgregarProducto = new ImageIcon(getClass().getResource("Imagenes_Sistema/BotonAgregarProducto.png"));
		setIconImage(IconoAgregarProducto.getImage()); //Icono de barra de titulo
		setBounds(100, 100, 348, 606);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 332, 567);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(productos);
        getContentPane().setLayout(null);
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		contentPanel.setBackground(new Color(241, 241, 233));
		
		Producto.imagenTemporal = null;
		Producto.nombreImagenTemporal = null;
		
		JLabel lblNewLabel = new JLabel("Nombre producto");
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblNewLabel.setBounds(27, 10, 228, 23);
		contentPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Descripcion producto");
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblNewLabel_1.setBounds(27, 73, 228, 25);
		contentPanel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Proveedor Producto");
		lblNewLabel_2.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblNewLabel_2.setBounds(27, 155, 228, 23);
		contentPanel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_4 = new JLabel("Precio");
		lblNewLabel_4.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblNewLabel_4.setBounds(27, 205, 79, 25);
		contentPanel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_3 = new JLabel("Cantidad");
		lblNewLabel_3.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblNewLabel_3.setBounds(215, 205, 92, 25);
		contentPanel.add(lblNewLabel_3);
		
		txtnombre = new JFormattedTextField();
		txtnombre.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtnombre.setColumns(10);
		txtnombre.setBounds(27, 43, 280, 20);
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
		         
		         //Solo permitimos esos caracteres especiales
		    	limpiarPorRegex(txtnombre, "a-zA-ZáéíóúÁÉÍÓÚñÑ0-9 .\"\\-/#°%+");
		    }
		});
		
		txtdesc = new JTextArea(2, 30);
		txtdesc.setLineWrap(true);           // Activa el salto de línea automático
		txtdesc.setWrapStyleWord(true);     // Corta por palabras completas (no en medio)
		txtdesc.setFocusTraversalKeysEnabled(false);
		txtdesc.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		txtdesc.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtdesc.setColumns(10);
		txtdesc.setBounds(27, 99, 280, 49);
		contentPanel.add(txtdesc);
		txtdesc.setFocusTraversalKeysEnabled(false);
		//Si se presiona tab o shift tab recorre controles
		txtdesc.getInputMap().put(KeyStroke.getKeyStroke("TAB"), "focusNextComponent");
		txtdesc.getActionMap().put("focusNextComponent", new AbstractAction() {
		    /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
		    public void actionPerformed(ActionEvent e) {
		        txtdesc.transferFocus();
		    }
		});

		txtdesc.getInputMap().put(KeyStroke.getKeyStroke("shift TAB"), "focusPreviousComponent");
		txtdesc.getActionMap().put("focusPreviousComponent", new AbstractAction() {
		    /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
		    public void actionPerformed(ActionEvent e) {
		        txtdesc.transferFocusBackward();
		    }
		});
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
		        limpiarPorRegexArea(txtdesc, "a-zA-ZáéíóúÁÉÍÓÚñÑ0-9 .,");
		    }
		});
		
		txtmarca = new JTextField();
		txtmarca.setColumns(10);
		txtmarca.setBounds(27, 180, 281, 20);
		txtmarca.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPanel.add(txtmarca);
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
		
		txtprecio = new JTextField();
		txtprecio.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtprecio.setColumns(10);
		txtprecio.setBounds(27, 232, 92, 20);
		contentPanel.add(txtprecio);
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
		        String nuevoTexto = txtprecio.getText()
;		        int puntoIndex = nuevoTexto.indexOf(".");
		        if (puntoIndex != -1) {
		            nuevoTexto = nuevoTexto.substring(0, puntoIndex + 1) +
		                         nuevoTexto.substring(puntoIndex + 1).replace(".", "");
		        }
		        nuevoTexto = nuevoTexto.replaceFirst("(\\.\\d{2}).*", "$1");
		        txtprecio.setText(nuevoTexto);
		    }
		});
		
		
		txtcant = new JTextField();
		txtcant.setColumns(10);
		txtcant.setBounds(215, 234, 92, 20);
		txtcant.setFont(new Font("Tahoma", Font.PLAIN, 15));		
		contentPanel.add(txtcant);
		txtcant.addKeyListener(new KeyAdapter() {
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
		         
		        limpiarPorRegex(txtcant, "0-9");
		    }
		});
				
		JLabel lblNewLabel_2_1 = new JLabel("Selecciona una imagen");
		lblNewLabel_2_1.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblNewLabel_2_1.setBounds(27, 263, 228, 24);
		contentPanel.add(lblNewLabel_2_1);
		
		JLabel lblimg = new JLabel("");
		lblimg.setBounds(68, 349, 201, 138);
		contentPanel.add(lblimg);
		txtcant.addKeyListener(new KeyAdapter() {
		    @Override
		    public void keyReleased(KeyEvent e) {
		        limpiarPorRegex(txtcant, "0-9");
		    }
		});
		
		ImageIcon imgcobrar = new ImageIcon(getClass().getResource("Imagenes_Sistema/IconoBotonCobrar.png"));
		Image imgcobrarr = imgcobrar.getImage().getScaledInstance(73, 50, Image.SCALE_SMOOTH);
		imgcobrar = new ImageIcon(imgcobrarr);
		
		JButton btncargar_img = new JButton("Cargar Imagen");
		btncargar_img.addActionListener(new ActionListener() {
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

			                // Mostrar la imagen en un JLabel
			                lblimg.setIcon((Icon) new ImageIcon(imagen.getScaledInstance(201, 138, Image.SCALE_SMOOTH)));
			            } else {
			                JOptionPane.showMessageDialog(null, "Archivo inválido.");
			            }
			        } catch (IOException ex) {
			            ex.printStackTrace();
			        }
			    }
			}
		});
		btncargar_img.setFont(new Font("Segoe UI", Font.BOLD, 18));
		btncargar_img.setBackground(new Color(0, 63, 125));
		btncargar_img.setForeground(new Color(255, 255, 255));
		btncargar_img.setBounds(27, 298, 281, 40);
		contentPanel.add(btncargar_img);
				
						
		JButton Btn_Agregar_Productos = new JButton("Agregar");
		Btn_Agregar_Productos.setForeground(new Color(255, 255, 255));
		Btn_Agregar_Productos.setBounds(33, 498, 274, 40);
		contentPanel.add(Btn_Agregar_Productos);
		Btn_Agregar_Productos.setBackground(new Color(28, 90, 121));
		Btn_Agregar_Productos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean completoAlta = Producto.registrarProducto(txtnombre, txtdesc, txtmarca, txtprecio, txtcant);
				if(completoAlta) {
					dispose();
					Producto.cargarProductos(tabla); //Cargamos los productos
					Productos.setText("Productos Encontrados: " + tabla.getRowCount());
				}
			}
		});
		Btn_Agregar_Productos.setFont(new Font("Segoe UI", Font.BOLD, 18));
	}
	private void limpiarPorRegex(JTextField txt, String regex) {
		txt.setText(txt.getText().replaceAll("[^" + regex + "]", ""));
	}
	
	private void limpiarPorRegexArea(JTextArea txt, String regex) {
		txt.setText(txt.getText().replaceAll("[^" + regex + "]", ""));
	}
}


import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Cobro_visual extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField Txt_Cobrar;
	private JTextField Txt_PorPagar;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cobro_visual dialogo = new Cobro_visual();
					dialogo.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the Dialog.
	 */
	public Cobro_visual(Ventas_visual ventas) {
		super(ventas, "Ventana de Cobro", true);
		ImageIcon IconoVentanaCobro = new ImageIcon(getClass().getResource("Imagenes_Sistema/IconoBotonCobrar.png"));
		setIconImage(IconoVentanaCobro.getImage()); //Icono de barra de titulo
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 509, 244);
		setLocationRelativeTo(ventas);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(241, 241, 233));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(241, 241, 233));
		panel.setBounds(10, 10, 475, 187);
        // Crear borde con ese color y grosor 4 px 
        Border borde = BorderFactory.createLineBorder(new Color(28, 90, 121) , 2);
        panel.setBorder(borde);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(28, 90, 121));
		panel_1.setBounds(0, 0, 475, 48);
		panel.add(panel_1);
		
		ImageIcon lbltituloi = new ImageIcon(getClass().getResource("Imagenes_Sistema/TextoVentanaCobro2.png"));
		Image lbltitulor = lbltituloi.getImage().getScaledInstance(230, 50, Image.SCALE_SMOOTH);
		lbltituloi = new ImageIcon(lbltitulor);
		panel_1.setLayout(null);
		JLabel lbltitulo = new JLabel(lbltituloi);
		lbltitulo.setBounds(87, 0, 306, 40);
		panel_1.add(lbltitulo);
		
		ImageIcon lblxi = new ImageIcon(getClass().getResource("Imagenes_Sistema/boton X Blanco.png"));
		Image lblxir= lblxi.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
		lblxi = new ImageIcon(lblxir);
		
		Txt_Cobrar = new JTextField();
		Txt_Cobrar.setFont(new Font("Dubai", Font.PLAIN, 18));
		Txt_Cobrar.setText("");
		Txt_Cobrar.setEditable(true);
		Txt_Cobrar.setColumns(10);
		Txt_Cobrar.setBounds(262, 82, 146, 20);
		panel.add(Txt_Cobrar);
		Txt_Cobrar.addKeyListener(new KeyAdapter() {
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
		        
		        Ventas_funcion.limpiarPorRegex(Txt_Cobrar, "0-9.");
		        String nuevoTexto = Txt_Cobrar.getText()
;		        int puntoIndex = nuevoTexto.indexOf(".");
		        if (puntoIndex != -1) {
		            nuevoTexto = nuevoTexto.substring(0, puntoIndex + 1) +
		                         nuevoTexto.substring(puntoIndex + 1).replace(".", "");
		        }
		        nuevoTexto = nuevoTexto.replaceFirst("(\\.\\d{2}).*", "$1");
		        Txt_Cobrar.setText(nuevoTexto);
		    }
		});
		
		JLabel lblNewLabel = new JLabel("Dinero Recibo (MXN)");
		lblNewLabel.setFont(new Font("Dubai Medium", Font.PLAIN, 17));
		lblNewLabel.setBounds(262, 58, 188, 23);
		panel.add(lblNewLabel);
		
		Txt_PorPagar = new JTextField();
		Txt_PorPagar.setFont(new Font("Dubai", Font.PLAIN, 18));
		//Cargamos el total de la venta
		if(Ventas_funcion.total_venta == 0) Txt_PorPagar.setText("$0.00");
		else Txt_PorPagar.setText(String.format("%.2f", Ventas_funcion.total_venta));
		Txt_PorPagar.setEditable(false);
		Txt_PorPagar.setColumns(10);
		Txt_PorPagar.setBounds(47, 82, 146, 20);
		panel.add(Txt_PorPagar);
		
		JLabel lblMontoAPagar = new JLabel("Monto a Pagar IVA (MXN)");
		lblMontoAPagar.setFont(new Font("Dubai Medium", Font.PLAIN, 17));
		lblMontoAPagar.setBounds(47, 58, 205, 23);
		panel.add(lblMontoAPagar);
		
		JButton Btn_Cancelar = new JButton(lblxi);
		Btn_Cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose(); //Cerramos cuadro de dialogo
			}
		});
		Btn_Cancelar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		Btn_Cancelar.setBounds(427, 0, 48, 48);
		Btn_Cancelar.setOpaque(false);
		Btn_Cancelar.setContentAreaFilled(false);
		Btn_Cancelar.setBorderPainted(false);
		Btn_Cancelar.setFocusPainted(false);
		Btn_Cancelar.setMargin(new Insets(0, 0, 0, 0));
		panel_1.add(Btn_Cancelar);
		
		ImageIcon imgcobrar = new ImageIcon(getClass().getResource("Imagenes_Sistema/IconoBotonCobrar.png"));
		Image imgcobrarr = imgcobrar.getImage().getScaledInstance(73, 50, Image.SCALE_SMOOTH);
		imgcobrar = new ImageIcon(imgcobrarr);
		JButton Btn_Cobrar = new JButton(imgcobrar);
		Btn_Cobrar.setForeground(new Color(255, 255, 255));
		Btn_Cobrar.setText("COBRAR");
		Btn_Cobrar.setBackground(new Color(28, 90, 121));
		Btn_Cobrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Ventas_funcion.seCompletoOperacion = Ventas_funcion.hacerCobro(Txt_Cobrar);
				if(Ventas_funcion.seCompletoOperacion) dispose(); //Si se hizo el cobro cerramos ventana
			}
		});
		Btn_Cobrar.setMargin(new Insets(0,0,0,0));
		Btn_Cobrar.setFont(new Font("Dubai Medium", Font.BOLD, 30));
		Btn_Cobrar.setBounds(127, 112, 227, 65);
		panel.add(Btn_Cobrar);
	}
}
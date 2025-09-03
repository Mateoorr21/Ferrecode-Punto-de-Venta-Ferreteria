import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class pntimg extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BufferedImage imagen;

    public pntimg(String rutaImagen) {
        try {
            imagen = ImageIO.read(getClass().getResource(rutaImagen));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (imagen != null) {
            // Escala la imagen al tamaño actual del panel
            g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
        }
    }
}

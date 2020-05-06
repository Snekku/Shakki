import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * Sotilas luokka. Taydentaa Nappula luokkaa.
 */
public class Sotilas extends Nappula {
	
	/**
	 * Sotilas luokan konstruktori. Asettaa Sotilaalle ID:n, kuvakkeen ja varin.
	 * @param id Sotilaan ID, joka on yksilöllinen.
	 * @param vari Kumman puolen Sotilaasta on kyse.
	 * @param x X-koordinaatti
	 * @param y Y-koordinaatti
	 */
	public Sotilas(String id, int vari, int x, int y) {
		this.id = id;
		this.vari = vari;
		this.x = x;
		this.y = y;
		
		Image image = null;
		try {
			image = ImageIO.read(new File("src/Pawn" + Integer.toString(vari) + ".png"));
		} catch (IOException e) {
			
		}
		this.kuvake = new JLabel(new ImageIcon(image));
	}
}
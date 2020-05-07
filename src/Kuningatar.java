import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * Kuningatar luokka. Taydentaa Nappula luokkaa.
 */
public class Kuningatar extends Nappula {
	
	/**
	 * Kuningatar luokan konstruktori. Asettaa Kuningattarelle ID:n, kuvakkeen ja varin.
	 * @param id Kuningattaren ID, joka on yksilollinen.
	 * @param vari Kumman puolen Kuningattaresta on kyse.
	 * @param x X-koordinaatti
	 * @param y Y-koordinaatti
	 */
	public Kuningatar(String id, int vari, int x, int y) {
		this.id = id;
		this.vari = vari;
		this.x = x;
		this.y = y;
		
		Image image = null;
		try {
			image = ImageIO.read(new File("src/Kuvakkeet/Queen" + Integer.toString(vari) + ".png"));
		} catch (IOException e) {
			
		}
		this.kuvake = new JLabel(new ImageIcon(image));
	}
}
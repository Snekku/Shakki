import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * Ratsu luokka. Taydentaa Nappula luokkaa.
 */
public class Ratsu extends Nappula {
	
	/**
	 * Ratsu luokan konstruktori. Asettaa Ratsulle ID:n, kuvakkeen ja varin.
	 * @param id Ratsun ID, joka on yksilollinen.
	 * @param vari Kumman puolen Ratsusta on kyse.
	 * @param x X-koordinaatti
	 * @param y Y-koordinaatti
	 */
	public Ratsu(String id, int vari, int x, int y) {
		this.id = id;
		this.vari = vari;
		this.x = x;
		this.y = y;
		
		Image image = null;
		try {
			image = ImageIO.read(new File("src/Kuvakkeet/Knight" + Integer.toString(vari) + ".png"));
		} catch (IOException e) {
			
		}
		this.kuvake = new JLabel(new ImageIcon(image));
	}
}
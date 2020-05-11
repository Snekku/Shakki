import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * Kuningas luokka. Taydentaa Nappula luokkaa.
 */
public class Kuningas extends Nappula {
	
	/**
	 * Kuningas luokan konstruktori. Asettaa Kuninkaalle ID:n, kuvakkeen ja varin.
	 * @param id Kuninkaan ID, joka on yksilollinen.
	 * @param vari Kumman puolen Kuninkaasta on kyse.
	 * @param x X-koordinaatti
	 * @param y Y-koordinaatti
	 */
	public Kuningas(String id, int vari, int x, int y) {
		this.id = id;
		this.vari = vari;
		this.x = x;
		this.y = y;
		
		Image image = null;
		try {
			image = ImageIO.read(new File("src/Kuvakkeet/King" + Integer.toString(vari) + ".png"));
		} catch (IOException e) {
			
		}
		this.kuvake = new JLabel(new ImageIcon(image));
	}
	
	/**
	 * Tarkistaa onko siirto laillinen.
	 * @param ruutu Ruutu, johon ollaan siirtymassa.
 	 * @param nappula Nappula, jota yritetaan siirtaa.
 	 * @return Palauttaa true tai false, sen mukaan onko siirto laillinen.
	 */
	protected Boolean nappulanLaillinenSiirto(Ruutu ruutu, Nappula nappula) {
		System.out.println("Kuningas");
		return true;
	}
}
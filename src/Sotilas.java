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
	 * @param id Sotilaan ID, joka on yksilollinen.
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
			image = ImageIO.read(new File("src/Kuvakkeet/Pawn" + Integer.toString(vari) + ".png"));
		} catch (IOException e) {
			
		}
		this.kuvake = new JLabel(new ImageIcon(image));
	}
	
	/**
	 * Tarkistaa onko siirto laillinen.
	 * @param Ruutu Ruutu, johon ollaan siirtymassa.
	 * @param nappula Nappula, jota yritetaan siirtaa.
 	 * @return Palauttaa true tai false, sen mukaan onko siirto laillinen.
	 */
	protected Boolean nappulanLaillinenSiirto(Ruutu ruutu, Nappula nappula) {
		System.out.println("Sotilas");
		int[] koordinaatit = ruutu.vertaaKoordinaatit(ruutu, nappula.x, nappula.y);
		if (koordinaatit[0] == 0) {
			if(koordinaatit[1] == 1) {
				nappula.paivitaKoordinaatit(nappula, koordinaatit[0], koordinaatit[1]);
				return true;
			}
			if(koordinaatit[1] == 2) {
				if(nappula.y == 1 || nappula.y == 6) {
					nappula.paivitaKoordinaatit(nappula, koordinaatit[0], koordinaatit[1]);
					return true;
				}
			}
		}
		return false;
	}
}
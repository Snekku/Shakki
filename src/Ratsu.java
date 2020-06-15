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
	
	/**
	 * Tarkistaa onko siirto laillinen.
	 * @param Ruutu Ruutu, johon ollaan siirtymassa.
	 * @param nappula Nappula, jota yritetaan siirtaa.
	 * @param shakkiLautaRuudut matriisi, jossa tallessa laudan ruutujen tiedot
 	 * @return Palauttaa true tai false, sen mukaan onko siirto laillinen.
	 */
	protected Boolean nappulanLaillinenSiirto(Ruutu ruutu, Nappula nappula, Ruutu[][] shakkiLautaRuudut) {
		int[] koordinaatit = ruutu.vertaaKoordinaatit(ruutu, nappula.x, nappula.y);
		if (koordinaatit[0] == 1 || koordinaatit[0] == -1) {
			if (koordinaatit[1] == 2 || koordinaatit[1] == -2) {
				if (!ruutu.onkoRuudussaNappula(ruutu) || (ruutu.onkoRuudussaNappula(ruutu) && ruutu.onkoVaritErit(ruutu, nappula))) {
					nappula.paivitaKoordinaatit(nappula, koordinaatit[0], koordinaatit[1]);
					return true;
				}
			}
		}
		if (koordinaatit[0] == 2 || koordinaatit[0] == -2) {
			if (koordinaatit[1] == 1 || koordinaatit[1] == -1) {
				if (!ruutu.onkoRuudussaNappula(ruutu) || (ruutu.onkoRuudussaNappula(ruutu) && ruutu.onkoVaritErit(ruutu, nappula))) {
					nappula.paivitaKoordinaatit(nappula, koordinaatit[0], koordinaatit[1]);
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Tarkistaa onko siirto laillinen.
	 * @param ruutu Ruutu, josta ollaan siirtymassa.
	 * @param ruutu2 Ruutu, johon ollaan siirtymassa.
	 * @param nappula Nappula, jota yritetaan siirtaa.
	 * @param shakkiLautaRuudut matriisi, jossa tallessa laudan ruutujen tiedot
 	 * @return Palauttaa true tai false, sen mukaan onko siirto laillinen.
	 */
	protected Boolean nappulanLaillinenSiirto(Ruutu ruutu, Ruutu ruutu2, Nappula nappula, Ruutu[][] shakkiLautaRuudut) {
		int[] koordinaatit = ruutu2.vertaaKoordinaatit(ruutu2, nappula.x, nappula.y);
		if (koordinaatit[0] == 1 || koordinaatit[0] == -1) {
			if (koordinaatit[1] == 2 || koordinaatit[1] == -2) {
				if (!ruutu2.onkoRuudussaNappula(ruutu2) || (ruutu2.onkoRuudussaNappula(ruutu2) && ruutu2.onkoVaritErit(ruutu2, nappula))) {
					return true;
				}
			}
		}
		if (koordinaatit[0] == 2 || koordinaatit[0] == -2) {
			if (koordinaatit[1] == 1 || koordinaatit[1] == -1) {
				if (!ruutu2.onkoRuudussaNappula(ruutu2) || (ruutu2.onkoRuudussaNappula(ruutu2) && ruutu2.onkoVaritErit(ruutu2, nappula))) {
					return true;
				}
			}
		}
		return false;
	}
}
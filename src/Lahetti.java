import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * Lahetti luokka. Taydentaa Nappula luokkaa.
 */
public class Lahetti extends Nappula {
	
	/**
	 * Lahetti luokan konstruktori. Asettaa Lahetille ID:n, kuvakkeen ja varin.
	 * @param id Lahetin ID, joka on yksilollinen.
	 * @param vari Kumman puolen Lahetista on kyse.
	 * @param x X-koordinaatti
	 * @param y Y-koordinaatti
	 */
	public Lahetti(String id, int vari, int x, int y) {
		this.id = id;
		this.vari = vari;
		this.x = x;
		this.y = y;
		
		Image image = null;
		try {
			image = ImageIO.read(new File("src/Kuvakkeet/Bishop" + Integer.toString(vari) + ".png"));
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
		//TODO Toiston purku.
		int[] koordinaatit = ruutu.vertaaKoordinaatit(ruutu, nappula.x, nappula.y);
		if (koordinaatit[0] == koordinaatit[1]) {
			if (koordinaatit[0] < 0) {//vasen ylos
				if (!ruutu.onkoRuudussaNappula(ruutu) || (ruutu.onkoRuudussaNappula(ruutu) && ruutu.onkoVaritErit(ruutu, nappula))) {
					for (int i=0;i<Math.abs(koordinaatit[0]);i++) {
						if (shakkiLautaRuudut[nappula.x-i][nappula.y-i].onkoRuudussaNappula(shakkiLautaRuudut[nappula.x-i][nappula.y-i])) {
							return false;
						}
					}
					nappula.paivitaKoordinaatit(nappula, koordinaatit[0], koordinaatit[1]);
					return true;
				}
			} else {//oikee alas
				if (!ruutu.onkoRuudussaNappula(ruutu) || (ruutu.onkoRuudussaNappula(ruutu) && ruutu.onkoVaritErit(ruutu, nappula))) {
					for (int i=0;i<Math.abs(koordinaatit[0]);i++) {
						if (shakkiLautaRuudut[nappula.x+i][nappula.y+i].onkoRuudussaNappula(shakkiLautaRuudut[nappula.x+i][nappula.y+i])) {
							return false;
						}
					}
					nappula.paivitaKoordinaatit(nappula, koordinaatit[0], koordinaatit[1]);
					return true;
				}
			}
		}
		if (koordinaatit[0] == koordinaatit[1]*-1 || koordinaatit[0]*-1 == koordinaatit[1]) {
			if (koordinaatit[0] < 0) {//vasen alas
				if (!ruutu.onkoRuudussaNappula(ruutu) || (ruutu.onkoRuudussaNappula(ruutu) && ruutu.onkoVaritErit(ruutu, nappula))) {
					for (int i=0;i<Math.abs(koordinaatit[0]);i++) {
						if (shakkiLautaRuudut[nappula.x-i][nappula.y+i].onkoRuudussaNappula(shakkiLautaRuudut[nappula.x-i][nappula.y+i])) {
							return false;
						}
					}
					nappula.paivitaKoordinaatit(nappula, koordinaatit[0], koordinaatit[1]);
					return true;
				}
			} else {//oikee ylos
				if (!ruutu.onkoRuudussaNappula(ruutu) || (ruutu.onkoRuudussaNappula(ruutu) && ruutu.onkoVaritErit(ruutu, nappula))) {
					for (int i=0;i<Math.abs(koordinaatit[0]);i++) {
						if (shakkiLautaRuudut[nappula.x+i][nappula.y-i].onkoRuudussaNappula(shakkiLautaRuudut[nappula.x+i][nappula.y-i])) {
							return false;
						}
					}
					nappula.paivitaKoordinaatit(nappula, koordinaatit[0], koordinaatit[1]);
					return true;
				}
			}
		}
		return false;
	}
}
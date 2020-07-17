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
	 * Korottaa sotilaan.
	 * @param nappula Nappula, joka korotetaan.
	 * @return Kuningatar, joksi sotilas korotettiin.
	 */
	protected Nappula korota(Nappula nappula, int korotukset) {
		Nappula korotettuNappula = new Kuningatar(nappula.id.charAt(0) + "D" + Integer.toString(korotukset+2), nappula.vari, nappula.x, nappula.y);
		return korotettuNappula;
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
		
		if (koordinaatit[0] == 0 && koordinaatit[1] == 0) {
			return false;
		}
		
		if (koordinaatit[0] == 0) {
			if(((koordinaatit[1] == 1 && nappula.vari == 0) || (koordinaatit[1] == -1 && nappula.vari == 1)) && !ruutu.onkoRuudussaNappula(ruutu)) {
				nappula.paivitaKoordinaatit(nappula, koordinaatit[0], koordinaatit[1]);
				return true;
			}
			if((koordinaatit[1] == 2 && nappula.vari == 0) || (koordinaatit[1] == -2 && nappula.vari == 1)) {
				if(nappula.y == 1 && !ruutu.onkoRuudussaNappula(ruutu)) {
					if (!shakkiLautaRuudut[nappula.x][nappula.y+1].onkoRuudussaNappula(shakkiLautaRuudut[nappula.x][nappula.y+1])) {
						nappula.paivitaKoordinaatit(nappula, koordinaatit[0], koordinaatit[1]);
						return true;
					}
				}
				if(nappula.y == 6 && !ruutu.onkoRuudussaNappula(ruutu)) {
					if (!shakkiLautaRuudut[nappula.x][nappula.y-1].onkoRuudussaNappula(shakkiLautaRuudut[nappula.x][nappula.y-1])) {
						nappula.paivitaKoordinaatit(nappula, koordinaatit[0], koordinaatit[1]);
						return true;
					}
				}
			}
		}
		if (((koordinaatit[0] == 1 || koordinaatit[0] == -1) && (koordinaatit[1] == 1 && nappula.vari == 0)) || ((koordinaatit[0] == 1 || koordinaatit[0] == -1) && (koordinaatit[1] == -1 && nappula.vari == 1))) {
			if (ruutu.onkoRuudussaNappula(ruutu) && ruutu.onkoVaritErit(ruutu, nappula)) {
				nappula.paivitaKoordinaatit(nappula, koordinaatit[0], koordinaatit[1]);
				return true;
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
		
		if (koordinaatit[0] == 0) {
			if(((koordinaatit[1] == 1 && nappula.vari == 0) || (koordinaatit[1] == -1 && nappula.vari == 1)) && !ruutu2.onkoRuudussaNappula(ruutu2)) {
				return true;
			}
			if((koordinaatit[1] == 2 && nappula.vari == 0) || (koordinaatit[1] == -2 && nappula.vari == 1)) {
				if(nappula.y == 1 && !ruutu2.onkoRuudussaNappula(ruutu2)) {
					if (!shakkiLautaRuudut[nappula.x][nappula.y+1].onkoRuudussaNappula(shakkiLautaRuudut[nappula.x][nappula.y+1])) {
						return true;
					}
				}
				if(nappula.y == 6 && !ruutu2.onkoRuudussaNappula(ruutu2)) {
					if (!shakkiLautaRuudut[nappula.x][nappula.y-1].onkoRuudussaNappula(shakkiLautaRuudut[nappula.x][nappula.y-1])) {
						return true;
					}
				}
			}
		}
		if (((koordinaatit[0] == 1 || koordinaatit[0] == -1) && (koordinaatit[1] == 1 && nappula.vari == 0)) || ((koordinaatit[0] == 1 || koordinaatit[0] == -1) && (koordinaatit[1] == -1 && nappula.vari == 1))) {
			if (ruutu2.onkoRuudussaNappula(ruutu2) && ruutu2.onkoVaritErit(ruutu2, nappula)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Tarkistaa hyokkaako Sotilas ruutuun.
	 * @param ruutu Ruutu, josta ollaan siirtymassa.
	 * @param ruutu2 Ruutu, johon ollaan siirtymassa.
	 * @param nappula Nappula, jota yritetaan siirtaa.
 	 * @return Palauttaa true tai false, sen mukaan hyokataanko ruutuun.
	 */
	protected Boolean hyokkaakoRuutuun(Ruutu ruutu, Ruutu ruutu2, Nappula nappula) {
		int[] koordinaatit = ruutu2.vertaaKoordinaatit(ruutu2, nappula.x, nappula.y);
		if ((koordinaatit[0] == 1 || koordinaatit[0] == -1) && (koordinaatit[1] == 1 && nappula.vari == 0)) {
			return true;
		}
		if ((koordinaatit[0] == 1 || koordinaatit[0] == -1) && (koordinaatit[1] == -1 && nappula.vari == 1)) {
			return true;
		}
		return false;
	}
}
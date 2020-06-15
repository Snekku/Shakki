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
	
	private boolean liikkunut = false;
	
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
	 * @param Ruutu Ruutu, johon ollaan siirtymassa.
	 * @param nappula Nappula, jota yritetaan siirtaa.
	 * @param shakkiLautaRuudut matriisi, jossa tallessa laudan ruutujen tiedot
 	 * @return Palauttaa true tai false, sen mukaan onko siirto laillinen.
	 */
	protected Boolean nappulanLaillinenSiirto(Ruutu ruutu, Nappula nappula, Ruutu[][] shakkiLautaRuudut) {
		int[] koordinaatit = ruutu.vertaaKoordinaatit(ruutu, nappula.x, nappula.y);
		if (koordinaatit[0] == 1 || koordinaatit[0] == -1 || koordinaatit[0] == 0) {
			if (koordinaatit[1] == 1 || koordinaatit[1] == -1 || koordinaatit[1] == 0) {
				if (!ruutu.onkoRuudussaNappula(ruutu) || (ruutu.onkoRuudussaNappula(ruutu) && ruutu.onkoVaritErit(ruutu, nappula))) {
					nappula.paivitaKoordinaatit(nappula, koordinaatit[0], koordinaatit[1]);
					liikkunut = true;
					return true;
				}
			}
		}
		if (liikkunut == false && (koordinaatit[0] == 2 && koordinaatit[1] == 0)) {
			if (!ruutu.onkoRuudussaNappula(ruutu)) {
				for (int i=0;i<7-nappula.x;i++) {
					if (shakkiLautaRuudut[nappula.x+i][nappula.y].onkoRuudussaNappula(shakkiLautaRuudut[nappula.x+i][nappula.y])) {
						return false;
					}
				}
				if(!shakkiLautaRuudut[nappula.x+3][nappula.y].onkoTorniLiikkunut(shakkiLautaRuudut[nappula.x+3][nappula.y])) {
					shakkiLautaRuudut[nappula.x+1][nappula.y].poistaJaLisaaNappula(shakkiLautaRuudut[nappula.x+1][nappula.y], shakkiLautaRuudut[nappula.x+3][nappula.y]);
					shakkiLautaRuudut[nappula.x+1][nappula.y].paivitaNappulanKoordinaatit(shakkiLautaRuudut[nappula.x+1][nappula.y]);
					nappula.paivitaKoordinaatit(nappula, koordinaatit[0], koordinaatit[1]);
					liikkunut = true;
					return true;
				}
			}
		}
		if (liikkunut == false && (koordinaatit[0] == -2 && koordinaatit[1] == 0)) {
			if (!ruutu.onkoRuudussaNappula(ruutu)) {
				for (int i=0;i<nappula.x;i++) {
					if (shakkiLautaRuudut[nappula.x-i][nappula.y].onkoRuudussaNappula(shakkiLautaRuudut[nappula.x-i][nappula.y])) {
						return false;
					}
				}
				if(!shakkiLautaRuudut[nappula.x-4][nappula.y].onkoTorniLiikkunut(shakkiLautaRuudut[nappula.x-4][nappula.y])) {
					shakkiLautaRuudut[nappula.x-1][nappula.y].poistaJaLisaaNappula(shakkiLautaRuudut[nappula.x-1][nappula.y], shakkiLautaRuudut[nappula.x-4][nappula.y]);
					shakkiLautaRuudut[nappula.x-1][nappula.y].paivitaNappulanKoordinaatit(shakkiLautaRuudut[nappula.x-1][nappula.y]);
					nappula.paivitaKoordinaatit(nappula, koordinaatit[0], koordinaatit[1]);
					liikkunut = true;
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Tarkistaa onko siirto laillinen.
	 * @param Ruutu Ruutu, johon ollaan siirtymassa.
	 * @param nappula Nappula, jota yritetaan siirtaa.
	 * @param shakkiLautaRuudut matriisi, jossa tallessa laudan ruutujen tiedot
 	 * @return Palauttaa true tai false, sen mukaan onko siirto laillinen.
	 */
	protected Boolean nappulanLaillinenSiirto(Ruutu ruutu, Ruutu ruutu2, Nappula nappula, Ruutu[][] shakkiLautaRuudut) {
		int[] koordinaatit = ruutu2.vertaaKoordinaatit(ruutu2, nappula.x, nappula.y);
		if (koordinaatit[0] == 1 || koordinaatit[0] == -1 || koordinaatit[0] == 0) {
			if (koordinaatit[1] == 1 || koordinaatit[1] == -1 || koordinaatit[1] == 0) {
				if (!ruutu2.onkoRuudussaNappula(ruutu2) || (ruutu2.onkoRuudussaNappula(ruutu2) && ruutu2.onkoVaritErit(ruutu2, nappula))) {
					return true;
				}
			}
		}
		if (liikkunut == false && (koordinaatit[0] == 2 && koordinaatit[1] == 0)) {
			if (!ruutu2.onkoRuudussaNappula(ruutu2)) {
				for (int i=0;i<7-nappula.x;i++) {
					if (shakkiLautaRuudut[nappula.x+i][nappula.y].onkoRuudussaNappula(shakkiLautaRuudut[nappula.x+i][nappula.y])) {
						return false;
					}
				}
				if(!shakkiLautaRuudut[nappula.x+3][nappula.y].onkoTorniLiikkunut(shakkiLautaRuudut[nappula.x+3][nappula.y])) {
					return true;
				}
			}
		}
		if (liikkunut == false && (koordinaatit[0] == -2 && koordinaatit[1] == 0)) {
			if (!ruutu2.onkoRuudussaNappula(ruutu2)) {
				for (int i=0;i<nappula.x;i++) {
					if (shakkiLautaRuudut[nappula.x-i][nappula.y].onkoRuudussaNappula(shakkiLautaRuudut[nappula.x-i][nappula.y])) {
						return false;
					}
				}
				if(!shakkiLautaRuudut[nappula.x-4][nappula.y].onkoTorniLiikkunut(shakkiLautaRuudut[nappula.x-4][nappula.y])) {
					return true;
				}
			}
		}
		return false;
	}
}
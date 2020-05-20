import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * Torni luokka. Taydentaa Nappula luokkaa.
 */
public class Torni extends Nappula {
	
	private boolean liikkunut = false;
	
	/**
	 * Torni luokan konstruktori. Asettaa Tornille ID:n, kuvakkeen ja varin.
	 * @param id Tornin ID, joka on yksilollinen.
	 * @param vari Kumman puolen Tornista on kyse.
	 * @param x X-koordinaatti
	 * @param y Y-koordinaatti
	 */
	public Torni(String id, int vari, int x, int y) {
		this.id = id;
		this.vari = vari;
		this.x = x;
		this.y = y;
		
		Image image = null;
		try {
			image = ImageIO.read(new File("src/Kuvakkeet/Rook" + Integer.toString(vari) + ".png"));
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
		//TODO Toiston purkaminen, laittamalla esim. metodiin.
		int[] koordinaatit = ruutu.vertaaKoordinaatit(ruutu, nappula.x, nappula.y);
		if (koordinaatit[1] == 0) {
			if (koordinaatit[0] < 0) {//vasen
				if (!ruutu.onkoRuudussaNappula(ruutu) || (ruutu.onkoRuudussaNappula(ruutu) && ruutu.onkoVaritErit(ruutu, nappula))) {
					for (int i=0;i<Math.abs(koordinaatit[0]);i++) {
						if (shakkiLautaRuudut[nappula.x-i][nappula.y].onkoRuudussaNappula(shakkiLautaRuudut[nappula.x-i][nappula.y])) {
							return false;
						}
					}
					nappula.paivitaKoordinaatit(nappula, koordinaatit[0], koordinaatit[1]);
					liikkunut = true;
					return true;
				}
			} else {//oikee
				if (!ruutu.onkoRuudussaNappula(ruutu) || (ruutu.onkoRuudussaNappula(ruutu) && ruutu.onkoVaritErit(ruutu, nappula))) {
					for (int i=0;i<Math.abs(koordinaatit[0]);i++) {
						if (shakkiLautaRuudut[nappula.x+i][nappula.y].onkoRuudussaNappula(shakkiLautaRuudut[nappula.x+i][nappula.y])) {
							return false;
						}
					}
					nappula.paivitaKoordinaatit(nappula, koordinaatit[0], koordinaatit[1]);
					liikkunut = true;
					return true;
				}
			}
		}
		if (koordinaatit[0] == 0) {
			if (koordinaatit[1] < 0) {//ylos
				if (!ruutu.onkoRuudussaNappula(ruutu) || (ruutu.onkoRuudussaNappula(ruutu) && ruutu.onkoVaritErit(ruutu, nappula))) {
					for (int i=0;i<Math.abs(koordinaatit[1]);i++) {
						if (shakkiLautaRuudut[nappula.x][nappula.y-i].onkoRuudussaNappula(shakkiLautaRuudut[nappula.x][nappula.y-i])) {
							return false;
						}
					}
					nappula.paivitaKoordinaatit(nappula, koordinaatit[0], koordinaatit[1]);
					liikkunut = true;
					return true;
				}
			} else {//alas
				if (!ruutu.onkoRuudussaNappula(ruutu) || (ruutu.onkoRuudussaNappula(ruutu) && ruutu.onkoVaritErit(ruutu, nappula))) {
					for (int i=0;i<Math.abs(koordinaatit[1]);i++) {
						if (shakkiLautaRuudut[nappula.x][nappula.y+i].onkoRuudussaNappula(shakkiLautaRuudut[nappula.x][nappula.y+i])) {
							return false;
						}
					}
					nappula.paivitaKoordinaatit(nappula, koordinaatit[0], koordinaatit[1]);
					liikkunut = true;
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Tarkistaako onko torni liikkunut.
	 * @param nappula Nappula, jota tarkastetaan.
	 * @return Palauttaa nappulan.
	 */
	protected boolean onkoTorniLiikkunut(Nappula nappula) {
		return liikkunut;
	}
}
import javax.swing.JLabel;

/**
 * Nappula luokka, nappulat (sotilas, yms.) laajentavat tata luokkaa.
 */
public abstract class Nappula {
	
	protected int vari;
	protected String id;
	protected int x, y;
	protected JLabel kuvake;
	
	/**
	 * Kutsuu kyseessa olevan nappulan omaa tarkistus metodia.
	 * @param ruutu Ruutu, johon ollaan siirtymassa.
	 * @param nappula Nappula, jota yritetaan siirtaa.
	 * @param shakkiLautaRuudut matriisi, jossa tallessa laudan ruutujen tiedot 
 	 * @return Palauttaa true tai false, sen mukaan onko siirto laillinen.
	 */
	protected Boolean nappulanLaillinenSiirto(Ruutu ruutu, Nappula nappula, Ruutu[][] shakkiLautaRuudut) {
		return nappula.nappulanLaillinenSiirto(ruutu, nappula, shakkiLautaRuudut);
	}
	
	/**
	 * Korottaa nappulan.
	 * @param nappula Nappula, joka korotetaan
	 * @return Palauttaa nappulan.
	 */
	protected Nappula korota(Nappula nappula) {
		return nappula;
	}
	
	/**
	 * Paivittaa nappulan koordinaatit.
	 * @param nappula Nappula, jota yritetaan siirtaa.
	 * @param x	X-koordinaatin siirtyma.
	 * @param y Y-koordinaatin siirtyma.
	 */
	protected void paivitaKoordinaatit(Nappula nappula, int x, int y) {
		nappula.x = nappula.x + x;
		nappula.y = nappula.y + y;
	}
	
	/**
	 * Tarkistaa onko ruudussa olevan nappulan ja siirrettavan nappulan varit erit.
	 * @param nappula Nappula, jota yritetaan siirtaa.
	 * @param ruudunNappula Nappula, joka on ruudussa, johon ollaan siirtymassa.
	 * @return true tai false, sen mukaan ovatko varit erit.
	 */
	protected Boolean onkoVaritErit(Nappula ruudunNappula, Nappula nappula) {
		if (ruudunNappula.vari == nappula.vari) {
			return false;
		}
		return true;
	}
	
}
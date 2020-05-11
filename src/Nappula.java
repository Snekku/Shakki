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
 	 * @return Palauttaa true tai false, sen mukaan onko siirto laillinen.
	 */
	protected Boolean nappulanLaillinenSiirto(Ruutu ruutu, Nappula nappula) {
		System.out.println("Nappula");
		return nappula.nappulanLaillinenSiirto(ruutu, nappula);
	}
	
	/**
	 * Paivittaa nappulan koordinaatit.
	 * @param nappula Nappula, jota yritetaan siirtaa.
	 * @param x	X-koordinaatin siirtyma.
	 * @param y Y-koordinaatin siirtyma.
	 */
	protected void paivitaKoordinaatit(Nappula nappula, int x, int y) {
		nappula.x = nappula.x - x;
		nappula.y = nappula.y - y;
	}
}
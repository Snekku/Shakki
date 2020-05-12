import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Ruutu luokka.
 */
public class Ruutu extends JPanel {

	private static final long serialVersionUID = 1L; //valitti ilman tata, en taysin ymmartanyt mika sen funktio on
	private Nappula nappula;
	private int x, y;
	private JLabel label;
	private Nappula taltio;

	/**
	 * Ruutu luokan konstruktori. Asetetaan shakkilaudan ruutujen varit.
	 * @param x koordinaatti
	 * @param y koordinaatti
	 * @param n Nappula, joka lisataan ruutuun.
	 */
	public Ruutu(int x, int y, Nappula n) {
		this.x = x;
		this.y = y;
		setLayout(new BorderLayout());
		if ((x + y) % 2 == 0) {
			setBackground(new Color(204,229,255));
		} else {
			setBackground(new Color(102,178,225));
		}
		if (n != null) {
			this.nappula = n;
			label = n.kuvake;
			this.add(label);
		}
	}
	
	/**
	 * Poistaa nappulan ruudusta.
	 * @param ruutu Ruutu, josta nappula poistetaan.
	 * @return Nappula palauttaa nappulan, joka poistettiin.
	 */
	protected Nappula poistaNappula(Ruutu ruutu) {
		taltio = null;
		if (ruutu.nappula != null) {
			taltio = ruutu.nappula;
			ruutu.nappula = null;
			ruutu.remove(ruutu.label);
		}
		return taltio;
	}
	
	/**
	 * Lisaa nappulan ruutuun.
	 * @param ruutu2 Ruutu, johon nappula lisataan.
	 * @param nappula Nappula, joka lisataan.
	 */
	protected void lisaaNappula(Ruutu ruutu2, Nappula nappula) {
		if (ruutu2.nappula != null) {
			taltio = poistaNappula(ruutu2);
		}
		this.nappula = nappula;
		label = nappula.kuvake;
		this.add(label);
	}
	
	/**
	 * Kutsuu nappulan tarkistus metodia.
	 * @param ruutu Ruutu, johon ollaan siirtymassa.
	 * @param nappula Nappula jota siirretaan.
	 * @param shakkiLautaRuudut matriisi, jossa tallessa laudan ruutujen tiedot
	 * @return Palauttaa true tai false, sen mukaan onko siirto laillinen.
	 */
	protected Boolean laillinenSiirto(Ruutu ruutu, Nappula nappula, Ruutu[][] shakkiLautaRuudut) {
		return nappula.nappulanLaillinenSiirto(ruutu, nappula, shakkiLautaRuudut);
	}
	
	/**
	 * Vertaa ruudun ja nappulan koordinaatteja.
	 * @param ruutu Ruutu, johon siirrytaan.
	 * @param X Nappulan X-koordinaatti.
	 * @param Y Nappulan Y-koordinaatti.
	 * @return Palauttaa X ja Y koordinaattien siirtyman.
	 */
	protected int[] vertaaKoordinaatit(Ruutu ruutu, int X, int Y) {
		int[] koordinaatit = new int[2];
		koordinaatit[0] = ruutu.x-X;
		koordinaatit[1] = ruutu.y-Y;
		return koordinaatit;
	}
	
	/**
	 * Tarkistaa onko ruudussa nappulaa.
	 * @param ruutu Ruutu, jonka sisaltoa tarkistetaan.
	 * @return Palauttaa true tai false, sen mukaan onko ruudussa nappulaa.
	 */
	protected Boolean onkoRuudussaNappula(Ruutu ruutu) {
		if (ruutu.nappula != null) {
			return true;
		}
		return false;
	}
	
	/**
	 * Tarkistaa onko ruudussa olevan nappulan vari eri kuin siirrettavan nappulan vari.
	 * @param ruutu Ruutu, jonka sisaltoa tarkistetaan.
	 * @param nappula Nappula, jota siirretaan.
	 * @return Palauttaa true tai false, sen mukaan onko varit samat vai ei.
	 */
	protected Boolean onkoVaritErit(Ruutu ruutu, Nappula nappula) {
		if (nappula.onkoVaritErit(ruutu.nappula, nappula)) {
			return true;
		}
		return false;
	}
	
}
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
	@SuppressWarnings("unused")
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
}
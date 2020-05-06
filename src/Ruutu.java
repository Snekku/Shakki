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

	/**
	 * Ruutu luokan konstruktori. Asetetaan shakkilaudan ruutujen varit.
	 * @param x koordinaatti
	 * @param y koordinaatti
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
}
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

/**
 * Kayttoliittyma-luokka.
 */
public class Kayttoliittyma implements MouseListener{
	
    private JFrame ikkuna;
    private JPanel shakkiLauta;
    private Ruutu[][] shakkiLautaRuudut;
    private Ruutu Ruutu;
    private Nappula nappula;
	
    /**
     * Kayttoliittyma-luokan konstruktori. Luo kehyksen ja kutsuu alusta-metodia, joka laittaa laudan alkutilanteeseen.
     */
    public Kayttoliittyma() {
    	alusta();
    	ikkuna = new JFrame("Jonin Shakkiohjelma!");
    	ikkuna.add(shakkiLauta);
    	ikkuna.setLocationByPlatform(true);
    	ikkuna.pack();
    	ikkuna.setSize(600, 600);
    	ikkuna.setMinimumSize(ikkuna.getSize());
    	ikkuna.setVisible(true);
    	ikkuna.setResizable(false);
    	ikkuna.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    
    /**
     * Alustaa kayttoliittyman, luomalla ruudut ja lisaamalla ne shakkilautaan. Taman jalkeen lisaa nappulat ruutuihin.
     */
    public final void alusta() {
    	shakkiLauta = new JPanel(new GridLayout(8, 8));
		shakkiLautaRuudut = new Ruutu[8][8];
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				switch (i) {
					case 0: switch (j) {
						case 0: nappula = new Torni("TT1", 0, i, j);
								break;
						case 1: nappula = new Ratsu("TR1", 0, i, j);
								break;
						case 2: nappula = new Lahetti("TL1", 0, i, j);
								break;
						case 3: nappula = new Kuningatar("TD", 0, i, j);
								break;
						case 4: nappula = new Kuningas("TK", 0, i, j);
								break;
						case 5: nappula = new Lahetti("TL2", 0, i, j);
								break;
						case 6: nappula = new Ratsu("TR2", 0, i, j);
								break;
						case 7: nappula = new Torni("TT2", 0, i, j);
								break;
						default: nappula = null;
								break;
							}
							break;
	            	case 1: nappula = new Sotilas("TS" + Integer.toString(j), 0, i, j);
	                     	break;
	            	case 6: nappula = new Sotilas("VS" + Integer.toString(j), 1, i, j);
	                     	break;
	            	case 7:  switch (j) {
						case 0: nappula = new Torni("TT1", 1, i, j);
								break;
						case 1: nappula = new Ratsu("TR1", 1, i, j);
								break;
						case 2: nappula = new Lahetti("TL1", 1, i, j);
								break;
						case 3: nappula = new Kuningatar("TD", 1, i, j);
								break;
						case 4: nappula = new Kuningas("TK", 1, i, j);
								break;
						case 5: nappula = new Lahetti("TL2", 1, i, j);
								break;
						case 6: nappula = new Ratsu("TR2", 1, i, j);
								break;
						case 7: nappula = new Torni("TT2", 1, i, j);
								break;
						default: nappula = null;
								break;
	            			}
	            			break;
	            	default: nappula = null;
	            		 	break;
				}
				Ruutu = new Ruutu(i, j, nappula);
				Ruutu.addMouseListener(this);
				shakkiLauta.add(Ruutu);
				shakkiLautaRuudut[i][j] = Ruutu;
			}
		}
    }

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		Ruutu ruutu = (Ruutu)e.getComponent();
		ruutu.poistaNappula(ruutu);
		shakkiLauta.repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e2) {
		Ruutu ruutu2 = (Ruutu)e2.getComponent();
		ruutu2.lisaaNappula(ruutu2);
		shakkiLauta.repaint();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}
}

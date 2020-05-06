import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Kayttoliittyma-luokka.
 */
public class Kayttoliittyma {
	
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
    }
    
    /**
     * Alustaa kayttoliittyman, luomalla ruudut ja lisaamalla ne shakkilautaan. Taman jalkeen lisaa sotilaat ruutuihin.
     */
    public final void alusta() {
    	shakkiLauta = new JPanel(new GridLayout(8, 8));
		shakkiLautaRuudut = new Ruutu[8][8];
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				switch (i) {
	            	case 1: nappula = new Sotilas("TS", 0, i, j);
	                     break;
	            	case 6: nappula = new Sotilas("VS", 1, i, j);
	                     break;
	            	default: nappula = null;
	            		 break;
				}
				Ruutu = new Ruutu(i, j, nappula);
				shakkiLauta.add(Ruutu);
				shakkiLautaRuudut[i][j] = Ruutu;
			}
		}
    }
}

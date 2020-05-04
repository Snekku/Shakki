import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Kayttoliittyma-luokka.
 */
public class Kayttoliittyma {
	
    private JFrame ikkuna;
    private JPanel shakkiLauta;
	
    /**
     * Kayttoliittyma-luokan konstruktori.
     */
    public Kayttoliittyma() {
    	alusta();
    	ikkuna = new JFrame("Jonin Shakkiohjelma!");
    	ikkuna.add(shakkiLauta);
    	ikkuna.setLocationByPlatform(true);
    	ikkuna.pack();
    	ikkuna.setSize(800, 600);
    	ikkuna.setMinimumSize(ikkuna.getSize());
    	ikkuna.setVisible(true);
    	ikkuna.setResizable(false);
    }
    
    /**
     * Alustaa kayttoliittyman.
     */
    public final void alusta() {
    	shakkiLauta = new JPanel(new GridLayout(8, 8));
    }
}

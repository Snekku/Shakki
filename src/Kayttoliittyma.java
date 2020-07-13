
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
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
    private Tekoaly tekoaly = new Tekoaly();
    private Ruutu Ruutu;
    private Ruutu edellinenRuutu;
    private Nappula nappula;
    private boolean poista = true;
    private int edellinenVari = 0;
    private int valkeanKorotukset = 0;
    private int tummanKorotukset = 0;
    private List<Ruutu> lahtoRuudut = new ArrayList<Ruutu> (100);
    private List<Ruutu> mahdollisetSiirrot = new ArrayList<Ruutu> (100);
    private Ruutu valkeanKuninkaanRuutu;
    private Ruutu tummanKuninkaanRuutu;
    private boolean onkoShakki = false;
	private boolean onkoMatti = false;
    
    /**
     * Kayttoliittyma-luokan konstruktori. Luo kehyksen ja kutsuu alusta-metodia, 
     * joka laittaa laudan alkutilanteeseen.
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
     * Alustaa kayttoliittyman, luomalla ruudut ja nappulat, jonka jalkeen lisaa ne shakkilautaan. 
     * Taman jalkeen lisaa nappulat ruutuihin.
     */
    public final void alusta() {
    	shakkiLauta = new JPanel(new GridLayout(8, 8));
		shakkiLautaRuudut = new Ruutu[8][8];
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				switch (i) {
					case 0: switch (j) {
						case 0: nappula = new Torni("TT1", 0, j, i);
								break;
						case 1: nappula = new Ratsu("TR1", 0, j, i);
								break;
						case 2: nappula = new Lahetti("TL1", 0, j, i);
								break;
						case 3: nappula = new Kuningatar("TD", 0, j, i);
								break;
						case 4: nappula = new Kuningas("TK", 0, j, i);
								break;
						case 5: nappula = new Lahetti("TL2", 0, j, i);
								break;
						case 6: nappula = new Ratsu("TR2", 0, j, i);
								break;
						case 7: nappula = new Torni("TT2", 0, j, i);
								break;
						default: nappula = null;
								break;
							}
							break;
	            	case 1: nappula = new Sotilas("TS" + Integer.toString(j), 0, j, i);
	                     	break;
	            	case 6: nappula = new Sotilas("VS" + Integer.toString(j), 1, j, i);
	                     	break;
	            	case 7:  switch (j) {
						case 0: nappula = new Torni("VT1", 1, j, i);
								break;
						case 1: nappula = new Ratsu("VR1", 1, j, i);
								break;
						case 2: nappula = new Lahetti("VL1", 1, j, i);
								break;
						case 3: nappula = new Kuningatar("VD", 1, j, i);
								break;
						case 4: nappula = new Kuningas("VK", 1, j, i);
								break;
						case 5: nappula = new Lahetti("VL2", 1, j, i);
								break;
						case 6: nappula = new Ratsu("VR2", 1, j, i);
								break;
						case 7: nappula = new Torni("VT2", 1, j, i);
								break;
						default: nappula = null;
								break;
	            			}
	            			break;
	            	default: nappula = null;
	            		 	break;
				}
				Ruutu = new Ruutu(j, i, nappula);
				Ruutu.addMouseListener(this);
				shakkiLauta.add(Ruutu);
				shakkiLautaRuudut[j][i] = Ruutu;
			}
		}
		valkeanKuninkaanRuutu = shakkiLautaRuudut[4][7];
		tummanKuninkaanRuutu = shakkiLautaRuudut[4][0];
    }

	@Override
	public void mouseClicked(MouseEvent e) {
		Ruutu ruutu = (Ruutu)e.getComponent();
		if (poista) {
			edellinenRuutu = ruutu;
			nappula = ruutu.poistaNappula(ruutu);
			poista = false;
		} else {
			if (nappula != null) {
				if (ruutu.laillinenSiirto(ruutu, nappula, shakkiLautaRuudut)) {
					if ((nappula.y == 0 || nappula.y == 7) && nappula.id.contains("S")) {
						nappula = nappula.korota(nappula, valkeanKorotukset);
						valkeanKorotukset += 1;
					}
					ruutu.lisaaNappula(ruutu, nappula);
						
					if (nappula.id.contains("TK")) {
						tummanKuninkaanRuutu = ruutu;
					}
						
					tarkistaMahdollisetSiirrot();
						
						//TODO: reaktio shakkiin, tekoalyn siirrot, bugisia, korjaa ettei vastustajan nappuloita voi liikuttaa
						
					onkoMatti = ruutu.tarkistaLauta(shakkiLautaRuudut);
					if (onkoMatti) {
						ikkuna.dispatchEvent(new WindowEvent(ikkuna, WindowEvent.WINDOW_CLOSING));
					}
					tarkistaTekoalynSiirrot();
				}
				else {
					edellinenRuutu.lisaaNappula(edellinenRuutu, nappula);
				}
			}
			poista = true;
		}
		shakkiLauta.revalidate();
		shakkiLauta.repaint();
	}
	
    /**
     * Tarkistaa mahdolliset siirrot ja merkitsee kuninkaan ruudun reunukset punaiseksi mikali on shakki. 
     */
	private void tarkistaMahdollisetSiirrot() {
		mahdollisetSiirrot.clear();
		for(int i=0;i<8;i++) {
			for(int j=0;j<8;j++) {
				if (shakkiLautaRuudut[j][i].nappulanVari(shakkiLautaRuudut[j][i]) != edellinenVari) {
					for(int k=0;k<8;k++) {
						for (int l=0;l<8;l++) {
							if (shakkiLautaRuudut[j][i].laillinenSiirto(shakkiLautaRuudut[j][i], shakkiLautaRuudut[l][k], shakkiLautaRuudut)) {
								mahdollisetSiirrot.add(shakkiLautaRuudut[l][k]);
								if (shakkiLautaRuudut[l][k].onkoRuudutSamat(shakkiLautaRuudut[l][k], tummanKuninkaanRuutu)) {
									onkoShakki = true;
									tummanKuninkaanRuutu.setBorder(BorderFactory.createLineBorder(Color.red));
								}
								if (shakkiLautaRuudut[l][k].onkoRuudutSamat(shakkiLautaRuudut[l][k], valkeanKuninkaanRuutu)) {
									onkoShakki = true;
									valkeanKuninkaanRuutu.setBorder(BorderFactory.createLineBorder(Color.red));
								}
							}
						}
					}
				}
			}
		}
	}
	
    /**
     * Tarkistaa mahdolliset siirrot ja merkitsee kuninkaan ruudun reunukset punaiseksi mikali on shakki. 
     */
	private void tarkistaTekoalynSiirrot() {
		mahdollisetSiirrot.clear();
		lahtoRuudut.clear();
		for(int i=0;i<8;i++) {
			for(int j=0;j<8;j++) {
				if (shakkiLautaRuudut[j][i].nappulanVari(shakkiLautaRuudut[j][i]) == 0) {
					for(int k=0;k<8;k++) {
						for (int l=0;l<8;l++) {
							if (shakkiLautaRuudut[j][i].laillinenSiirto(shakkiLautaRuudut[j][i], shakkiLautaRuudut[l][k], shakkiLautaRuudut)) {
								lahtoRuudut.add(shakkiLautaRuudut[j][i]);
								mahdollisetSiirrot.add(shakkiLautaRuudut[l][k]);
							}
						}
					}
				}
			}
		}
		Ruutu[] ruudut = tekoaly.valitseSiirto(mahdollisetSiirrot, lahtoRuudut);
		nappula = ruudut[0].poistaNappula(ruudut[0]);
		if ((nappula.y == 0 || nappula.y == 7) && nappula.id.contains("S")) {
			if (nappula.vari == 0) {
				nappula = nappula.korota(nappula, tummanKorotukset);
				tummanKorotukset += 1;
			}
		}
		ruudut[1].lisaaNappula(ruudut[1], nappula);
		if (nappula.id.contains("TK")) {
			tummanKuninkaanRuutu = ruudut[1];
		}
		tarkistaMahdollisetSiirrot();
		shakkiLauta.revalidate();
		shakkiLauta.repaint();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		//Ei kaytossa
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		//Ei kaytossa
	}

	@Override
	public void mouseExited(MouseEvent e) {
		//Ei kaytossa
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		//Ei kaytossa
	}
}

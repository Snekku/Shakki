
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

/**
 * Kayttoliittyma-luokkassa, luodaan peliin tarvittavat graafiset elementit. 
 * Kyseisessa luokassa luodaan kaikki muut visuaaliset komponentit, paitsi nappuloiden kuvakkeet ja ruutujen varit. 
 * Ensimmaiseksi luokka luo ikkunan, johon myohemmin lisataan shakkilauta ja nappulat. Kun ikkuna on luotu kutsuu ohjelma alusta-metodia, 
 * jonka tehtava on luoda shakkilaudan ruudut ja lisata niihin nappulat. Kun lauta on alustettu alkaa luokka kuuntelemaan pelaajan hiiren painalluksia.
 * Vastaa myos tekoalyn siirroista, kutsumalla tekoaly-luokkaa.
 * @author Joni Piispanen
 */
public class Kayttoliittyma implements MouseListener{
	
    private JFrame ikkuna;
    private JFrame pieniIkkuna;
    private JPanel shakkiLauta;
    private Ruutu[][] shakkiLautaRuudut;
    private Tekoaly tekoaly = new Tekoaly();
    private Ruutu Ruutu;
    private Ruutu edellinenRuutu;
    private Nappula nappula;
    private boolean poista = true;
    private int valkeanKorotukset = 0;
    private int tummanKorotukset = 0;
    private List<Ruutu> lahtoRuudut = new ArrayList<Ruutu> (100);
    private List<Ruutu> valkeanHyokatytRuudut = new ArrayList<Ruutu> (1000);
    private List<Ruutu> tummanHyokatytRuudut = new ArrayList<Ruutu> (1000);
    private List<Ruutu> tummanMahdollisetSiirrot = new ArrayList<Ruutu> (1000);
    private Ruutu valkeanKuninkaanRuutu;
    private Ruutu tummanKuninkaanRuutu;
    private boolean valkeaShakki = false;
    private boolean tummaShakki = false;
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
			if (ruutu.nappulanVari(ruutu) == 1) {
				edellinenRuutu = ruutu;
				nappula = ruutu.poistaNappula(ruutu);
				poista = false;
			}
		} else {
			if (nappula != null) {
				if ((nappula.id.contains("VK") && ruutu.onkoHyokattyRuutu(ruutu, tummanHyokatytRuudut)) || !nappula.id.contains("VK")) {
					if (ruutu.laillinenSiirto(ruutu, nappula, shakkiLautaRuudut)) {
						if ((nappula.y == 0 || nappula.y == 7) && nappula.id.contains("S")) {
							nappula = nappula.korota(nappula, valkeanKorotukset);
							valkeanKorotukset += 1;
						}
						ruutu.lisaaNappula(ruutu, nappula);
						if (valkeaShakki) {
							valkeaShakki = false;
							valkeanKuninkaanRuutu.setBorder(null);
						}
						if (nappula.id.contains("VK")) {
							valkeanKuninkaanRuutu = ruutu;
						}
								
						tarkistaMahdollisetSiirrot();
						shakkiLauta.revalidate();
						shakkiLauta.repaint();
								
						onkoMatti = ruutu.tarkistaLauta(shakkiLautaRuudut);
						if (onkoMatti) {
					    	pieniIkkuna = new JFrame("ShakkiMatti!");
					    	JLabel teksti = new JLabel();
					    	teksti.setText("SHAKKIMATTI!");
					    	pieniIkkuna.add(teksti);
					    	pieniIkkuna.setLocationByPlatform(true);
					    	pieniIkkuna.pack();
					    	pieniIkkuna.setSize(150, 100);
					    	pieniIkkuna.setMinimumSize(pieniIkkuna.getSize());
					    	pieniIkkuna.setVisible(true);
					    	pieniIkkuna.setResizable(false);
					    	pieniIkkuna.setAlwaysOnTop(true);
					    	pieniIkkuna.setTitle("SHAKKIMATTI!");
					    	pieniIkkuna.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
					    	ikkuna.setEnabled(false);
					    	return;
						}
						tarkistaTekoalynSiirrot();
					} else {
						edellinenRuutu.lisaaNappula(edellinenRuutu, nappula);
					}
				} else {
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
		valkeanHyokatytRuudut.clear();
		for(int i=0;i<8;i++) {
			for(int j=0;j<8;j++) {
				if (shakkiLautaRuudut[j][i].nappulanVari(shakkiLautaRuudut[j][i]) == 1) {
					for(int k=0;k<8;k++) {
						for (int l=0;l<8;l++) {
							if ((shakkiLautaRuudut[j][i].laillinenSiirtoErillinen(shakkiLautaRuudut[j][i], shakkiLautaRuudut[l][k], shakkiLautaRuudut)) || (shakkiLautaRuudut[j][i].hyokkaakoRuutuun(shakkiLautaRuudut[j][i], shakkiLautaRuudut[l][k]))) {
								valkeanHyokatytRuudut.add(shakkiLautaRuudut[l][k]);
							}
							if (shakkiLautaRuudut[j][i].laillinenSiirto(shakkiLautaRuudut[j][i], shakkiLautaRuudut[l][k], shakkiLautaRuudut)) {
								if (shakkiLautaRuudut[l][k].onkoRuudutSamat(shakkiLautaRuudut[l][k], tummanKuninkaanRuutu)) {
									tummaShakki = true;
									tummanKuninkaanRuutu.setBorder(BorderFactory.createLineBorder(Color.red));
								}
							}
						}
					}
				}
			}
		}
	}
	
    /**
     * Tarkistaa tekoalyn mahdolliset siirrot ja valitsee yhden niista satunnaisesti.
     */
	private void tarkistaTekoalynSiirrot() {
		tummanMahdollisetSiirrot.clear();
		lahtoRuudut.clear();
		for(int i=0;i<8;i++) {
			for(int j=0;j<8;j++) {
				if (shakkiLautaRuudut[j][i].nappulanVari(shakkiLautaRuudut[j][i]) == 0) {
					for(int k=0;k<8;k++) {
						for (int l=0;l<8;l++) {
							if (shakkiLautaRuudut[j][i].laillinenSiirto(shakkiLautaRuudut[j][i], shakkiLautaRuudut[l][k], shakkiLautaRuudut)) {
								lahtoRuudut.add(shakkiLautaRuudut[j][i]);
								tummanMahdollisetSiirrot.add(shakkiLautaRuudut[l][k]);
							}
						}
					}
				}
			}
		}
		Ruutu[] ruudut;
		if (tummaShakki) {
			ruudut = tekoaly.valitseSiirto(tummanMahdollisetSiirrot, lahtoRuudut, tummanKuninkaanRuutu);
			while (ruudut[0].onkoSiirtoSallittu(ruudut[0], ruudut[1], valkeanHyokatytRuudut)) {
				if (!ruudut[0].onkoLaillistaSiirtoa(tummanKuninkaanRuutu, lahtoRuudut, tummanMahdollisetSiirrot, valkeanHyokatytRuudut)) {
			    	pieniIkkuna = new JFrame("ShakkiMatti!");
			    	JLabel teksti = new JLabel();
			    	teksti.setText("SHAKKIMATTI!");
			    	pieniIkkuna.add(teksti);
			    	pieniIkkuna.setLocationByPlatform(true);
			    	pieniIkkuna.pack();
			    	pieniIkkuna.setSize(150, 100);
			    	pieniIkkuna.setMinimumSize(pieniIkkuna.getSize());
			    	pieniIkkuna.setVisible(true);
			    	pieniIkkuna.setResizable(false);
			    	pieniIkkuna.setAlwaysOnTop(true);
			    	pieniIkkuna.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			    	ikkuna.setEnabled(false);
			    	return;
				}
				ruudut = tekoaly.valitseSiirto(tummanMahdollisetSiirrot, lahtoRuudut, tummanKuninkaanRuutu);
			}
		} else {
			ruudut = tekoaly.valitseSiirto(tummanMahdollisetSiirrot, lahtoRuudut);
			while (ruudut[0].onkoSiirtoSallittu(ruudut[0], ruudut[1], valkeanHyokatytRuudut)) {
				ruudut = tekoaly.valitseSiirto(tummanMahdollisetSiirrot, lahtoRuudut);
			}
		}
		nappula = ruudut[0].poistaNappula(ruudut[0]);
		if (nappula != null) {
			if (ruudut[1].laillinenSiirto(ruudut[1], nappula, shakkiLautaRuudut)) {
				if ((nappula.y == 0 || nappula.y == 7) && nappula.id.contains("S")) {
					nappula = nappula.korota(nappula, tummanKorotukset);
					tummanKorotukset += 1;
				}
				ruudut[1].lisaaNappula(ruudut[1], nappula);
				if (tummaShakki) {
					tummaShakki = false;
					tummanKuninkaanRuutu.setBorder(null);
				}
			}
		}
		if (nappula.id.contains("TK")) {
			tummanKuninkaanRuutu = ruudut[1];
		}
		tummanHyokatytRuudut.clear();
		for(int i=0;i<8;i++) {
			for(int j=0;j<8;j++) {
				if (shakkiLautaRuudut[j][i].nappulanVari(shakkiLautaRuudut[j][i]) == 0) {
					for(int k=0;k<8;k++) {
						for (int l=0;l<8;l++) {
							if (shakkiLautaRuudut[j][i].laillinenSiirto(shakkiLautaRuudut[j][i], shakkiLautaRuudut[l][k], shakkiLautaRuudut)) {
								if (shakkiLautaRuudut[l][k].onkoRuudutSamat(shakkiLautaRuudut[l][k], valkeanKuninkaanRuutu)) {
									valkeaShakki = true;
									valkeanKuninkaanRuutu.setBorder(BorderFactory.createLineBorder(Color.red));
								}
							}
							if ((shakkiLautaRuudut[j][i].laillinenSiirtoErillinen(shakkiLautaRuudut[j][i], shakkiLautaRuudut[l][k], shakkiLautaRuudut)) || (shakkiLautaRuudut[j][i].hyokkaakoRuutuun(shakkiLautaRuudut[j][i], shakkiLautaRuudut[l][k]))) {
								tummanHyokatytRuudut.add(shakkiLautaRuudut[l][k]);
							}
						}
					}
				}
			}
		}
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

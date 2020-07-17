import java.awt.BorderLayout;
import java.awt.Color;
import java.util.List;

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
	 * Lisaa nappulan ruutuun. Erillinen metodi linnoittautumista varten.
	 * @param ruutu1 Ruutu, johon nappula lisataan.
	 * @param ruutu2 Ruutu, josta nappula poistetaan.
	 */
	protected void poistaJaLisaaNappula(Ruutu ruutu1, Ruutu ruutu2) {
		ruutu1.nappula = ruutu2.nappula;
		label = ruutu1.nappula.kuvake;
		ruutu1.add(label);
		ruutu2.nappula = null;
		ruutu2.remove(ruutu2.label);
	}
	
	/**
	 * Paivittaa ruudun nappulan koordinaatit. Erillinen metodi linnoittautumista varten.
	 * @param ruutu1 Ruutu, jonka nappulan koordinaatteja paivitetaan.
	 */
	protected void paivitaNappulanKoordinaatit(Ruutu ruutu1) {
		ruutu1.nappula.paivitaNappulanKoordinaatit(nappula, ruutu1.x, ruutu1.y);
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
	 * Kutsuu nappulan tarkistus metodia.
	 * @param ruutu Ruutu, josta ollaan siirtymassa.
	 * @param ruutu2 Ruutu, johon ollaan siirtymassa.
	 * @param shakkiLautaRuudut matriisi, jossa tallessa laudan ruutujen tiedot
	 * @return Palauttaa true tai false, sen mukaan onko siirto laillinen.
	 */
	protected Boolean laillinenSiirto(Ruutu ruutu, Ruutu ruutu2, Ruutu[][] shakkiLautaRuudut) {
		if (ruutu.nappula != null) {
			return ruutu.nappula.nappulanLaillinenSiirto(ruutu, ruutu2, ruutu.nappula, shakkiLautaRuudut);
		}
		return false;
	}
	
	/**
	 * Kutsuu nappulan tarkistus metodia. Erillinen metodi hyokattyjen ruutujen tarkistukseen.
	 * @param ruutu Ruutu, josta ollaan siirtymassa.
	 * @param ruutu2 Ruutu, johon ollaan siirtymassa.
	 * @param shakkiLautaRuudut matriisi, jossa tallessa laudan ruutujen tiedot
	 * @return Palauttaa true tai false, sen mukaan onko siirto laillinen.
	 */
	protected Boolean laillinenSiirtoErillinen(Ruutu ruutu, Ruutu ruutu2, Ruutu[][] shakkiLautaRuudut) {
		if (ruutu.nappula != null) {
			if (ruutu.nappula.id.contains("S")) {
				return false;
			}
			return ruutu.nappula.nappulanLaillinenSiirtoErillinen(ruutu, ruutu2, ruutu.nappula, shakkiLautaRuudut);
		}
		return false;
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
	 * Tarkistaa onko ruudussa torni.
	 * @param ruutu Ruutu, jonka sisaltoa tarkistetaan.
	 * @return Palauttaa true tai false, sen mukaan onko ruudussa torni.
	 */
	protected Boolean onkoTorniLiikkunut(Ruutu ruutu) {
		if (ruutu.nappula != null) {
			return ruutu.nappula.onkoTorniLiikkunut(ruutu.nappula);
		}
		return true;
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
	
	/**
	 * Katsoo nappulan varin.
	 * @param ruutu Ruutu, jonka sisaltoa tarkistetaan.
	 * @return Palauttaa 0 tai 1, sen mukaan onko vari valkea vai tumma.
	 */
	protected int nappulanVari(Ruutu ruutu) {
		if (ruutu.nappula != null) {
			if (ruutu.nappula.vari == 0) {
				return 0;
			}
		}
		return 1;
	}
	
	/**
	 * Tarkistaa onko kyseessa sama ruutu.
	 * @param ruutu Ensimmainen ruutu.
	 * @param ruutu2 Toinen ruutu.
	 * @return Palauttaa true tai false sen mukaan tasmaavatko ruutujen koordinaatit.
	 */
	protected boolean onkoRuudutSamat(Ruutu ruutu, Ruutu ruutu2) {
		if (ruutu.x == ruutu2.x && ruutu.y == ruutu2.y) {
			return true;
		}
		return false;
	}
	
	/**
	 * Tarkistaa loytyyko molemmat kuninkaat laudalta.
	 * @param shakkiLautaRuudut shakkilauta
	 * @return Palauttaa true tai false sen mukaan loytyyko kuninkaat.
	 */
	protected boolean tarkistaLauta(Ruutu[][] shakkiLautaRuudut) {
		boolean valkeaKuningas = false;
		boolean tummaKuningas = false;
		for (int i=0;i<8;i++) {
			for (int j=0;j<8;j++) {
				if (shakkiLautaRuudut[j][i].nappula != null) {
					if (shakkiLautaRuudut[j][i].nappula.id.contains("VK")) {
						valkeaKuningas = true;
					}
					if (shakkiLautaRuudut[j][i].nappula.id.contains("TK")) {
						tummaKuningas = true;
					}
				}
			}
		}
		if (valkeaKuningas && tummaKuningas) {
			return false;
		}
		return true;
	}
	
	/**
	 * Tarkistaa hyokkaako ruudussa oleva nappula ruutuun2. Palauttaa automaattisesti true kaikkien muiden paitsi sotilaan kohdalla.
	 * @param ruutu Ruutu, josta ollaan liikkumassa.
	 * @param ruutu2 Ruutu, johon ollaan liikkumassa.
	 * @return Palauttaa true tai false sen mukaan hyokataanko ruutuun.
	 */
	protected Boolean hyokkaakoRuutuun(Ruutu ruutu, Ruutu ruutu2) {
		return ruutu.nappula.hyokkaakoRuutuun(ruutu, ruutu2, ruutu.nappula);
	}
	
	
	/**
	 * Tarkistaa hyokkaako ruudussa oleva nappula ruutuun2. Palauttaa automaattisesti true kaikkien muiden paitsi sotilaan kohdalla.
	 * @param ruutu Ruutu, josta ollaan liikkumassa.
	 * @param ruutu2 Ruutu, johon ollaan liikkumassa.
	 * @return Palauttaa true tai false sen mukaan hyokataanko ruutuun.
	 */
	protected int[] koordinaatit(Ruutu ruutu, Ruutu ruutu2) {
		int[] koords = new int[4];
		koords[0] = ruutu.x;
		koords[1] = ruutu.y;
		koords[2] = ruutu2.x;
		koords[3] = ruutu2.y;
		return koords;
	}
	
	/**
	 * Tarkistaa onko kuningas liikkumassa hyokattyyn ruutuun.
	 * @param ruutu Ruutu, johon ollaan liikkumassa.
	 * @param mahdollisetSiirrot Lista vastustajan mahdollisista siirroista.
	 * @return Palauttaa true tai false sen mukaan hyokataanko ruutuun.
	 */
	protected boolean onkoHyokattyRuutu(Ruutu ruutu, List<Ruutu> mahdollisetSiirrot) {
	    for (int i = 0; i < mahdollisetSiirrot.size(); i++) {
	    	if (onkoRuudutSamat(ruutu, mahdollisetSiirrot.get(i))) {
	    		return false;
	    	}
	    }
		return true;
	}
}
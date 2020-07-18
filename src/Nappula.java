import javax.swing.JLabel;

/**
 * Nappula luokka, nappulat (sotilas, yms.) laajentavat tata luokkaa. Vastaa nappuloille yhteisista asioista ja kutsuu sille tuodun nappulan
 * omia metodeja nappulan luokan mukaan.
 * @author Joni Piispanen
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
	 * @param shakkiLautaRuudut matriisi, jossa tallessa laudan ruutujen tiedot 
 	 * @return Palauttaa true tai false, sen mukaan onko siirto laillinen.
	 */
	protected Boolean nappulanLaillinenSiirto(Ruutu ruutu, Nappula nappula, Ruutu[][] shakkiLautaRuudut) {
		return nappula.nappulanLaillinenSiirto(ruutu, nappula, shakkiLautaRuudut);
	}
	
	/**
	 * Kutsuu kyseessa olevan nappulan omaa tarkistus metodia.
	 * @param ruutu Ruutu, josta ollaan siirtymassa.
	 * @param ruutu2 Ruutu, johon ollaan siirtymassa.
	 * @param nappula Nappula, jota yritetaan siirtaa.
	 * @param shakkiLautaRuudut matriisi, jossa tallessa laudan ruutujen tiedot 
 	 * @return Palauttaa true tai false, sen mukaan onko siirto laillinen.
	 */
	protected Boolean nappulanLaillinenSiirto(Ruutu ruutu, Ruutu ruutu2, Nappula nappula, Ruutu[][] shakkiLautaRuudut) {
		return nappula.nappulanLaillinenSiirto(ruutu, ruutu2, nappula, shakkiLautaRuudut);
	}
	
	/**
	 * Kutsuu kyseessa olevan nappulan omaa tarkistus metodia. Erillinen metodi hyokattyjen ruutujen tarkistukseen.
	 * @param ruutu Ruutu, josta ollaan siirtymassa.
	 * @param ruutu2 Ruutu, johon ollaan siirtymassa.
	 * @param nappula Nappula, jota yritetaan siirtaa.
	 * @param shakkiLautaRuudut matriisi, jossa tallessa laudan ruutujen tiedot 
 	 * @return Palauttaa true tai false, sen mukaan onko siirto laillinen.
	 */
	protected Boolean nappulanLaillinenSiirtoErillinen(Ruutu ruutu, Ruutu ruutu2, Nappula nappula, Ruutu[][] shakkiLautaRuudut) {
		return nappula.nappulanLaillinenSiirtoErillinen(ruutu, ruutu2, nappula, shakkiLautaRuudut);
	}
	
	/**
	 * Korottaa nappulan.
	 * @param nappula Nappula, joka korotetaan
	 * @return Palauttaa nappulan.
	 */
	protected Nappula korota(Nappula nappula, int korotukset) {
		return nappula;
	}
	
	/**
	 * Tarkistaa onko kyseessa torni.
	 * @param nappula Nappula, jota tarkastetaan.
	 * @return Palauttaa true.
	 */
	protected boolean onkoTorniLiikkunut(Nappula nappula) {
		return true;
	}
	
	/**
	 * Paivittaa nappulan koordinaatit.
	 * @param nappula Nappula, jota yritetaan siirtaa.
	 * @param x	X-koordinaatin siirtyma.
	 * @param y Y-koordinaatin siirtyma.
	 */
	protected void paivitaKoordinaatit(Nappula nappula, int x, int y) {
		nappula.x = nappula.x + x;
		nappula.y = nappula.y + y;
	}
	
	/**
	 * Paivittaa nappulan koordinaatit. Erillinen metodi linnoittautumista varten.
	 * @param nappula Nappula, jota yritetaan siirtaa.
	 * @param x	Uusi x-koordinaatti.
	 * @param y Uusi y-koordinaatti.
	 */
	protected void paivitaNappulanKoordinaatit(Nappula nappula, int x, int y) {
		nappula.x = x;
		nappula.y = y;
	}
	
	/**
	 * Tarkistaa onko ruudussa olevan nappulan ja siirrettavan nappulan varit erit.
	 * @param ruudunNappula Nappula, joka on ruudussa, johon ollaan siirtymassa.
	 * @param nappula Nappula, jota yritetaan siirtaa.
	 * @return true tai false, sen mukaan ovatko varit erit.
	 */
	protected Boolean onkoVaritErit(Nappula ruudunNappula, Nappula nappula) {
		if (ruudunNappula.vari == nappula.vari) {
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
	protected Boolean hyokkaakoRuutuun(Ruutu ruutu, Ruutu ruutu2, Nappula nappula) {
		if (nappula.id.contains("S")) {
			return nappula.hyokkaakoRuutuun(ruutu, ruutu2, nappula);
		}
		return false;
	}
}
import java.util.List;
import java.util.Random;

/**
 * Tekoaly luokka.
 */
public class Tekoaly {
	
    private Random randomGenerator;

	/**
	 * Tekoaly luokan konstruktori.
	 */
    public Tekoaly() { 
        randomGenerator = new Random();
    }

	/**
	 * Valitsee mahdollisista siirroista jonkin satunnaisesti.
	 * @param mahdollisetSiirrot Lista mahdollisista ruuduista, joihin nappulan voi liikuttaa.
	 * @param lahtoRuudut Lista ruuduista, joista nappulan voi laillisesti siirtaa.
	 * @return Ruutu[] Palauttaa lahtoruudun ja tavoite ruudun taulukko muodossa.
	 */
    protected Ruutu[] valitseSiirto(List<Ruutu> mahdollisetSiirrot, List<Ruutu> lahtoRuudut) {
        int index = randomGenerator.nextInt(mahdollisetSiirrot.size());
        Ruutu[] ruudut = new Ruutu[2];
        ruudut[0] = lahtoRuudut.get(index);
        ruudut[1] = mahdollisetSiirrot.get(index);
        return ruudut;
    }
}
